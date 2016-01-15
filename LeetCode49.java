/*
49. Group Anagrams My Submissions Question
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
*/

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        return helper(strs);
    }
    
    private List<List<String>> helper(String [] strs)
    {
        List<List<String>> res = new LinkedList<List<String>>();
        if(strs == null || strs.length == 0) return res;
      
        Map<String, List<String>> m = new HashMap<String, List<String>>();
        for(int i = 0; i < strs.length; i++)
        {
            String sig = getSig(strs[i]);
            if(m.get(sig) == null){
                m.put(sig, new ArrayList<String>());
            }
            m.get(sig).add(strs[i]);
        }
        
        for(List<String> s : m.values())
        {
            Collections.sort(s);
            res.add(s);
        }
        
        return res;
    }
    
    private String getSig(String s)
    {
        char [] sc = s.toCharArray();
        Arrays.sort(sc);
        return new String(sc);
    }
}