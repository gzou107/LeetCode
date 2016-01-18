/*
295. Find Median from Data Stream My Submissions Question
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/

class MedianFinder {
     private PriorityQueue<Integer> minHeap; // minHeap, the top element is minimal, stores all the elements equals to median or bigger than mdeian
     private PriorityQueue<Integer> maxHeap; // maxHeap, the top element is maximum, stores the median or all the elements small than median
     
     private int size;
     
     public MedianFinder()
     {
         minHeap = new PriorityQueue<Integer>();
         maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
         size = 0;
     }
     
    // Adds a number into the data structure.
    public void addNum(int num) 
    {
         size += 1;
         maxHeap.offer(num);
         minHeap.offer(maxHeap.poll());
         
         if(maxHeap.size() < minHeap.size())
         {
             maxHeap.offer(minHeap.poll());
         }
    }

    // Returns the median of current data stream
    public double findMedian() 
    {
        if(size %2 == 1)
        {
            return maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek())/2.0;
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();