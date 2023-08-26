package boj;

import java.util.Scanner;

/*
	(a + b) mod N = [(a mod N) + (b mod N)] mod N
	(a - b) mod N = [(a mod N) - (b mod N)] mod N
	(a * b) mod N = [(a mod N) * (b mod N)] mod N
 */
public class Hashing_15829 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		long sum = 0;
		long pow = 1;

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int k = c - 'a' + 1;
			sum += k * pow;
			pow = (pow * 31) % 1234567891;
		}
		
		System.out.println(sum % 1234567891);
	}
}
