/*
266. Palindrome Permutation My Submissions Question
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/
public class Solution {
    public boolean canPermutePalindrome(String s) {
        return helper0(s);
       // return helper(s);
    }
    
    private boolean helper0(String s)
    {
        BitSet set = new BitSet(256);
        for(byte c : s.getBytes()){
            set.flip(c);
        }
        
        return set.cardinality() < 2;
    }
    private boolean helper(String s)
    {
        if( s== null) return true;
        int [] t = new int[256];
        for(int i = 0; i < s.length(); i++){
            t[s.charAt(i)]++;
        }
        
        int oddCount = 0;
        for(int i = 0; i<256; i++){
            if(t[i]%2 == 1){
                oddCount++;
                if(oddCount >= 2){
                    return false;
                }
            }
        }
        
        return true;
    }
}