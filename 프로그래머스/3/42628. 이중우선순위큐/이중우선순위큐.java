import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pqASC = new PriorityQueue<>(); // 최소힙
        PriorityQueue<Integer> pqDESC = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙

        for(String op : operations){
            if(op.charAt(0) == 'I'){
                int num = Integer.parseInt(op.substring(2));
                pqASC.add(num);
                pqDESC.add(num);
            }
            // 최댓값 삭제
            else if(op.equals("D 1")){
                if(!pqDESC.isEmpty()){
                    int max = pqDESC.poll(); // 최대값 꺼내기
                    pqASC.remove(max);       // 반대 큐에서도 제거
                }
            }
            // 최솟값 삭제
            else if(op.equals("D -1")){
                if(!pqASC.isEmpty()){
                    int min = pqASC.poll();  // 최소값 꺼내기
                    pqDESC.remove(min);      // 반대 큐에서도 제거
                }
            }
        }

        int[] answer = new int[2];
        if(!pqASC.isEmpty()){
            answer[0] = pqDESC.peek(); // 최대값
            answer[1] = pqASC.peek();  // 최소값
        } else {
            answer[0] = 0;
            answer[1] = 0;
        }

        return answer;
    }
}
