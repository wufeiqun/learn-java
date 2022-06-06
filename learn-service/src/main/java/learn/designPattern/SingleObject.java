package learn.designPattern;

/**
 * 只能创建一个实例, 自己来创建, 别人来使用, 避免了频繁的创建对象
 *
 * 使用场景, 比如spring中的bean默认就是这种模式
 * @author 吴飞群
 * @createTime 2022/06/05
 */
public class SingleObject {
    public static SingleObject singleObject= new SingleObject();;

    private SingleObject(){}

    public static SingleObject getInstance(){
        return singleObject;
    }
}
