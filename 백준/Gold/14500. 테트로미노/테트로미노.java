/* ************************************************************************** */
/*                                                                            */
/*                                                      :::    :::    :::     */
/*   Problem Number: 14500                             :+:    :+:      :+:    */
/*                                                    +:+    +:+        +:+   */
/*   By: snsn8105 <boj.kr/u/snsn8105>                +#+    +#+          +#+  */
/*                                                  +#+      +#+        +#+   */
/*   https://boj.kr/14500                          #+#        #+#      #+#    */
/*   Solved: 2025/07/07 11:47:09 by snsn8105      ###          ###   ##.kr    */
/*                                                                            */
/* ************************************************************************** */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] shapes = {
            // ㅡ 모양 (2가지 회전)
            {{0,0},{0,1},{0,2},{0,3}},
            {{0,0},{1,0},{2,0},{3,0}},
            // ■ 모양
            {{0,0},{0,1},{1,0},{1,1}},
            // L자형 4방향 × 2(뒤집기) = 8가지
            {{0,0},{1,0},{2,0},{2,1}},
            {{0,1},{1,1},{2,1},{2,0}},
            {{0,0},{0,1},{1,0},{2,0}},
            {{0,0},{0,1},{1,1},{2,1}},
            {{0,0},{1,0},{1,1},{1,2}},
            {{0,2},{1,2},{1,1},{1,0}},
            {{0,0},{0,1},{0,2},{1,0}},
            {{0,0},{0,1},{0,2},{1,2}},
            // S자형 2방향 × 2(뒤집기) = 4가지
            {{0,0},{0,1},{1,1},{1,2}},
            {{1,0},{1,1},{0,1},{0,2}},
            {{0,1},{1,1},{1,0},{2,0}},
            {{0,0},{1,0},{1,1},{2,1}},
            // ㅗ자형 4가지
            {{0,0},{0,1},{0,2},{1,1}},
            {{0,1},{1,0},{1,1},{2,1}},
            {{1,0},{1,1},{1,2},{0,1}},
            {{0,0},{1,0},{2,0},{1,1}}
        };

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] shape : shapes) {
                    int sum = 0;
                    boolean ok = true;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + shape[k][0], nj = j + shape[k][1];
                        if (ni < 0 || ni >= N || nj < 0 || nj >= M) {
                            ok = false;
                            break;
                        }
                        sum += map[ni][nj];
                    }
                    if (ok) ans = Math.max(ans, sum);
                }
            }
        }
        System.out.println(ans);

    }
}
