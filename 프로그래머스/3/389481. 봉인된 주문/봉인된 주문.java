import java.util.*;
class Solution {

    static long[] pow = new long[12];
    static long[] prefix = new long[12];

    static {
        pow[0] = 1;
        for (int i = 1; i <= 11; i++) pow[i] = pow[i - 1] * 26;
        prefix[0] = 0;
        for (int len = 1; len <= 11; len++) prefix[len] = prefix[len - 1] + pow[len];
    }

    long getRank(String s) {
        int len = s.length();
        long rank = prefix[len - 1];
        for (int i = 0; i < len; i++) {
            int diff = s.charAt(i) - 'a';
            rank += diff * pow[len - 1 - i];
        }
        return rank + 1;
    }

    String fromRank(long k) {
        int len = 1;
        while (prefix[len] < k) len++;
        k -= prefix[len - 1];
        k--;

        char[] s = new char[len];
        for (int i = 0; i < len; i++) {
            long block = pow[len - 1 - i];
            int c = (int)(k / block);
            s[i] = (char)('a' + c);
            k %= block;
        }
        return new String(s);
    }

    long countBansLE(long x, long[] bans) {
        int idx = Arrays.binarySearch(bans, x);
        if (idx < 0) return ~idx;
        while (idx + 1 < bans.length && bans[idx + 1] == x) idx++;
        return idx + 1;
    }

    long findOriginalRank(long n, long[] bans) {
        long left = 1, right = prefix[11];
        while (left < right) {
            long mid = (left + right) / 2;
            long removed = countBansLE(mid, bans);
            if (mid - removed >= n) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public String solution(long n, String[] bans) {
        long[] br = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            br[i] = getRank(bans[i]);
        }
        Arrays.sort(br);

        long original = findOriginalRank(n, br);
        return fromRank(original);
    }
}
