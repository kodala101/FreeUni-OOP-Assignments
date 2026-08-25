import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    @Test
    public void totalMoneyCheck() {
        String testFile = "100k.txt";
        int workers = 8;

        Bank bank = new Bank();
        bank.processFile(testFile, workers);

        int total = 0;
        int transactions = 0;
        Account[] sth = bank.getAccs();

        for (int i = 0; i < sth.length; i++) {
            total += sth[i].getBalance();
            transactions += sth[i].getTransactions();
        }

        assertEquals(20000, total);
        assertEquals(200000, transactions);
    }

    @Test
    public void testMain() {
        String[] args1 = {"small.txt", "6"};
        Bank.main(args1);

        String[] args2 = {};
        Bank.main(args2);
    }
}
