/*248. Strobogrammatic Number III My Submissions Question
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

public class Solution {

    public int strobogrammaticInRange(String low, String high) {
        return help(low, high);
    }
    
    int count = 0;
    private int help(String low, String high)
    {
        char[][] pairs ={{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
        for(int len = low.length(); len<= high.length(); len++)
        {
            dfs(low, high, new char[len], 0, len-1, pairs);
        }
        return count;
    }
    
    private void dfs(String low, String high, char[] chars, int left, int right, char[][]pairs)
    {
        if(left > right)
        {
            String num = new String(chars);
            if((num.length() == low.length() && low.compareTo(num) >0) || (num.length() == high.length() && high.compareTo(num) < 0))
            {
                return;
            }
            count++;
            return;
        }
        
        for(char[]pair : pairs)
        {
            chars[left] = pair[0];
            chars[right] = pair[1];
            if(chars.length != 1 && chars[0]=='0')
            {
                continue;
            }
            // be careful at condition pair[0] == pair[1], NOT chars[left] == chars[right]
            if(left < right || (left == right && pair[0] == pair[1]))
            {
                dfs(low, high, chars, left+1, right-1, pairs);
            }
        }
    }
}