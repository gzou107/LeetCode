/*
17. Letter Combinations of a Phone Number My Submissions Question
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

public class Solution {
    public List<String> letterCombinations(String digits) {
        return help0(digits);
        //return help(digits);
    }
    
    private List<String> help0(String digits)
    {
        LinkedList<String> res = new LinkedList<>();
        if(digits == null || digits.length() == 0){
            return res;
        }
        
        String []mapping ={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        res.add("");
        for(int i = 0; i < digits.length(); i++)
        {
            int c = Integer.valueOf(digits.charAt(i));// Character.getNumericValue(digits.charAt(i));//
            
            while(res.peek().length() == i)
            {
                String s = res.remove();
                for(char t : mapping[c].toCharArray())
                {
                    res.add(s+t);
                }
            }
        }
        
        return res;
    }
    private List<String> help(String digits)
    {
        Map<Character, List<Character>> m = new HashMap<>();
        m.put('1', new LinkedList<Character>());
        m.put('2', new LinkedList<Character>(Arrays.asList('a','b','c')));
        m.put('3', new LinkedList<Character>(Arrays.asList('d','e','f')));
        m.put('4', new LinkedList<Character>(Arrays.asList('g','h','i')));
        m.put('5', new LinkedList<Character>(Arrays.asList('j','k','l')));
        m.put('6', new LinkedList<Character>(Arrays.asList('m','n','o')));
        m.put('7', new LinkedList<Character>(Arrays.asList('p','q','r','s')));
        m.put('8', new LinkedList<Character>(Arrays.asList('t','u','v')));
        m.put('9', new LinkedList<Character>(Arrays.asList('w','x','y','z')));
        m.put('0', new LinkedList<Character>(Arrays.asList('_')));
        
        List<String> res = new LinkedList<String>();
        if(digits == null || digits.length() == 0){
            return res;
        }
        dfs(digits, 0, m, new StringBuilder(), res);
        
        return res;
    }
    
    private void dfs(String digits, int pos, Map<Character,List<Character>> m, StringBuilder buffer, List<String> result)
    {
        if(pos == digits.length()){
            result.add(buffer.toString());
            return;
        }
        char digit = digits.charAt(pos);
        if(digit <'0' || digit >'9'){
            return;
        }
        
        for(int i = 0; i <m.get(digit).size(); i++)
        {
            int len = buffer.length();
            buffer.append(m.get(digit).get(i));
            dfs(digits, pos+1, m, buffer, result);
            buffer.setLength(len);
        }
    }
}