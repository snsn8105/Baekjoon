import java.util.*;
class Solution {
    public int calcTime(List<int[]> nReqs, int mentorNum) {
        int totalWaitTime = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0 ; i < mentorNum ; i++) {
            pq.add(0);
        }
        for (int[] req : nReqs) {
            int startTime = req[0];
            int duration = req[1];
            
            int currentTime = pq.poll();
            
            if (currentTime > startTime) { // 대기 시간 발생
                totalWaitTime += currentTime - startTime;
                pq.add(currentTime + duration);
            }
            else
                pq.add(startTime + duration);
        }
        return totalWaitTime; 
    }
    
    public int dfs(int remainMentor, int type, int[][] waitTimeStore, int k) {
        if (type > k)
            return 0;
        // k개의 유형에 1명씩은 일단 필요
        int minTotal = 10000000;
        
        for (int i = 1 ; i <= remainMentor - (k - type) ; i++) {
            int tmp = waitTimeStore[type][i] + dfs(remainMentor - i, type+1, waitTimeStore, k);
            minTotal = Math.min(minTotal, tmp);
        }
        
        return minTotal;
    }
    public int solution(int k, int n, int[][] reqs) {
        // 먼저 첫번째 요소를 넣고 peek로 현재 종료 시각을 계산하고, 현재 시각이랑 시각을 비교해서 큐에 넣는다
        // 큐의 길이가 멘토의 수라고 생각
        // 종료 시각이 빠른 멘토가 pq의 앞에 가게됨
        // k 개의 상담 유형마다 1개씩 pq를 만든다
        // int[상담 유형][멘토 수]에 멘토 수 별 대기 시간을 저장하고 가장 작은 값을 answer에 더한다
        
        List<List<int[]>> reqsList = new ArrayList<>(); 
        
        // 1. 먼저 k개만큼 리스트를 생성해둔다.
        for (int i = 0; i <= k; i++) {
            reqsList.add(new ArrayList<>());
        }

        // 2. reqs를 딱 한 번만 순회하며 유형별로 담는다.
        for (int[] r : reqs) {
            reqsList.get(r[2]).add(new int[]{r[0], r[1]});
        }

        
        int[][] waitTimeStore = new int[k+1][n+1];

        for (int type = 1 ; type <= k ; type++) {
            for (int num = 1 ; num <= n - k + 1 ; num++) {
                List<int[]> nReqs = reqsList.get(type);
                waitTimeStore[type][num] = calcTime(nReqs, num);
            }
        }
        
        int answer = dfs(n, 1, waitTimeStore, k);
        return answer;
    }
}