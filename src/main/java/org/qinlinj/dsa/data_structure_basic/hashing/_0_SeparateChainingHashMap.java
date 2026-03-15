package org.qinlinj.dsa.data_structure_basic.hashing;

import java.util.*;

public class _0_SeparateChainingHashMap {
    class MyHashMap {
        private static class Node {
            int key;
            int value;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private LinkedList<Node>[] table;
        private int size;       // Number of active key-value pairs
        private int capacity;   // Current array length (always a power of 2)
        private int mask;       // Used for (capacity - 1) bitwise masking
        private static final double LOAD_FACTOR = 0.75;
        private static final double SHRINK_THRESHOLD = 0.25;
        private static final int MIN_CAPACITY = 16;

        public MyHashMap() {
            this.capacity = 16;
            this.mask = capacity - 1; // Pre-calculate mask for bitwise indexing
            this.table = new LinkedList[capacity];
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
            if ((double) size / capacity > LOAD_FACTOR) {
                resize(capacity << 1);
            }

            int index = hash(key);
            if (table[index] == null) {
                table[index] = new LinkedList<>();
            }

            for (Node node : table[index]) {
                if (node.key == key) {
                    node.value = value;
                    return;
                }
            }

            table[index].addLast(new Node(key, value));
            size++;
        }

        public int get(int key) {
            int index = hash(key);
            if (table[index] == null) return -1;
            for (Node node : table[index]) {
                if (node.key == key) return node.value;
            }
            return -1;
        }

        public void remove(int key) {
            int index = hash(key);
            if (table[index] == null) return;

            boolean removed = table[index].removeIf(node -> node.key == key);

            if (removed) {
                size--;
                if (capacity > MIN_CAPACITY && (double) size / capacity < SHRINK_THRESHOLD) {
                    resize(capacity >> 1);
                }
            }
        }

        private void resize(int newCap) {
            LinkedList<Node>[] oldTable = table;

            this.capacity = newCap;
            this.mask = capacity - 1;
            this.table = new LinkedList[capacity];
            this.size = 0;

            for (LinkedList<Node> bucket : oldTable) {
                if (bucket != null) {
                    for (Node node : bucket) {
                        this.put(node.key, node.value);
                    }
                }
            }
        }
    }
}
