class Solution {
    public int[] solution(int e, int[] starts) {
        // 100 -> 1, 2, 5, 10, 20, 50, 100
        // 약수 개수가 가장 큰 것 중에 값이 가장 작은 원소
        // 전처리 과정
        int[] counts = new int[e+1]; // 1~e까지 수의 약수의 개수를 저장
        
        for (int i = 1 ; i <= e ; i++) {
            for (int j = 1 ; j * i <= e ; j++) {
                counts[i*j]++;
            }
        }
        
        // starts의 원소와 e 사이의 수 중 약수가 가장 많고 값이 가장 작은 수 구하기
        // 새로운 배열에 약수 개수를 저장
        int[] result = new int[e+1];
        int maxValue = 0;
        int maxIdx = 0;
        for (int i = e ; i >= 1 ; i--) {
            if (maxValue <= counts[i]) {
                maxValue = counts[i];
                maxIdx = i;
            }
            result[i] = maxIdx;
        }
        int[] answer = new int[starts.length];
        
        for (int i = 0 ; i < starts.length ; i++) {
            answer[i] = result[starts[i]];
        }
        return answer;
    }
}