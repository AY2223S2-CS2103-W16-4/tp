package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDUSTRY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INDUSTRY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDUSTRY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOBTITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OCCUPATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOBTITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOBTITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.OCCUPATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OCCUPATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + GENDER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + INDUSTRY_DESC_AMY + OCCUPATION_DESC_AMY + JOBTITLE_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB
                + JOBTITLE_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GENDER_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_COMPANY_BOB + VALID_INDUSTRY_BOB + VALID_OCCUPATION_BOB + VALID_JOBTITLE_BOB
                + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB
                + JOBTITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB
                + JOBTITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB
                + JOBTITLE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_GENDER_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_COMPANY_DESC + INVALID_INDUSTRY_DESC + INVALID_OCCUPATION_DESC + INVALID_JOBTITLE_DESC
                + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + COMPANY_DESC_BOB + INDUSTRY_DESC_BOB + OCCUPATION_DESC_BOB + JOBTITLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
