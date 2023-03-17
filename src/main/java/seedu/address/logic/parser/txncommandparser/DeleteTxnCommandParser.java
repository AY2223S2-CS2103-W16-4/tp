package seedu.address.logic.parser.txncommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.txncommands.DeleteTxnCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTxnCommand object
 */
public class DeleteTxnCommandParser implements Parser<DeleteTxnCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTxnCommand
     * and returns a DeleteTxnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTxnCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTxnCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTxnCommand.MESSAGE_USAGE), pe);
        }
    }
}
