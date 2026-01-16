import java.util.*;

class Solution {
    // 사용자님이 정의한 static 변수 유지
    static int[][] boardCopy;
    static int[][] tableCopy;
    static boolean[][] tableCheck;
    static boolean[][] boardCheck;
    
    // 조각을 찾는 DFS (사용자님 로직 보완)
    public void findPuzzle(int x, int y, int[][] grid, boolean[][] visited, List<int[]> puzzleIndice, int target) {
        int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; 

        for (int[] dxy : d) {
            int nx = x + dxy[0];
            int ny = y + dxy[1];

            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid.length) {
                // 아직 방문하지 않았고, 찾는 대상(조각 1 또는 빈칸 0)인 경우
                if (!visited[ny][nx] && grid[ny][nx] == target) {
                    visited[ny][nx] = true;
                    puzzleIndice.add(new int[]{nx, ny});
                    findPuzzle(nx, ny, grid, visited, puzzleIndice, target);
                } 
            }
        }
    }

    // 좌표를 (0,0) 기점으로 맞추고 정렬하는 정규화 함수
    public List<int[]> normalize(List<int[]> piece) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] p : piece) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        for (int[] p : piece) {
            p[0] -= minX;
            p[1] -= minY;
        }

        // 좌표 비교를 위해 정렬 (x기준 오름차순, 같으면 y기준)
        piece.sort((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        return piece;
    }

    // 조각을 90도 회전시키는 함수
    public List<int[]> rotate(List<int[]> piece) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] p : piece) {
            // (x, y) -> (y, -x) 회전 공식
            rotated.add(new int[]{p[1], -p[0]});
        }
        return normalize(rotated);
    }

    public int solution(int[][] game_board, int[][] table) {
        this.boardCopy = game_board;
        this.tableCopy = table;
        int n = table.length;

        tableCheck = new boolean[n][n];
        boardCheck = new boolean[n][n];
        
        // 1. table에서 퍼즐 조각들 추출
        List<List<int[]>> pieces = new ArrayList<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (table[y][x] == 1 && !tableCheck[y][x]) {
                    List<int[]> piece = new ArrayList<>();
                    tableCheck[y][x] = true;
                    piece.add(new int[]{x, y});
                    findPuzzle(x, y, table, tableCheck, piece, 1);
                    pieces.add(normalize(piece));
                }
            }
        }

        // 2. game_board에서 빈칸들 추출
        List<List<int[]>> blanks = new ArrayList<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (game_board[y][x] == 0 && !boardCheck[y][x]) {
                    List<int[]> blank = new ArrayList<>();
                    boardCheck[y][x] = true;
                    blank.add(new int[]{x, y});
                    findPuzzle(x, y, game_board, boardCheck, blank, 0);
                    blanks.add(normalize(blank));
                }
            }
        }

        // 3. 빈칸에 맞는 퍼즐 찾기
        int answer = 0;
        boolean[] pieceUsed = new boolean[pieces.size()];

        for (List<int[]> blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (pieceUsed[i] || blank.size() != pieces.get(i).size()) continue;

                List<int[]> currentPiece = pieces.get(i);
                boolean isMatch = false;

                // 4번 회전하며 확인
                for (int r = 0; r < 4; r++) {
                    if (compare(blank, currentPiece)) {
                        isMatch = true;
                        break;
                    }
                    currentPiece = rotate(currentPiece);
                }

                if (isMatch) {
                    pieceUsed[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    // 두 좌표 리스트가 일치하는지 비교하는 함수
    public boolean compare(List<int[]> a, List<int[]> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
}