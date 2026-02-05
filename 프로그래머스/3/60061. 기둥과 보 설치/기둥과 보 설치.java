import java.util.*;

class Solution {
    boolean[][] map0; // 기둥
    boolean[][] map1; // 보
    int n;

    boolean isValid() {
        for (int y = 0; y <= n; y++) {
            for (int x = 0; x <= n; x++) {
                // 1. 기둥 체크
                if (map0[y][x]) {
                    if (!(y == 0 || (y > 0 && map0[y-1][x]) || (x > 0 && map1[y][x-1]) || map1[y][x])) 
                        return false;
                }
                // 2. 보 체크
                if (map1[y][x]) {
                    if (!((y > 0 && map0[y-1][x]) || (y > 0 && x < n && map0[y-1][x+1]) || (x > 0 && x < n && map1[y][x-1] && map1[y][x+1]))) 
                        return false;
                }
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        map0 = new boolean[n + 2][n + 2]; // 인덱스 여유 있게 +2
        map1 = new boolean[n + 2][n + 2];

        for (int[] bf : build_frame) {
            int x = bf[0], y = bf[1], kind = bf[2], b = bf[3];

            if (kind == 0) { // 기둥
                if (b == 1) {
                    map0[y][x] = true;
                    if (!isValid()) map0[y][x] = false;
                } else {
                    map0[y][x] = false;
                    if (!isValid()) map0[y][x] = true;
                }
            } else { // 보
                if (b == 1) {
                    map1[y][x] = true;
                    if (!isValid()) map1[y][x] = false;
                } else {
                    map1[y][x] = false;
                    if (!isValid()) map1[y][x] = true;
                }
            }
        }

        // 최종 결과 추출 (x순 -> y순 -> 기둥우선 순서로 루프)
        List<int[]> list = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (map0[y][x]) list.add(new int[]{x, y, 0});
                if (map1[y][x]) list.add(new int[]{x, y, 1});
            }
        }

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}