import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    public void dfs(int[][] board, int[][][] costs, int y, int x, int cost, int beforeDirection) {
        int[] dx = {1, 0, -1, 0}; // 동, 남, 서, 북 (시계방향)
        int[] dy = {0, 1, 0, -1};
        // bD -> 0 = 동, 1 = 남, 2 = 서, 3 = 북
        
        if (y == board.length-1 && x == board.length-1) {
            if (min > cost) {
                min = cost;
                return;
            }
            return;
        }
        
        if (cost > min) {
            return;
        }
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isInBoard(ny, nx, board.length, board.length) && board[ny][nx] == 0) {
                if (beforeDirection == i && costs[ny][nx][i] >= cost + 100) {
                    costs[ny][nx][i] = cost + 100;
                    dfs(board, costs, ny, nx, cost + 100, i);
                } else {
                    if (beforeDirection == -1) {
                        if (costs[ny][nx][i] >= cost + 100) {
                            costs[ny][nx][i] = cost + 100;
                            dfs(board, costs, ny, nx, cost + 100, i);
                        }
                    }
                    else
                        if (costs[ny][nx][i] >= cost + 600) {
                            costs[ny][nx][i] = cost + 600;
                            dfs(board, costs, ny, nx, cost + 600, i);
                        }
                }
            }
        }
        
        return;
    }
    
    public boolean isInBoard(int y, int x, int r, int c) {
        if (y < 0 || x < 0 || y >= r || x >= c)
            return false;
        else    
            return true;
    }
    
    public int solution(int[][] board) {
        // 방향을 추적해야함, 90도로 꺾일 때 500원
        // 
        int[][][] costs = new int[board.length][board.length][4];
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board.length ; j++) {
                for (int k = 0 ; k < 4 ; k++) {
                    costs[i][j][k] = 9999999;
                }
            }
        }
        
        dfs(board, costs, 0, 0, 0, -1);
        
        int answer = min;
        return answer;
    }
}