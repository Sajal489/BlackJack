package twentyonegui;

public class Player {

    private int points;
    private int money;

    public Player() {
    }

    public Player(int points, int money) {
        this.points = points;
        this.money = money;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addPoints(int n) {
        this.points += n;
    }

    public void managemoney(int x) {
        if (x < 0) {
            x *= -1;
            this.money -= x;
        }
        else{
            this.money += x;
        }
    }

    @Override
    public String toString() {
        return "\nYou have a total of " + points + " points" + "\n$" + money;
    }
    
    

}
