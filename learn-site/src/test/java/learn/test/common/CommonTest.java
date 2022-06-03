package learn.test.common;

import learn.test.TestBase;
import learn.utils.AsyncUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CommonTest extends TestBase {

    @Autowired
    private AsyncUtil asyncUtil;

    @Test
    public void asyncUtilTest() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
