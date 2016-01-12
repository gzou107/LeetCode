/*
11. Container With Most Water My Submissions Question
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

public class Solution {
    public int maxArea(int[] height) {
        return helper0(height);
        //return helper(height);
    }
    
    private int helper0(int [] h)
    {
        if(h == null || h.length <=1) return 0;
        
        int left = 0;
        int right = h.length - 1;
        int max = 0;
        
        while(left < right)
        {
            int hei = h[left] < h[right] ? h[left] : h[right];
            int cur = (right - left) * hei;
            max = max < cur? cur : max;
            
            // skip non-optimal solution
            while(left < right && h[left] <= hei){
                left++;
            }
            
            while(left < right && h[right] <= hei){
                right--;
            }
        }
        
        return max;
    }
    private int helper(int [] h)
    {
        if( h == null || h.length <= 1) return 0;
        
        int left = 0;
        int right = h.length - 1;
        int max = 0;
        
        while(left < right)
        {
            int area = (right -left) * Math.min(h[left], h[right]);
            
            max = Math.max(area, max);
            
            if(h[left] < h[right]){
                left++;
            }else{
                right--;
            }
        }
        
        return max;
    }
}