package com.google;

public class Factorial {

	public static void main(String[] args) {

		factorial(6);
		System.out.println(factorialRecur(6));

		factorialLarge(100);
	}

	private static void factorialLarge(int n) {

		int res[] = new int[500];

		res[0] = 1;
		int ptr = 1;

		for (int i = 2; i <= n; i++)
			ptr = multiply(i, res, ptr);

		System.out.println("Factorial of given number is ");
		System.out.println(ptr);
		for (int i = ptr - 1; i >= 0; i--)
			System.out.print(res[i]);

	}

	private static int multiply(int x, int[] res, int ptr) {

		int carry = 0;

		for (int i = 0; i < ptr; i++) {
			int prod = res[i] * x + carry;
			res[i] = prod % 10;
			carry = prod / 10;
		}

		while (carry != 0) {
			res[ptr] = carry % 10;
			carry = carry / 10;
			ptr++;
		}
		return ptr;
	}

	private static int factorialRecur(int n) {

		if (n == 0)
			return 1;

		return n * factorialRecur(n - 1);
	}

	private static void factorial(int n) {

		int res = 1;
		for (int i = 2; i <= n; i++)
			res = res * i;
		System.out.println(res);

	}

}
