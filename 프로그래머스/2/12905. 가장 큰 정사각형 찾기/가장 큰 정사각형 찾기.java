import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int R = board.length;
        int C = board[0].length;
        int max = 0;
        
        if (R < 2 || C < 2) {
            for (int i = 0 ; i < R ; i++) {
                for (int j = 0 ; j < C ; j++) {
                    if (board[i][j] == 1)
                        return 1;
                }
            }
        }
        // 왼쪽, 위, 왼쪽 대각선 위가 정사각형이면 +1해서 board에 저장
        for (int i = 1 ; i < R ; i++) {
            for (int j = 1 ; j < C ; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(Math.min(board[i-1][j], board[i-1][j-1]), board[i][j-1]) + 1;
                    max = Math.max(max, board[i][j]);
                }
            }
        }
        return max * max;
    }
}