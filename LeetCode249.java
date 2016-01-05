/*
249. Group Shifted Strings My Submissions Question
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
*/

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        return helper0(strings);
       // return helper(strings);
    }
    
    private List<List<String>> helper0(String[] strings)
    {
        List<List<String>> res = new ArrayList<List<String>>();
        
        Map<String, List<String>> mapper = new HashMap<String,List<String>>();
        for(String s : strings)
        {
            String sig = encode(s);
            if(!mapper.containsKey(sig))
            {
                mapper.put(sig, new ArrayList<String>());
            }
            mapper.get(sig).add(s);
        }
        
        for(String sig : mapper.keySet())
        {
            Collections.sort(mapper.get(sig));
            res.add(mapper.get(sig));
        }
        
        return res;
    }
    
    private String encode(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < s.length(); i++)
        {
            int diff = (s.charAt(i) - s.charAt(i-1) + 26)%26;
            sb.append(diff+"-");
        }
        return sb.toString();
    }
    private List<List<String>> helper(String[] strings)
    {
        List<List<String>> res = new ArrayList<List<String>>();
        if(strings == null || strings.length == 0){
            return res;
        }
        
        Map<Integer, List<String>> counter = new HashMap<Integer, List<String>>();
        for(int i = 0; i < strings.length; i++)
        {
            if(!counter.containsKey(strings[i].length()))
            {
                counter.put(strings[i].length(), new ArrayList<String>());
            }
            counter.get(strings[i].length()).add(strings[i]);
        }
        
        // now create the signature
        Map<String, List<String>> mapper = new HashMap<String, List<String>>();
        for(Integer num : counter.keySet())
        {
            if(num == 1){
                Collections.sort(counter.get(num));
                res.add(counter.get(num));
            }else{
                for(String s : counter.get(num))
                {
                    String sig = getSignature(s);
                    if(!mapper.containsKey(sig)){
                        mapper.put(sig, new ArrayList<String>());
                    }
                    mapper.get(sig).add(s);
                }
                

            }
        }
        
      for(String sig : mapper.keySet())
      {
           Collections.sort(mapper.get(sig));
           res.add(mapper.get(sig));
      }
        
      return res;
    }
    
    private String getSignature(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < s.length(); i++){
            int diff = s.charAt(i) - s.charAt(i-1);
            diff = diff < 0 ? diff + 26 : diff;
            sb.append(String.valueOf(diff) + "-");
        }
        
        return sb.toString();
    }
}