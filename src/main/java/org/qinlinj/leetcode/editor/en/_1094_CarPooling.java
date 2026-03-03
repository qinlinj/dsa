package org.qinlinj.leetcode.editor.en;
import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [1094] Car Pooling
public class _1094_CarPooling {
    public static void main(String[] args) {
        Solution solution = new _1094_CarPooling().new Solution();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int length = 1001;
        int[] diff = new int[length];
        int from, to, numP;
        for (int[] trip : trips) {
            from = trip[1];
            to = trip[2];
            numP = trip[0];
            diff[from] += numP;
            if (to + 1 < length) {
                diff[to] -= numP;
            }
        }
        int[] res = new int[length];
        if (diff[0] > capacity) {
            return false;
        }
        res[0] = diff[0];
        for (int i = 1; i < length; i++) {
            res[i] = diff[i] + res[i - 1];
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

// There is a car with capacity empty seats. The vehicle only drives east (i.e., 
// it cannot turn around and drive west). 
// 
//  You are given the integer capacity and an array trips where trips[i] = [
// numPassengersi, fromi, toi] indicates that the iᵗʰ trip has numPassengersi passengers 
// and the locations to pick them up and drop them off are fromi and toi 
// respectively. The locations are given as the number of kilometers due east from the car's 
// initial location. 
// 
//  Return true if it is possible to pick up and drop off all passengers for all 
// the given trips, or false otherwise. 
// 
//  
//  Example 1: 
// 
//  
// Input: trips = [[2,1,5],[3,3,7]], capacity = 4
// Output: false
//  
// 
//  Example 2: 
// 
//  
// Input: trips = [[2,1,5],[3,3,7]], capacity = 5
// Output: true
//  
// 
//  
//  Constraints: 
// 
//  
//  1 <= trips.length <= 1000 
//  trips[i].length == 3 
//  1 <= numPassengersi <= 100 
//  0 <= fromi < toi <= 1000 
//  1 <= capacity <= 10⁵ 
//  
// 
//  Related Topics Array Sorting Heap (Priority Queue) Simulation Prefix Sum 👍 481
// 7 👎 123
