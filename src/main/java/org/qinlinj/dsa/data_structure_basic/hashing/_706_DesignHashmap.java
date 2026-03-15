package org.qinlinj.dsa.data_structure_basic.hashing;
import org.qinlinj.leetcode.editor.common.*;
// [706] Design HashMap
public class _706_DesignHashmap {
    public static void main(String[] args) {
        MyHashMap solution = new _706_DesignHashmap().new MyHashMap();
        // put your test code here
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MyHashMap {
    private static class Node {
        int key;
        int value;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final Node DELETED = new Node(-1, -1);
    private Node[] table;
    private int size;
    private int capacity;
    private int mask;
    private static final double LOAD_FACTOR = 0.5;

    public MyHashMap() {
        this.capacity = 16;
        this.mask = capacity - 1;
        this.table = new Node[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        int h = Integer.hashCode(key);
        h ^= (h >>> 16);
        return h & mask;
    }

    public void put(int key, int value) {
        if ((double) size / capacity >= LOAD_FACTOR) {
            rehash();
        }
        internalPut(table, key, value);
    }

    private void internalPut(Node[] targetTable, int key, int value) {
        int m = targetTable.length - 1;
        int idx = (Integer.hashCode(key) ^ (Integer.hashCode(key) >>> 16)) & m;
        int firstDeletedIdx = -1;

        while (targetTable[idx] != null) {
            if (targetTable[idx] == DELETED) {
                if (firstDeletedIdx == -1) firstDeletedIdx = idx;
            } else if (targetTable[idx].key == key) {
                targetTable[idx].value = value;
                return;
            }
            idx = (idx + 1) & m;
        }

        int insertIdx = (firstDeletedIdx == -1) ? idx : firstDeletedIdx;
        targetTable[insertIdx] = new Node(key, value);
        size++;
    }

    public int get(int key) {
        int idx = hash(key);
        int startIdx = idx;

        while (table[idx] != null) {
            if (table[idx] != DELETED && table[idx].key == key) {
                return table[idx].value;
            }
            idx = (idx + 1) & mask;
            if (idx == startIdx) break;
        }
        return -1;
    }

    public void remove(int key) {
        int idx = hash(key);
        int startIdx = idx;

        while (table[idx] != null) {
            if (table[idx] != DELETED && table[idx].key == key) {
                table[idx] = DELETED;
                size--;
                return;
            }
            idx = (idx + 1) & mask;
            if (idx == startIdx) break;
        }
    }

    private void rehash() {
        Node[] oldTable = table;
        capacity <<= 1;
        mask = capacity - 1;
        table = new Node[capacity];
        size = 0;

        for (Node node : oldTable) {
            if (node != null && node != DELETED) {
                internalPut(table, node.key, node.value);
            }
        }
    }
}
/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

// Design a HashMap without using any built-in hash table libraries. 
// 
//  Implement the MyHashMap class: 
// 
//  
//  MyHashMap() initializes the object with an empty map. 
//  void put(int key, int value) inserts a (key, value) pair into the HashMap. If 
// the key already exists in the map, update the corresponding value. 
//  int get(int key) returns the value to which the specified key is mapped, or -1 
// if this map contains no mapping for the key. 
//  void remove(key) removes the key and its corresponding value if the map 
// contains the mapping for the key. 
//  
// 
//  
//  Example 1: 
// 
//  
// Input
// ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
// [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
// Output
// [null, null, null, 1, -1, null, 1, null, -1]
// 
// Explanation
// MyHashMap myHashMap = new MyHashMap();
// myHashMap.put(1, 1); // The map is now [[1,1]]
// myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
// myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
// myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2
// ]]
// myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the 
// existing value)
// myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
// myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
// myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
//  
// 
//  
//  Constraints: 
// 
//  
//  0 <= key, value <= 10⁶ 
//  At most 10⁴ calls will be made to put, get, and remove. 
//  
// 
//  Related Topics Array Hash Table Linked List Design Hash Function 👍 5419 👎 501
