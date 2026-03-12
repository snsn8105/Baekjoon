import java.util.*;
class Solution {
    int[][] board;
    int moves = 0; // 이동 횟수
    
    int[] dc = {1, 0, -1, 0};
    int[] dr = {0, 1, 0, -1};
    // [승리여부, 최대 거리] 반환
    public int[] dfs(int[] me, int[] enemy) {
        boolean canWin = false;
        boolean canMove = false;
        int minValue = 20000; // 진 애의 값
        int maxValue = 0; // 이긴 애의 값
        
        // 종료 시엔 0, 0 을 반환해 재귀 호출이 끝나면서 1씩 더하는 방식으로 이동 거리를 계산
        if (board[me[0]][me[1]] == 0) {
            return new int[]{0, 0};
        }
        for (int i = 0 ; i < 4 ; i++) {
            int cr = me[0] + dr[i];
            int cc = me[1] + dc[i];
            
            // 맵 안에 있으면 실행
            if (checkInMap(cr, cc) && board[cr][cc] != 0) {
                canMove = true;
                board[me[0]][me[1]] = 0;
                // 상대 입장에서 실행
                int[] res = dfs(enemy, new int[]{cr, cc});
                board[me[0]][me[1]] = 1;
                // 상대가 졌으면
                if (res[0] == 0) {
                    canWin = true;
                    minValue = Math.min(minValue, res[1] + 1);
                } else if (!canWin) {
                    maxValue = Math.max(maxValue, res[1] + 1);
                }
            }
        }
        
        if (!canMove) {
            return new int[]{0, 0};
        }
        
        return new int[]{canWin ? 1 : 0 , canWin ? minValue : maxValue};
    }
    public boolean checkInMap(int r, int c) {
        if (r < board.length && r >= 0 && c < board[0].length && c >= 0)
            return true;
        else
            return false;
    }
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        int answer = -1;
        return dfs(aloc, bloc)[1];
    }
}