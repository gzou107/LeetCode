/*
287. Find the Duplicate Number My Submissions Question
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

public class Solution {
    public int findDuplicate(int[] nums) {
        //return help0(nums);
        return help1(nums);
    }
    
    private int help0(int [] nums)
    {
        // per math theory, there must be a duplicate/cycle with n+1 integers
        // ranging from 1 to n, we can think off there is a mapping between i --> num[i]
        // define such function as f(i) = num[i], and i from 1 to n, and num[i] from 1 to n + 1
        int slow = 0;
        int fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
    
    private int help1(int [] nums)
    {
        int l = 0;
        int r = nums.length-1;
        
        while(l < r)
        {
            int m = l + ((r-l)>>1);
            
            int count = smallOrEqualCount(nums, m);
            if(count <= m){ // meaning there is no duplicate between l and m(inclusive)
                l = m + 1;
            }else{
                r = m;
            }
        }
        
        return l; // !!!l rather than nums[l]
    }
    
    private int smallOrEqualCount(int [] nums,int mid){
        int count = 0;
        for(int num : nums){
            count += num <= mid ? 1 : 0;
        }
        return count;
    }
}