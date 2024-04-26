package project;


import java.util.concurrent.TimeUnit;
import java.lang.Math;




public class Race {
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    public Race(int distance) {
        raceLength = distance;
    }

    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber == 1) {
            lane1Horse = theHorse;
        } else if (laneNumber == 2) {
            lane2Horse = theHorse;
        } else if (laneNumber == 3) {
            lane3Horse = theHorse;
        } else {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }

    public void startRace() {
        // Reset horses to the start line
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();

        boolean finished = false;
        while (!finished) {
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
            printRace();

            if (raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse)) {
                finished = true;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (raceWonBy(lane1Horse)) {
            System.out.println("And the winner is " + lane1Horse.getName());
        } else if (raceWonBy(lane2Horse)) {
            System.out.println("And the winner is " + lane2Horse.getName());
        } else if (raceWonBy(lane3Horse)) {
            System.out.println("And the winner is " + lane3Horse.getName());
        }
    }

    private void moveHorse(Horse theHorse) {
        if (!theHorse.hasFallen()) {
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            if (Math.random() < (0.1 * theHorse.getConfidence() * theHorse.getConfidence())) {
                theHorse.fall();
            }
        }
    }

    private boolean raceWonBy(Horse theHorse) {
        return theHorse.getDistanceTravelled() >= raceLength;
    }

    private void printRace() {
        clearScreen();
        printRaceLine(lane1Horse);
        printRaceLine(lane2Horse);
        printRaceLine(lane3Horse);
        System.out.println();
    }

    private void printRaceLine(Horse horse) {
        StringBuilder line = new StringBuilder("|");
        int distance = horse.getDistanceTravelled();
        for (int i = 0; i < raceLength; i++) {
            if (i == distance) {
                if (horse.hasFallen()) {
                    line.append('⌢');
                } else {
                    line.append(horse.getSymbol());
                }
            } else {
                line.append(' ');
            }
        }
        line.append('|');
        System.out.println(line);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        // Create Horse objects
        Horse horse1 = new Horse('♘', "PIPPI LONGSTOCKING", 0.6);
        Horse horse2 = new Horse('♞', "KOKOMO", 0.6);
        Horse horse3 = new Horse('❌', "EL JEFE", 0.4);

        // Create Race object
        Race race = new Race(50); // Length of the race track is 50 units

        // Add horses to the race
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        // Start the race
        race.startRace();
    }
}