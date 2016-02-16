/*
334. Increasing Triplet Subsequence My Submissions Question
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        return help0(nums);
       // return help(nums);
    }
    /*
    设所求的上升序列为 x1,x2,x3 （x1<x2<x3）

    初始时设置x1 = x2 = INT_MAX ，然后遍历数组

    num <= x1: 此时将x1设置为num
    若 x1 < num <= x2，则 把x2 设置为num
    x1 < x2 < num  说明有解，返回true即可
    简单的说，上述的过程就是不断的缩小x1和x2，（并且保证x1 < x2）看看是否有第三个数 比x2大。
    */
    private boolean help0(int [] nums)
    {
        if(nums == null || nums.length < 3) return false;
        
        int nums0 = Integer.MAX_VALUE;
        int nums1 = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] <= nums0){
                nums0 = nums[i];
            }else if(nums[i] <= nums1){
                nums1 = nums[i];
            }else{
                return true;
            }
        }
        
        return false;
    }
    private boolean help(int[] nums)
    {
        if(nums == null || nums.length < 3) return false;
        
        boolean [] bigger = new boolean[nums.length];
        boolean []smaller = new boolean[nums.length];
        int big = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] > big){
                bigger[i] = true;
            }else{
                big = nums[i];
            }
        }
        
        int small = nums[nums.length-1];
        for(int i = nums.length - 2; i >= 0; i--)
        {
            if(nums[i] <small)
            {
                smaller[i] = true;
            }else{
                small = nums[i];
            }
        }
        
        for(int i = 1; i < nums.length-1; i++)
        {
            if(bigger[i] && smaller[i]){
                return true;
            }
        }
        return false;
    }
}