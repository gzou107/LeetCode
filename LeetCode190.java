/*
Total Accepted: 45858 Total Submissions: 157363 Difficulty: Easy
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).
*/
public class Solution {
    
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // flip every bits of n
        // return (((n >>>24)^0xff)<<24) | (((n >>>16)^0xff)<<16) | (((n >>>8)^0xff)<<8) | ( n^0xff);
        
        /*
        // reverse bits, like we reverse a string
        for(int i = 0; i < 16; i++){
            n = swap(n, i, 32 -i - 1);
        }
        
        return n;
        */
        
        for(int step = 1; step < 32; step *= 2)
        {
            for(int j = 0; j < 32; j += 2*step)
            {
                // here we need swap the bits between [j, j + step - 1] and [j + step, j + 2*step -1]
                // tricky, sleep on it 
                n = swap(n,j, j+step, step);
            }
        }
        return n;
        
    }
    
    private static int swap(int num, int i, int j){
        int a = (num >> i) & 1;
        int b = (num >> j) & 1;
        
        if ((a^b) != 0) // we do the below only if a and b are different; nothing to do if they're the same
        {
             num ^= ( 1 << i) | ( 1 << j);
        }
        
        return num;
    }
    
    /*
       1) Move all bits of first set to rightmost side
        set1 =  (x >> p1) & ((1U << n) - 1)
        Here the expression (1U << n) - 1 gives a number that 
        contains last n bits set and other bits as 0. We do & 
        with this expression so that bits other than the last 
        n bits become 0.
        2) Move all bits of second set to rightmost side
           set2 =  (x >> p2) & ((1U << n) - 1)
        3) XOR the two sets of bits
           xor = (set1 ^ set2) 
        4) Put the xor bits back to their original positions. 
           xor = (xor << p1) | (xor << p2)
        5) Finally, XOR the xor with original number so 
           that the two sets are swapped.
           result = x ^ xor
   */
    private static int swap(int num, int i, int j, int step){
       /* int xor = ((num >> i) ^ (x >> j)) & ((1U << step) - 1);
 
       // To swap two sets, we need to again XOR the xor with original sets 
       return num ^ ((xor << i) | (xor << j));
       */
       
        int a = (num >>> i ) & ( (1<<step) -1);
        int b = (num >>> j ) & ( (1<<step) -1);
        int xor = a ^ b;
        int ax = (xor << i) | ( xor << j);
        num ^= ax;
        return num;
        
    }
}