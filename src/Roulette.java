
import java.util.Random;
import java.util.Scanner;

public class Roulette {
    private Bet[] bets;
    private int ballLocation;
    private int winningAmount;
    private int whereTheBallIs;
    private COLOR colorTheBallsOn;

    public Roulette(Bet[] bet) {
        this.bets = bet;
    }

    public void ballLoc() {
        Random spot = new Random();
        this.ballLocation = spot.nextInt(37); // 0-36
    }

    public int getLoc() {
        return this.ballLocation;
    }

    public void colorLoc() {
        if (this.ballLocation == 0) {
            this.colorTheBallsOn = COLOR.GREEN;
        } else if (this.ballLocation % 2 == 0) {
            this.colorTheBallsOn = COLOR.BLACK;
        } else {
            this.colorTheBallsOn = COLOR.RED;
        }
    }

    public int getWinningAmount() {
        return this.winningAmount;
    }

    public Bet[] getBets() {
        return this.bets;
    }

    public Bet[] getWinners() {
        this.winningAmount = 0;
        this.whereTheBallIs = this.getLoc();
        this.colorLoc();

        int amountOfWinners = 0;

        for (int i = 0; i < this.bets.length; i++) {
            boolean numberWin = (this.bets[i].getNum() == this.whereTheBallIs);
            COLOR betColor = this.bets[i].getColor();
            boolean colorWin = (betColor != null && betColor == this.colorTheBallsOn);

            if (numberWin || colorWin) {
                amountOfWinners++;
            }
        }

        Bet[] winners = new Bet[amountOfWinners];
        int winnerLoc = 0;

        for (int i = 0; i < this.bets.length; i++) {
            boolean numberWin = (this.bets[i].getNum() == this.whereTheBallIs);
            COLOR betColor = this.bets[i].getColor();
            boolean colorWin = (betColor != null && betColor == this.colorTheBallsOn);

            if (numberWin || colorWin) {
                winners[winnerLoc] = this.bets[i];
                this.winningAmount += this.bets[i].getAmount();
                winnerLoc++;
            }
        }

        return winners;
    }

    public int winningAmount() {
        this.whereTheBallIs = this.ballLocation;
        this.colorLoc();

        int totalWinnings = 0;
        this.winningAmount = 0;

        for (int i = 0; i < this.bets.length; i++) {
            boolean numberWin = (this.bets[i].getNum() == this.whereTheBallIs);
            COLOR betColor = this.bets[i].getColor();
            boolean colorWin = (betColor != null && betColor == this.colorTheBallsOn);

            if (numberWin || colorWin) {
                totalWinnings += this.bets[i].getAmount();
                this.winningAmount += this.bets[i].getAmount();
            }
        }

        return totalWinnings;
    }

    public static void main(String[] args) {
        Scanner bet = new Scanner(System.in);
        System.out.print("Your bets: ");
        String bets = bet.nextLine();
        String[] betsArr = bets.split(", ");

        Bet[] intsBetsArr = new Bet[betsArr.length];

        for (int i = 0; i < intsBetsArr.length; i++) {
            String[] firstBet = betsArr[i].trim().split(" ");

            int amount = Integer.parseInt(firstBet[0]);

            if (firstBet.length >= 2) {
                String second = firstBet[1].toUpperCase();

                if (second.equals("RED") || second.equals("BLACK") || second.equals("GREEN")) {
                    intsBetsArr[i] = new Bet(amount, COLOR.valueOf(second));
                } else {
                    int number = Integer.parseInt(second);
                    intsBetsArr[i] = new Bet(amount, number);
                }
            } else {
                intsBetsArr[i] = new Bet(amount, -1);
            }
        }

        Roulette roulette = new Roulette(intsBetsArr);

        System.out.println("Spinning");
        roulette.ballLoc();
        System.out.println("It lands on " + roulette.getLoc());

        System.out.println("You won $" + roulette.winningAmount());
        bet.close();
    }
}