package learn.dataStructure;

/**
 * @author 吴飞群
 * @createTime 2022/06/02
 */
public class LearnString {

    /**
     * 判断一个字符串是不是回文字符串
     * 方法一: 中间分隔, 依次对比
     */
    public static boolean isPalindrome_1(String s) {
        if (s == null || s.equals("") || s.length()%2 != 0){
            return false;
        }

        String[] array = s.split("");

        for (int i = 0; i < s.length()/2; i++) {
            if (!array[i].equals(array[s.length()-1-i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断一个字符串是不是回文字符串
     * 方法一: 中间分隔, 依次对比
     */
    public static boolean isPalindrome_2(String s) {
        if (s == null || s.equals("") || s.length()%2 != 0){
            return false;
        }

        StringBuilder sb = new StringBuilder();

        String[] array = s.split("");

        for (int i = s.length()-1; i >= 0; i--) {
            sb.append(array[i]);
        }

        return sb.toString().equals(s);
    }

    public String breakPalindrome(String s) {
        return null;
    }

    public static void main(String[] args) {
        String abc ="ab   ccba";

//        System.out.println(isPalindrome_1(abc));
//        System.out.println(isPalindrome_2(abc));

        char[] array = abc.toCharArray();
//        System.out.println(array);
        System.out.println('a' > 'b');
    }

}
