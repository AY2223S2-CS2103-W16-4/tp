package seedu.address.logic.commands.txncommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all transactions in the address book to the user.
 */
public class ListTxnCommand extends Command {

    public static final String COMMAND_WORD = "listtxn";

    public static final String MESSAGE_SUCCESS = "Listed all transactions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
