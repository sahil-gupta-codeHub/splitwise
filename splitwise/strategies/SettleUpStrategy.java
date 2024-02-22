package splitwise.strategies;

import splitwise.dtos.Transaction;
import splitwise.models.User;

import java.util.HashMap;
import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUpUsers(HashMap<User, Integer> amountPerUserMap);
}
