package seedu.address.model.person.status;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.status.LeadStatusName.QUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.UNCONTACTED;
import static seedu.address.model.person.status.LeadStatusName.UNQUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.WORKING;
import static seedu.address.model.person.status.LeadStatusName.isValidLeadStatus;


/**
 * Represents the lead status of a contact.
 * A lead status is immutable, and has restricted values.
 */
public class LeadStatus {
    public static final String MESSAGE_CONSTRAINTS = "A lead status can only be "
            + "one of the following 4 categories: \n"
            + UNCONTACTED + "\n"
            + WORKING + "\n"
            + QUALIFIED + "\n"
            + UNQUALIFIED;

    public final LeadStatusName statusName;
    //TODO add in LocalDateTime to record timestamp of status creation

    /**
     * Creates a LeadStatus given a valid String key for a LeadStatusName.
     */
    public LeadStatus(String statusName) {
        requireNonNull(statusName);
        checkArgument(isValidLeadStatus(statusName), MESSAGE_CONSTRAINTS);
        this.statusName = LeadStatusName.get(statusName);
    }

    public LeadStatusName getStatusName() {
        return statusName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeadStatus // instanceof handles nulls
                && statusName.equals(((LeadStatus) other).statusName)); // state check
    }

    @Override
    public String toString() {
        return statusName.toString();
    }
}
