/*
294. Flip Game II My Submissions Question
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/


public class Solution {
    public boolean canWin(String s) {
        return help2(s);
    }
    
    private boolean help1(String s)
    {
        if( s == null || s.length() < 2) return false;
        
        for(int i = 0; i < s.length() -1; i++ )
        {
            if(s.startsWith("++", i))
            {
                String nS = s.substring(0, i) + "--" + s.substring(i+2);
                
                if(!help1(nS)) // if we reach one losing position after one switch, we can win now
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean help2(String s)
    {
        if(s == null || s.length() < 2) return false;
        
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        return solve(s, m);
    }
    
    private boolean solve(String s, Map<String, Boolean> m)
    {
        if(m.containsKey(s)) 
        {
            return m.get(s);
        }
        
        for(int i = 0; i < s.length() - 1; i++)
        {
            if(s.startsWith("++", i))
            {
                String nS = s.substring(0, i) + "--" + s.substring(i+2);
                
                if(!solve(nS, m))
                {
                    m.put(s, true);
                    return true;
                }
            }
        }
        
        m.put(s, false);
        return false;
    }
}