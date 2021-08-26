package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * Stack, Postfix Notation
 */
public class 계산기2_1223 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> st = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			st.clear();
			sb.setLength(0);
			br.readLine();
			char[] expressions = br.readLine().toCharArray();
			
			for (char c : expressions) {
				if (Character.isDigit(c)) {
					sb.append(c);
				} else {
					if (st.isEmpty()) st.push(c);
					// 현재 연산자가 우선순위가 높으면 스택에 넣는다.
					else if (getPriority(c) > getPriority(st.peek())) st.push(c);
					// 현재 연산자보다 우선순위가 낮은게 있을 때까지 빼서 스택에서 뺀다.
					else {
						while (!st.isEmpty() && getPriority(c) <= getPriority(st.peek()))
							sb.append(st.pop());
						st.push(c);
					}
				}
			}
			while (!st.isEmpty()) sb.append(st.pop());
			
			String postfixNotation = sb.toString();
			System.out.println("#" + t + " " + evaluate(postfixNotation));
		}
	}
	
	static int evaluate(String expression) {
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (Character.isDigit(c)) {
				st.push(c - '0');
			} else {
				int n2 = st.pop();
				int n1 = st.pop();
				int result = 0;
				if (c == '+') result = (n1 + n2);
				else if (c == '*') result = (n1 * n2);
				st.push(result);
			}
		}
		
		return st.pop();
	}
	
	static int getPriority(char c) {
		if (c == '*')
			return 1;
		else
			return 0;
	}
}
