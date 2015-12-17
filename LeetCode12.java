/*
Roman to Integer My Submissions Question
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public int romanToInt(String s) {
        if( s == null || s.length() == 0){
            return 0;
        }
        
       Map<Character, Integer> mapping = new HashMap<Character, Integer>();
       mapping.put('I',1);
       mapping.put('V',5);
       mapping.put('X',10);
       mapping.put('L',50);
       mapping.put('C',100);
       mapping.put('D',500);
       mapping.put('M',1000);
       // starting from least significant position
       int ans = mapping.get(s.charAt(s.length()-1));
       
       for(int i = s.length()-2; i>=0; i--){
           // direct translation of meaning of C, X, I
           if(mapping.get(s.charAt(i+1)) > mapping.get(s.charAt(i))){
               ans -= mapping.get(s.charAt(i));
           }else{
               ans += mapping.get(s.charAt(i));
           }
       }
       
       return ans;
    }
}