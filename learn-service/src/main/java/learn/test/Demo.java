package learn.test;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 吴飞群
 * @createTime 2022/05/30
 */
public class Demo {
    public static final long MAX_TURN = 5;
    static int threadSeqNumber = 0;

    static List<Thread> threadList = new ArrayList<>();

    private static void printThreadStatus(){
        for (Thread thread : threadList)
        {
            System.out.println(thread.getName() + " 状态为 " + thread.getState());

        }
    }

    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    static class StatusDemoThread extends Thread{
        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSeqNumber));
            //将自己加入全局的静态线程列表
            addStatusThread(this);
        }

        public void run()
        {
            System.out.println(getName() + ", 状态为" + getState());
            for (int turn = 0; turn < MAX_TURN; turn++)
            {
                //线程睡眠
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //输出所有线程的状态
                printThreadStatus();
            }
            System.out.println(getName() + "- 运行结束.");
        }

    }

    static class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

        /**
         * Method that may be invoked by a {@link ThreadPoolExecutor} when
         * {@link ThreadPoolExecutor#execute execute} cannot accept a
         * task.  This may occur when no more threads or queue slots are
         * available because their bounds would be exceeded, or upon
         * shutdown of the Executor.
         *
         * <p>In the absence of other alternatives, the method may throw
         * an unchecked {@link RejectedExecutionException}, which will be
         * propagated to the caller of {@code execute}.
         *
         * @param r        the runnable task requested to be executed
         * @param executor the executor attempting to execute this task
         * @throws RejectedExecutionException if there is no remedy
         */
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("线程池满了, 有新线程被拒绝了: " + r + ", get task count: "
            + executor.getTaskCount());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //队列默认是Integer.MAX
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                10,
                100,
                1L,
                TimeUnit.MINUTES,
                workQueue
        );
        //预先加载core线程, 不用等待默认的有新任务的时候才创建core线程了
        pool.prestartAllCoreThreads();

        Future<Integer> future = pool.submit(
                () -> {
                    Thread.sleep(5000L);
                    return RandomUtil.randomInt(10, 100);
                }
        );

        System.out.println(future.get());

        System.out.println(pool.getActiveCount());
        System.out.println(pool.getPoolSize());
        System.out.println(pool.getCorePoolSize());

    }
}
