/*
224. Basic Calculator My Submissions Question
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
*/
public class Solution {
    public int calculate(String s) {
        return helper1(s);
    }
    
    /*
    One important thing is that the input is valid, which means the parentheses are always paired and in order. Only 5 possible input we need to pay attention:

    digit: it should be one digit from the current number
    '+': number is over, we can add the previous number and start a new number
    '-': same as above
    '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
    ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
    Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
     */
    private int helper1(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }
        if(i == s.length()) return 0;
        
        Stack<Integer> st = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        
        while(i < s.length()){
            char c = s.charAt(i);
            
            if(Character.isDigit(c)){
                    number = number * 10 + c - '0';
                    c = s.charAt(i);
            }else if(c == '+'){
                 result += number * sign;
                 sign = 1;
                 number = 0;
            }else if(c == '-'){
                 result += number * sign;
                 sign = -1;
                 number = 0;
            }else if(c == '('){
                // we need calculate all the ones within the parenthesis
                st.push(result);
                st.push(sign);
                result = 0;
                number = 0;
                sign = 1;
                
            }else if(c == ')'){
                // get the result within the parathesis
                result += number * sign;
                // combine with previous result
                result *= st.pop();
                result += st.pop();
                // reset for next usage
                number = 0;
                sign = 1;
            }
            i++;
        }
        
        if(number != 0){
            result += number * sign;
        }
        
        return result;
    }
}