package learn.test;

/**
 * @author 吴飞群
 * @createTime 2022/06/06
 */
public class SingleObject {
    private static SingleObject singleObject = new SingleObject();

    private SingleObject (){}

    public static SingleObject getInstance() {
        return singleObject;
    }

    public void sayHello(){
        System.out.println("Hello World!");
    }
}
