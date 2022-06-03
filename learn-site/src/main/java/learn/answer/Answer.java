package learn.answer;

/**
 * @author 吴飞群
 * @createTime 2022/05/19
 */
public class Answer {
    static final int MIN_YEAR = 0;
    static final int MIN_MONTH = 1;
    static final int MAX_MONTH = 12;
    static final int MIN_DAY = 1;
    /**
     * @param year 年份
     * @return boolean
     * @description 校验年份是否为闰年
     * 闰年条件如下:
     * 1. 年分为4的倍数但非100的倍数为闰年
     * 2. 年分为400的倍数为闰年
     */
    public static boolean isLeapYear(int year){
        return ((year % 4) == 0 && (year % 100) != 0) || ((year) % 400) == 0;
    }

    /**
     * @param year 年
     * @param month 月
     * @param day 日
     * @return boolean
     * @description 校验日期是否合法
     */
    private boolean validDate(int year, int month, int day){
        //校验年的合法性
        if (year <= MIN_YEAR){
            return false;
        }
        //校验月的合法性
        if (month < MIN_MONTH || month > MAX_MONTH) {
            return false;
        }
        //校验日的合法性
        Month monthEnum = Month.of(month);
        boolean isLeapYear = isLeapYear(year);
        return day >= MIN_DAY && day <= monthEnum.days(isLeapYear);
    }

    int dayOfYear(int year, int month, int day){
        int dayOfYear = day;
        if (!validDate(year, month, day)){
            return -1;
        }

        if (month == MIN_MONTH){
            return dayOfYear;
        }

        boolean isLeapYear = isLeapYear(year);

        for (int i = MIN_MONTH; i < month; i++) {
            Month monthEnum = Month.of(i);
            dayOfYear+=monthEnum.days(isLeapYear);
        }

        return dayOfYear;
    }


    public static void main(String[] args){
        Answer answer = new Answer();
        //非法年测试
//        int ret = answer.dayOfYear(-100, 2,2);
        //非法月测试
//        int ret = answer.dayOfYear(2000, 23,2);
        //非法日测试
//        int ret = answer.dayOfYear(2022, 2,29);
//        int ret = answer.dayOfYear(2000, 4,31);
        //正常测试
        int ret = answer.dayOfYear(2022, 5,19);
        System.out.println(ret);
    }
}
