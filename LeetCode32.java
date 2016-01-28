/*
32. Longest Valid Parentheses My Submissions Question
Total Accepted: 55072 Total Submissions: 250693 Difficulty: Hard
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

public class Solution {
    public int longestValidParentheses(String s) {
        return help1(s);
    }
    
    private int help0(String s)
    {
        if(s == null || s.length() <= 1){
            return 0;
        }
        
        int start = -1;
        int res = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                if(!stack.isEmpty()){
                    stack.pop();
                    if(!stack.isEmpty()){
                        res = Math.max(res,i-stack.peek());
                    }else{
                        res = Math.max(res,i-start);
                    }
                }else{
                    start = i;
                }
            }
        }
        return res;
    }
    
    // time out???
    private int help1(String s)
    {
        if(s == null || s.length() == 0) return 0;
        
        Stack<Integer> st = new Stack<Integer>();
        int maxLen = 0;
        
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '(')
            {
                /*if(!st.isEmpty() && s.charAt(st.peek()) == '(')
                {
                    st.pop();
                }*/
                st.push(i);
            }else{
                if(!st.isEmpty() && s.charAt(st.peek()) == '(')
                {
                    st.pop();
                    int len = i - (st.isEmpty()? -1 : st.peek());
                    maxLen = Math.max(len, maxLen);
                }else
                {
                    /*while(!st.isEmpty())
                    {
                        st.pop();
                    }*/
                    st.push(i); //serves as an end position index
                }
            }
        }
        
        return maxLen;
    }
    
        // NOT time out
    private int help2(String s)
    {
        if(s == null || s.length() == 0) return 0;
        
        Stack<Integer> st = new Stack<Integer>();
        int maxLen = 0;
        int start = -1;
        
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '(')
            {
                st.push(i);
            }else{
                if(!st.isEmpty() && s.charAt(st.peek()) == '(')
                {
                    st.pop();
                    int len = i - (st.isEmpty()? start : st.peek());
                    maxLen = Math.max(len, maxLen);
                }else
                {
                    start = i; //serves as an end position index
                }
            }
        }
        
        return maxLen;
    }
}