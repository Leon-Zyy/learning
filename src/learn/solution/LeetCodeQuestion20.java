package learn.solution;

import java.util.Stack;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion20 {

    /**
     * LeetCode 20. 利用栈验证括号匹配
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        String validStr = s.trim();
        Stack<Character> stack = new Stack<>();

        for (int i = 0, size = validStr.length(); i < size; i++) {
            char validChr = validStr.charAt(i);

            if (validChr == '[' || validChr == '(' || validChr == '{') {
                stack.push(validChr);
            } else {
                if (stack.isEmpty())
                    return false;

                char topEle = stack.pop();
                if (validChr == ']' && topEle != '[')
                    return false;
                if (validChr == ')' && topEle != '(')
                    return false;
                if (validChr == '}' && topEle != '{')
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
