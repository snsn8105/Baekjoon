import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // 로봇 청소기의 행 좌표 (북쪽부터)
        int c = Integer.parseInt(st.nextToken()); // 로봇 청소기의 열 좌표 (서쪽부터)
        int d = Integer.parseInt(st.nextToken()); // 방향 (0=북, 1=동, 2=남, 3=서)

        int[][] room = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cleanedCount = cleaningRoom(room, r, c, d, N, M);
        System.out.println(cleanedCount);
    }

    static int cleaningRoom(int[][] room, int r, int c, int d, int N, int M) {
        int count = 0;
        int y = r; // 행 좌표
        int x = c; // 열 좌표

        // 방향 벡터 (북, 동, 남, 서)
        // 북: y-1, 동: x+1, 남: y+1, 서: x-1
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        while (true) {
            // 현재 위치가 청소되지 않은 칸이면 청소
            if (room[y][x] == 0) {
                room[y][x] = 2; // 청소 완료 상태
                count++;
            }

            // 주변 4칸에 청소되지 않은 칸이 있는지 확인 (반시계 방향으로 회전)
            boolean foundNewSpot = false;
            int currentDirection = d;
            for (int i = 0; i < 4; i++) {
                currentDirection = (currentDirection + 3) % 4;
                int nx = x + dx[currentDirection];
                int ny = y + dy[currentDirection];

                if (room[ny][nx] == 0) {
                    // 청소되지 않은 칸 발견 → 해당 칸으로 이동
                    x = nx;
                    y = ny;
                    d = currentDirection;
                    foundNewSpot = true;
                    break;
                }
            }

            // 주변 4칸 모두 청소할 수 없는 경우 → 후진 시도
            if (!foundNewSpot) {
                int backDirection = (d + 2) % 4;
                int bx = x + dx[backDirection];
                int by = y + dy[backDirection];

                // 후진이 가능하면 후진, 벽이면 작동 멈춤
                if (room[by][bx] != 1) {
                    x = bx;
                    y = by;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}
