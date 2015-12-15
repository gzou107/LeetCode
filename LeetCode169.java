/*
Majority Element My Submissions Question
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/
public class Solution {
    // Here is the idea, as the majority element exists, we can keep track the number which appears most
    // add the count, if the new element equals to current one, minus the count if not, and replace the value
    // once the count drops to 0, and reset the counter
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == ans){
                count++;
            }else{
                count--;
                if(count == 0){
                    ans = nums[i];
                    count = 1;
                }
            }
        }
        
        return ans;
    }
}