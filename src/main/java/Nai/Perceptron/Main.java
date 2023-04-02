package Nai.Perceptron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Algorytm a = new Algorytm(0.01, 10000, "iris");

    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);
        boolean work = true;

        System.out.println();


        a.ucz();
        a.run(true);

        while (a.acc < 0.95){
            a.ucz();
            a.run(true);
        }

        while (true) {
            System.out.println("Co chcesz zrobić?");
            System.out.println("1 - ucz");
            System.out.println("2 - sprawdź własny wektor");
            System.out.println("3 - pokaz wagi");
            int test = Integer.parseInt(sc.nextLine().trim());
            switch (test) {
                case 1 :
                    a.ucz();
                    a.run(true);
                    break;
                case 2 :
                    double[] punkty = new double[LoadCSV.length];
                    for (int i = 0; i < LoadCSV.length; i++) {
                        System.out.println("Podaj wymiar: " + i);
                        punkty[i] = Double.parseDouble(sc.nextLine());
                    }
                    Vektor v = new Vektor("null", punkty);
                    ArrayList<Vektor> sprawdz = new ArrayList<>();
                    sprawdz.add(v);
                    a.setTestArray(sprawdz);
                    a.run(true);
                    break;
                case 3:
                    a.wagi();
                    break;
                default:
                    System.out.println("Niepoprawny numer");
                    break;
            }
        }
    }
}