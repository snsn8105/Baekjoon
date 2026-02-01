import java.util.*;
class Solution {
    int[][] dice;
    boolean[] check;
    int winsA = 0;
    int winsB = 0;
    int maxWins = 0;
    int[] answer;
    void combination(int r, int c, int start, int count) {
        // 1. 조합 기저 조건 수정
        if (count == c) {
            List<Integer> aIdx = new ArrayList<>();
            List<Integer> bIdx = new ArrayList<>();
            for (int i = 0; i < r; i++) { // 0부터 r-1까지 확인
                if (check[i]) aIdx.add(i);
                else bIdx.add(i);
            }

            List<Integer> aSums = new ArrayList<>();
            List<Integer> bSums = new ArrayList<>();

            // 0번 깊이부터 시작하도록 호출
            calcSum(0, 0, aIdx, aSums); 
            calcSum(0, 0, bIdx, bSums);

            // 이후 승수 계산 및 maxWins 갱신 로직...
            Collections.sort(bSums);
            int currentWins = calcWins(aSums, bSums);

            if (currentWins > maxWins) {
                maxWins = currentWins;
                // answer 배열에 1부터 시작하는 주사위 번호 저장
                for(int i = 0; i < aIdx.size(); i++) {
                    answer[i] = aIdx.get(i) + 1;
                }
            }
            return;
        }
        for (int i = start ; i < r ; i++) {
            check[i] = true;
            combination(r, c, i + 1, count + 1);
            check[i] = false;
        }
    }
    // 2. calcSum 함수 수정 (0-based depth)
    void calcSum(int depth, int sum, List<Integer> diceIndices, List<Integer> sums) {
        if (depth == diceIndices.size()) {
            sums.add(sum);
            return;
        }

        int currentDiceIdx = diceIndices.get(depth); // 현재 depth를 인덱스로 사용
        for (int i = 0; i < 6; i++) {
            calcSum(depth + 1, sum + dice[currentDiceIdx][i], diceIndices, sums);
        }
    }
    // 승 수 계산
    int calcWins(List<Integer> aSums, List<Integer> bSums) {
        int wins = 0;
        for (int a : aSums) {
            int left = 0 ;
            int right = bSums.size();
            
            while (left < right) {
                int mid = (left + right) / 2;
                if (bSums.get(mid) < a)
                    left = mid + 1;
                else
                    right = mid;
            }
            wins += left;
        }
        
        return wins;
    }
    public int[] solution(int[][] dice) {
        // 완전 탐색
        this.dice = dice;
        check = new boolean[dice.length+1];
        int r = dice.length;
        int c = dice.length / 2;
        answer = new int[c];
        combination(r, c, 0, 0);

        return answer;
    }
    
}