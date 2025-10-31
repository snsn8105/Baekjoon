import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // 1. int -> String 변환
        String[] strs = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            strs[i] = String.valueOf(numbers[i]);
        }

        // 2. Comparator로 정렬: (a+b) vs (b+a)
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String a, String b){
                return (b + a).compareTo(a + b);
             }
        });

        // 3. "0"만 있는 경우 처리
        if(strs[0].equals("0")) return "0";

        // 4. 연결
        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(s);
        }

        return sb.toString();
    }
}
