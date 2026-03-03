import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        // idx, value로 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a , int[] b) {
                return b[1] - a[1];
            }
        });
        
        int useCount = 0;
        int idx = 0;
        for (int e : enemy) {
            if (e > n && k > 0) {
                k--;
                if (!pq.isEmpty() && pq.peek()[1] > e) {
                    int[] tmp = pq.poll();
                    pq.add(new int[]{idx, e});
                    n += tmp[1] - e;
                    useCount -= tmp[1] - e;
                }
            } else if (n - e >= 0) {
                n -= e;
                useCount += e;
                
                pq.add(new int[]{idx, e});
            } else
                break;
            
            idx++;
        }
        
        int answer = idx;
        return answer;
    }
}