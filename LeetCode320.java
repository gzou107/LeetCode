/*
320. Generalized Abbreviation My Submissions Question
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/

public class Solution {
    public List<String> generateAbbreviations(String word) {
        return helper(word);
    }
    
    private List<String> helper(String word)
    {
        List<String> res = new ArrayList<String>();
        
        dfs0(res, new StringBuilder(), 0, 0, word.toCharArray());
        return res;
    }
    
    // as we uses the StringBuilder append, and we need keep on revering the previous change
    // when adding new change, and hence need mark and unmark the change and before entering
    // and leaving dfs
    private void dfs0(List<String> res, StringBuilder sb, int i, int number, char [] c)
    {
        int len = sb.length();
        if(i == c.length)
        {
            if(number > 0)
            {
                sb.append(String.valueOf(number));
            }
            res.add(sb.toString());
            sb.setLength(len);
            return;
        }
        
           dfs(res, sb, i + 1, number+1, c); // abbreviate it 
           
            // two cases here, either abbreviate c[i] or do not abbreviate c[i];
            if(number > 0)
            {
                sb.append(number);
            }
            dfs(res, sb.append(c[i]), i + 1, 0, c); // do not abbreviate
 
            sb.setLength(len);
    }
    
    private void dfs(List<String> res, StringBuilder sb, int i, int number, char [] c)
    {
        int len = sb.length();
        if(i == c.length)
        {
            if(number != 0)
            {
                sb.append(String.valueOf(number));
            }
            res.add(sb.toString());
        }else{
            
            // two cases here, either abbreviate c[i] or do not abbreviate c[i];
            int prelen = sb.length();
            if(number > 0){
                sb.append(number);
            }
            dfs(res, sb.append(c[i]), i + 1, 0, c); // do not abbreviate
            
            sb.setLength(prelen);
            dfs(res, sb, i + 1, number+1, c); // abbreviate it 
        }
        sb.setLength(len);
    }
    
    private void dfs2(List<String> res, StringBuilder sb, int i, int number, char [] c)
    {
        int len = sb.length();
        if(i == c.length)
        {
            if(number != 0)
            {
                sb.append(String.valueOf(number));
            }
            res.add(sb.toString());
            sb.setLength(len);
            return;
        }
            // two cases here, either abbreviate c[i] or do not abbreviate c[i];
            int prelen = sb.length();
            if(number > 0){
                sb.append(number);
            }
            dfs(res, sb.append(c[i]), i + 1, 0, c); // do not abbreviate
            
            sb.setLength(prelen);
            dfs(res, sb, i + 1, number+1, c); // abbreviate it 
        
            sb.setLength(len);
    }
}