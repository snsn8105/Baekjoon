import java.util.*;

class Solution {
    int[] weak;
    int[] linearWeak; // 2배로 늘린 취약 지점 위치
    int n;
    int answer = Integer.MAX_VALUE;

    void permutation(int depth, int[] dist, boolean[] check, int[] result) {
        if (depth == dist.length) {
            clean(result); // 모든 친구의 순서가 정해지면 점검 시작
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (!check[i]) {
                check[i] = true;
                result[depth] = dist[i];
                permutation(depth + 1, dist, check, result);
                check[i] = false;
            }
        }
    }

    void clean(int[] result) {
        int L = weak.length;
        
        // 각 취약 지점을 시작점으로 설정 (start는 0부터 L-1까지)
        for (int i = 0; i < L; i++) {
            int cover = linearWeak[i] + result[0];
            
            int count = 1;
            
            for (int j = i ; j < i + L ; j++) {
                if (cover < linearWeak[j]) {
                    count++; // 먼저 증가 시켜서 사람이 남아있는지 확인 해야됨
                    if (count > result.length)
                        break;
                    
                    cover = linearWeak[j] + result[count-1];
                }
            }
            if (count <= result.length)
                answer = Math.min(answer, count);
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        
        // 1. 직선화 작업
        linearWeak = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            linearWeak[i] = weak[i];
            linearWeak[i + weak.length] = weak[i] + n;
        }

        // 2. 친구 순열 탐색
        permutation(0, dist, new boolean[dist.length], new int[dist.length]);

        // 3. 결과 반환 (갱신 안 됐으면 -1)
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}