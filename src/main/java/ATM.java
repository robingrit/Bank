public class ATM {


    private Bank bank;

    private boolean loggedIn = false;

    int Loginattempts = 3;


    public ATM(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomerFromList(int id) {
        Customer returnedCustomer = null;
        for (Customer customer : getBank().getCustomers()) {
            if (id == customer.getId()) {
                returnedCustomer = customer;
            }
        }
        return returnedCustomer;
    }

    public Boolean putInCard(Card card) {
        if (card.getInService()) {
            loggedIn = true;

        }

        return card.getInService();

    }

    public void Cardin(Card card) {
        getCustomerFromList(card.getId());

    }

    public int putInPin(int input, Card card) {

        if (checkPin(input, card)) {

            Loginattempts = 0;
        } else {
            pinAttempts(card);


        }
        return Loginattempts;


    }

    public boolean signOut() {
        loggedIn = false;
        return loggedIn;
    }

    public Boolean checkPin(int input, Card card) {
        return input == card.getPin();

    }

    public void pinAttempts(Card card) {

        if (Loginattempts > 1) {

            {
                Loginattempts--;
                System.out.println("You have " + Loginattempts + " more attempts before card is locked");
            }

        } else {
            Loginattempts--;
            //Loginattempts =0;
            card.setInService(false);

            System.out.println("card is locked");
        }


    }

    public int checkBalance(int id, Card card) {
        int returnValue = 0;
        for (Customer customer : bank.getCustomers()) {
            if (customer.getId() == id) {
                returnValue = findAccountToCard(card).getBalance();

            }

        }
        return returnValue;

    }

    public void depositToAccount(int depositAmount, int id, Customer customer) {

        bank.deposit(customer, id, depositAmount);
    }

    public String withdrawFromAccount(int withdrawalAmount, int id, Customer customer) {
        String returnString = "";
        findAccountFromCustomer(customer, id);


        if (withdrawalAmount < findAccountFromCustomer(customer, id).getBalance()) {
            returnString = bank.withdraw(customer, id, withdrawalAmount);
        } else {
            returnString = "Insufficient balance. No funds were withdrawn!";
        }
        return returnString;
    }

    public Account findAccountToCard(Card card) {
        for (Customer customer : getBank().getCustomers())
            for (Account account : customer.getAccounts()) {
                if (card.getId() == account.getAccountid()) {

                }
                return account;
            }
        return null;
    }

    public Account findAccountFromCustomer(Customer customer, int id) {
        for (Account account : customer.getAccounts()) {
            if (account.getAccountid() == id) {

            }
            return account;
        }
        return null;
    }


}
