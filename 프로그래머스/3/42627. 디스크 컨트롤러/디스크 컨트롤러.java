import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 1. 요청 시간순 정렬 (유지)
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // 2. 소요 시간 짧은 순 PQ (유지)
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
            if (i1[2] == i2[2]) return i1[1] - i2[1];
            return i1[2] - i2[2];
        });

        boolean[] done = new boolean[jobs.length];
        int[] returnTimes = new int[jobs.length];
        int time = 0;
        int count = 0;

        // 💡 수동으로 첫 번째 작업을 넣지 않고 바로 while문 진입
        while (count < jobs.length) {
            
            // 💡 [수정] 현재 시간(time) 이전에 들어온 '모든' 작업을 큐에 먼저 넣음
            // 이렇게 해야 처음 시작할 때 0초에 온 여러 작업 중 짧은 걸 고를 수 있음
            for (int i = 0; i < jobs.length; i++) {
                if (!done[i] && jobs[i][0] <= time) {
                    pq.add(new int[]{i, jobs[i][0], jobs[i][1]});
                    done[i] = true;
                }
            }

            // 만약 현재 시간에 가용한 작업이 없다면?
            if (pq.isEmpty()) {
                // 아직 처리 안 된 가장 빠른 작업의 요청 시간으로 '타임 워프'
                for (int i = 0; i < jobs.length; i++) {
                    if (!done[i]) {
                        time = jobs[i][0];
                        // 💡 여기서 break만 하고 루프를 다시 돌면 위에서 큐에 넣어줌
                        break; 
                    }
                }
                continue; // 다시 위로 올라가서 큐에 넣기
            }

            // 💡 이제 큐에서 가장 짧은 놈을 꺼냄 (SJF 보장)
            int[] cjob = pq.poll();
            count++;
            
            // 시간 업데이트 및 결과 계산 (유지)
            if (time < cjob[1]) {
                time = cjob[1] + cjob[2];
            } else {
                time += cjob[2];
            }
            
            returnTimes[cjob[0]] = time - cjob[1];
        }

        long sum = 0; // 합계가 클 수 있으므로 long 권장
        for (int r : returnTimes) sum += r;
        
        return (int) (sum / jobs.length);
    }
}