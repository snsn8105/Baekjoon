import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Point{
        int x, y, time;
        boolean hasGram;

        public Point(int x, int y, int time, boolean hasGram){
            this.x = x;
            this.y = y;
            this.time = time;
            this.hasGram = hasGram;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken()); // T시간 내 최단시간에 구출

        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[N][M][2]; // [][][0]은 그람이 없을때 ,[][][1]은 그람 소유시

        Queue<Point> queue = new LinkedList<>();

        int gramX = 0, gramY = 0;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    visited[i][j][1] = true;
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int answer = 0; // 걸린시간 저장

        queue.add(new Point(0, 0, 0, false));
        // 1. (0,0)에서 빈 공간을 찾아 이동하면서 visited가 1인 칸을 만나면 현재 시간을 1씩 뺀다.
        while (!queue.isEmpty()) {
            Point p = queue.poll(); // 큐에서 하나씩 꺼내서 확인인
            
            // p.time이 T이상이면 스킵
            if(p.time > T){
                continue;
            }
            // 큐에서 나온 칸이 (N,M)이면 종료
            if(p.x == M-1 && p.y == N-1){
                answer = p.time;
                break;
            }
            for(int i = 0 ; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 범위 내에서 이동 가능한지 체크
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (!p.hasGram) { // 그람이 없을 때
                        if (map[ny][nx] == 0 && !visited[ny][nx][0]) {
                            visited[ny][nx][0] = true;
                            queue.add(new Point(nx, ny, p.time + 1, false));
                        } else if (map[ny][nx] == 2) { // 그람 획득
                            visited[ny][nx][1] = true;
                            queue.add(new Point(nx, ny, p.time + 1, true));
                        }
                    } else { // 그람이 있을 때 벽도 통과 가능
                        if (!visited[ny][nx][1]) {
                            visited[ny][nx][1] = true;
                            queue.add(new Point(nx, ny, p.time + 1, true));
                        }
                    }
                }
            }
        }
        if(answer == 0)
            System.out.println("Fail");
        else
            System.out.println(answer);
    }
}
