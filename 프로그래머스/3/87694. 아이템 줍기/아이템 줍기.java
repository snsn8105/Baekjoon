import java.util.*;

class Solution {
    static int[][] board = new int[102][102];
    static boolean[][] visited = new boolean[102][102];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1. 좌표 스케일링 및 테두리/내부 표시
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    // 내부
                    if (x1 < i && i < x2 && y1 < j && j < y2) {
                        board[j][i] = 2;
                    }
                    // 테두리 (내부가 아니면)
                    else if (board[j][i] != 2) {
                        board[j][i] = 1;
                    }
                }
            }
        }

        // 2. BFS 탐색
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{characterX * 2, characterY * 2, 0});
        visited[characterY * 2][characterX * 2] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];

            // 아이템 도착
            if (x == itemX * 2 && y == itemY * 2) {
                return dist / 2; // 좌표 복원
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= 102 || ny >= 102) continue;
                if (visited[ny][nx]) continue;

                // 테두리(1)만 이동 가능
                if (board[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return 0; // 기본 반환
    }
}
