package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_ASSIGN;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Industry;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.tag.Tag;


/**
 * Assigns a person's lead status,
 * or finds people matching a lead status.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Handles lead statuses, "
            + "allows for setting of statuses.\n"
            + "Parameters: \n"
            + "[INDEX " + PREFIX_STATUS_ASSIGN + " STATUS_TYPE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS_ASSIGN + "qualified\n";

    public static final String MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS = "Assigned %1$s status of "
            + " %2$s";
    public static final String MESSAGE_STATUS_IS_SAME = "The status of %1$s is already %2$s";

    private final Index index;
    private final LeadStatus status;

    //TODO add DateTime timestamp of when the status was set.

    /**
     * Constructor for a StatusCommand. Takes in an index of a person valid lead status.
     * @param index a valid index for a person
     * @param status a valid LeadStatus
     */
    public StatusCommand(Index index, LeadStatus status) {
        requireNonNull(index);
        requireNonNull(status);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        requireNonNull(index);
        if (index.getZeroBased() >= lastShownList.size()) { // index not found in list
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person toBeUpdated = lastShownList.get(index.getZeroBased());
        LeadStatus currLeadStatus = toBeUpdated.getStatus();

        if (status.equals(currLeadStatus)) { // lead status is already assigned
            throw new CommandException(String.format(MESSAGE_STATUS_IS_SAME, toBeUpdated.getName(), status));
        }

        Person updatedStatusPerson = createPersonWithNewStatus(toBeUpdated, status);
        model.setPerson(toBeUpdated, updatedStatusPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS, updatedStatusPerson.getName(),
                status));
    }

    private static Person createPersonWithNewStatus(Person toBeUpdated, LeadStatus status) {
        assert toBeUpdated != null;

        Name name = toBeUpdated.getName();
        Gender gender = toBeUpdated.getGender();
        Phone phone = toBeUpdated.getPhone();
        Email email = toBeUpdated.getEmail();
        Address address = toBeUpdated.getAddress();
        Company company = toBeUpdated.getCompany();
        Industry industry = toBeUpdated.getIndustry();
        Occupation occupation = toBeUpdated.getOccupation();
        JobTitle jobTitle = toBeUpdated.getJobTitle();
        Remark remark = toBeUpdated.getRemark();
        Set<Tag> tags = toBeUpdated.getTags();

        return new Person(name, gender, phone, email, company, industry, occupation, jobTitle, address, remark, tags,
                status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StatusCommand
                && index.equals(((StatusCommand) other).index)
                && status.equals(((StatusCommand) other).status));
    }
}
