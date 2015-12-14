/*
Count Primes My Submissions Question
Description:

Count the number of prime numbers less than a non-negative number, n.
*/
/*
A prime number (or a prime) is a natural number greater than 1 that has no positive divisors other than 1 and itself. A natural number greater than 1 that is not a prime number is called a composite number
*/
public class Solution {
    public int countPrimes(int n) {
        if( n <= 1){
            return 0;
        }
        
        boolean [] isPrime = new boolean[n];
        // always starting with 2 (inclusive) per the definition of prime number
        for(int i = 2; i <n; i++){
            isPrime[i] = true;
        }
        // the stop condition is i * i < n, as we do not need to search for i < n
        // with i -> Math.sqrt(n), we have crossed out all the composite number
        for(int i = 2; i <= n/i; i++){
            if(!isPrime[i]) continue;
            // i is prime number now, and all numbers strictly smaller than i * i have been processed
            // starting from i sqaure, and any mulitly of i will be crossed out
            for(int j = i * i; j < n; j +=i){
                isPrime[j]= false;
            }
        }
        
        int count = 0;
        for(int i = 2; i< n; i++){
            count += isPrime[i]?1:0;
        }
        
        return count;
    }
    
    private static boolean isPrime(int n)
    {
        for(int i=2; i <= n/i; i++){
            if(n % i == 0){
                return false;
            }
        }
        
        return true;
    }
}