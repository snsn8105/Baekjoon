import java.util.*;
class Solution {
    int[] parent = new int[2501]; // 2차원 배열을 1차원으로 표현, 50*(r-1) + c
    String[] values = new String[2501];
    public int find(int cell) {
        if (parent[cell] == cell)
            return cell;
        else
            return parent[cell] = find(parent[cell]); 
    }
    public void union(int cell1, int cell2) { // (r1,c1), (r2, c2)를 변환해서 넣음
        int root1 = find(cell1); // 대표 cell에는 자기 자신 대입
        int root2 = find(cell2);
        
        if (root1 == root2) 
            return;
        String value1 = values[root1];
        String value2 = values[root2];
        
        parent[root2] = root1;
        values[root2] = "";
        if (value1.equals("") && !value2.equals("")) {
            values[root1] = value2;
        }
    }
    public void unmerge(int cell) {
        int root = find(cell);
        String rootValue = values[root];

        List<Integer> targets = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == root) targets.add(i);
        }

        for (int idx : targets) {
            parent[idx] = idx;
            values[idx] = "";
        }
        values[cell] = rootValue; // 원래 요청받은 위치에만 값 복구
    }
    public void update(String value1, String value2) {
        for (int i = 1 ; i <= 2500 ; i++) {
            if (values[i].equals(value1)) {
                values[i] = value2;
            }
        }
    }
    public void update(int cell, String value) {
        values[find(cell)] = value;
    }
    
    public String print(int cell) {
        int root = find(cell);
        if (values[root].equals(""))
            return "EMPTY";
        else
            return values[root];
    }
    private int convert(int r, int c) {
        return (r-1) * 50 + c;
    }
    public String[] solution(String[] commands) {
        // 1. 초기화 (매우 중요!)
        // values 배열을 null이 아닌 ""로 채우고, 
        // parent 배열을 각자 자기 자신(i)으로 초기화하세요.
        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
            values[i] = "";
        }

        List<String> result = new ArrayList<>(); // PRINT 결과를 담을 리스트

        for (String command : commands) {
            String[] split = command.split(" ");
            String type = split[0];

            if (type.equals("UPDATE")) {
                // Case 1: UPDATE r c value (인자 4개)
                if (split.length == 4) {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    String value = split[3];
                    update(convert(r,c), value);
                } 
                // Case 2: UPDATE value1 value2 (인자 3개)
                else {
                    update(split[1], split[2]);
                }
            } 
            else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(split[1]);
                int c1 = Integer.parseInt(split[2]);
                int r2 = Integer.parseInt(split[3]);
                int c2 = Integer.parseInt(split[4]);
                union(convert(r1, c1), convert(r2, c2));
            } 
            else if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                unmerge(convert(r, c));
            } 
            else if (type.equals("PRINT")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                result.add(print(convert(r, c)));
            }
        }

        // 결과 리스트를 String[] 배열로 변환해서 반환
        return result.toArray(new String[0]);
    }
}