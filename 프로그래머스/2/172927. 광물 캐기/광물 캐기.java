import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[] intMineral = new int[minerals.length];
        for (int i = 0; i < minerals.length; i++) {
            if (minerals[i].equals("diamond")) intMineral[i] = 25;
            else if (minerals[i].equals("iron")) intMineral[i] = 5;
            else intMineral[i] = 1;
        }

        // 피로도가 높은 순서대로 묶음을 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((m1, m2) -> m2[1] - m1[1]);

        int totalPicks = picks[0] + picks[1] + picks[2];
        int maxDig = Math.min(minerals.length, totalPicks * 5);

        for (int i = 0; i < maxDig; i += 5) {
            int sum = 0;
            for (int j = i; j < Math.min(i + 5, maxDig); j++) {
                sum += intMineral[j];
            }
            pq.add(new int[]{i, sum});
        }

        int answer = 0;
        // 곡괭이 순서: 다이아(0) -> 철(1) -> 돌(2)
        for (int p = 0; p < 3; p++) {
            int count = picks[p];
            while (count > 0 && !pq.isEmpty()) {
                int startIdx = pq.poll()[0]; // 가장 힘든 구간의 시작 인덱스 추출
                
                // 5번 혹은 남은 광물만큼만 반복
                for (int j = startIdx; j < Math.min(startIdx + 5, minerals.length); j++) {
                    if (p == 0) answer += 1; // 다이아 곡괭이
                    else if (p == 1) { // 철 곡괭이
                        answer += (intMineral[j] == 25) ? 5 : 1;
                    } else { // 돌 곡괭이
                        answer += intMineral[j];
                    }
                }
                count--;
            }
        }
        return answer;
    }
}