import java.util.*;
class Solution {
    int[][] rotate(int[][] key) {
        int[][] newKey = new int[key.length][key[0].length]; 
        
        for (int i = 0 ; i < key.length ; i++) {
            for (int j = 0 ; j < key[0].length ; j++) {
                newKey[j][key.length - 1 -i] = key[i][j];
            }
        }    
        
        return newKey;
    }
    boolean check(int[][] newLock, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 자물쇠 시작 위치는 (M-1, M-1)이었죠?
                if (newLock[i + M - 1][j + M - 1] != 1) {
                    return false; // 0(홈 안채워짐)이나 2(돌기 충돌)면 탈락!
                }
            }
        }
        return true;
    }
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        int[][] newLock = new int[N + (M - 1) * 2][N + (M - 1) * 2];

        // 1. 자물쇠 중앙 배치 (여기 j < N 인거 꼭 확인!)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        int[][] currentKey = key; // 원본부터 시작

        // 2. 4방향 회전 루프
        for (int a = 0; a < 4; a++) {
            // 이동 루프 (i, j)
            for (int i = 0; i < N + M - 1; i++) {
                for (int j = 0; j < N + M - 1; j++) {

                    // 열쇠 더하기
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            newLock[i + k][j + l] += currentKey[k][l];
                        }
                    }

                    // 검사
                    if (check(newLock, N, M)) return true;

                    // 열쇠 다시 빼기
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            newLock[i + k][j + l] -= currentKey[k][l];
                        }
                    }
                }
            }
            currentKey = rotate(currentKey); // 다음 루프를 위해 회전
        }
        return false;
    }
}