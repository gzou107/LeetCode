/*
170. Two Sum III - Data structure design My Submissions Question
Total Accepted: 7878 Total Submissions: 32517 Difficulty: Easy
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/

public class TwoSum {
    private Map<Integer, Integer> count;
    private List<Integer> candidate;
    
    public TwoSum()
    {
        count = new HashMap<Integer, Integer>();
        candidate = new ArrayList<Integer>();
    }
    // Add the number to an internal data structure.
	public void add(int number) 
	{
	    if(!count.containsKey(number))
	    {
	        count.put(number, 1);
	        candidate.add(number);
	    }else
	    {
	        count.put(number, count.get(number) + 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) 
	{
	    for(int i : candidate)
	    {
	        int diff = value - i;
	        
	        if (diff == i && count.get(diff) > 1 || diff != i && count.containsKey(diff)) // make sure the diff != i in the second condition
	        {
	            return true;
	        }
	    }
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);