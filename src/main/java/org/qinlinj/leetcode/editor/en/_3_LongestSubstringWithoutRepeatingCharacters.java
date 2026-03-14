package org.qinlinj.leetcode.editor.en;
import org.qinlinj.leetcode.editor.common.*;
// [3] Longest Substring Without Repeating Characters
public class _3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new _3_LongestSubstringWithoutRepeatingCharacters().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring1(String s) {
        int[] map = new int[256];
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            map[c]++;
            while (map[c] > 1) {
                char lc = s.charAt(left);
                map[lc]--;
                left++;
            }
            len = Math.max(len, (right - left));
        }
        return len;
    }
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        int[] lastIndex = new int[256];

        for (int left = 0, right = 0; right < n; right++) {
            char c = s.charAt(right);
            // If the character occurs before, jump left to the next duplicate
            // Note: Use Math.max to make sure left doesn’t jump back.
            left = Math.max(lastIndex[c], left);

            res = Math.max(res, right - left + 1);
            // record/update index
            lastIndex[c] = right + 1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given a string s, find the length of the longest substring without duplicate 
// characters. 
// 
//  
//  Example 1: 
// 
//  
// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3. Note that "bca" and 
// "cab" are also correct answers.
//  
// 
//  Example 2: 
// 
//  
// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
//  
// 
//  Example 3: 
// 
//  
// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a 
// substring.
//  
// 
//  
//  Constraints: 
// 
//  
//  0 <= s.length <= 5 * 10⁴ 
//  s consists of English letters, digits, symbols and spaces. 
//  
// 
//  Related Topics Hash Table String Sliding Window 👍 44664 👎 2190
