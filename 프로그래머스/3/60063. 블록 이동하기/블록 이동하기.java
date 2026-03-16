import java.util.*;

class Robot {
    int r, c, dir, count;
    public Robot(int r, int c, int dir, int count) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.count = count;
    }
}

class Solution {
    int[][] board;
    boolean[][][] visited;
    int N, M;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    // 두 칸 모두 맵 안에 있는지 확인
    public boolean checkInMap(int ar, int ac, int br, int bc) {
        return ar >= 0 && ar < N && ac >= 0 && ac < M && 
               br >= 0 && br < N && bc >= 0 && bc < M;
    }

    // 두 칸 중 하나라도 벽인지 확인
    public boolean isWall(int ar, int ac, int br, int bc) {
        return board[ar][ac] == 1 || board[br][bc] == 1;
    }

    public List<Robot> getNextSteps(Robot curr) {
        List<Robot> nexts = new ArrayList<>();

        // 1. 평행 이동 (상, 하, 좌, 우)
        for (int i = 0; i < 4; i++) {
            int nr = curr.r + dr[i];
            int nc = curr.c + dc[i];
            int nr2, nc2;

            if (curr.dir == 0) { // 가로
                nr2 = nr;
                nc2 = nc + 1;
            } else { // 세로
                nr2 = nr + 1;
                nc2 = nc;
            }

            // [수정] checkInMap 인자 4개 전달
            if (checkInMap(nr, nc, nr2, nc2) && !isWall(nr, nc, nr2, nc2)) {
                nexts.add(new Robot(nr, nc, curr.dir, curr.count + 1));
            }
        }

        // 2. 회전 로직
        if (curr.dir == 0) { // 가로 -> 세로 회전
            // 위쪽 두 칸 비었는지 확인
            if (checkInMap(curr.r - 1, curr.c, curr.r - 1, curr.c + 1) &&
                board[curr.r - 1][curr.c] == 0 && board[curr.r - 1][curr.c + 1] == 0) {
                nexts.add(new Robot(curr.r - 1, curr.c, 1, curr.count + 1));
                nexts.add(new Robot(curr.r - 1, curr.c + 1, 1, curr.count + 1));
            }
            // 아래쪽 두 칸 비었는지 확인
            if (checkInMap(curr.r + 1, curr.c, curr.r + 1, curr.c + 1) &&
                board[curr.r + 1][curr.c] == 0 && board[curr.r + 1][curr.c + 1] == 0) {
                nexts.add(new Robot(curr.r, curr.c, 1, curr.count + 1));
                nexts.add(new Robot(curr.r, curr.c + 1, 1, curr.count + 1));
            }
        } else { // 세로 -> 가로 회전
            // 왼쪽 두 칸 비었는지 확인
            if (checkInMap(curr.r, curr.c - 1, curr.r + 1, curr.c - 1) &&
                board[curr.r][curr.c - 1] == 0 && board[curr.r + 1][curr.c - 1] == 0) {
                nexts.add(new Robot(curr.r, curr.c - 1, 0, curr.count + 1));
                nexts.add(new Robot(curr.r + 1, curr.c - 1, 0, curr.count + 1));
            }
            // 오른쪽 두 칸 비었는지 확인
            if (checkInMap(curr.r, curr.c + 1, curr.r + 1, curr.c + 1) &&
                board[curr.r][curr.c + 1] == 0 && board[curr.r + 1][curr.c + 1] == 0) {
                nexts.add(new Robot(curr.r, curr.c, 0, curr.count + 1));
                nexts.add(new Robot(curr.r + 1, curr.c, 0, curr.count + 1));
            }
        }
        return nexts;
    }

    public int solution(int[][] board) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;
        this.visited = new boolean[N][M][2];

        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true; // [중요] 시작점 방문 처리

        while (!q.isEmpty()) {
            Robot cur = q.poll();

            // 한 칸이라도 (N-1, M-1)에 도달하면 정답
            int r2 = (cur.dir == 0) ? cur.r : cur.r + 1;
            int c2 = (cur.dir == 0) ? cur.c + 1 : cur.c;
            if ((cur.r == N - 1 && cur.c == M - 1) || (r2 == N - 1 && c2 == M - 1)) {
                return cur.count;
            }

            for (Robot next : getNextSteps(cur)) {
                if (!visited[next.r][next.c][next.dir]) {
                    visited[next.r][next.c][next.dir] = true;
                    q.add(next);
                }
            }
        }
        return 0;
    }
}