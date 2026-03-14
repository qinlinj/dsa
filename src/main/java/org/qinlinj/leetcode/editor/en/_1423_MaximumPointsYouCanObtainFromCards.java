package org.qinlinj.leetcode.editor.en;
import org.qinlinj.leetcode.editor.common.*;
import javax.print.MultiDocPrintJob;

// [1423] Maximum Points You Can Obtain from Cards
public class _1423_MaximumPointsYouCanObtainFromCards {
    public static void main(String[] args) {
        Solution solution = new _1423_MaximumPointsYouCanObtainFromCards().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // get the min score for sum of n - k cards
        int n = cardPoints.length;
        int windowSize = n - k;
        int totalSum = 0;

        for (int point : cardPoints) {
            totalSum += point;
        }

        if (k == n) return totalSum;

        int currentWindowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += cardPoints[i];
        }

        int minWindowSum = currentWindowSum;
        for (int i = windowSize; i < n; i++) {
            currentWindowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }

        return totalSum - minWindowSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// There are several cards arranged in a row, and each card has an associated 
// number of points. The points are given in the integer array cardPoints. 
// 
//  In one step, you can take one card from the beginning or from the end of the 
// row. You have to take exactly k cards. 
// 
//  Your score is the sum of the points of the cards you have taken. 
// 
//  Given the integer array cardPoints and the integer k, return the maximum score 
// you can obtain. 
// 
//  
//  Example 1: 
// 
//  
// Input: cardPoints = [1,2,3,4,5,6,1], k = 3
// Output: 12
// Explanation: After the first step, your score will always be 1. However, 
// choosing the rightmost card first will maximize your total score. The optimal strategy 
// is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
// 
//  
// 
//  Example 2: 
// 
//  
// Input: cardPoints = [2,2,2], k = 2
// Output: 4
// Explanation: Regardless of which two cards you take, your score will always be 4
// .
//  
// 
//  Example 3: 
// 
//  
// Input: cardPoints = [9,7,7,9,7,7,9], k = 7
// Output: 55
// Explanation: You have to take all the cards. Your score is the sum of points of 
// all cards.
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= cardPoints.length <= 10⁵ 
//  1 <= cardPoints[i] <= 10⁴ 
//  1 <= k <= cardPoints.length 
//  
// 
//  Related Topics Array Sliding Window Prefix Sum 👍 7067 👎 319
