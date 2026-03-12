package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [739] Daily Temperatures
public class _739_DailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new _739_DailyTemperatures().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stk = new LinkedList<>();
        int[] result = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]) {
               stk.pop();
            }
            result[i] = stk.isEmpty() ? 0 : stk.peek() - i;
            stk.push(i);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an array of integers temperatures represents the daily temperatures, 
// return an array answer such that answer[i] is the number of days you have to wait 
// after the iᵗʰ day to get a warmer temperature. If there is no future day for which 
// this is possible, keep answer[i] == 0 instead. 
// 
//  
//  Example 1: 
//  Input: temperatures = [73,74,75,71,69,72,76,73]
// Output: [1,1,4,2,1,1,0,0]
//  
//  Example 2: 
//  Input: temperatures = [30,40,50,60]
// Output: [1,1,1,0]
//  
//  Example 3: 
//  Input: temperatures = [30,60,90]
// Output: [1,1,0]
//  
//  
//  Constraints: 
// 
//  
//  1 <= temperatures.length <= 10⁵ 
//  30 <= temperatures[i] <= 100 
//  
// 
//  Related Topics Array Stack Monotonic Stack 👍 14596 👎 373
