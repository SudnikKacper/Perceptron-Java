package Nai.Perceptron;

public class Vektor {
    private String name;
    private double[] points;

    public Vektor(String name, double[] points)
    {
        this.name = name;
        this.points = points;
    }


    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
