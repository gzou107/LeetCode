/*
Implement Queue using Stacks My Submissions Question
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

class MyQueue {
    private Stack<Integer> reader;
    private Stack<Integer> writer;
    public MyQueue(){
        reader = new Stack<Integer>();
        writer = new Stack<Integer>();
    }
    // Push element x to the back of queue.
    public void push(int x) {
        writer.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(!writer.isEmpty()){
            reader.push(writer.pop());
        }
        reader.pop();
        while(!reader.isEmpty()){
            writer.push(reader.pop());
        }
    }

    // Get the front element.
    public int peek() {
         while(!writer.isEmpty()){
            reader.push(writer.pop());
        }
        int top = reader.peek();
        while(!reader.isEmpty()){
            writer.push(reader.pop());
        }
        return top;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return writer.isEmpty();
    }
}