import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) { 
        List<List<int[]>> pointsPerSec = new ArrayList<>();
        int maxLength = 0;

        for (int[] r : routes) {
            List<int[]> pps = new ArrayList<>();
            
            // 1. 로봇의 전체 경로 중 가장 첫 번째 시작점 추가 (0초)
            int curR = points[r[0] - 1][0];
            int curC = points[r[0] - 1][1];
            pps.add(new int[]{curR, curC});

            // 2. 설정된 경유지(R)들을 순차적으로 방문
            for (int i = 1; i < r.length; i++) {
                int targetR = points[r[i] - 1][0];
                int targetC = points[r[i] - 1][1];

                // 🔥 규칙 1: R(y/행) 좌표가 먼저 변합니다.
                while (curR != targetR) {
                    if (curR > targetR) curR--;
                    else curR++;
                    pps.add(new int[]{curR, curC});
                }

                // 🔥 규칙 2: 그다음 C(x/열) 좌표가 변합니다.
                while (curC != targetC) {
                    if (curC > targetC) curC--;
                    else curC++;
                    pps.add(new int[]{curR, curC});
                }
            }
            
            pointsPerSec.add(pps);
            maxLength = Math.max(maxLength, pps.size());
        }

        int answer = 0;
        int counter = 0;
        
        // 3. 시간대별 충돌 체크
        while (counter < maxLength) {
            Map<String, Integer> map = new HashMap<>();

            for (List<int[]> pps : pointsPerSec) {
                // 현재 시간(counter)이 로봇의 이동 경로 시간 안에 있다면
                if (counter < pps.size()) {
                    int[] pos = pps.get(counter);
                    String s = pos[0] + "," + pos[1];
                    map.put(s, map.getOrDefault(s, 0) + 1);
                }
            }

            // 한 좌표에 2대 이상 모인 곳만 카운트
            for (int k : map.values()) {
                if (k >= 2) {
                    answer++;
                }
            }
            counter++;
        }

        return answer;
    }
}