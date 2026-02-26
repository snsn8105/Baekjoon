import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 가장 작은 수 저장할 배열
        int[] answer = new int[queries.length];
        int[][] map = new int[rows+1][columns+1];
        
        int c = 1;
        for (int i = 1 ; i <= rows ; i++) {
            for (int j = 1 ; j <= columns ; j++) {
                map[i][j] = c;
                c++;
            }
        }
        int[][] copyMap = new int[rows+1][columns+1];
        int idx = 0;
        for (int[] q: queries) {
            for (int i = 1 ; i <= rows ; i++) {
                copyMap[i] = Arrays.copyOf(map[i], columns+1);
            }
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];
            PriorityQueue<Integer> pq = new PriorityQueue<>(); // pq에 넣고 맨 앞에꺼 꺼내기
            // 1. 가로 회전 (x1, y1 ~ y2-1)
            for (int y = y1 ; y < y2 ; y++) {
                int cx = x1;
                int cy = y;
                pq.add(map[cx][cy]);
                copyMap[cx][cy+1] = map[cx][cy];
            }
            
            // 2. 세로 회전 (x1 ~ x2 - 1, y2)
            for (int x = x1 ; x < x2 ; x++) {
                int cx = x;
                int cy = y2;
                pq.add(map[cx][cy]);
                copyMap[cx+1][cy] = map[cx][cy];
            }
            // 3. 가로 회전 (x2, y2 ~ y1 + 1) 
            for (int y = y2 ; y > y1 ; y--) {
                int cx = x2;
                int cy = y;
                pq.add(map[cx][cy]);
                copyMap[cx][cy-1] = map[cx][cy];
            }
            
            // 4. 세로 회전 (x2 ~ x1 + 1 , y1)
            for (int x = x2 ; x > x1 ; x--) {
                int cx = x;
                int cy = y1;
                pq.add(map[cx][cy]);
                copyMap[cx-1][cy] = map[cx][cy];
            }
            
            // 변경사항 map에 저장
            for (int i = 1 ; i <= rows ; i++) {
                map[i] = Arrays.copyOf(copyMap[i], columns+1);
            }
            
            answer[idx] = pq.poll();
            idx++;
        }
        return answer;
    }
}