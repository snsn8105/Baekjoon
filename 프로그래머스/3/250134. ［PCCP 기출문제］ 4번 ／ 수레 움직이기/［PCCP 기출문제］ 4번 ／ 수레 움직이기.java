import java.util.*;
class Solution {
    int[][] maze;
    int[] redDest;
    int[] blueDest;
    
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    
    int minTurns = 999999; // 최솟값 결과 저장
    // dfs에서 현재 칸이 유효한지(이동 가능한 칸인지) 확인하고 가능하면 다음칸으로 이동한 후 현재 칸 색칠
    public void dfs(int[] red, int[] blue, int turn, boolean[][] rFP, boolean[][] bFP) {
        // 1. 종료 조건
        if (isDest(red[0], red[1], redDest) && isDest(blue[0], blue[1], blueDest)) {
            minTurns = Math.min(minTurns, turn);
            return;
        }

        // 2. 가지치기 (이미 찾은 최솟값보다 크거나 같으면 종료)
        if (turn >= minTurns) return;

        for (int i = 0; i < 4; i++) {
            int nrR = red[0], ncR = red[1];
            boolean rDone = isDest(red[0], red[1], redDest);
            if (!rDone) {
                nrR += dr[i]; ncR += dc[i];
            }

            for (int j = 0; j < 4; j++) {
                int nrB = blue[0], ncB = blue[1];
                boolean bDone = isDest(blue[0], blue[1], blueDest);
                if (!bDone) {
                    nrB += dr[j]; ncB += dc[j];
                }

                // --- 유효성 검사 (필터링) ---

                // A. 맵 밖이거나 벽(5)인 경우
                if (!isInMap(nrR, ncR) || maze[nrR][ncR] == 5) continue;
                if (!isInMap(nrB, ncB) || maze[nrB][ncB] == 5) continue;

                // B. [핵심] 이동 중인데 이미 방문한 경우 (도착한 수레는 제외)
                if (!rDone && rFP[nrR][ncR]) continue;
                if (!bDone && bFP[nrB][ncB]) continue;

                // C. 충돌 체크 (동일 칸, 교차)
                if (nrR == nrB && ncR == ncB) continue;
                if (nrR == blue[0] && ncR == blue[1] && nrB == red[0] && ncB == red[1]) continue;

                // --- 이동 및 재귀 ---
                rFP[nrR][ncR] = true;
                bFP[nrB][ncB] = true;

                dfs(new int[]{nrR, ncR}, new int[]{nrB, ncB}, turn + 1, rFP, bFP);

                // --- 백트래킹 (도착지는 점유 유지) ---
                if (!rDone) rFP[nrR][ncR] = false;
                if (!bDone) bFP[nrB][ncB] = false;

                if (bDone) break; // 파랑 도착 시 j루프는 한 번만
            }
            if (rDone) break; // 빨강 도착 시 i루프는 한 번만
        }
    }
    // map 안에 있는지 확인
    public boolean isInMap(int r, int c) {
        if (r >= 0 && r < maze.length && c >= 0 && c < maze[0].length)
            return true;
        else
            return false;
    }
    // 도착 했는지 확인
    public boolean isDest(int r, int c, int[] dest) {
        if (r == dest[0] && c == dest[1])
            return true;
        else
            return false;
    }
    public int solution(int[][] maze) {
        this.maze = maze;
        int[] red = new int[2];
        int[] blue = new int[2];
        boolean[][] redFootPrint = new boolean[maze.length][maze[0].length];
        boolean[][] blueFootPrint = new boolean[maze.length][maze[0].length];
        for (int i = 0 ; i < maze.length ; i++) {
            for (int j = 0 ; j < maze[0].length ; j++) {
                if (maze[i][j] == 1) {
                    red = new int[]{i,j};
                    redFootPrint[i][j] = true;
                }
                if (maze[i][j] == 2) {
                    blue = new int[]{i, j};
                    blueFootPrint[i][j] = true;
                }
                if (maze[i][j] == 3)
                    this.redDest = new int[]{i, j};
                if (maze[i][j] == 4)
                    this.blueDest = new int[]{i,j};
            }
        }
        
        dfs(red, blue, 0, redFootPrint, blueFootPrint);
        
        if (minTurns == 999999)
            return 0;
        return minTurns;
    }
}