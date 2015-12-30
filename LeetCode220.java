/*
220. Contains Duplicate III My Submissions Question
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //return helper1(nums, k, t);
        return helper2(nums, k, t);
    }
    
    private boolean helper1(int[] nums, int k, int t){
        if(k < 1 || t < 0)
            return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0; i < nums.length; i++)
        {
            int n = nums[i];
            if(set.floor(n) != null && n <= t + set.floor(n) || set.ceiling(n) != null && set.ceiling(n) <= t + n)
            {
                return true;
            }
            set.add(n);
            
            // we need kick off element if the index is bigger or equal than k
            if (i >= k){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
    
    private boolean helper2(int[] nums, int k, int t)
    {
        if(nums == null || nums.length <2 || k < 1 || t < 0) return false;
        
        TreeSet<Long> ts = new TreeSet<Long>();
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            SortedSet<Long> s = ts.subSet((long)n-t, (long)n+t+1);
            if(!s.isEmpty()){
                return true;
            }
            
            ts.add((long)n);
            if(i >=k){
                ts.remove((long)nums[i-k]);
            }
        }
        
        return false;
    }
}