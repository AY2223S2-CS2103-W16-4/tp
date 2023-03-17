package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's value in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIndustry(String)}
 */
public class Industry {

    public static final String MESSAGE_CONSTRAINTS =
            "Industry should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the value must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Industry}.
     *
     * @param industry A valid industry.
     */
    public Industry(String industry) {
        requireNonNull(industry);
        checkArgument(isValidIndustry(industry), MESSAGE_CONSTRAINTS);
        this.value = industry;
    }

    /**
     * Returns true if a given string is a valid value.
     */
    public static boolean isValidIndustry(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Industry // instanceof handles nulls
                && value.equals(((Industry) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
