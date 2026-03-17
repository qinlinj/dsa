package org.qinlinj.dsa.data_structure_basic.monotonic_queue;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [239] Sliding Window Maximum
public class _239_SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new _239_SlidingWindowMaximum().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            if (deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }

            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// You are given an array of integers nums, there is a sliding window of size k 
// which is moving from the very left of the array to the very right. You can only 
// see the k numbers in the window. Each time the sliding window moves right by one 
// position. 
// 
//  Return the max sliding window. 
// 
//  
//  Example 1: 
// 
//  
// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation: 
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
//  
// 
//  Example 2: 
// 
//  
// Input: nums = [1], k = 1
// Output: [1]
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= nums.length <= 10⁵ 
//  -10⁴ <= nums[i] <= 10⁴ 
//  1 <= k <= nums.length 
//  
// 
//  Related Topics Array Queue Sliding Window Heap (Priority Queue) Monotonic 
// Queue 👍 20327 👎 837
