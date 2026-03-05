import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
            
        for (int i = 0 ; i < stones.length ; i++) {
            // 한번의 순회로 최솟값 찾기
            pq.add(new int[]{stones[i], i});
            
            int max = 0;
            // k-1 까지 왔을 때 (윈도우 크기가 k일 때부터 시작)
            if (i >= k-1) {
                // 윈도우에서 벗어난 최댓값은 제외
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                
                max = pq.peek()[0];
                min = Math.min(min, max);
            }
        }
        int answer = min;
        return answer;
    }
}