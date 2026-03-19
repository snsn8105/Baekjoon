import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        
        int left = 0;
        int right = 10000 * n;
        int time = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            long count = cores.length;
            
            // 각 코어마다 mid 분까지 처리할 수 있는 개수를 count에 더함
            for (int i = 0; i < cores.length ; i++) {
                count += mid / cores[i];
            }
            
            // 너무 크면 right를 줄이고 작으면 left를 늘림
            if (count >= n) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // time 이전까지 완료된 작업 수를 구한다
        long doneCount = cores.length;
        for (int i = 0 ; i < cores.length ; i++) {
            doneCount += (time-1) / cores[i];
        }
        
        for (int i = 0 ; i < cores.length ; i++) {
            if (time % cores[i] == 0) {
                doneCount++;
            }
            
            if (doneCount == n)
                return i+1;
        }
        
        return 0;
    }
}