package org.qinlinj.dsa.data_structure_basic.monotonic_stack;
import java.util.*;

// [581] Shortest Unsorted Continuous Subarray
public class R_581_ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        Solution solution = new R_581_ShortestUnsortedContinuousSubarray().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        ArrayDeque<Integer> minStk = new ArrayDeque<>();
        ArrayDeque<Integer> maxStk = new ArrayDeque<>();
        int left = nums.length;
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            while (!minStk.isEmpty() && nums[minStk.peek()] > nums[i]) {
                left = Math.min(left, minStk.pop());
            }
            minStk.push(i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!maxStk.isEmpty() && nums[maxStk.peek()] < nums[i]) {
                right = Math.max(right, maxStk.pop());
            }
            maxStk.push(i);
        }

        return (right - left >= 0) ? right - left + 1 : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an integer array nums, you need to find one continuous subarray such that 
// if you only sort this subarray in non-decreasing order, then the whole array 
// will be sorted in non-decreasing order. 
// 
//  Return the shortest such subarray and output its length. 
// 
//  
//  Example 1: 
// 
//  
// Input: nums = [2,6,4,8,10,9,15]
// Output: 5
// Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the 
// whole array sorted in ascending order.
//  
// 
//  Example 2: 
// 
//  
// Input: nums = [1,2,3,4]
// Output: 0
//  
// 
//  Example 3: 
// 
//  
// Input: nums = [1]
// Output: 0
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= nums.length <= 10⁴ 
//  -10⁵ <= nums[i] <= 10⁵ 
//  
// 
//  
// Follow up: Can you solve it in 
// O(n) time complexity?
// 
//  Related Topics Array Two Pointers Stack Greedy Sorting Monotonic Stack 👍 8017 
// 👎 275
