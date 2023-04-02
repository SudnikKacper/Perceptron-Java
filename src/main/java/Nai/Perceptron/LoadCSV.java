package Nai.Perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadCSV {

    public static HashMap<String, Integer> wynik = new HashMap<>();
    public static int length;

    public static ArrayList<Vektor> parse(String fPath) {
        ArrayList<Vektor> vektors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fPath))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] strings = line.split(",");
                length = strings.length - 1;
                double[] val = new double[length];
                for (int i = 0; i < val.length; i++)
                    val[i] = Double.parseDouble(strings[i].trim());
                wynik.put(strings[length], null);
                vektors.add(new Vektor(strings[length], val));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int id = 0;
        for (Map.Entry<String, Integer> entry : wynik.entrySet())
            entry.setValue(id++);

        return vektors;
    }
}

