package com.company.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Consecutively find the max value in sliding window of size k in an array of n integers
 */
public class KMax implements IProblem {
    public String getName() {
        return "KMax";
    }

    public void run() {
        int[] a = new int[] {1, 2, 7, 7, 8, 2, 3, 1, 5};
        int k = 3;
        int[] result = findKMax(a,k); // Expected: 7, 7, 8, 8, 8, 3, 5

        for(int i=0; i<=result.length - k; i++)
            System.out.print(result[i] + " ");

        System.out.println();

        result = findKMax_MonotoneQueue(a,k);

        for(int i=0; i<=result.length - k; i++)
            System.out.print(result[i] + " ");

        System.out.println();
    }

    private int[] findKMax(int[] a, int k) {
        int n = a.length;
        int[] result = new int[n];

        int[] d = new int[32];
        int m = 0;
        while(k > 0)  { d[m++] = k; k = k>>1; }

        for(int i=0; i<n; i++)
            result[i] = a[i];

        for(int j=m-2; j>=0; j--) {
            for(int i=0; i<=n - d[j]; i++) {
                result[i] = Math.max(result[i], result[i + d[j+1]]);
                if( (d[j] & 1) == 1) {
                    result[i] = Math.max(result[i], a[i + d[j] - 1]);
                }
            }
        }
        return result;
    }

    class Pair {
        public int Value;
        public int Index;

        public Pair(int value, int index) {
            this.Value = value;
            this.Index = index;
        }
    }

    private int[] findKMax_MonotoneQueue(int[] a, int k)
    {
        int n = a.length, m = 0;
        LinkedList<Pair> monotoneQueue = new LinkedList<Pair>();

        for(int i=0; i<k; i++) {
            while(!monotoneQueue.isEmpty() && monotoneQueue.getLast().Value < a[i]) {
                monotoneQueue.removeLast();
            }
            monotoneQueue.addLast(new Pair(a[i], i));
        }

        int[] result = new int[n];
        for(int i=0; i<=n-k; i++) {
            result[i] = monotoneQueue.peekFirst().Value;
            while(!monotoneQueue.isEmpty() && monotoneQueue.peekFirst().Index <= i) {
                monotoneQueue.removeFirst();
            }
            if(i+k<n) {
                while (!monotoneQueue.isEmpty() && monotoneQueue.getLast().Value < a[i+k]) {
                    monotoneQueue.removeLast();
                }
                monotoneQueue.addLast(new Pair(a[i + k], i + k));
            }
        }
        return result;
    }
}
