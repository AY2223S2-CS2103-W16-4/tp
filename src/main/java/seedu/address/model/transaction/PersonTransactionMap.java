package seedu.address.model.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import seedu.address.model.person.Person;

/**
 * Stores and manages the relationship between Person and Transactions.
 * Note that a transaction can only be mapped to at most one Person, while a Person can have many Transactions
 */
public class PersonTransactionMap {
    private final Map<Person, PriorityQueue<Transaction>> personToTxnMap = new HashMap<>();

    // TreeMap to store Transactions as keys and Person as value.
    // sorted in order of transactions' last updated date.
    // Can obtain a list of transactions listed in time order from getting keys
    private final Map<Transaction, Person> txnToPersonMap = new TreeMap<>(new UpdatedTransactionComparator());

    /**
     * Add transaction-customer relationship (to be updated)
     * @param transaction txn
     * @param customer customer
     */
    public void addRelation(Transaction transaction, Person customer) {
        // Assume the customer is already listed, exceptions to be discussed later
        personToTxnMap.get(customer).add(transaction);
        txnToPersonMap.put(transaction, customer);
    }
}
