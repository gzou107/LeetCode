/*
281. Zigzag Iterator My Submissions Question
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/
public class ZigzagIterator {
    /*
    private Queue<Iterator<Integer>> q;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) 
    {
        q = new ArrayDeque<Iterator<Integer>>(2);
        Iterator<Integer> it1 = v1.iterator();
        if(it1.hasNext()){
            q.add(it1);
        }
        
        Iterator<Integer> it2 = v2.iterator();
        if(it2.hasNext()){
            q.add(it2);
        }
    }

    public int next() {
        Iterator<Integer> top = q.remove();
        int result = top.next();
        if(top.hasNext()){
            q.add(top);
        }
        return result;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }*/
    private Iterator<Integer> it1;
    private Iterator<Integer> it2;
    private Iterator<Integer> temp;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) 
    {
         it1 = v2.iterator();
         it2 = v1.iterator();
    }

    public int next() {
        // always does the switch before reading
        if(it2.hasNext()){
            Iterator<Integer> temp = it2;
            it2 = it1;
            it1 = temp;
        }
        return it1.next();
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */