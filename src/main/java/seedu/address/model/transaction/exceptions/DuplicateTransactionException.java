package seedu.address.model.transaction.exceptions;

/**
 * Signals that the operation will result in duplicate Transactions
 */
public class DuplicateTransactionException extends RuntimeException {
    public DuplicateTransactionException() {
        super("Operation would result in duplicate transactions");
    }
}
