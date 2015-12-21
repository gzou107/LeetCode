/*
284. Peeking Iterator My Submissions Question
Total Accepted: 11133 Total Submissions: 34777 Difficulty: Medium
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    
    private final Iterator<Integer> it;
    private boolean hasPeeked;
    private Integer val;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	     it = iterator;
	     hasPeeked = false;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(!hasPeeked){
            val = it.next();
            hasPeeked = true;
            return val;
        }
        return val;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	     if(hasPeeked){
	         hasPeeked = false;
	         return val;
	     }
	     val = it.next();
	     hasPeeked = false;
	     return val;
	}

	@Override
	public boolean hasNext() {
	    return hasPeeked || it.hasNext();
	}
}

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    //private Iterator<Integer> it;
    private Deque<Integer> q;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    q = new ArrayDeque<Integer>();
	    while(iterator.hasNext()){
	        q.add(iterator.next());
	    }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        
        if(!q.isEmpty()){
            return q.peek();
        }
        return q.peek();
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	   if(!q.isEmpty()){
	       return q.remove();
	   }
	   return q.remove();
	}

	@Override
	public boolean hasNext() {
	    return !q.isEmpty();
	}
}