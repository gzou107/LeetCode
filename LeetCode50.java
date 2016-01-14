/*
50. Pow(x, n) My Submissions Question
Implement pow(x, n).
*/

public class Solution {
    public double myPow(double x, int n) {
        //return helper0(x, n);
        return helper1(x, n);
    }
    
    public double helper0(double x, int n)
    {
        long m = n > 0 ? n : -(long)n;
        double ans = 1.0;
        while (m != 0) {
            if ((m & 1) == 1)
                ans *= x;
            x *= x;
            m >>= 1;
        }
        return n >= 0 ? ans : 1 / ans;
    }
    public double helper1(double x, int n)
    {
        if(x == 1)
        {
            return 1;
        }else if( x == -1)
        {
            return n % 2 == 0? 1 : -1;
        }
        
        
        if( n < 0)
        {
          return 1/helper1(x, -n);
        }else if( n == 0)
        {
            return x == 0? 0 : 1;
        }else if(n == 1){
            return x;
        }else{
            
            double half = helper1(x, n/2);
            
            if(n % 2 == 0)
            {
                return half * half;
            }else{
                return half * half * x;
            }
        }
    }
}