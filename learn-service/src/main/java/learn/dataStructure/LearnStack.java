package learn.dataStructure;

import java.util.Stack;

/**
 * 关于栈的相关学习和练习题目
 * @author 吴飞群
 * @createTime 2022/06/01
 */
public class LearnStack {

    /**
     * 输入字符串"hello world java", 输出字符串"java world hello"
     * 考察内容为栈数据结构和字符串的基本操作
     */
    public static String reverseWords(String str) {
        StringBuilder sb = new StringBuilder();
        String[] array = str.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals("")){
                stack.push(array[i]);
            }

        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String src = "   hello            world       java    ";
        String des = reverseWords(src);
        System.out.println(des);
    }
}
