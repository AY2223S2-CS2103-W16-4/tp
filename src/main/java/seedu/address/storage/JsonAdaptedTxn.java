package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
public class JsonAdaptedTxn {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Transaction's %s field is missing!";

    private final String description;
    private final String owner;
    private final String status;
    private final String value;

    /**
     * Constructs a {@code JsonAdaptedTxn} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedTxn(@JsonProperty("description") String description,
             @JsonProperty("owner") String owner,
             @JsonProperty("status") String status,
             @JsonProperty("value") String value) {
        this.description = description;
        this.owner = owner;
        this.status = status;
        this.value = value;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedTxn(Transaction source) {
        description = source.getDescription().value;
        owner = source.getOwner().value;
        status = source.getStatus().value;
        value = source.getValue().value;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Transaction toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (owner == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Owner.class.getSimpleName()));
        }
        if (!Owner.isValidOwner(owner)) {
            throw new IllegalValueException(Owner.MESSAGE_CONSTRAINTS);
        }
        final Owner modelOwner = new Owner(owner);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TxnStatus.class.getSimpleName()));
        }
        if (!TxnStatus.isValidTxnStatus(status)) {
            throw new IllegalValueException(TxnStatus.MESSAGE_CONSTRAINTS);
        }
        final TxnStatus modelStatus = new TxnStatus(status);

        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Value.class.getSimpleName()));
        }
        if (!Value.isValidValue(value)) {
            throw new IllegalValueException(Value.MESSAGE_CONSTRAINTS);
        }
        final Value modelValue = new Value(value);

        return new Transaction(modelDescription, modelValue, modelStatus, modelOwner);
    }

}
