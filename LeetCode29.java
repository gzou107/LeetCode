/*
29. Divide Two Integers My Submissions Question
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

public class Solution {
    public int divide(int dividend, int divisor) {
        return help(dividend, divisor);
    }
    
    private int help0(int divid, int divir)
    {
        int result = 0;
        
        if(divid == Integer.MAX_VALUE && divir == -1){
            return Integer.MIN_VALUE;
        }
        if(divid == Integer.MIN_VALUE && divir == -1){
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = false;
        if((divid >0 && divir< 0) || (divid < 0 && divir >0)){
            isNegative = true;
        }
        
        long dividend = Math.abs((long)divid);
        long divisor = Math.abs((long)divir);
       
        while(dividend >= divisor)
        {
            int step = 0;
            while((divisor<<step)< dividend)
            {
                step++;
            }
            
            if((divisor<<step) == dividend){
                result += (1<<step);
                return isNegative? -result : result;
            }else{
                result += (1 <<(step-1));
                dividend -= (divisor<<(step-1));
            }
        }
        
        return isNegative? -result : result;
    }
}