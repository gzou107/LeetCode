/*
Word Pattern My Submissions Question
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

public class Solution {
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null){
            return false;
        }
        
        String [] strs = str.split(" ");
        if(pattern.length() != strs.length){
            return false;
        }
        
        Map<Character, String> pMapping = new HashMap<Character, String>();
        for(int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            if(pMapping.containsKey(c)){
                if(!strs[i].equals(pMapping.get(c))){
                    return false;
                }
            }else if(pMapping.containsValue(strs[i])){
                return false;
            }else{
                pMapping.put(c, strs[i]);
            }
        }
        return true;
    }
}