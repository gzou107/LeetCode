/*
291. Word Pattern II My Submissions Question
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        //return help(pattern, str);
        return help0(pattern, str);
    }
    
 public boolean help0(String pattern, String str) 
 {
    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();

    return isMatch(str, 0, pattern, 0, map, set);
  }

  boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set)
  {
    // base case
    if (i == str.length() && j == pat.length()) return true;
    if (i == str.length() || j == pat.length()) return false;

    // get current pattern character
    char c = pat.charAt(j);

    // if the pattern character exists
    if (map.containsKey(c)) 
    {
      String s = map.get(c);

      // then check if we can use it to match str[i...i+s.length()]
      if (!str.startsWith(s, i)) {
        return false;
      }

      // if it can match, great, continue to match the rest
      return isMatch(str, i + s.length(), pat, j + 1, map, set);
    }

    // pattern character does not exist in the map
    for (int k = i+1; k <= str.length(); k++) 
    {
      String p = str.substring(i, k);

      if (set.contains(p)) {
        continue;
      }

      // create or update it
      map.put(c, p);
      set.add(p);

      // continue to match the rest
      if (isMatch(str, k, pat, j + 1, map, set))
      {
        return true;
      }

      // backtracking
      map.remove(c);
      set.remove(p);
    }

    // we've tried our best but still no luck
    return false;
  }
  
    private boolean help1(String pattern, String str)
    {
        if(pattern == null) return str == null;
        
        if(str == null) return false;
        
        int len0 = pattern.length();
        int len1 = str.length();
        
        if(len1 < len0) return false;
        
        Set<String> s = new HashSet<String>();
        Map<Character, String> m = new HashMap<Character, String>();
        List<Boolean> res = new ArrayList<Boolean>();
        res.add(false);
        
        dfs1(pattern, str, 0, 0, res, s, m);
        return res.get(0);
    }
    
    private void dfs1(String pattern, String str, int i, int j, List<Boolean> res, Set<String> s, Map<Character, String> m)
    {
        if(res.get(0)){
            return;
        }
        
        if(i == pattern.length() && j == str.length()){
            res.set(0, true);
            return;
        }
        
        if(i == pattern.length() || j == str.length()){
            return;
        }
        
        char c = pattern.charAt(i);
        
        for(int k = j + 1; k <= str.length(); k++)
        {
            String value = str.substring(j, k);
            
            // if this i --> value mapping does not exist before
            if(!s.contains(value) && !m.containsKey(c))
            {
                s.add(value);
                m.put(c, value);
                dfs1(pattern, str, i+1, k, res, s, m);
                s.remove(value);
                m.remove(c);
            }else if(m.containsKey(c) && m.get(c).equals(value))
            {
                dfs1(pattern, str, i+1, k, res, s, m);
            }
        }
    }
}