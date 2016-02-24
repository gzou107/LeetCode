/*
16. 3Sum Closest My Submissions Question
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/	

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //return help0(nums, target);
        return help1(nums, target);
    }
    
    public int help0(int[] nums, int target) {
        Arrays.sort(nums);
        int closetSum = 0, minDiff = Integer.MAX_VALUE / 2;
        for(int i = 0; i < nums.length; i++){
            int left = i + 1, right = nums.length - 1;
            while(left < right){
                // 当前组合的和
                int sum = nums[i] + nums[left] + nums[right];
                // 当前组合的和与目标的差值
                int diff = Math.abs(sum - target);
                // 如果差值更小则更新最接近的和
                if(diff < minDiff){
                    closetSum = sum;
                    minDiff = diff;
                }
                // 双指针的移动方法和3Sum一样
                if (sum < target){
                    left++;
                } else if (sum > target){
                    right--;
                } else {
                    return sum;
                }
            }
        }
        return closetSum;
    }
    
    
    private int help1(int[]nums, int target)
    {
        if(nums == null || nums.length == 0) return 0;
        
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int optimal = 0;
        
        if(nums.length <= 3){
            for(int num : nums){
                optimal += num;
            }
            return optimal;
        }
        
        for(int i = 0; i < nums.length-2; i++)
        {
            for(int j = i + 1, k = nums.length-1; k > j;)
            {
                int val = nums[j] + nums[k] + nums[i];
               
                if(Math.abs(target - val) < diff){
                    diff = Math.abs(target-val);
                    optimal = val;
                }
                
                if(val < target){
                    j++;
                }else if(val > target){
                    k--;
                }else{
                    return target;
                }
            }
        }
        
        return optimal;
    }
}