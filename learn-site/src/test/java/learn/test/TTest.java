package learn.test;

import com.alibaba.fastjson2.JSON;
import learn.user.User;
import learn.utils.AsyncUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TTest extends TestBase{

    @Autowired
    private TestService testService;

    @Autowired
    private AsyncUtil asyncUtil;

    @Test
    public void test() {
      User wufeiqun = new User();
        wufeiqun.setAccount("wufeiqun");
        wufeiqun.setName("吴飞群");

        User wufeiqun1 = new User();
        wufeiqun1.setAccount(wufeiqun.getAccount());
        wufeiqun1.setName(wufeiqun.getName());

        System.out.println(wufeiqun.equals(wufeiqun1));

        System.out.println(JSON.toJSONString(wufeiqun));
        System.out.println(JSON.toJSONString(wufeiqun1));
        wufeiqun.setName("吴飞群改名字了");
        System.out.println("***************************");
        System.out.println("wufeiqun: " + JSON.toJSONString(wufeiqun));
        System.out.println("wufeiqun1: " + JSON.toJSONString(wufeiqun1));
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        //不关心回值的异步任务
        asyncUtil.doTask(
                () -> {
                    System.out.println("ABC");
                }
        );
        //关心返回值的异步任务
        Future<String> future = asyncUtil.doTaskWithResult(
                () -> {
                    System.out.println("DEF");
                    return "DEF";
                }
        );

        try {
            String o = future.get(1000L, TimeUnit.MILLISECONDS);
            System.out.println(o);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private void change(User user, int t){
//        user.setName(null);
//        user.setAccount(null);
//        user = null;
//        t = 1;
    }


}
