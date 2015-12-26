/*
227. Basic Calculator II My Submissions Question
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

public class Solution {
    public int calculate(String s) {
        
        return helper1(s);
    }
    
    private int helper1(String s){
        if(s == null || s.length() == 0) return 0;
        
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }
        
        if( i == s.length()) return 0;
        // now i point to first non-empty character
        // considering the + and - are evaluated from left to right,
        // hence we need support operation from both end, so we need deque, rather than stack!!!
        Deque<Integer> temp = new ArrayDeque<Integer>();
        Deque<Character> op = new ArrayDeque<Character>();
        for(; i < s.length();){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int end = advance(s, i);
                int te = stringToInt(s.substring(i, end));
                temp.addLast(te);
                i = end;
            }else if(c == '+' || c == '-'){
                op.addLast(c);
                i++;
            }else if( c == '*'){
                int op1 = temp.removeLast();
                i++;
                while(s.charAt(i) == ' '){
                    i++;
                }
                int end = advance(s, i);
                int op2 = stringToInt(s.substring(i, end));
                
                temp.addLast(op1 * op2);
                i = end;
            }else if( c == '/'){
                int op1 = temp.removeLast();
                i++;
                while(s.charAt(i) == ' '){
                    i++;
                }
                int end = advance(s, i);
                int op2 = stringToInt(s.substring(i, end));

                temp.addLast(op1/op2);
                i = end;
            }else{ // empty space here
                i++;
            }
        }
        
        while( !op.isEmpty()){
            char c = op.removeFirst();
            
            int op1 = temp.removeFirst();
            int op2 = temp.removeFirst();
            if( c == '+'){
                temp.addFirst(op1 + op2);
            }else if ( c == '-'){
                temp.addFirst(op1 - op2);
            }
        }
        
       return temp.removeFirst();
    }
    
    
    private int advance(String s, int start){
        int end = start;
        
        while(end < s.length() && Character.isDigit(s.charAt(end))){
            end++;
        }
        return end;
    }
    
    private int stringToInt(String s){
        int ans = 0;
        for(int i = 0; i < s.length(); i++)
        {
            ans = ans * 10 + s.charAt(i) - '0';
        }
        return ans;
    }
}