package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's owner in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOwner(String)}
 */
public class Owner {
    public static final String MESSAGE_CONSTRAINTS =
            "Owner name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Owner name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Owner}.
     *
     * @param name A valid name.
     */
    public Owner(String name) {
        requireNonNull(name);
        checkArgument(isValidOwner(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOwner(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Owner // instanceof handles nulls
                && value.equals(((Owner) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
