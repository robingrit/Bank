import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> Customers;

    public Bank() {
        Customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return Customers;
    }

    // lägger till customer metod
    public void AddCustomer(Customer customer) {
        Customers.add(customer);
    }









    // Sätter in penger
    public void deposit(Customer customer, int accountnummer, int balance) {
        Account account = findAccount(customer, accountnummer);

        int currentBalance = account.getBalance();
        account.setBalance(currentBalance + balance);

    }

    public Account findAccount(Customer customer, int accountnummer) {
        for (Account account : customer.getAccounts()) {
            if (account.getAccountid() == accountnummer) {
                return account;
            }

        }
        return null;
    }

    // Tar ut pengar
    public String withdraw(Customer customer, int accountnummer, int balance) {
        Account account = findAccount(customer, accountnummer);
        String returnString = "";

        int currentBalance = account.getBalance();
        account.setBalance(currentBalance - balance);

        returnString = "Specified balance of " + balance + "was withdrawn successfully!";

        return returnString;
    }


    // ändrar namn på en customer
    public boolean ChangeCustommerName(String nyaname, Long personummer) {
        // Kollar igenom listan stämmer personummret gör if
        for (var customer : Customers) {
            if (personummer == customer.getPersonummer()) {
                customer.setFname(nyaname);
                return true;
            }

        }
        return false;
    }

    //Remove custommer
    public List<String> RemoveCustomer(long persnum) {
        List<String> info = new ArrayList<>();
        // Kollar igenom listan stämmer personummret gör if
        // lägger till information i listan info
        // Tar bort kunden sen stoppar Break;
        for (var customer : Customers) {
            if (persnum == customer.getPersonummer()) {
                info.add("Customer:  " + customer.getFname() + "  " + customer.getPersonummer() +
                        " Konton na som är bortaggna:");
                info.add(customer.removeAccounts());
                Customers.remove(customer);
                break;

            }
        }
        return info;

    }

    // remove account
    public String CloseAccount(long persnum, int accountnumber) {


        for (var customer : Customers) {
            if (persnum == customer.getPersonummer()) {
                //String borta = customer.removeAccounts() + "De kontot som är kvar är" ;


                customer.Removeaccount(accountnumber);
                //String borta = customer.getaccs() + "De kontot som är kvar är" ;
                String borta = customer.removeAccounts() + "De kontot som är kvar är";
                return borta;

            }
        }
        return null;

    }
    public static String getWhichBankOwnsAtm(){
        return "Input Bank Name";
    }
}