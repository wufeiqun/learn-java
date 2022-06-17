package learn.java8Feature;

import java.util.function.Predicate;

/**
 * @author 吴飞群
 * @createTime 2022/06/16
 */
public class LearnJava8Feature {

    public static void predict(){
        Predicate<String> p = str -> str.startsWith("A");

        boolean ret = p.test("ABC");
        boolean ret1 = p.test("BC");
    }
}
