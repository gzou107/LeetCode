/*Summary Ranges My Submissions Question
Total Accepted: 29867 Total Submissions: 138282 Difficulty: Easy
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<String>();
        
        if(nums == null){
            return ans;
        }
        
        int start = 0;
        int end = 0;
        
        while(end < nums.length){    
            while(end < nums.length && end - start == nums[end] - nums[start]){
                end++;
            }
                
            // we're here either because end = nums.length or end - start != nums[end] - nums[start]
            if(start == end -1){
                ans.add(String.valueOf(nums[start]));
            }else{
                ans.add(String.valueOf(nums[start]) + "->" + String.valueOf(nums[end -1]));
            }
            start = end;
        }
        
        return ans;
    }
}