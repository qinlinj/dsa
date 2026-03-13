package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [84] Largest Rectangle in Histogram
public class _84_LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new _84_LargestRectangleInHistogram().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] data = new int[heights.length + 2];
        System.arraycopy(heights, 0, data, 1, data.length - 2);
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i < data.length; i++) {
            while (!stk.isEmpty() && data[i] < data[stk.peek()]) {
                int height = data[stk.pop()];
                int width = i - stk.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stk.push(i);
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an array of integers heights representing the histogram's bar height 
// where the width of each bar is 1, return the area of the largest rectangle in the 
// histogram. 
// 
//  
//  Example 1: 
//  
//  
// Input: heights = [2,1,5,6,2,3]
// Output: 10
// Explanation: The above is a histogram where width of each bar is 1.
// The largest rectangle is shown in the red area, which has an area = 10 units.
//  
// 
//  Example 2: 
//  
//  
// Input: heights = [2,4]
// Output: 4
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= heights.length <= 10⁵ 
//  0 <= heights[i] <= 10⁴ 
//  
// 
//  Related Topics Array Stack Monotonic Stack 👍 19514 👎 383
