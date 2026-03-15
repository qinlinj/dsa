package org.qinlinj.dsa.data_structure_basic.hashing;

/**
 * Custom HashMap implementation using Open Addressing with Linear Probing.
 * Optimized with Bitwise Operations for maximum performance.
 * Added dynamic shrinking logic to maintain memory efficiency.
 */
public class _0_OpenAddressingHashmap {
    class MyHashMap {
        /**
         * Basic storage unit for a Key-Value pair.
         */
        private static class Node {
            int key;
            int value;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        /**
         * Sentinel 'Tombstone' node to mark a slot as deleted without breaking
         * the probe sequence (ensures subsequent keys can still be found).
         */
        private static final Node DELETED = new Node(-1, -1);

        private Node[] table;
        private int size;       // Number of active key-value pairs
        private int capacity;   // Current array length (always a power of 2)
        private int mask;       // Used for (capacity - 1) bitwise masking
        private static final double LOAD_FACTOR = 0.5; // Threshold to trigger resizing

        /**
         * New constants for shrinking logic.
         * SHRINK_FACTOR: If load drops below 12.5%, we downsize the table.
         * MIN_CAPACITY: Minimum table size to avoid unnecessary resizing for small maps.
         */
        private static final double SHRINK_FACTOR = 0.125;
        private static final int MIN_CAPACITY = 16;

        public MyHashMap() {
            this.capacity = MIN_CAPACITY;
            this.mask = capacity - 1; // Pre-calculate mask for bitwise indexing
            this.table = new Node[capacity];
            this.size = 0;
        }

        /**
         * Hash function with perturbation to reduce collisions.
         * 1. (h >>> 16): Brings high-bit entropy down to the low-bits.
         * 2. (^): XORs high and low bits to mix the signature.
         * 3. (& mask): Fast bitwise alternative to (h % capacity).
         */
        private int hash(int key) {
            int h = Integer.hashCode(key);
            h ^= (h >>> 16);
            return h & mask;
        }

        public void put(int key, int value) {
            // Monitor load factor to prevent performance degradation
            if ((double) size / capacity >= LOAD_FACTOR) {
                rehash(capacity << 1); // Double the capacity
            }
            internalPut(table, key, value);
        }

        /**
         * Core insertion logic. Supports reusing 'DELETED' slots (recycling).
         */
        private void internalPut(Node[] targetTable, int key, int value) {
            int m = targetTable.length - 1;
            int h = Integer.hashCode(key);
            int idx = (h ^ (h >>> 16)) & m;
            int firstDeletedIdx = -1;

            // Probe until an empty (null) slot is found
            while (targetTable[idx] != null) {
                if (targetTable[idx] == DELETED) {
                    // Remember the first tombstone to recycle the slot later
                    if (firstDeletedIdx == -1) firstDeletedIdx = idx;
                } else if (targetTable[idx].key == key) {
                    // Update value if key already exists
                    targetTable[idx].value = value;
                    return;
                }
                // (idx + 1) & m: Increments index and wraps back to 0 via bitwise AND
                idx = (idx + 1) & m;
            }

            // Insert into either the first recycled tombstone or the first null slot
            int insertIdx = (firstDeletedIdx == -1) ? idx : firstDeletedIdx;
            targetTable[insertIdx] = new Node(key, value);
            size++;
        }

        public int get(int key) {
            int idx = hash(key);
            int startIdx = idx;

            while (table[idx] != null) {
                // Ignore DELETED nodes; keep looking for the actual key
                if (table[idx] != DELETED && table[idx].key == key) {
                    return table[idx].value;
                }
                idx = (idx + 1) & mask;
                if (idx == startIdx) break; // Safety check to prevent infinite loops
            }
            return -1;
        }

        public void remove(int key) {
            int idx = hash(key);
            int startIdx = idx;

            while (table[idx] != null) {
                if (table[idx] != DELETED && table[idx].key == key) {
                    // Lazy deletion: mark as DELETED to preserve the probe chain
                    table[idx] = DELETED;
                    size--;

                    /**
                     * Shrinking check:
                     * If the current size is less than 12.5% of capacity, we downsize
                     * to save memory, ensuring we don't go below the MIN_CAPACITY.
                     */
                    if (capacity > MIN_CAPACITY && (double) size / capacity < SHRINK_FACTOR) {
                        rehash(capacity >> 1); // Halve the capacity
                    }
                    return;
                }
                idx = (idx + 1) & mask;
                if (idx == startIdx) break;
            }
        }

        /**
         * Redistributes existing elements into a table of the specified new capacity.
         * This handles both expansion and shrinking.
         */
        private void rehash(int newCapacity) {
            Node[] oldTable = table;
            this.capacity = newCapacity;
            this.mask = capacity - 1;     // Update mask for the new size
            this.table = new Node[capacity];
            this.size = 0;                // Reset size as internalPut will re-increment it

            for (Node node : oldTable) {
                /**
                 * During rehash, we discard all DELETED (tombstone) nodes.
                 * This acts as a 'defragmentation' step, cleaning up the probe chains.
                 */
                if (node != null && node != DELETED) {
                    internalPut(table, node.key, node.value);
                }
            }
        }
    }
}