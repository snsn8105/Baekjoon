import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long totalMove = 0;
        int d = 0; // 배달 누적분
        int p = 0; // 수거 누적분
        
        for (int i = n-1 ; i >= 0 ; i--) {
            int cnt = 0;
            
            while (deliveries[i] > d || pickups[i] > p) {
                d += cap;
                p += cap;
                cnt++;
            }
            
            d -= deliveries[i];
            p -= pickups[i];
            
            totalMove += (i+1) * 2 * cnt;
        }
        return totalMove;
    }
}