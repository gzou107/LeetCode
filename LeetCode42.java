/*
42. Trapping Rain Water My Submissions Question
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*/

public class Solution {
    public int trap(int[] height) {
         return helper0(height);
        // return helper(height);
    }
    
    public int helper0(int [] height)
    {
         if (height.length <= 2) return 0;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }
    
        int leftMax = height[0];
        int water = 0;
        for (int i = 1; i < maxIndex; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            } else {
                water += leftMax - height[i];
            }
        }
    
        int rightMax = height[height.length - 1];
        for (int i = height.length - 2; i > maxIndex; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            } else {
                water += rightMax - height[i];
            }
        }
    
        return water;
    }
    
    
    public int helper(int [] h)
    {
        if(h == null || h.length <= 1) return 0;
        
        int l = 0;
        int r = h.length - 1;
        int area = 0;
        int hei = 0;
        
        while ( l < r)
        {
            if(h[l] < h[r])
            {
                hei = Math.max(hei, h[l]);
                area += hei - h[l];
                l++;
            }else
            {
                hei = Math.max(hei, h[r]);
                area+= hei - h[r];
                r--;
            }
        }
        return area;
    }
}