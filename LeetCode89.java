/*
89. Gray Code My Submissions Question
Total Accepted: 52246 Total Submissions: 148977 Difficulty: Medium
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

public class Solution {
    public List<Integer> grayCode(int n) {
        //return help0(n);
        return help1(n);
    }
    
    public List<Integer> help0(int n)
    {
        List<Integer> res = new LinkedList<>();
        if(n < 0){
            return res;
        }
        int len = (1 << n);
        for(int i = 0; i < len; i++)
        {
            res.add( (i >> 1 )^i);
        }
        return res;
    }
    
    public List<Integer> help1(int n)
    {
        List<Integer> res = new LinkedList<>();
        if(n < 0){
            return res;
        } 
        
        res.add(0);
        int step = 1;
        for(int i = 1; i <= n; i++)
        {
            for(int j = res.size()-1; j >= 0; j-- )
            {
                res.add(res.get(j) + step);
            }
            
            step <<= 1;
        }
        
        return res;
    }
}