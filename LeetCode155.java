/*
Min Stack My Submissions Question
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/

class MinStack {
    private Stack<Integer> s;
    private Stack<Integer> min;
    
    public MinStack(){
        s = new Stack<Integer>();
        min = new Stack<Integer>();
    }
    public void push(int x) {
        s.push(x);
        if(min.isEmpty() || x <= min.peek()){
            min.push(x);
        }
    }

    public void pop() {
        int top = s.pop();
        if(top == min.peek()){
            min.pop();
        }
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return min.peek();
    }
}