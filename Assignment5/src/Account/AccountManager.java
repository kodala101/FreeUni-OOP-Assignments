package Account;

import java.util.HashMap;
import java.util.Objects;

public class AccountManager {
    HashMap<String, String> pairs;

    public AccountManager() {
        pairs = new HashMap<>();

        pairs.put("Patrick", "1234");
        pairs.put("Molly", "FloPup");
    }

    public boolean accExists(String name) {
        return pairs.containsKey(name);
    }

    public boolean isPassword(String name, String pass) {
        return Objects.equals(pairs.get(name), pass);
    }

    public boolean createAccount(String name, String pass) {
        if (pairs.containsKey(name)) return false;
        pairs.put(name, pass);
        return true;
    }
}
