package seedu.address.model.transaction;


//import javax.management.Descriptor;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Transaction in the sales book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Transaction {

    private final Description description;
    private final Value value;
    private final TxnStatus status;
    private final Owner owner;

    /**
     * Constructor
     * @param description transaction descriptions
     * @param value transaction amounts
     * @param txnStatus transaction status
     */
    public Transaction(Description description, Value value, TxnStatus txnStatus, Owner owner) {
        requireAllNonNull(description, value, txnStatus, owner);
        this.description = description;
        this.value = value;
        this.status = txnStatus;
        this.owner = owner;
    }

    public Description getDescription() {
        return description;
    }

    public Value getValue() {
        return value;
    }

    public TxnStatus getStatus() {
        return status;
    }

    public Owner getOwner() {
        return owner;
    }


    /**
     * Returns true if the other transaction has the same description and value.
     */
    public boolean isSameTransaction(Transaction other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getDescription().equals(description)
                && other.getValue().equals(value)
                && other.getStatus().equals(status)
                && other.getOwner().equals(owner);
    }

    /**
     * Returns true if both transactions have the same identity and data fields.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getDescription().equals(getDescription())
                && otherTransaction.getValue().equals(getValue())
                && otherTransaction.getStatus().equals(getStatus())
                && otherTransaction.getOwner().equals(getOwner());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, value, status, owner);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Transaction: ")
                .append(getDescription())
                .append("; Value: ")
                .append(getValue())
                .append("; Status: ")
                .append(getStatus())
                .append("; Owner: ")
                .append(getOwner());

        return builder.toString();
    }
}

