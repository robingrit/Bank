public class Account {

    // State blueprint

    private int Balance;
    private String Kontotyp;


    private int accountid;

    // **********************

    /// construkter
    public Account(int balance, String kontotyp, int accountid) {
        Balance = balance;
        Kontotyp = kontotyp;

        this.accountid = accountid;

    }


    /// Getter till balance som tillåter mig att hämta datan/info om saldot på kontot.
    public int getBalance() {
        return Balance;
    }

    // Setter till balance som har funktionen att ändra värdet i balance
    public void setBalance(int balance) {
        Balance = balance;
    }

    /// Getter till kontotyp som tillåter mig att hämta datan/info om kontotypen på kontot.
    // vilket i detta fallet kommer alltid vara debit.
    public String getKontotyp() {
        return Kontotyp;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }


}
