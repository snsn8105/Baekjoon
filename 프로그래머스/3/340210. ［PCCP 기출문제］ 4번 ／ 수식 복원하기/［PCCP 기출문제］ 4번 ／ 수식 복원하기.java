import java.util.*;
class Solution {
    int toDecimal(int base, String a) {
        int result = 0;
        for (char c : a.toCharArray()) {
            result = result * base + (c - '0');
        }
        return result;
    }
    String fromDecimal(int base, int a) {
        StringBuilder sb = new StringBuilder();
        
        if (a == 0)
            return "0";
        while (a > 0) {
            sb.insert(0, a % base);
            a = a / base;
        }
        return sb.toString();
    }

    public String[] solution(String[] expressions) {
        List<String[]> expList = new ArrayList<>();
        List<String[]> xList = new ArrayList<>();
        boolean[] bases = new boolean[10];
        for (int i = 2; i < 10; i++) bases[i] = true;

        int maxDigit = 0;
        for (String e : expressions) {
            String[] tmp = e.split(" ");

            // 0, 2, 4번 위치의 모든 숫자를 확인해서 최소 진법 파악
            for (int i = 0; i <= 4; i += 2) {
                if (tmp[i].equals("X")) continue;
                for (char c : tmp[i].toCharArray()) {
                    maxDigit = Math.max(maxDigit, c - '0');
                }
            }

            if (tmp[4].equals("X")) xList.add(tmp);
            else expList.add(tmp);
        }

        // 최소 진법 미만은 모두 제외 (maxDigit이 3이면 2,3진법 제외)
        for (int i = 2; i <= maxDigit; i++) {
            if (i < 10) bases[i] = false;
        }

        // 기성 수식으로 진법 필터링
        for (String[] el : expList) {
            for (int i = 2; i < 10; i++) {
                if (!bases[i]) continue;
                int A = toDecimal(i, el[0]);
                int B = toDecimal(i, el[2]);
                int R = toDecimal(i, el[4]);
                if (el[1].equals("+")) {
                    if (A + B != R) bases[i] = false;
                } else {
                    if (A - B != R) bases[i] = false;
                }
            }
        }
        String[] answer = new String[xList.size()];

        // xList 계산 결과 예측
        int idx = 0;
        for (String[] xl : xList) {
            String resultStr = "";
            for (int i = 2 ; i < 10 ; i++) {
                if (!bases[i])
                    continue;
                int base = i;
                int A = toDecimal(base, xl[0]);
                int B = toDecimal(base, xl[2]);
                    
                if (xl[1].equals("+")) {
                    if (resultStr.equals(""))
                        resultStr = fromDecimal(i, A + B);
                    else if (!resultStr.equals(fromDecimal(i, (A+B)))) {
                        resultStr = "?";
                        break;
                    }
                    else
                        resultStr = fromDecimal(i, A+B);
                }
                else {
                    if (resultStr.equals(""))
                        resultStr = fromDecimal(i, A - B);
                    else if (!resultStr.equals(fromDecimal(i, (A - B)))) {
                        resultStr = "?";
                        break;
                    }
                    else
                        resultStr = fromDecimal(i, A-B);
                }
            }
            
            answer[idx] = xl[0] + " " + xl[1] + " " + xl[2] + " " + xl[3] + " " + resultStr;
            idx++;
        }
        
        return answer;
    }
}