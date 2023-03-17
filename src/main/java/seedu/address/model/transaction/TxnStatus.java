package seedu.address.model.transaction;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Status of Transaction
 */
public class TxnStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Transaction status should only be open or closed, and it should not be blank";

    /* The input should be "open" or "closed" */
    public static final String VALIDATION_REGEX = ".*\\bopen\\b|.*\\bclosed\\b";

    public final String value;
    public final LocalDateTime lastUpdate;

    /**
     * Construct a {@code TxnStatus}.
     * @param isClosed a valid transaction status
     */
    public TxnStatus(String isClosed) {
        requireNonNull(isClosed);
        checkArgument(isValidTxnStatus(isClosed), MESSAGE_CONSTRAINTS);
        this.value = isClosed;
        this.lastUpdate = LocalDateTime.now();
    }

    /**
     * Check if the transaction is closed
     * @return True if the transaction is closed.
     */
    public boolean isClosed() {
        return value.equals("Closed");
    }
    public static boolean isValidTxnStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get the time when the transaction is last updated.
     * @return time of last update
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TxnStatus // instanceof handles nulls
                && value.equals(((TxnStatus) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
