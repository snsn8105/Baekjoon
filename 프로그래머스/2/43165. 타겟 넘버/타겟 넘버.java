class Solution {
    int count = 0;
    public void dfs(int value, int depth, int[] numbers, int target) {
        
        if (depth == numbers.length) { 
            if (value == target) {
                count++;
            }
            return;
        }
        dfs(value + numbers[depth], depth+1, numbers, target);
        dfs(value - numbers[depth], depth+1, numbers, target);
        
        return;
    }
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        
       return count;
    }
}