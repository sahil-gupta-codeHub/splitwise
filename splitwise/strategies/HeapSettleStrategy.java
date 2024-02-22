package splitwise.strategies;

import splitwise.dtos.Transaction;
import splitwise.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSettleStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUpUsers(HashMap<User, Integer> amountPerUserMap) {
        PriorityQueue<Pair> givers = new PriorityQueue<>();
        PriorityQueue<Pair> receivers = new PriorityQueue<>();

        List<Transaction> transactions = new ArrayList<>();
        for(User user : amountPerUserMap.keySet()){
            int amount = amountPerUserMap.get(user);
            if(amount < 0){
                givers.add(new Pair(amount*-1, user));
            }
            else{
                receivers.add(new Pair(amount, user));
            }
        }
        while(receivers.size() > 0 && givers.size() > 0){
            Pair giver = givers.poll();
            Pair receiver = receivers.poll();

            transactions.add(new Transaction(giver.user.getName(), receiver.user.getName(), giver.amount));
            if(giver.amount < receiver.amount){
                receivers.add(new Pair(receiver.amount - giver.amount, receiver.user));
            }

        }
        return transactions;
    }
}
class Pair implements Comparable{
    int amount;
    User user;

    public Pair(int amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    @Override
    public int compareTo(Object o) {
        Pair other = (Pair)o;
        if(this.amount <= other.amount){
            return -1;
        }
        else{
            return 1;
        }
    }
}
