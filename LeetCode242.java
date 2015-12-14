/*
Valid Anagram My Submissions Question
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        // This assumes that the s contains only lower case alphabets
        if(s == null){
            return t == null;
        }
        
        if(t == null){
            return false;
        }
        
        if(s.length() != t.length()){
            return false;
        }
        
        int []counter = new int[256];
        Arrays.fill(counter, 0);
        for(int i = 0; i < s.length(); i++){
            counter[s.charAt(i)]++;
        }
        
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if(counter[c] <= 0){
                return false;
            }
            counter[c]--;
        }
        // Make sure no chars left
        for(int i = 0; i< 256; i++){
            if(counter[i] >0){
                return false;
            }
        }
        return true;
    }
}
