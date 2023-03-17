package seedu.address.logic.parser.txncommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.txncommands.EditTxnCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTxnCommand object
 */
public class EditTxnCommandParser implements Parser<EditTxnCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTxnCommand
     * and returns an EditTxnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTxnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TXN_DESCRIPTION, PREFIX_TXN_OWNER, PREFIX_TXN_STATUS,
                        PREFIX_TXN_VALUE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTxnCommand.MESSAGE_USAGE), pe);
        }

        EditTxnCommand.EditTxnDescriptor editTxnDescriptor = new EditTxnCommand.EditTxnDescriptor();
        if (argMultimap.getValue(PREFIX_TXN_DESCRIPTION).isPresent()) {
            editTxnDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_TXN_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TXN_OWNER).isPresent()) {
            editTxnDescriptor.setOwner(ParserUtil.parseOwner(argMultimap.getValue(PREFIX_TXN_OWNER).get()));
        }
        if (argMultimap.getValue(PREFIX_TXN_STATUS).isPresent()) {
            editTxnDescriptor.setStatus(ParserUtil.parseTxnStatus(argMultimap.getValue(PREFIX_TXN_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_TXN_VALUE).isPresent()) {
            editTxnDescriptor.setValue(ParserUtil.parseValue(argMultimap.getValue(PREFIX_TXN_VALUE).get()));
        }
        if (!editTxnDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTxnCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTxnCommand(index, editTxnDescriptor);
    }

}
