package seedu.address.logic.commands.txncommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

/**
 * Edits the details of an existing transaction in the sales book.
 */
public class EditTxnCommand extends Command {
    public static final String COMMAND_WORD = "edittxn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the transaction identified "
            + "by the index number used in the displayed transaction list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TXN_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TXN_VALUE + "VALUE] "
            + "[" + PREFIX_TXN_OWNER + "OWNER] "
            + "[" + PREFIX_TXN_STATUS + "STATUS]";
    public static final String MESSAGE_EDIT_TRANSACTION_SUCCESS = "Edited Transaction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists in the sales book.";

    private final Index index;
    private final EditTxnCommand.EditTxnDescriptor editTxnDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editTxnDescriptor details to edit the person with
     */
    public EditTxnCommand(Index index, EditTxnCommand.EditTxnDescriptor editTxnDescriptor) {
        requireNonNull(index);
        requireNonNull(editTxnDescriptor);

        this.index = index;
        this.editTxnDescriptor = new EditTxnCommand.EditTxnDescriptor(editTxnDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TXN_DISPLAYED_INDEX);
        }

        Transaction transactionToEdit = lastShownList.get(index.getZeroBased());
        Transaction editedTransaction = createEditedTransaction(transactionToEdit, editTxnDescriptor);

        if (!transactionToEdit.isSameTransaction(editedTransaction) && model.hasTransaction(editedTransaction)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRANSACTION);
        }

        model.setTransaction(transactionToEdit, editedTransaction);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTransaction));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Transaction createEditedTransaction(Transaction transactionToEdit,
                                                  EditTxnCommand.EditTxnDescriptor editTxnDescriptor) {
        assert transactionToEdit != null;

        Description updatedDescription = editTxnDescriptor.getDescription().orElse(transactionToEdit.getDescription());
        Owner updatedOwner = editTxnDescriptor.getOwner().orElse(transactionToEdit.getOwner());
        TxnStatus updatedStatus = editTxnDescriptor.getStatus().orElse(transactionToEdit.getStatus());
        Value updatedValue = editTxnDescriptor.getValue().orElse(transactionToEdit.getValue());

        return new Transaction(updatedDescription, updatedValue, updatedStatus, updatedOwner);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTxnCommand)) {
            return false;
        }

        // state check
        EditTxnCommand e = (EditTxnCommand) other;
        return index.equals(e.index)
                && editTxnDescriptor.equals(e.editTxnDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTxnDescriptor {
        private Description description;
        private Owner owner;
        private TxnStatus status;
        private Value value;

        public EditTxnDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTxnDescriptor(EditTxnCommand.EditTxnDescriptor toCopy) {
            setDescription(toCopy.description);
            setOwner(toCopy.owner);
            setStatus(toCopy.status);
            setValue(toCopy.value);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, owner, status, value);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public Optional<Owner> getOwner() {
            return Optional.ofNullable(owner);
        }

        public void setStatus(TxnStatus status) {
            this.status = status;
        }

        public Optional<TxnStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public Optional<Value> getValue() {
            return Optional.ofNullable(value);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTxnCommand.EditTxnDescriptor)) {
                return false;
            }

            // state check
            EditTxnCommand.EditTxnDescriptor e = (EditTxnCommand.EditTxnDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getOwner().equals(e.getOwner())
                    && getStatus().equals(e.getStatus())
                    && getValue().equals(e.getValue());
        }
    }
}
