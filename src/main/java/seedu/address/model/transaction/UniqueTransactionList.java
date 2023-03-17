package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.exceptions.DuplicateTransactionException;
import seedu.address.model.transaction.exceptions.TransactionNotFoundException;



/**
 * Stores a global list of transactions. Adapted from UniquePersonList implementation from AB3.
 */
public class UniqueTransactionList implements Iterable<Transaction> {
    private final ObservableList<Transaction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Check if the transaction list contains the specified transaction
     * @param toCheck txn
     * @return True if contained
     */
    public boolean contains(Transaction toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTransaction);
    }

    /**
     * Adds a transaction to the list.
     * The transaction must not already exist in the list.
     */
    public void add(Transaction toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTransactionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the list.
     * The transaction identity of {@code editedTransaction} must not be the same as another existing transaction
     * in the list.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireAllNonNull(target, editedTransaction);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransactionNotFoundException();
        }

        if (!target.isSameTransaction(editedTransaction) && contains(editedTransaction)) {
            throw new DuplicateTransactionException();
        }

        internalList.set(index, editedTransaction);
    }

    /**
     * Removes the equivalent transaction from the list.
     * The transaction must exist in the list.
     */
    public void remove(Transaction toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransactionNotFoundException();
        }
    }

    public void setTransactions(UniqueTransactionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code transactions}.
     * {@code persons} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        requireAllNonNull(transactions);
        if (!transactionsAreUnique(transactions)) {
            throw new DuplicateTransactionException();
        }

        internalList.setAll(transactions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Transaction> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalList.iterator();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTransactionList // instanceof handles nulls
                && internalList.equals(((UniqueTransactionList) other).internalList));
    }
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean transactionsAreUnique(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(i).isSameTransaction(transactions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
