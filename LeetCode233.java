/*
233. Number of Digit One My Submissions Question
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/

public class Solution {
    public int countDigitOne(int n) {
        return helper1(n);
    }
    
    // for less than 10, there are 1
    // for numbers between [10, 100), there are 10, plus another 10, if it's bigger than 20
    // for numbers between [100, 1000), there are 100, plus another 100, if it's bigger than 200
    // similarly for number between [1000, 10000), there are 1000, plus another 1000, if it's bigger than 2000
    // we calcute the number of 1 based on each value in each digit
    /*
    reference: https://leetcode.com/discuss/44281/4-lines-o-log-n-c-java-python
    intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  
    例子:
    以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况: 
        case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
        case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次. 
        case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次. 
    以上三种情况可以用 一个公式概括:
    (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
   */
    private int helper1(int n){
        if(n <= 0) return 0;
        int res = 0;
        long r = 1;
        long a = 0;
        long b = 0;
        
        while(r <= n){
            
            a = n/r;
            b = n % r;
            
            res += ( a + 8)/10 * r;
            if(a%10 == 1){
                res += b + 1;
            }
            r *= 10;
        }
        
        return res;
    }
}