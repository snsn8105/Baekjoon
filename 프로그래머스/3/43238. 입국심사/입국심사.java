import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;

            // mid 시간 동안 모든 심사관이 처리할 수 있는 총 인원 계산
            for (int t : times) {
                total += mid / t;
            }

            if (total >= n) { // n명 이상 처리 가능 -> 시간 단축 가능
                answer = mid;
                right = mid - 1;
            } else { // n명 처리 불가능 -> 시간 연장 필요
                left = mid + 1;
            }
        }
        return answer;
    }
}