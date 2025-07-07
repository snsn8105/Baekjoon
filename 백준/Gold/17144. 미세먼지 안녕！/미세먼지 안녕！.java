/* ************************************************************************** */
/*                                                                            */
/*                                                      :::    :::    :::     */
/*   Problem Number: 17144                             :+:    :+:      :+:    */
/*                                                    +:+    +:+        +:+   */
/*   By: snsn8105 <boj.kr/u/snsn8105>                +#+    +#+          +#+  */
/*                                                  +#+      +#+        +#+   */
/*   https://boj.kr/17144                          #+#        #+#      #+#    */
/*   Solved: 2025/07/04 13:01:11 by snsn8105      ###          ###   ##.kr    */
/*                                                                            */
/* ************************************************************************** */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// 참조를 위한 래퍼 클래스
class Cell {
    int amount;
    public Cell(int amount) { this.amount = amount; }
}

public class Main {
    static int R, C, T;
    static Cell[][] room;
    static Queue<Cell> upperWind = new LinkedList<>();
    static Queue<Cell> lowerWind = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new Cell[R][C];
        int top = -1, bot = -1;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int v = Integer.parseInt(st.nextToken());
                room[i][j] = new Cell(v);
                if (v == -1) {
                    if (top < 0) top = i;
                    else bot = i;
                }
            }
        }

        setUpperWind(top);
        setLowerWind(bot);

        for (int t = 0; t < T; t++) {
            dustSpread();
            circulate(upperWind);
            circulate(lowerWind);
        }

        int sum = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (room[i][j].amount > 0)
                    sum += room[i][j].amount;

        bw.write(String.valueOf(sum));
        bw.flush();
    }

    // 먼지 확산
    static void dustSpread() {
        int[][] delta = new int[R][C];
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int amt = room[i][j].amount;
                if (amt > 0) {
                    int spread = amt / 5, cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dr[d], nj = j + dc[d];
                        if (ni < 0 || ni >= R || nj < 0 || nj >= C) continue;
                        if (room[ni][nj].amount == -1) continue;
                        delta[ni][nj] += spread;
                        cnt++;
                    }
                    room[i][j].amount -= spread * cnt;
                }
            }
        }
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                room[i][j].amount += delta[i][j];
    }

    // 공기 순환
    static void circulate(Queue<Cell> wind) {
        Iterator<Cell> it = wind.iterator();
        int prev = 0;
        while (it.hasNext()) {
            Cell cell = it.next();
            if (cell.amount != -1) {
                int tmp = cell.amount;
                cell.amount = prev;
                prev = tmp;
            }
        }
    }

    // 상단 공기 순환 경로 (반시계 방향)
    static void setUpperWind(int top) {
        // 1) 오른쪽
        for (int j = 1; j < C; j++)             upperWind.add(room[top][j]);
        // 2) 위쪽
        for (int i = top - 1; i >= 0; i--)      upperWind.add(room[i][C - 1]);
        // 3) 왼쪽
        for (int j = C - 2; j >= 0; j--)        upperWind.add(room[0][j]);
        // 4) 아래쪽
        for (int i = 1; i < top; i++)           upperWind.add(room[i][0]);
    }

    // 하단 공기 순환 경로 (시계 방향)
    static void setLowerWind(int bot) {
        // 1) 오른쪽
        for (int j = 1; j < C; j++)             lowerWind.add(room[bot][j]);
        // 2) 아래쪽
        for (int i = bot + 1; i < R; i++)       lowerWind.add(room[i][C - 1]);
        // 3) 왼쪽
        for (int j = C - 2; j >= 0; j--)        lowerWind.add(room[R - 1][j]);
        // 4) 위쪽
        for (int i = R - 2; i > bot; i--)       lowerWind.add(room[i][0]);
    }
}