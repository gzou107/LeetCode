/*
163. Missing Ranges My Submissions Question
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        //return helper1(nums, lower, upper);
        return helper2(nums, lower, upper);
    }
    
    private List<String> helper1(int [] nums, int lower, int upper)
    {
        List<String> res = new ArrayList<String>();
        int prev = lower -1;
     
        for(int i = 0; i<= nums.length; i++)
        {
            int curr = (i == nums.length) ? upper+1 : nums[i];
            
            if(curr - prev >=2)
            {
                res.add(makeRange(prev+1, curr - 1));
            }
            prev = curr;
        }
        
        return res;
    }
    
    private String makeRange(int from, int to)
    {
        return from == to? String.valueOf(from) : from + "->" + to;
    }
    
    private List<String> helper2(int [] nums, int lower, int upper)
    {
           List<String> ranges = new ArrayList<String>();
            if (nums.length == 0) {  //Empty array misses the range lower->upper.
                ranges.add(makeRange(lower, upper));
                return ranges;
            }
            int prev;
            if (nums[0] - lower > 0) {    //Handles lower boundary. Notice "inclusive".
                ranges.add(makeRange(lower, nums[0] - 1));
                prev = nums[0];
            } else {
                prev = lower;
            }
            for (int cur : nums) {
                if (cur - prev > 1) { 
                    ranges.add(makeRange(prev + 1, cur - 1)); //Misses range if distance > 1.
                }
                prev = cur;
            }
            if (upper - prev > 0) {  //Handles the upper boundary.
                ranges.add(makeRange(prev + 1, upper));
            }
        
            return ranges;
    }
}