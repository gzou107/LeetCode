/*
336. Palindrome Pairs My Submissions Question
Total Accepted: 1037 Total Submissions: 5886 Difficulty: Hard
Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/

public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        return help(words);
    }
    
    private List<List<Integer>> help(String[]words)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(words == null || words.length < 2) return result;
        
        Map<String, Integer> m = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++){
            m.put(words[i], i);
        }
        
        for(int i = 0; i < words.length; i++)
        {
            for(int j = 0; j <= words[i].length(); j++)// can equals to length as using substring
            {
                if(isPalin(words[i], j, words[i].length()-1) )
                {
                    String suffix = reverseString(words[i].substring(0, j));
                    // avoid duplicate i can't be euqals to itself
                    if(m.containsKey(suffix) && i != m.get(suffix)){
                        List<Integer> oneSol = new ArrayList<Integer>();
                        oneSol.add(i);
                        oneSol.add(m.get(suffix));
                        result.add(oneSol);
                    }
                }
                
                // to avoid duplicate, j must be great than 0
                if(j > 0 && isPalin(words[i], 0, j-1))
                {
                    String prefix = reverseString(words[i].substring(j));
                    
                    if(m.containsKey(prefix) && m.get(prefix) != i)
                    {
                        List<Integer> oneSol = new ArrayList<Integer>();
                        oneSol.add(m.get(prefix));
                        oneSol.add(i);
                        result.add(oneSol);
                    }
                }
            }
        
        }
        
        return result;
    }
    
    private boolean isPalin(String s, int i, int j)
    {
        for(; i < j; )
        {
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        
        return true;
    }
    
    private String reverseString(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }
}