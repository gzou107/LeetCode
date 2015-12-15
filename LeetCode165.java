/*
Compare Version Numbers My Submissions Question
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

public class Solution {
    public int compareVersion(String version1, String version2) {
        // this is a very bad design to represent version using String, ideally Version should be a class, which implements comparable, and it should have a constructor
        // which takes the string as a parameter.
        
        // how to compare 1.01(consider it illegal now) and 1.1
        // how to compare 1.1.2 and 1.1.3, 1.1.2 < 1.1.3
        // how to compare 1.2 and 1.12, 1.2 > 1.12
        int i = 0;
        String [] v1 = version1.split("\\.");// we can do either \\. or pattern.quote(".") to escape
        String [] v2 = version2.split("\\.");
        for(; i < Math.min(v1.length, v2.length); i++){
            long v1L = Long.parseLong(v1[i]);// this can be dangerous, as it may overflow the long range
            long v2L = Long.parseLong(v2[i]);
            
            if(v1L < v2L){
                return -1;
            }else if(v1L > v2L){
                return 1;
            }
        }
        
        if( v1.length == v2.length)
        {
            return 0;
        }else if(v1.length < v2.length)
        {
            // consider 1.2.0 and 1.2, they should be equal
            for(int j = i; j < v2.length; j++)
            {
                if(Long.parseLong(v2[j]) > 0L)
                {
                    return -1;
                }
            }
            return 0;
        }else
        {
            for(int j = i; j < v1.length; j++)
            {
                if(Long.parseLong(v1[j]) > 0L)
                {
                    return 1;
                }
            }
            return 0;
        }
    }       
 
}