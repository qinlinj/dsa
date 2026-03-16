package org.qinlinj.dsa.data_structure_basic.stack_and_queue;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [155] Min Stack
public class _155_MinStack {
    public static void main(String[] args) {
        MinStack solution = new _155_MinStack().new MinStack();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {
    ArrayDeque<Long> minStack = new ArrayDeque<>();
    long min;

    public MinStack() {
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            min = val;
            minStack.push(0L);
        } else {
            long diff = val - min;
            if (diff < 0) {
                min = val;
            }
            minStack.push(diff);
        }
    }

    public void pop() {
        if (minStack.isEmpty()) {
            return;
        }
        long diff = minStack.pop();
        if (diff < 0) {
            min = min - diff;
        }
    }

    public int top() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException();
        }
        long diff = minStack.peek();
        if (diff < 0) {
            return (int)min;
        } else {
            return (int)(min + diff);
        }    
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException();
        }
        return (int)(min);
    }
}

class MinStack1 {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    ArrayDeque<Integer> minStack = new ArrayDeque<>();

    public MinStack1() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }
    
    public int top() {
        if (stack.isEmpty()){
            return -1;
        }
        return stack.peek();
    }
    
    public int getMin() {
        if (minStack.isEmpty()){
            return -1;
        }
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}

// Design a stack that supports push, pop, top, and retrieving the minimum element 
// in constant time. 
// 
//  Implement the MinStack class: 
// 
//  
//  MinStack() initializes the stack object. 
//  void push(int val) pushes the element val onto the stack. 
//  void pop() removes the element on the top of the stack. 
//  int top() gets the top element of the stack. 
//  int getMin() retrieves the minimum element in the stack. 
//  
// 
//  You must implement a solution with O(1) time complexity for each function. 
// 
//  
//  Example 1: 
// 
//  
// Input
// ["MinStack","push","push","push","getMin","pop","top","getMin"]
// [[],[-2],[0],[-3],[],[],[],[]]
// 
// Output
// [null,null,null,null,-3,null,0,-2]
// 
// Explanation
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin(); // return -3
// minStack.pop();
// minStack.top();    // return 0
// minStack.getMin(); // return -2
//  
// 
//  
//  Constraints: 
// 
//  
//  -2³¹ <= val <= 2³¹ - 1 
//  Methods pop, top and getMin operations will always be called on non-empty 
// stacks. 
//  At most 3 * 10⁴ calls will be made to push, pop, top, and getMin. 
//  
// 
//  Related Topics Stack Design 👍 15916 👎 991
