/*
71. Simplify Path My Submissions Question
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

public class Solution {
    public String simplifyPath(String path) {
        return help(path);
    }
    
    public String help(String path)
    {
        if(path == null || path.length() <= 1) return path;
        
        String [] t = path.split("/");
        Stack<String> s = new Stack<>();
        
        for(String st : t)
        {
            switch(st)
            {
                case "":
                case ".":
                    break;
                case "..":
                    if(!s.isEmpty()){
                        s.pop();
                    }
                    break;
                default:
                    s.push(st);
                    break;
            }
        }
        
        StringBuilder res = new StringBuilder();
        if(s.isEmpty()){
            return "/";
        }
        
        /*
        while(!s.isEmpty())
        {
            res.insert(0, "/"+ s.pop());
        }
        */
        for(String st : s)
        {
            res.append("/"+ st);
        }
        return res.toString();
    }
}