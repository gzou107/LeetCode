/*
273. Integer to English Words My Submissions Question
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
*/
public class Solution {
    private static Map<Integer, String> m = new HashMap<Integer,String>();
    static {
        m.put(0,"Zero");
        m.put(1,"One");
        m.put(2,"Two");
        m.put(3,"Three");
        m.put(4,"Four");
        m.put(5,"Five");
        m.put(6,"Six");
        m.put(7,"Seven");
        m.put(8,"Eight");
        m.put(9,"Nine");
        m.put(10, "Ten"); 
        m.put(11,"Eleven");
        m.put(12,"Twelve");
        m.put(13,"Thirteen");
        m.put(14,"Fourteen");
        m.put(15,"Fifteen");
        m.put(16,"Sixteen");
        m.put(17,"Seventeen");
        m.put(18,"Eighteen");
        m.put(19,"Nineteen");
        m.put(20,"Twenty");
        m.put(30,"Thirty");
        m.put(40,"Forty");
        m.put(50,"Fifty");
        m.put(60,"Sixty");
        m.put(70,"Seventy");
        m.put(80,"Eighty");
        m.put(90,"Ninety");
        m.put(100,"Hundred");
    };
    
    public String numberToWords(int num) {
        if(num < 0) return "error";
        if(num == 0) return "Zero";
        
        
        HashMap<Integer,String> affix = new HashMap<Integer,String>();
        affix.put(0,"");
        affix.put(1," Thousand");
        affix.put(2," Million");
        affix.put(3," Billion");
        
        LinkedList<String> re = new LinkedList<String>();
        int count = 0;
        while(num > 0){
            int curr = num % 1000;
            String part =handle1To999(curr);
            
            if(!part.equals("")){
                part += affix.get(count);
                re.addFirst(part);
            }
            
            count++;
            num /= 1000;
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < re.size()-1; i++){
            ans.append(re.get(i)+" ");
        }
        ans.append(re.get(re.size()-1));
        return ans.toString();
    }
    
    private String handle1To999(int num){
        StringBuilder ans = new StringBuilder();
        if(num >= 100){
            ans.append(m.get(num/100)).append(" ").append(m.get(100));
            num = num%100;
        }
        
        if(num >=20){
            ans.append((ans.length()==0?"":" ") + m.get((num/10)*10) );
            num = num%10;
        }
        
        if(num >0){
                ans.append((ans.length()==0?"":" ") + m.get(num));
        }
        
        String res = ans.toString();
        return res;
    }
}

// Minor improvement
public class Solution {
    private static Map<Integer, String> m = new HashMap<Integer,String>();
    static {
        m.put(0,"Zero");
        m.put(1,"One");
        m.put(2,"Two");
        m.put(3,"Three");
        m.put(4,"Four");
        m.put(5,"Five");
        m.put(6,"Six");
        m.put(7,"Seven");
        m.put(8,"Eight");
        m.put(9,"Nine");
        m.put(10, "Ten"); 
        m.put(11,"Eleven");
        m.put(12,"Twelve");
        m.put(13,"Thirteen");
        m.put(14,"Fourteen");
        m.put(15,"Fifteen");
        m.put(16,"Sixteen");
        m.put(17,"Seventeen");
        m.put(18,"Eighteen");
        m.put(19,"Nineteen");
        m.put(20,"Twenty");
        m.put(30,"Thirty");
        m.put(40,"Forty");
        m.put(50,"Fifty");
        m.put(60,"Sixty");
        m.put(70,"Seventy");
        m.put(80,"Eighty");
        m.put(90,"Ninety");
        m.put(100,"Hundred");
    };
    
    public String numberToWords(int num) {
        if(num < 0) return "error";
        if(num == 0) return "Zero";
        
        
        HashMap<Integer,String> affix = new HashMap<Integer,String>();
        affix.put(0,"");
        affix.put(1," Thousand");
        affix.put(2," Million");
        affix.put(3," Billion");
        
        String ans = "";
        int count = 0;
        while(num > 0){
            int curr = num % 1000;
            String part =handle1To999(curr);
            
            if(!part.equals("")){
                part += affix.get(count);
                ans = part + (ans.equals("")?"" :" " + ans);
            }
            
            count++;
            num /= 1000;
        }
        return ans;
    }
    
    private String handle1To999(int num){
        StringBuilder ans = new StringBuilder();
        if(num >= 100){
            ans.append(m.get(num/100)).append(" ").append(m.get(100));
            num = num%100;
        }
        
        if(num >=20){
            ans.append((ans.length()==0?"":" ") + m.get((num/10)*10) );
            num = num%10;
        }
        
        if(num >0){
                ans.append((ans.length()==0?"":" ") + m.get(num));
        }
        
        String res = ans.toString();
        return res;
    }
}