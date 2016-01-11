/*
293. Flip Game My Submissions Question
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
*/

public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        // return helper(s);
       // return helper1(s);
    }
    
    private List<String> helper(String s)
    {
        List<String> res = new LinkedList<String>();
        
        char [] t = s.toCharArray();
        for(int i = 0; i < t.length -1; i++)
        {
            if(t[i] == '+' && t[i+1] == '+')
            {
                t[i] = t[i+1] = '-';
                res.add(String.valueOf(t));
                t[i] = t[i+1] = '+';
            }
        }
        
        return res;
    }
    
    private List<String> helper1(String s)
    {
        List<String> res = new LinkedList<String>();
        
        for(int i = 0; i < s.length() -1; i++)
        {
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+')
            {
                String newState = s.substring(0, i) + "--" + s.substring(i+2, s.length());
                res.add(newState);
            }
        }
        
        return res;
    }
}