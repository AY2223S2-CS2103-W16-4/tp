package seedu.address.logic.commands.txncommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

/**
 * Add command for transactions
 */
public class AddTxnCommand extends Command {
    public static final String COMMAND_WORD = "addtxn";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to the sales book. "
            + "Parameters: "
            + PREFIX_TXN_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TXN_VALUE + "VALUE "
            + PREFIX_TXN_STATUS + "STATUS "
            + PREFIX_TXN_OWNER + "OWNER";

    public static final String MESSAGE_SUCCESS = "New transaction record added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRANSACTION =
            "This transaction record already exists in the sales book";

    private final Transaction toAdd;

    /**
     * Creates an AddTxnCommand to add the specified {@code Transaction}
     */
    public AddTxnCommand(Transaction transaction) {
        requireNonNull(transaction);
        toAdd = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTransaction(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRANSACTION);
        }

        model.addTransaction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTxnCommand // instanceof handles nulls
                && toAdd.equals(((AddTxnCommand) other).toAdd));
    }
}
