package project;


public class Horse {
    // Fields
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;

    // Constructor
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    // Accessor methods
    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean hasFallen() {
        return hasFallen;
    }

    // Mutator methods
    public void fall() {
        hasFallen = true;
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    public void moveForward() {
        if (!hasFallen) {
            distanceTravelled++;
        }
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence >= 0 && newConfidence <= 1) {
            confidence = newConfidence;
        }
    }

    public void setSymbol(char newSymbol) {
        symbol = newSymbol;
    }
}
