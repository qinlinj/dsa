package org.qinlinj.dsa.data_structure_basic.stack_and_queue;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [394] Decode String
public class _394_DecodeString {
    public static void main(String[] args) {
        Solution solution = new _394_DecodeString().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {
        Deque<String> strDeque = new ArrayDeque<>();
        Deque<Integer> countDeque = new ArrayDeque<>();
        char[] cArray = s.toCharArray();
        int k = 0;
        StringBuilder sb = new StringBuilder();
        
        for(char c : cArray) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                strDeque.push(sb.toString());
                countDeque.push(k);
                sb = new StringBuilder();
                k = 0;
            }
            else if (c == ']') {
                int times = countDeque.pop();
                StringBuilder str = new StringBuilder(strDeque.pop());
                str.append(String.valueOf(sb).repeat(Math.max(0, times)));
                sb = str;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given an encoded string, return its decoded string. 
// 
//  The encoding rule is: k[encoded_string], where the encoded_string inside the 
// square brackets is being repeated exactly k times. Note that k is guaranteed to 
// be a positive integer. 
// 
//  You may assume that the input string is always valid; there are no extra white 
// spaces, square brackets are well-formed, etc. Furthermore, you may assume that 
// the original data does not contain any digits and that digits are only for those 
// repeat numbers, k. For example, there will not be input like 3a or 2[4]. 
// 
//  The test cases are generated so that the length of the output will never 
// exceed 10⁵. 
// 
//  
//  Example 1: 
// 
//  
// Input: s = "3[a]2[bc]"
// Output: "aaabcbc"
//  
// 
//  Example 2: 
// 
//  
// Input: s = "3[a2[c]]"
// Output: "accaccacc"
//  
// 
//  Example 3: 
// 
//  
// Input: s = "2[abc]3[cd]ef"
// Output: "abcabccdcdcdef"
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= s.length <= 30 
//  s consists of lowercase English letters, digits, and square brackets '[]'. 
//  s is guaranteed to be a valid input. 
//  All the integers in s are in the range [1, 300]. 
//  
// 
//  Related Topics String Stack Recursion 👍 13950 👎 701
