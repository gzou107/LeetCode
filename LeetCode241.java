/*
241. Different Ways to Add Parentheses My Submissions Question
Total Accepted: 13790 Total Submissions: 44522 Difficulty: Medium
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        
        //return helper1(input);
        
        return helper2(input, 0, input.length());
    }
    
    public List<Integer> helper1(String input){
        
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c == '+' || c == '*' || c == '-'){
                List<Integer> left = helper1(input.substring(0,i));
                List<Integer> right = helper1(input.substring(i+1, input.length()));
                
                for(int j = 0; j< left.size(); j++)
                {
                    for(int k = 0; k <right.size(); k++){
                        int l = left.get(j);
                        int r = right.get(k);
                        
                        if(c == '+'){
                            ans.add(l + r);
                        }else if( c == '-'){
                            ans.add(l-r);
                        }else if( c == '*'){
                            ans.add(l*r);
                        }
                    }
                }
            }
        }
        if(ans.size() == 0){
            ans.add(Integer.valueOf(input));
        }
        return ans;
    }
      
    Map<String,List<Integer>>cache = new HashMap<String, List<Integer>>();
    
    String getSig(int start, int end){
        return String.valueOf(start) + "-" + String.valueOf(end);
    }
    
    public List<Integer> helper2(String input, int start, int end){
        String key = getSig(start, end);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        
        // now do the calculation
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = start; i < end; i++){
            char c = input.charAt(i);
            if(c == '+' || c == '*' || c == '-'){
                List<Integer> left = helper2(input, start,i);
                List<Integer> right = helper2(input, i+1, end);
                
                for(int j = 0; j< left.size(); j++)
                {
                    for(int k = 0; k <right.size(); k++){
                        int l = left.get(j);
                        int r = right.get(k);
                        
                        if(c == '+'){
                            ans.add(l + r);
                        }else if( c == '-'){
                            ans.add(l-r);
                        }else if( c == '*'){
                            ans.add(l*r);
                        }
                    }
                }
            }
        }
        
        if(ans.size() == 0){
            ans.add(Integer.valueOf(input.substring(start, end)));
        }
        
        cache.put(key, ans);
        return ans;
    }
}