package org.qinlinj.dsa.data_structure_basic.monotonic_queue;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [1438] Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
public class _1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        Solution solution = new _1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int[] maxQ = new int[n];
        int[] minQ = new int[n];
        int maxHead = 0, maxTail = 0;
        int minHead = 0, minTail = 0;
        int left = 0, res = 0;

        for (int right = 0; right < n; right++) {
            while (maxTail > maxHead && nums[maxQ[maxTail - 1]] < nums[right]) maxTail--;
            maxQ[maxTail++] = right;

            while (minTail > minHead && nums[minQ[minTail - 1]] > nums[right]) minTail--;
            minQ[minTail++] = right;

            while (nums[maxQ[maxHead]] - nums[minQ[minHead]] > limit) {
                left++;
                if (maxQ[maxHead] < left) maxHead++;
                if (minQ[minHead] < left) minHead++;
            }

            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int longestSubarray1(int[] nums, int limit) {
        MonotonicQueue queue = new MonotonicQueue();
        int left = 0, right = 0;
        int n = nums.length;
        int windowSize = 0;
        while (right < n) {
            queue.push(nums[right]);
            right++;
            while (queue.getMax() - queue.getMin() > limit) {
                left++;
                queue.pop();
            }
            windowSize = Math.max(windowSize, right - left);
        }

        return windowSize;
    }

    static class MonotonicQueue {
        LinkedList<Integer> maxQ;
        LinkedList<Integer> minQ;
        LinkedList<Integer> q;

        public MonotonicQueue() {
            maxQ = new LinkedList<>();
            minQ = new LinkedList<>();
            q = new LinkedList<>();
        }

        public int pop() {
            int deletedItem = q.removeFirst();
            if (minQ.getFirst() == deletedItem) {
                minQ.removeFirst();
            }
            if (maxQ.getFirst() == deletedItem) {
                maxQ.removeFirst();
            }
            return deletedItem;
        }

        public void push(int i) {
            while (!minQ.isEmpty() && minQ.getLast() > i) {
                minQ.removeLast();
            }
            minQ.addLast(i);
            while (!maxQ.isEmpty() && maxQ.getLast() < i) {
                maxQ.removeLast();
            }
            maxQ.addLast(i);
            q.addLast(i);
        }

        public int getMax() {
            return maxQ.getFirst();
        }

        public int getMin() {
            return minQ.getFirst();
        }

        public int size() {
            return q.size();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an array of integers nums and an integer limit, return the size of the 
// longest non-empty subarray such that the absolute difference between any two 
// elements of this subarray is less than or equal to limit. 
// 
//  
//  Example 1: 
// 
//  
// Input: nums = [8,2,4,7], limit = 4
// Output: 2 
// Explanation: All subarrays are: 
// [8] with maximum absolute diff |8-8| = 0 <= 4.
// [8,2] with maximum absolute diff |8-2| = 6 > 4. 
// [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
// [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
// [2] with maximum absolute diff |2-2| = 0 <= 4.
// [2,4] with maximum absolute diff |2-4| = 2 <= 4.
// [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
// [4] with maximum absolute diff |4-4| = 0 <= 4.
// [4,7] with maximum absolute diff |4-7| = 3 <= 4.
// [7] with maximum absolute diff |7-7| = 0 <= 4. 
// Therefore, the size of the longest subarray is 2.
//  
// 
//  Example 2: 
// 
//  
// Input: nums = [10,1,2,4,7,2], limit = 5
// Output: 4 
// Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute 
// diff is |2-7| = 5 <= 5.
//  
// 
//  Example 3: 
// 
//  
// Input: nums = [4,2,2,2,4,4,2,2], limit = 0
// Output: 3
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= nums.length <= 10⁵ 
//  1 <= nums[i] <= 10⁹ 
//  0 <= limit <= 10⁹ 
//  
// 
//  Related Topics Array Queue Sliding Window Heap (Priority Queue) Ordered Set 
// Monotonic Queue 👍 4502 👎 226
