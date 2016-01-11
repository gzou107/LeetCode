/*
251. Flatten 2D Vector My Submissions Question
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
*/

public class Vector2D {
    
        private int listIndex;
        private int innerIndex;
        private List<List<Integer>> l;
    
    public Vector2D(List<List<Integer>> vec2d) 
    {
       l = vec2d;
       listIndex  = 0;
       innerIndex = 0;
    }

    public int next() 
    {
        return l.get(listIndex).get(innerIndex++);
    }

    public boolean hasNext()
    {
        while(listIndex < l.size())
        {
            if(innerIndex < l.get(listIndex).size())
            {
                return true;
            }else{
                listIndex++;
                innerIndex = 0;
            }
        }
        
        return false;
    }
    /*
    Deque<Iterator<Integer>> its;
    
    public Vector2D(List<List<Integer>> vec2d) 
    {
        its = new LinkedList<Iterator<Integer>>();
        for(int i = 0; i < vec2d.size(); i++)
        {
            if(vec2d.get(i).size() > 0)
            {
                its.addLast(vec2d.get(i).iterator());
            }
        }
    }

    public int next() 
    {
        int top = its.peekFirst().next();
        if(!its.peekFirst().hasNext())
        {
            its.removeFirst();
        }
        return top;
    }

    public boolean hasNext()
    {
        return !its.isEmpty();
    }*/
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */