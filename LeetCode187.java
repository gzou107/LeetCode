/*
187. Repeated DNA Sequences My Submissions Question
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        //return help(s);
        return help1(s);
    }
    
    private List<String> help(String s)
    {
        List<String> res = new LinkedList<>();
        if(s == null || s.length() <= 0) return res;
        
        Set<String> m = new HashSet<>();
        Set<String> t = new HashSet<>();
        for(int i = 0; i <= s.length()-10; i++)
        {
            if(!m.contains(s.substring(i, i+10)))
            {
                m.add(s.substring(i, i+10));
            }else{
                t.add(s.substring(i, i+10));
            }
        }
        
        return new LinkedList<String>(t);
    }
    
    private List<String> help1(String s)
    {
        List<String> res = new LinkedList();
        if(s == null || s.length() <= 10) return res;
        
        Set<Integer> once = new HashSet<>();
        Set<Integer> twice = new HashSet<>();
        
        char[] m = new char[26];
        m['A'-'A']=0;
        m['C'-'A'] = 1;
        m['T' - 'A'] = 2;
        m['G' - 'A'] = 3;
        
        for(int i = 0; i <= s.length() - 10; i++)
        {
            int sig = 0;
            for(int j = i; j <i+10; j++)
            {
                sig <<= 2;
                sig |= m[s.charAt(j)-'A'];
            }
            
            if(!once.add(sig) && twice.add(sig)){
                res.add(s.substring(i, i+10));
            }
        }
        
        return res;
    }
}