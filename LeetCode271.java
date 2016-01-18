/*
271. Encode and Decode Strings My Submissions Question
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // the idea is to encode every string with stringLength, followed by a marker and then the original string
        // to decode, we first search the marker, and then parse the string length, and then get the original string
        StringBuilder sb = new StringBuilder();
        if(strs == null) return sb.toString();
        
        for(int i = 0; i < strs.size(); i++)
        {
            if(strs.get(i) == null){
                sb.append(0 + "#");
            }else{
                sb.append(strs.get(i).length() + "#" + strs.get(i));
            }
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new LinkedList<>();
        if(s == null || s.length() == 0) return res;
        
        int start = 0;
        while(start < s.length()){
            int end = s.indexOf('#', start);
            int len = Integer.valueOf(s.substring(start, end)); // substring end does not include end
            
            if(len > 0){
                res.add(s.substring(end+1, end + 1 + len));
            }else{
                res.add("");
            }
            start = end + len + 1;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));