package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAD_STATUS_LABEL_QUALIFIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAD_STATUS_UNCONTACTED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAD_STATUS_WORKING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;

public class StatusCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test void execute_changeLeadStatus_success() {
        Person toBeUpdated = getTypicalAddressBook().getPersonList().get(0);
        assertEquals(toBeUpdated.getStatus(), VALID_LEAD_STATUS_UNCONTACTED); // default should be uncontacted

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_PERSON, VALID_LEAD_STATUS_WORKING);

        String expectedMessage = String.format(StatusCommand.MESSAGE_STATUS_ASSIGN_PERSON_SUCCESS,
                toBeUpdated.getName(), VALID_LEAD_STATUS_WORKING);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), toBeUpdated);

        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        // the lead status chosen should not matter as ofOfBound check occurs before lead status assignment;
        StatusCommand statusCommand = new StatusCommand(outOfBoundIndex, VALID_LEAD_STATUS_WORKING);

        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_leadStatusIsSame_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LeadStatusName statusName = firstPerson.getStatus().getStatusName();
        LeadStatus sameStatus = new LeadStatus(statusName.getLabel());

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_PERSON, sameStatus);
        String expectedMessage = String.format(StatusCommand.MESSAGE_STATUS_IS_SAME, firstPerson.getName(), sameStatus);

        assertCommandFailure(statusCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        StatusCommand first = new StatusCommand(INDEX_FIRST_PERSON, VALID_LEAD_STATUS_LABEL_QUALIFIED);
        StatusCommand second = new StatusCommand(INDEX_FIRST_PERSON,
                new LeadStatus(LeadStatusName.QUALIFIED.getLabel()));

        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }

}
