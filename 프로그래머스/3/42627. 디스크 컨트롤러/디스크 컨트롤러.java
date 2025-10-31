import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 요청 시간 기준 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 작업시간 짧은 순
        int time = 0;
        int total = 0;
        int idx = 0;
        int count = 0;
        
        while (count < jobs.length) {
            // 현재 시점까지 요청된 모든 작업 pq에 넣기
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(jobs[idx++]);
            }
            
            if (pq.isEmpty()) {
                // 아직 도착한 작업이 없으면 시간만 진행
                time = jobs[idx][0];
                continue;
            }
            
            int[] job = pq.poll();
            time += job[1]; // 작업 수행
            total += (time - job[0]); // 요청~완료 시간 누적
            count++;
        }
        
        return total / jobs.length; // 평균 반환
    }
}
