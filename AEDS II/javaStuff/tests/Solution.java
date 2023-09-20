public class Solution {
    public static void main(String[] args) {
        String str = "([)]";
        ValidParentheses checkString = new ValidParentheses();
        if (checkString.isValid(str)){
            System.out.println("Yeah it's valid");
        } else {
            System.out.println("nah");
        }

    }
}
