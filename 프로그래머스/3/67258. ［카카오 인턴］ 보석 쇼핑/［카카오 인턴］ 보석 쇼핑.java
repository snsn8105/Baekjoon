import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 1. 보석 종류를 Map에 담아 인덱스를 관리 (indexOf 대체)
        Map<String, Integer> gemMap = new HashMap<>();
        Set<String> set = new LinkedHashSet<>(Arrays.asList(gems));
        int kind = set.size();
        
        int idx = 0;
        for (String g : set) gemMap.put(g, idx++);

        // 2. 각 보석의 최근 발견 위치와 체크 여부 관리
        int[] lastIdx = new int[kind];
        boolean[] check = new boolean[kind];
        int collectedCount = 0; // 몇 종류 모았는지 카운트 (isChecked 대체)

        int[] answer = {0, 1000000};

        // 3. 재귀 대신 for 문 사용
        for (int i = 0; i < gems.length; i++) {
            int gemTypeIdx = gemMap.get(gems[i]);
            
            // 처음 발견하는 종류라면 카운트 증가
            if (!check[gemTypeIdx]) {
                check[gemTypeIdx] = true;
                collectedCount++;
            }
            // 해당 보석의 위치 갱신
            lastIdx[gemTypeIdx] = i;

            // 4. 모든 종류를 다 모은 시점부터 정답 갱신
            if (collectedCount == kind) {
                int minVal = Integer.MAX_VALUE;
                int maxVal = -1;

                for (int pos : lastIdx) {
                    if (pos < minVal) minVal = pos;
                    if (pos > maxVal) maxVal = pos;
                }

                // 기존 정답보다 짧은 구간이 나오면 갱신
                if (maxVal - minVal < answer[1] - answer[0]) {
                    answer[0] = minVal;
                    answer[1] = maxVal;
                } else if (maxVal - minVal == answer[1] - answer[0]) {
                    if (minVal < answer[0]) {
                        answer[0] = minVal;
                        answer[1] = maxVal;
                    }
                }
            }
        }

        return new int[]{answer[0] + 1, answer[1] + 1};
    }
}