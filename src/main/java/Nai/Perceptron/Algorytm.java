package Nai.Perceptron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Algorytm {

    private ArrayList<Vektor> testArray;
    private final ArrayList<Vektor> trainingArray;
    private double[] weights;
    private double theta, alpha;
    private int epoki;
    public double acc;

    public Algorytm(double alpha, int epoki, String model)
    {
        testArray = LoadCSV.parse("DATA\\" + model +".test.data");
        trainingArray = LoadCSV.parse("DATA\\" + model + ".data");
        randomWagaiTeta();
        this.alpha = alpha;
        this.epoki = epoki;

    }


    //Losowanie teta i wag dla każdego wejścia
    private void randomWagaiTeta() {
        theta = (Math.random());
        weights = new double[LoadCSV.length];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random());
        }
    }

    // Określenie rodzaju/gatunku na podstawie wszystkich wag
    private int ewaluacja(Vektor vektor)
    {
        double netValue = theta;

        for (int i = 0; i < vektor.getPoints().length; i++) {
            netValue += vektor.getPoints()[i] * weights[i];
        }

        return (netValue >= this.theta? 1:0);
    }

    // uczy się i określa nową tetę i wagi dla każdego vektora
    //aktualizuje wagi perceptronu i bias w taki sposób, aby zminimalizować błąd klasyfikacji na zestawie danych wejściowych.
    public void ucz() {
        for (int i = 0; i < epoki; i++) { //Iteracja epok
            int errors = 0;

            for (Vektor v : trainingArray) {
                int actualDeterminant = 0, writtenDeterminant = ewaluacja(v); //obliczamy determinante

                for (Map.Entry<String, Integer> e : LoadCSV.wynik.entrySet())
                    if (e.getKey().equals(v.getName()))
                        actualDeterminant = e.getValue();

                int error = actualDeterminant - writtenDeterminant; //błąd klasyfikacji

                errors += error;



                //na podstawie błędu i alfa aktualizuję wagi
                for (int j = 0; j < LoadCSV.length; j++) {
                    weights[j] += alpha * v.getPoints()[j] * error;
                }

                this.theta += errors * alpha; // aktualizuja bias

            }
            if (errors == 0) break;
        }
    }

    // określa gatunek danych na podstawie treningowych
    public void run(boolean czyPokazacWszystko) {
        int actualDeterminant;
        double total = 0, found = 0;
        System.out.println("Jak powinno być [wymiary] -> wynik");
        for (Vektor v : testArray) {
            actualDeterminant = ewaluacja(v);   //Obliczamy wartość wyjścia (determinanty)


            //Przechodzimy przez mapę "LoadCSV.wynik", aby znaleźć nazwę gatunku, którego determinant odpowiada wartości wyjściowej (actualDeterminant) i przypisujemy ją do zmiennej "gatunek".
            String gatunek= null;
            for (Map.Entry<String, Integer> e : LoadCSV.wynik.entrySet())
                if (actualDeterminant == e.getValue())
                    gatunek = e.getKey();

            if (czyPokazacWszystko && v.getName().equals(gatunek))
                found++;
            total++;


        System.out.println((czyPokazacWszystko? v.getName():"") + Arrays.toString(v.getPoints()) + " -> " + gatunek);

        }
        if (czyPokazacWszystko) System.out.println("Dokładność: " + found/total);
        this.acc = found/total;
    }

    public void setTestArray(ArrayList<Vektor> testArray) {this.testArray = testArray;}

    public void wagi() {
        System.out.println("-------------");
        for (int i = 0; i < weights.length; i++) {
            System.out.print(weights[i] + " || ");
        }
        System.out.println("-------------");
    }

}
