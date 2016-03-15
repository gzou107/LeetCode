/*
12. Integer to Roman My Submissions Question
Total Accepted: 58622 Total Submissions: 154367 Difficulty: Medium
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public String intToRoman(int num) {
       return help1(num); 
    }
    
    private int []numbers =    {1000, 900, 500,400, 100, 90, 50, 40, 10,  9,  5,   4,   1};
    private String [] romans = {"M", "CM","D","CD","C","XC","L","XL","X","IX","V","IV", "I"};
    
    private String help(int num)
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numbers.length && num > 0; i++)
        {
            while(num >= numbers[i])
            {
                result.append(romans[i]);
                num -= numbers[i];
            }
        }
        return result.toString();
    }
    
    private String help1(int num)
    {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(num > 0)
        {
            int k = num/numbers[i];
            for(int j = 0; j < k; j++)
            {
                result.append(romans[i]);
                num -= numbers[i];
            }
            i++;
        }
        return result.toString();
    }
}