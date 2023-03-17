package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS_ASSIGN;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;

/**
 * Parses input arguments and creates a new StatusCommand object.
 */
public class StatusCommandParser implements Parser<StatusCommand> {

    /**
     * Parses the given {@code String} of arguments intended for a StatusCommand and
     * returns a StatusCommand object for execution.
     * @throws ParseException if the user does not conform to the expected format.
     */
    public StatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STATUS_ASSIGN);

        LeadStatus status;

        if (argMultimap.getValue(PREFIX_STATUS_ASSIGN).isPresent()) {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        StatusCommand.MESSAGE_USAGE), pe);
            }

            String statusText = argMultimap.getValue(PREFIX_STATUS_ASSIGN).get();
            if (!LeadStatusName.isValidLeadStatus(statusText)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeadStatus.MESSAGE_CONSTRAINTS));
            }

            status = new LeadStatus(statusText);
            return new StatusCommand(index, status);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StatusCommand.MESSAGE_USAGE));
    }
}
