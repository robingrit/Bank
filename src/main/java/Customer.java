import java.util.ArrayList;
import java.util.List;

public class Customer {

    // State
    // lagrar värden
    private String Fname;
    private int id;
    private Card card;
    private long personummer;

    // lista med kundens alla Account
    private List<Account> Accounts;
    private List<Card> cards;
    //**************************


    public List<Account> getAccounts() {
        return Accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Customer(String fname, Long personummer, Card card) {
        this.personummer = personummer;
        this.Fname = fname;
        this.id = card.getId();
        Accounts = new ArrayList<>();
        cards = new ArrayList<>();
    }


    public void setFname(String fname) {
        Fname = fname;
    }

    public String getFname() {
        return Fname;
    }


    public long getPersonummer() {
        return personummer;
    }





    public void addCardToList(Card card) {
        cards.add(card);
    }

    public void addAccountToList(Account account) {
        Accounts.add(account);
    }


    public int Removeaccount(int accountnum) {
        Accounts.removeIf(account -> accountnum == account.getAccountid());
        return accountnum;
    }

    public String removeAccounts() {

        StringBuilder builder = new StringBuilder();
        for (var acc : Accounts) {
            builder.append(" Account number:  ").append(String.valueOf(acc.getAccountid()))

                    .append(" Balance:  ").append(String.valueOf(acc.getBalance()))
                    .append(" Kontotyp: ").append(acc.getKontotyp()).append("\n");

        }
        Accounts.removeAll(Accounts);
        String line = builder.toString();
        return line;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
