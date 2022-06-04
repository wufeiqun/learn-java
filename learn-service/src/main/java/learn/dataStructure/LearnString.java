package learn.dataStructure;

/**
 * @author 吴飞群
 * @createTime 2022/06/02
 */
public class LearnString {

    /**
     * leeCode: 125
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例1:
     *  输入: "A man, a plan, a canal: Panama"
     *  输出: true
     *  解释："amanaplanacanalpanama" 是回文串
     *
     * 示例2:
     *  输入: "race a car"
     *  输出: false
     *  解释："raceacar" 不是回文串
     */
    public static boolean isPalindrome(String s) {
        String str = s.toUpperCase();

        int i = 0;int j = str.length()-1;

        while(i < j){
            if (!Character.isLetterOrDigit(str.charAt(i))){
                i++;
            }else if (!Character.isLetterOrDigit(str.charAt(j))){
                j--;
            }else if (str.charAt(i) != str.charAt(j)){
                return false;
            }else {
                i++;
                j--;
            }
        }

        return true;
    }

    public String breakPalindrome(String s) {
        return null;
    }

    public static void main(String[] args) {
        String abc ="ac, B,,,,,,,bC>>><<>?A>>";
        System.out.println(isPalindrome(abc));
    }

}
