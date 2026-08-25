package Account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAccountManager {
    @Test
    public void testDefaultAccountsExist() {
        AccountManager manager = new AccountManager();

        assertTrue(manager.accExists("Patrick"));
        assertTrue(manager.accExists("Molly"));
        assertFalse(manager.accExists("Luka"));
    }

    @Test
    public void testIsPassword() {
        AccountManager manager = new AccountManager();

        assertTrue(manager.isPassword("Patrick", "1234"));
        assertTrue(manager.isPassword("Molly", "FloPup"));

        assertFalse(manager.isPassword("Patrick", "4321"));
        assertFalse(manager.isPassword("Luka", "0000"));
    }

    @Test
    public void testCreateAccountSuccess() {
        AccountManager manager = new AccountManager();

        String newName = "Alice";
        String newPass = "secure987";

        assertFalse(manager.accExists(newName));

        boolean created = manager.createAccount(newName, newPass);

        assertTrue(created);
        assertTrue(manager.accExists(newName));
        assertTrue(manager.isPassword(newName, newPass));
    }

    @Test
    public void testCreateAccountFailure() {
        AccountManager manager = new AccountManager();

        assertTrue(manager.accExists("Patrick"));

        boolean created = manager.createAccount("Patrick", "newpass");

        assertFalse(created);
        assertTrue(manager.accExists("Patrick"));
        assertTrue(manager.isPassword("Patrick", "1234"));
    }
}

