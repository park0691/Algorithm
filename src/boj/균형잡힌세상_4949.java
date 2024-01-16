package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 균형잡힌세상_4949 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> s = new Stack<>();
        while (true) {
            String inputString = br.readLine();
            if (".".equals(inputString)) break;
            char[] input = inputString.toCharArray();
            boolean valid = true;
            for (char c : input) {
                if (c == '(' || c == '[') {
                    s.push(c);
                }
                if (c == ')') {
                    if (s.isEmpty() || s.pop() != '(') {
                        valid = false;
                        break;
                    }
                } else if (c == ']') {
                    if (s.isEmpty() || s.pop() != '[') {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid && !s.isEmpty()) {
                valid = false;
            }
            s.clear();
            System.out.println(valid ? "yes" : "no");
        }
    }
}
