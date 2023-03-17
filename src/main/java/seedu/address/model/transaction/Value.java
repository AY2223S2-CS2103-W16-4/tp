package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a transaction's value.
 */
public class Value {
    public static final String MESSAGE_CONSTRAINTS =
            "Transaction value should only contain numbers and at most one dot, and it should not be blank";

    public static final String VALIDATION_REGEX = "-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)";
    public final String value;

    /**
     * Constructs a {@code Value}.
     *
     * @param value A valid value amount.
     */
    public Value(String value) {
        requireNonNull(value);
        checkArgument(isValidValue(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidValue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Value // instanceof handles nulls
                && value.equals(((Value) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }




}


