package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEAD_STATUS_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAD_STATUS_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAD_STATUS_LABEL_QUALIFIED;
import static seedu.address.logic.commands.StatusCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;



public class StatusCommandParserTest {

    private StatusCommandParser parser = new StatusCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_LEAD_STATUS_LABEL;
        StatusCommand expectedCommand = new StatusCommand(targetIndex, VALID_LEAD_STATUS_LABEL_QUALIFIED);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidLeadStatusSpecified_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_LEAD_STATUS_LABEL;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeadStatus.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_indexNotSpecified_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        assertParseFailure(parser, VALID_LEAD_STATUS_LABEL, expectedMessage);
    }

    @Test
    public void parse_statusPrefixNotSpecified_failure() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex + LeadStatusName.WORKING.getLabel();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
