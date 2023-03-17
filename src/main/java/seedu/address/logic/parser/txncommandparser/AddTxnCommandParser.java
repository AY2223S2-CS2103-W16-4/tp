package seedu.address.logic.parser.txncommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;

import java.util.stream.Stream;

import seedu.address.logic.commands.txncommands.AddTxnCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

/**
 * Parses input arguments and creates a new AddTxnCommand object
 */

public class AddTxnCommandParser implements Parser<AddTxnCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTxnCommand
     * and returns an AddTxnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTxnCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TXN_DESCRIPTION, PREFIX_TXN_STATUS, PREFIX_TXN_VALUE,
                        PREFIX_TXN_OWNER);
        if (!arePrefixesPresent(argMultimap, PREFIX_TXN_DESCRIPTION, PREFIX_TXN_STATUS, PREFIX_TXN_VALUE,
                PREFIX_TXN_OWNER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTxnCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_TXN_DESCRIPTION).get());
        TxnStatus status = ParserUtil.parseTxnStatus(argMultimap.getValue(PREFIX_TXN_STATUS).get());
        Value value = ParserUtil.parseValue(argMultimap.getValue(PREFIX_TXN_VALUE).get());
        Owner owner = ParserUtil.parseOwner(argMultimap.getValue(PREFIX_TXN_OWNER).get());


        Transaction transaction = new Transaction(description, value, status, owner);

        return new AddTxnCommand(transaction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
