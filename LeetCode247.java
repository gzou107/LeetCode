/*
247. Strobogrammatic Number II My Submissions Question
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

public class Solution {
    public List<String> findStrobogrammatic(int n) {
       // return helper0(n);
        return helper2(n,n);
       // return helper(n);
    }
    
    
    
    private List<String> helper0(int n)
    {
        List<String> zero = Arrays.asList("");
        List<String> one = Arrays.asList("0","1", "8");
        List<String> r = zero;
        if(n % 2 == 1)
        {
            r = one;
        }
        
        for(int i = (n%2) + 2; i <=n; i += 2)
        {
            List<String> temp = new ArrayList<String>();
            for(String s : r)
            {
                if(i != n)
                {
                    temp.add("0" + s + "0");
                }
                
                temp.add("1" + s + "1");
                temp.add("6" + s + "9");
                temp.add("8" + s + "8");
                temp.add("9" + s + "6");
            }
           r = temp; 
        }
        
        return r;
    }
    
    private List<String> helper2(int n, int m)
    {
        if(n == 0) return Arrays.asList("");
        if(n == 1) return Arrays.asList("0", "1", "8");
        
        List<String> temp = helper2(n-2, m);
        List<String> res = new ArrayList<String>();
        for(String t : temp)
        {
            if(m != n)
            {
                res.add("0" + t + "0");
            }
            
            res.add("1" + t + "1");
            res.add("6" + t + "9");
            res.add("8" + t + "8");
            res.add("9" + t + "6");
        }
        
        return res;
    }
    private List<String> helper(int n)
    {
        List<String> ans = new ArrayList<String>();
        if(n<=0) return ans;
        
        String candidate = "";
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        m.put(0,0);
        m.put(1,1);
        m.put(6,9);
        m.put(9,6);
        m.put(8,8);
        int [] sys = {0,1,8};
        
        if(n%2 == 1)
        {
            for(Integer i :sys)
            {
                dfs(String.valueOf(i), n, ans, m);
            }
        }else
        {
            dfs(candidate, n, ans, m);
        }
        
        return ans;
    }
    
    private void dfs(String candidate, int n, List<String>ans, Map<Integer, Integer> m)
    {
        if(n > 1 && candidate.length() == n && candidate.charAt(0) !='0')
        {
            ans.add(candidate);
            return;
        }
        
        if( n == 1 && (candidate.equals("0") || candidate.equals("1") || candidate.equals("8")))
        {
            ans.add(candidate);
            return;
        }
        
        if(candidate.length() >= n)
        {
            return;
        }
        
        for(Integer i : m.keySet())
        {
            candidate = String.valueOf(i) + candidate + String.valueOf(m.get(i));
            dfs(candidate, n, ans, m);
            candidate = candidate.substring(1, candidate.length()-1);
        }
    }
}