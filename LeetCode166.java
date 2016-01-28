/*
166. Fraction to Recurring Decimal My Submissions Question
Total Accepted: 25495 Total Submissions: 177043 Difficulty: Medium
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        return help(numerator, denominator);
    }
    
    private String help(int numerator, int denominator)
    {
        StringBuilder result = new StringBuilder();
        String sign = ((numerator <=0 == denominator <= 0) || numerator == 0 ) ? "" : "-";
        
        long numL = numerator < 0 ? -(long)numerator : (long)numerator;
        long denL = denominator < 0 ? -(long)denominator : (long)denominator;
        result.append(sign);
        result.append(numL/denL);
        long reminder = numL%denL;
        
        if(reminder == 0){
            return result.toString();
        }
        result.append(".");
        // keep record of the first time the reminder appears
        Map<Long, Integer> m = new HashMap<>();
        while(!m.containsKey(reminder))
        {
            m.put(reminder, result.length());
            result.append(reminder*10/denL);
            reminder = reminder*10%denL;
        }
        // now the reminder has appear before, we stop here
        int start = m.get(reminder);
        result.insert(start, '(');
        result.append(')');
        
        return result.toString().replace("(0)", "");
    }
}