class Solution {
    public long solution(int[] sequence) {
        // 부분 최대 합을 계산해서 구하면 됨
        long[] S = new long[sequence.length+1];
        S[0] = 0;
        
        int idx = 1;
        for (int i : sequence) {
            S[idx] = (idx % 2 == 0) ? i : -1 * i;
            
            S[idx] += S[idx-1];
            
            idx++;
        }
        
        long max = 0;
        long min = 110000;
        for (long i : S) {
            if (i <= min)
                min = i;
            if (i >= max)
                max = i;
        }
        
        long answer = max - min;
        return answer;
    }
}