package org.qinlinj.dsa.data_structure_basic.monotonic_queue;
import org.qinlinj.leetcode.editor.common.*;
// [862] Shortest Subarray with Sum at Least K
public class _862_ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        Solution solution = new _862_ShortestSubarrayWithSumAtLeastK().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        MonotonicQueue mq = new MonotonicQueue(n + 1);
        int minLen = n + 1;

        for (int i = 0; i < preSum.length; i++) {
            while (!mq.isEmpty() && preSum[i] - preSum[mq.peekFirst()] >= (long)k) {
                minLen = Math.min(minLen, i - mq.pollFirst());
            }

            while (!mq.isEmpty() && preSum[i] <= preSum[mq.peekLast()]) {
                mq.pollLast();
            }

            mq.offerLast(i);
        }

        return minLen > n ? -1 : minLen;
    }

    static class MonotonicQueue {
        private int[] q;
        private int head;
        private int tail;

        public MonotonicQueue(int capacity) {
            q = new int[capacity];
            head = 0;
            tail = 0;
        }

        public void offerLast(int index) {
            q[tail++] = index;
        }

        public int pollFirst() {
            return q[head++];
        }

        public int pollLast() {
            return q[--tail];
        }

        public int peekFirst() {
            return q[head];
        }

        public int peekLast() {
            return q[tail - 1];
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public int size() {
            return tail - head;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an integer array nums and an integer k, return the length of the shortest 
// non-empty subarray of nums with a sum of at least k. If there is no such 
// subarray, return -1. 
// 
//  A subarray is a contiguous part of an array. 
// 
//  
//  Example 1: 
//  Input: nums = [1], k = 1
// Output: 1
//  
//  Example 2: 
//  Input: nums = [1,2], k = 4
// Output: -1
//  
//  Example 3: 
//  Input: nums = [2,-1,2], k = 3
// Output: 3
//  
//  
//  Constraints: 
// 
//  
//  1 <= nums.length <= 10⁵ 
//  -10⁵ <= nums[i] <= 10⁵ 
//  1 <= k <= 10⁹ 
//  
// 
//  Related Topics Array Binary Search Queue Sliding Window Heap (Priority Queue) 
// Prefix Sum Monotonic Queue 👍 5174 👎 144
