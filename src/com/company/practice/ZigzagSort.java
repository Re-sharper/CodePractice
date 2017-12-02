package com.company.practice;

import java.util.Random;

/*
 * Sort integer array a[1], a[2], a[3], ... , a[n] into array b[1], b[2], b[3], ..., b[n],
 * w/ b[1] < b[2] > b[3], b[3] < b[4] > b[5], ..., b[2k-1] < b[2k] > b[2k+1]
 */
public class ZigzagSort implements IProblem {
    public String getName() {
        return "Zigzag sort";
    }

    public void run() {
        int n = 10;
        int[] a = new int[n];
        Random random = new Random();
        for(int i=0; i<n; i++)
            a[i] = random.nextInt(n) + 1;

        ZigzagSorter sorter = new ZigzagSorter();
        sorter.sort(a);

        for(int v: a)
            System.out.print(v + " ");

        System.out.println();
    }

    class ZigzagSorter {

        public void sort(int[] input) {
            if(input == null)
                return;

            int n = input.length;
            int temp;
            for(int i=1; i<n-1; i+=2) {
                if(input[i-1] > input[i]) {
                    temp = input[i-1];
                    input[i-1] = input[i];
                    input[i] = temp;
                }

                if(input[i] < input[i+1]) {
                    temp = input[i];
                    input[i] = input[i+1];
                    input[i+1] = temp;
                }
            }
        }
    }
}


