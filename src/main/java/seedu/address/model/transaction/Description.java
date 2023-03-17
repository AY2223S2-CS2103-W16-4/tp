package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Transaction's description in the address book.
 * Guarantees: immutable;
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Class constructor.
     * @param description input description.
     */
    public Description(String description) {
        requireNonNull(description);
        value = description;
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
