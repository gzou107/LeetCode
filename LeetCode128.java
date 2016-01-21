/*
128. Longest Consecutive Sequence My Submissions Question
Total Accepted: 57149 Total Submissions: 183883 Difficulty: Hard
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

public class Solution {
    public int longestConsecutive(int[] nums) {
        return help2(nums);
    }

    
    private int help2(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        Set<Integer> s = new HashSet<Integer>();
        for(int num : nums)
        {
            s.add(num);
        }
        
        int max = 1;
        for(int num : nums)
        {
            if(!s.contains(num-1))
            {
                int val = num;
                
                while(s.remove(val++));
                
                max = Math.max(max, val - num - 1);
            }
            
        }
        
        return max;
    }

    private int help(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        Set<Integer> s = new HashSet<Integer>();
        for(int num : nums)
        {
            s.add(num);
        }
        
        int max = 1;
        for(int num : nums)
        {
            s.remove(num);
            
            int center = num;
            int len = 1;
            while(s.contains(center-1))
            {
                len++;
                s.remove(center-1);
                center--;
            }
            
            center = num;
            while(s.contains(center+1)){
                len++;
                s.remove(center+1);
                center++;
            }
            
            max = Math.max(max, len);
            
        }
        
        return max;
    }
    
    private int help0(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        Set<Integer> s = new HashSet<Integer>();
        for(int num : nums)
        {
            s.add(num);
        }
        
        int max = 1;
        for(int num : nums)
        {
            s.remove(num);
            
            int center = num-1;
            int len = 1;
            while(s.remove(center--))
            {
                len++;
            }
            
            center = num + 1;
            while(s.remove(center++)){
                len++;
            }
            
            max = Math.max(max, len);
            
        }
        
        return max;
    }
}