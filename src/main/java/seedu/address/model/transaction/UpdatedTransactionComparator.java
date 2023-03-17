package seedu.address.model.transaction;

import java.util.Comparator;

/**
 * Comparator for transactions
 */

public class UpdatedTransactionComparator implements Comparator<Transaction> {
    /**
     * Comparator, sort two transaction in order of their last updated time
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getStatus().getLastUpdate()
                .compareTo(t2.getStatus().getLastUpdate());
    }
}
