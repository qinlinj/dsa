package org.qinlinj.dsa.array_operations.diff_array;
import org.qinlinj.leetcode.editor.common.*;
// [1109] Corporate Flight Bookings
public class _1109_CorporateFlightBookings {
    public static void main(String[] args) {
        Solution solution = new _1109_CorporateFlightBookings().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] difference = new int[n];
        int start = 0;
        int end = 0;
        for (int[] booking : bookings) {
            start = booking[0];
            end = booking[1];
            difference[start - 1] += booking[2];
            if (end < n) {
                difference[end] -= booking[2];
            }
        }
        int[] res = new int[n];
        res[0] = difference[0];
        for (int i = 1; i < n; i++) {
            res[i] = difference[i] + res[i - 1];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// There are n flights that are labeled from 1 to n. 
// 
//  You are given an array of flight bookings bookings, where bookings[i] = [
// firsti, lasti, seatsi] represents a booking for flights firsti through lasti (
// inclusive) with seatsi seats reserved for each flight in the range. 
// 
//  Return an array answer of length n, where answer[i] is the total number of 
// seats reserved for flight i. 
// 
//  
//  Example 1: 
// 
//  
// Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
// Output: [10,55,45,25,25]
// Explanation:
// Flight labels:        1   2   3   4   5
// Booking 1 reserved:  10  10
// Booking 2 reserved:      20  20
// Booking 3 reserved:      25  25  25  25
// Total seats:         10  55  45  25  25
// Hence, answer = [10,55,45,25,25]
//  
// 
//  Example 2: 
// 
//  
// Input: bookings = [[1,2,10],[2,2,15]], n = 2
// Output: [10,25]
// Explanation:
// Flight labels:        1   2
// Booking 1 reserved:  10  10
// Booking 2 reserved:      15
// Total seats:         10  25
// Hence, answer = [10,25]
// 
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= n <= 2 * 10⁴ 
//  1 <= bookings.length <= 2 * 10⁴ 
//  bookings[i].length == 3 
//  1 <= firsti <= lasti <= n 
//  1 <= seatsi <= 10⁴ 
//  
// 
//  Related Topics Array Prefix Sum 👍 1856 👎 166
