/*
277. Find the Celebrity My Submissions Question
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
*/

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if(n <= 1) return -1;
        
        //return helper0(n);
        return helper1(n);
    }
    
    private int helper0(int n)
    {
      if(n <= 1) return -1;
      
      int candidate = 0;
      for(int i = 1; i < n; i++)
      {
          if(!knows(i, candidate))
          {
              candidate = i;
          }
      }
      
      for(int i = 0; i < n; i++)
      {
          if(i != candidate && (!knows(i, candidate) || knows(candidate, i)))
          {
              return -1;
          }
      }
      
      return candidate;
    }
    
    private int helper1(int n)
    {
      Stack<Integer> s = new Stack<Integer>();
      for(int i = 0; i < n; i++)
      {
          s.push(i);
      }
      
      while(s.size() > 1)
      {
          int a = s.pop();
          int b = s.pop();
          
          if(knows(a,b)){
              s.push(b);
          }else{
              s.push(a);
          }
      }
      
      int res = s.pop();
      for(int i = 0; i < n; i++)
      {
          if( i != res && (!knows(i, res) || knows(res, i)))
          {
              return -1;
          }
      }
      
      return res;
    }
}