
public class Bet {
    private int amount;
    private int number;
    private COLOR color;

    public Bet(int betAmount, int betNumber) {
        this.amount = betAmount;
        this.number = betNumber;
        this.color = null;
    }

    public Bet(int betAmount, COLOR betColor) {
        this.amount = betAmount;
        this.color = betColor;
        this.number = -1;
    }

    public int getNum() {
        return this.number;
    }

    public int getAmount() {
        return this.amount; // FIXED: used to return number
    }

    public COLOR getColor() {
        return this.color;
    }
}