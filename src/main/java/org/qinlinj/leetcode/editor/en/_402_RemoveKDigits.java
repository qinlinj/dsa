package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [402] Remove K Digits
public class _402_RemoveKDigits {
    public static void main(String[] args) {
        Solution solution = new _402_RemoveKDigits().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeKdigits(String num, int k) {
        ArrayDeque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stk.isEmpty() && stk.peek() > num.charAt(i)) {
                stk.pop();
                k--;
            }
            if (stk.isEmpty() && num.charAt(i) == '0') {
                continue;
            }
            stk.push(num.charAt(i));
        }
        while (k > 0 && !stk.isEmpty()) {
            stk.pop();
            k--;
        }
        if (stk.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.removeLast());
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// Given string num representing a non-negative integer num, and an integer k, 
// return the smallest possible integer after removing k digits from num. 
// 
//  
//  Example 1: 
// 
//  
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 
// which is the smallest.
//  
// 
//  Example 2: 
// 
//  
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the number is 200. Note that the output 
// must not contain leading zeroes.
//  
// 
//  Example 3: 
// 
//  
// Input: num = "10", k = 2
// Output: "0"
// Explanation: Remove all the digits from the number and it is left with nothing 
// which is 0.
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= k <= num.length <= 10⁵ 
//  num consists of only digits. 
//  num does not have any leading zeros except for the zero itself. 
//  
// 
//  Related Topics String Stack Greedy Monotonic Stack 👍 10555 👎 547
