/*
279. Perfect Squares My Submissions Question
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*/

public class Solution {
    public int numSquares(int n) {
        
       // return helper1(n);
       return helper2(n);
       
    }
    
    public int helper2(int n){
        
        int [] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for(int i = 1; i * i <=n; i++){
            dp[i*i] = 1;
        }
        
        // update the value on the fly, not necesary in order
        for(int i = 1; i <=n; i++){
            for(int j = 1; i + j*j <= n; j++){
                dp[i + j*j] = Math.min(dp[i + j *j], dp[i]+1);
            }
        }
        
        return dp[n];
    }
    
    public int helper1(int n){
        if(n <= 0){
            return 0;
        }
        
        if(n <=3) return n;
        
        int [] ans = new int[n+1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for(int i = 1; i<=3; i++){
            ans[i] = i;
        }
        
        int i = 4;
        while(i <= n){
            if(isPerfect(i)){
                ans[i] = 1;
                i++;
                continue;
            }
            
            for(int j = 1; j<= i/2; j++){
                if(ans[j] + ans[i-j] < ans[i]){
                    ans[i] = ans[j] + ans[i-j];
                }
            }
            
            i++;
        }
        return ans[n];
    }
    
    private boolean isPerfect(int num){
        int l = 1;
        int h = (int)Math.sqrt(num);
        
        while(l <= h){
            int m = l + (h-l)/2;
            if(m == (double)num/m){
                return true;
            }else if(m < num/m){
                l = m + 1;
            }else{
                h = m - 1;
            }
        }
        
        return false;
    }
}