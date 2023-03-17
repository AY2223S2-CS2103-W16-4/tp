package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCUPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_ASSIGN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_GENDER_AMY = "female";
    public static final String VALID_GENDER_BOB = "male";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_COMPANY_AMY = "Tesleh";
    public static final String VALID_COMPANY_BOB = "NUS";
    public static final String VALID_INDUSTRY_AMY = "automobile";
    public static final String VALID_INDUSTRY_BOB = "education";
    public static final String VALID_OCCUPATION_AMY = "engineer";
    public static final String VALID_OCCUPATION_BOB = "professor";
    public static final String VALID_JOBTITLE_AMY = "industrial engineer";
    public static final String VALID_JOBTITLE_BOB = "research instructor";

    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String COMPANY_DESC_AMY = " " + PREFIX_COMPANY + VALID_COMPANY_AMY;
    public static final String COMPANY_DESC_BOB = " " + PREFIX_COMPANY + VALID_COMPANY_BOB;
    public static final String INDUSTRY_DESC_AMY = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_AMY;
    public static final String INDUSTRY_DESC_BOB = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_BOB;
    public static final String OCCUPATION_DESC_AMY = " " + PREFIX_OCCUPATION + VALID_OCCUPATION_AMY;
    public static final String OCCUPATION_DESC_BOB = " " + PREFIX_OCCUPATION + VALID_OCCUPATION_BOB;
    public static final String JOBTITLE_DESC_AMY = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_AMY;
    public static final String JOBTITLE_DESC_BOB = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_BOB;

    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "guy"; // 'guy' not allowed as gender
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY; // empty string not allowed
    public static final String INVALID_INDUSTRY_DESC = " " + PREFIX_INDUSTRY; // empty string not allowed
    public static final String INVALID_JOBTITLE_DESC = " " + PREFIX_JOBTITLE; // empty string not allowed
    public static final String INVALID_OCCUPATION_DESC = " " + PREFIX_OCCUPATION; // empty string not allowed
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final LeadStatus VALID_LEAD_STATUS_UNCONTACTED;
    public static final LeadStatus VALID_LEAD_STATUS_WORKING;

    public static final String VALID_LEAD_STATUS_LABEL = " " + PREFIX_STATUS_ASSIGN + "Qualified";
    public static final LeadStatus VALID_LEAD_STATUS_LABEL_QUALIFIED; // for the lead status in line above
    public static final String INVALID_LEAD_STATUS_LABEL = " " + PREFIX_STATUS_ASSIGN + "Contacted"; // is not defined

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCompany(VALID_COMPANY_AMY)
                .withIndustry(VALID_INDUSTRY_AMY).withOccupation(VALID_OCCUPATION_AMY).withJobTitle(VALID_JOBTITLE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withCompany(VALID_COMPANY_BOB)
                .withIndustry(VALID_INDUSTRY_BOB).withOccupation(VALID_OCCUPATION_BOB).withJobTitle(VALID_JOBTITLE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        VALID_LEAD_STATUS_UNCONTACTED = new LeadStatus(LeadStatusName.UNCONTACTED.getLabel());
        VALID_LEAD_STATUS_WORKING = new LeadStatus(LeadStatusName.WORKING.getLabel());
        VALID_LEAD_STATUS_LABEL_QUALIFIED = new LeadStatus(LeadStatusName.QUALIFIED.getLabel());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
