class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        // 누적합을 표현하는 4개의 좌표 sum에 저장 (r1이 세로)
        for (int[] s : skill) {
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            if (s[0] == 1) {
                sum[r1][c1] += -degree;
                sum[r1][c2+1] += degree;
                sum[r2+1][c1] += degree;
                sum[r2+1][c2+1] += -degree;
            }
            else if (s[0] == 2) {
                sum[r1][c1] += degree;
                sum[r1][c2+1] += -degree;
                sum[r2+1][c1] += -degree;
                sum[r2+1][c2+1] += degree;
            }
        }
        // 저장 된 결과들을 sweep 해 모든 좌표에 적용
        for (int i = 0 ; i < sum.length ; i++) {
            for (int j = 0 ; j < sum[0].length-1 ; j++) {
                sum[i][j+1] += sum[i][j];
            }
        }
        
        for (int i = 0 ; i < sum[0].length ; i++) {
            for (int j = 0 ; j < sum.length-1 ; j++) {
                sum[j+1][i] += sum[j][i];
            }
        }
        
        // 원래 board와 합쳐서 살아남은 건물 세기
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length ; j++) {
                if (board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}