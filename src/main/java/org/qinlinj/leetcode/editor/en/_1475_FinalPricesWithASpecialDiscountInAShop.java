package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [1475] Final Prices With a Special Discount in a Shop
public class _1475_FinalPricesWithASpecialDiscountInAShop {
    public static void main(String[] args) {
        Solution solution = new _1475_FinalPricesWithASpecialDiscountInAShop().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] finalPrices(int[] prices) {
        // find the first prices[j] which lower than price[i]
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int[] res = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && prices[i] < stk.peek()) {
                stk.pop();
            }
            res[i] = stk.isEmpty() ? prices[i] : prices[i] - stk.peek();
            stk.push(prices[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// You are given an integer array prices where prices[i] is the price of the iᵗʰ 
// item in a shop. 
// 
//  There is a special discount for items in the shop. If you buy the iᵗʰ item, 
// then you will receive a discount equivalent to prices[j] where j is the minimum 
// index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive 
// any discount at all. 
// 
//  Return an integer array answer where answer[i] is the final price you will pay 
// for the iᵗʰ item of the shop, considering the special discount. 
// 
//  
//  Example 1: 
// 
//  
// Input: prices = [8,4,6,2,3]
// Output: [4,2,4,2,3]
// Explanation: 
// For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4
// , therefore, the final price you will pay is 8 - 4 = 4.
// For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2
// , therefore, the final price you will pay is 4 - 2 = 2.
// For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2
// , therefore, the final price you will pay is 6 - 2 = 4.
// For items 3 and 4 you will not receive any discount at all.
//  
// 
//  Example 2: 
// 
//  
// Input: prices = [1,2,3,4,5]
// Output: [1,2,3,4,5]
// Explanation: In this case, for all items, you will not receive any discount at 
// all.
//  
// 
//  Example 3: 
// 
//  
// Input: prices = [10,1,1,6]
// Output: [9,0,1,6]
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= prices.length <= 500 
//  1 <= prices[i] <= 1000 
//  
// 
//  Related Topics Array Stack Monotonic Stack 👍 2909 👎 152
