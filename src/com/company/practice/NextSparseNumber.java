package com.company.practice;

/*
 * Get next sparse number >= n, i.e. no consecutive "1" in the binary representation of a number
 */
public class NextSparseNumber implements IProblem {
    public String getName() {
        return "Next sparse number";
    }

    public void run() {
        int n = 46; // 101110
        long result = getNextSparseNumber(n);
        System.out.println(n + " " + result);

        n = 38; // 1111
        result = getNextSparseNumber(n);
        System.out.println(n + " " + result);

        n = 0xAB6; // 101010110110;
        result = getNextSparseNumber(n);
        System.out.println(n + " " + result);

        n = 0x12B6; // 1001010110110;
        result = getNextSparseNumber(n);
        System.out.println(n + " " + result + " " + 0x1400);
    }

    private long getNextSparseNumber(int n) {
        long mask = 0;
        int i;
        for( i=31; i>=0; i--) {
            mask = 3L << i;
            if( (mask & n ) == mask )
                break;
        }

        if(i < 0)
            return n;

        long d = 1L << i;
        long result = n - n % d;
        while(true) {
            result += d;
            mask = mask << 2;
            if( (result & mask) != mask )
                break;

            d  = d << 2;
        }

        return result;
    }
}
