/*
Maximum Product of Word Lengths My Submissions Question
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

public class Solution {
    public int maxProduct(String[] words) {
       
       return helper2(words);
    }
    
    public int helper1(String [] words){
         if( words == null || words.length < 2){
            return 0;
        }
        
        String[] sWords = new String[words.length];
        for(int i = 0; i < words.length; i++){
            char[] temp = words[i].toCharArray();
            Arrays.sort(temp);
            sWords[i] = new String(temp);
        }
        Arrays.sort(sWords);
        
        int ans = 0;
        for(int i = 0; i < sWords.length; i++){
            for(int j = i + 1; j < sWords.length; j++){
                if(!isOverlap(sWords[i], sWords[j])){
                    int candidate = sWords[i].length() * sWords[j].length();
                    if(ans < candidate){
                        ans = candidate;
                    }
                }
            }
        }
        
        return ans;
    }
    
    public int helper2(String [] words){
        // a smarter way is to use bit mask as an indicator, to see whether a char apppears or not
        // as an java integer is of bit 32, and all lower case is of size 26, hence, we can use an
        // integer as a signature of the string
        if(words == null || words.length <= 1) return 0;
        int [] sig = new int[words.length];
        Arrays.fill(sig, 0);
        
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length(); j++){
                sig[i] |= (1 <<(words[i].charAt(j) -'a'));
            }
        }
        
        int ans = 0;
        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                if((sig[i] & sig[j]) == 0 && words[i].length() * words[j].length() > ans){
                    ans = words[i].length() * words[j].length();
                }
            }
        }
        
        return ans;
    }
    public boolean isOverlap(String s, String t){
        int i = 0;
        int j = 0;
    
        while(i < s.length() && j < t.length()){
            char sc = s.charAt(i);
            char tc = t.charAt(j);
            if(sc == tc){
                return true;
            }else if(sc < tc){
                i++;
            }else{
                j++;
            }
        }
        
        return false;
    }
}