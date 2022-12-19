import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ATMTest {

    Card card1;
    Customer customer1;
    ArrayList<Customer> customers = new ArrayList<>();

    Account account1;

    @Mock
    Bank bank;

    @InjectMocks
    ATM atm;

    @BeforeEach
    public void setUp() {
        card1 = new Card(4321, "Visa", 0001);
        customer1 = new Customer("Gustav Svensson", 199002261111L, card1);
        account1 = new Account(0, "debit", 0001);

        customer1.addAccountToList(account1);
        customers.add(customer1);
    }

    @Test
    public void TestGetCustomersFromBank() {
        when(bank.getCustomers()).thenReturn(customers);
        System.out.println(bank.getCustomers().size());

        assertEquals(atm.getBank().getCustomers().size(), 1);
    }

    //1 Using card ID, returns associated user
    @Test
    public void TestGetCustomerWithCardId() {
        when(bank.getCustomers()).thenReturn(customers);
        Customer actual = atm.getCustomerFromList(customer1.getId());
        assertEquals(customer1, actual);
    }

    // Login Succses
    @Test
    public void TestPincode() {

        int actual = atm.putInPin(4321, card1);
        assertEquals(0, actual);
    }

    // multiple attempts login faliure
    @Test
    public void TestWrongPincodes() {

        atm.putInPin(4322, card1);
        atm.putInPin(4325, card1);


        int actual = atm.putInPin(4323, card1);
        assertEquals(0, actual);
    }

    //4 Check if card is locked before logging in
    @Test
    public void TestIfCardIsInService() {
        Card cardExample = new Card(4321, "Visa", 0001);

        boolean actual = atm.putInCard(cardExample);
        assertTrue(actual);
    }

    //5 Getting account balance from user account via bank
    @ParameterizedTest
    @ValueSource(ints = {1004, 2700, 7001})
    public void TestCheckAccountBalance(int accountBalance) {

        customers.get(0).getAccounts().get(0).setBalance(accountBalance);
        when(bank.getCustomers()).thenReturn(customers);
        int actual = atm.checkBalance(0001, card1);
        assertEquals(accountBalance, actual);
    }

    //6 Deposit balance into account via bank
    @Test
    public void TestDepositBalance() {
        atm.depositToAccount(100, 0001, customer1);
        verify(bank, times(1)).deposit(any(), anyInt(), anyInt());
    }

    //7 Withdraw balance from account via bank
    @Test
    public void TestWithdrawBalance() {
        customer1.getAccounts().get(0).setBalance(300);
        atm.withdrawFromAccount(150, 0001, customer1);
        atm.withdrawFromAccount(150, 0001, customer1);
        verify(bank, times(2)).withdraw(any(), anyInt(), anyInt());
    }

    //8 Withdrawing balance with insufficient funds
    @Test
    public void TestInsufficientBalance() {
        customer1.getAccounts().get(0).setBalance(300);

        String actual = atm.withdrawFromAccount(5000,0001, customer1);
        assertEquals("Insufficient balance. No funds were withdrawn!", actual);
    }

    //9 Exit the bank process
    @Test
    public void TestLogOut() {
        boolean actual = atm.signOut();
        assertFalse(actual);
    }

    //10 Get ATM's associated bank name
    @Test
    public void TestGetAtmBankName() {
        try (MockedStatic<Bank> mockedStatic = mockStatic(Bank.class)) {
            mockedStatic.when(Bank::getWhichBankOwnsAtm).thenReturn("SwedBank");

            assertEquals("SwedBank", Bank.getWhichBankOwnsAtm());
        }
    }

}