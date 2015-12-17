/*
ZigZag Conversion My Submissions Question
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
    public String convert(String s, int numRows) {
        if(numRows <= 1){ return s;}
        if(s == null || s.length() < numRows) {return s;}
        StringBuilder ans = new StringBuilder();
        int period = 2 * numRows - 2;
        for(int i = 0; i < numRows; i++)
        {
            // we calculate center of each symmetry, which is numRows, k*(numRows-1)
            for(int j = i; j< s.length(); j+= period){
                ans.append(s.charAt(j));
                
                if(i != 0 && i != numRows - 1 && j + period - 2 * i < s.length())
                {
                    ans.append(s.charAt(j + period - 2 * i));
                }
            }
                
        }
            
        return ans.toString();
    }
}