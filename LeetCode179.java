/*
179. Largest Number My Submissions Question
Total Accepted: 39665 Total Submissions: 217567 Difficulty: Medium
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

public class Solution {
    public String largestNumber(int[] nums) {
        return help0(nums);
    }
    
    private String help0(int[] nums)
    {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
        {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2)
            {
                return (s1+s2).compareTo(s2+s1);
            }
        });
        
        StringBuilder res = new StringBuilder();
        for(int i = strs.length-1; i >= 0; i--)
        {
            res.append(strs[i]);
        }
        if(res.charAt(0)=='0') return "0";
        
        return res.toString();
    }
}