package helpers.enums;

/**
 * Created by Sergey on 09.02.2015.
 */
public enum GameType
{
    CHGK(1),
    SI(5),
    BK(2),
    BR(3);

    private int number;

    GameType (int n) {
        setNumber(n);
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
