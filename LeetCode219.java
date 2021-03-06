/*
Contains Duplicate II My Submissions Question
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        if(nums == null || nums.length == 0 || k <= 0){
            return false;
        }
        
        Map<Integer, Integer> numToPosMapping = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++)
        {
            if(numToPosMapping.containsKey(nums[i]))
            {
                int prePos = numToPosMapping.get(nums[i]);
                if(i - prePos <= k){
                    return true;
                }
            }
            numToPosMapping.put(nums[i], i);
        }
          
        return false;
    }
}