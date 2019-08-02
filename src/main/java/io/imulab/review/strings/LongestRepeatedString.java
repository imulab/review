package io.imulab.review.strings;

public class LongestRepeatedString {

    public static String compute(String s) {
        // assume all lower case
        s = s.toLowerCase();

        // compute suffix arrays
        String[] suffixes = new String[s.length()];
        for (int i = 0; i < suffixes.length; i++) {
            suffixes[i] = s.substring(i);
        }

        // sort the suffix arrays, common words will reveal
        ThreeWayRadixQuickSort.sort(suffixes, c -> c - 'a');

        String lrs = "";
        for (int i = 0; i < suffixes.length - 1; i++) {
            int len = longestCommonPrefix(suffixes[i], suffixes[i+1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }

        return lrs;
    }

    private static int longestCommonPrefix(String a, String b) {
        int N = Math.min(a.length(), b.length());
        for (int i = 0; i < N; i++) {
            if (a.charAt(i) != b.charAt(i))
                return i;
        }
        return N;
    }
}
