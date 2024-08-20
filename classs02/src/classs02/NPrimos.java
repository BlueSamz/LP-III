package classs02;
import java.util.Scanner;

public class NPrimos {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Introduce un número: ");
		int n = input.nextInt();
		imprimirNumerosPrimos(n);
		}
		public static boolean esPrimo(int num) {
			if (num <= 1) {
				return false;
			}
			for (int i = 2; i <= num / 2; i++) {
				if (num % i == 0) {
					return false;
				}
			}
			return true;
		}
		public static void imprimirNumerosPrimos(int n) {
		for (int num = 2; num <= n; num++) {
		if (esPrimo(num)) {
		System.out.println(num + " es primo.");
		}
		}
		}

}
