package com.company.practice;

import java.util.ArrayList;

/*
 * VLQ (Variable-length quantity) encoding
 */
public class VLQ {
    public String getName() {
        return "VLQ";
    }

    public void run() {
        VLQEncoder encoder = new VLQEncoder();
        int num = 137;
        String result = encoder.encode(num);
        System.out.println(num + " : " + result);
    }

    class VLQEncoder {

        private static final int MASK = 127;

        public String encode(int num) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            while(num > 0) {
                list.add(num & MASK);
                num = num >> 7;
            }
            int len = list.size();
            for(int i=1; i<len; i++)
                list.set(i, list.get(i) | 128);

            StringBuilder sb = new StringBuilder();
            for(int i=len-1; i>=0; i--) {
                String str = Integer.toBinaryString(list.get(i));
                StringBuilder temp = new StringBuilder(8);
                for(int j=str.length(); j<8; j++)
                    temp.append('0');
                temp.append(str);

                sb.append(temp);
                if(i > 0) sb.append(" ");
            }

            return sb.toString();
        }
    }
}
