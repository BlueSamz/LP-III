package classs02;
import java.util.Scanner;

public class arreglos {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[]args) {
		int [] arreglo = {1, 2, 3, 4, 5, 6};
		int suma = 0;
		
		for(int i = 0; i < arreglo.length; i++) {
			suma += arreglo[i];
		}
		System.out.println("Suma: "+ suma);
	}
}
