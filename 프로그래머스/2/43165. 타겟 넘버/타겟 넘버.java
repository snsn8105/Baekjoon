class Solution {
    public int solution(int[] numbers, int target) {
        // 시동을 겁니다: 0번째 인덱스부터, 현재 합계는 0
        return dfs(numbers, target, 0, 0);
    }

    private int dfs(int[] numbers, int target, int idx, int sum) {
        // [Base Case] 모든 숫자를 다 사용했을 때
        if (idx == numbers.length) {
            // 합계가 타겟과 같다면 1개 방법 찾음, 아니면 0개
            return sum == target ? 1 : 0;
        }

        // 왼쪽 가지의 성공 횟수 + 오른쪽 가지의 성공 횟수
        return dfs(numbers, target, idx + 1, sum + numbers[idx]) 
             + dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}