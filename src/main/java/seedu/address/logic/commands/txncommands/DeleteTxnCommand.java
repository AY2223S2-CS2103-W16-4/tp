package seedu.address.logic.commands.txncommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;



/**
 * Deletes a transaction identified using it's displayed index from the sales book.
 */
public class DeleteTxnCommand extends Command {
    public static final String COMMAND_WORD = "deletetxn";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the transaction identified by the index number used in the displayed transaction list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRANSACTION_SUCCESS = "Deleted Transaction: %1$s";

    private final Index targetIndex;

    public DeleteTxnCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TXN_DISPLAYED_INDEX);
        }

        Transaction txnToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTransaction(txnToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS, txnToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTxnCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTxnCommand) other).targetIndex)); // state check
    }
}
