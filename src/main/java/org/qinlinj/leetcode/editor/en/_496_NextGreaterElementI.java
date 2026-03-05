package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [496] Next Greater Element I
public class _496_NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new _496_NextGreaterElementI().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.removeFirst();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.addFirst(nums2[i]);
        }
        int[] output = new int[nums1.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = map.get(nums1[i]);
        }

        return output;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// The next greater element of some element x in an array is the first greater
// element that is to the right of x in the same array. 
// 
//  You are given two distinct 0-indexed integer arrays nums1 and nums2, where 
// nums1 is a subset of nums2. 
// 
//  For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
//  and determine the next greater element of nums2[j] in nums2. If there is no 
// next greater element, then the answer for this query is -1. 
// 
//  Return an array ans of length nums1.length such that ans[i] is the next 
// greater element as described above. 
// 
//  
//  Example 1: 
// 
//  
// Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
// Output: [-1,3,-1]
// Explanation: The next greater element for each value of nums1 is as follows:
// - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so 
// the answer is -1.
// - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
// - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so 
// the answer is -1.
//  
// 
//  Example 2: 
// 
//  
// Input: nums1 = [2,4], nums2 = [1,2,3,4]
// Output: [3,-1]
// Explanation: The next greater element for each value of nums1 is as follows:
// - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
// - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so 
// the answer is -1.
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= nums1.length <= nums2.length <= 1000 
//  0 <= nums1[i], nums2[i] <= 10⁴ 
//  All integers in nums1 and nums2 are unique. 
//  All the integers of nums1 also appear in nums2. 
//  
// 
//  
// Follow up: Could you find an 
// O(nums1.length + nums2.length) solution?
// 
//  Related Topics Array Hash Table Stack Monotonic Stack 👍 9750 👎 1095
