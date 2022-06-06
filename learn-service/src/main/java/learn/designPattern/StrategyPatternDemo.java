package learn.designPattern;

/**
 * @author 吴飞群
 * @createTime 2022/06/05
 */
public class StrategyPatternDemo implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1+num2;
    }
}
