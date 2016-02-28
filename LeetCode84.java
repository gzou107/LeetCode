/*
84. Largest Rectangle in Histogram My Submissions Question
Total Accepted: 54976 Total Submissions: 231059 Difficulty: Hard
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
*/

public class Solution {
    public int largestRectangleArea(int[] heights) {
        return help(heights);
    }
   
   // area = num[top] * i; // if s.isEmpty()
   //        num[top] * (i - s.peek()-1); // if stack is NOT empty
    private int help(int [] h)
    {
        if(h == null || h.length == 0) return 0;
        int max = -1;
        int area = 0;
        int i = 0;
        Stack<Integer> s = new Stack<Integer>();
        for(i = 0; i < h.length;)
        {
            if(s.isEmpty() || h[i] >= h[s.peek()]){
                s.push(i++);
            }else{
                int top = s.pop();
                if(s.isEmpty()){
                    area = h[top] * (i);
                }else{
                    area = h[top]*(i - s.peek()-1);
                }
                
                max = Math.max(max, area);
            }
        }
        
        while(!s.isEmpty()){
            int top = s.pop();
            if(s.isEmpty()){
                area = h[top] *(i);
            }else{
                area = h[top] * (i - s.peek()-1);
            }
            
            max = Math.max(max, area);
        }
         return max;
    }
	// clean the code a little bit
	private int help2(int [] h)
    {
        if(h == null || h.length == 0) return 0;
        int max = -1;
        int area = 0;
        int i = 0;
        Stack<Integer> s = new Stack<Integer>();
        for(i = 0; i < h.length;)
        {
            if(s.isEmpty() || h[i] >= h[s.peek()]){
                s.push(i++);
            }else{
                int top = s.pop();
                area = h[top] * ( s.isEmpty()? i : i - s.peek()-1);
                max = Math.max(max, area);
            }
        }
        
        while(!s.isEmpty()){
            int top = s.pop();
            area = h[top] * ( s.isEmpty()? i : i - s.peek()-1);
            max = Math.max(max, area);
        }
         return max;
    }
}