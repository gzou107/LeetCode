/*
150. Evaluate Reverse Polish Notation My Submissions Question
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

public class Solution {
    public int evalRPN(String[] tokens) {
        return help0(tokens);
    }
    
    private int help0(String [] a)
    {
          Stack<Integer> stack = new Stack<Integer>();

          for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
              case "+":
                stack.push(stack.pop() + stack.pop());
                break;
        
              case "-":
                stack.push(-stack.pop() + stack.pop());
                break;
        
              case "*":
                stack.push(stack.pop() * stack.pop());
                break;
        
              case "/":
                int n1 = stack.pop(), n2 = stack.pop();
                stack.push(n2 / n1);
                break;
        
              default:
                stack.push(Integer.parseInt(a[i]));
            }
          }
        
          return stack.pop();
    }
    
    private int help(String[]tokens)
    {
        if(tokens == null || tokens.length == 0) return 0;
        
        Stack<Integer> s = new Stack<Integer>();
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].length() >= 2  || (tokens[i].charAt(0)>='0' && tokens[i].charAt(0) <='9') )
            {
                s.push(Integer.valueOf(tokens[i]));
            }else{
                char operator = tokens[i].charAt(0);
                int op0 = s.pop();
                int op1 = s.pop();
                
                switch(operator)
                {
                    case '+':
                        s.push(op0 + op1);
                        break;
                    case '-':
                        s.push(op1-op0);
                        break;
                    case '*':
                        s.push(op0*op1);
                        break;
                    case '/':
                        s.push(op1/op0);
                        break;
                    default:
                        break;
                }
            }
        }
        
        return s.pop();
    }
}