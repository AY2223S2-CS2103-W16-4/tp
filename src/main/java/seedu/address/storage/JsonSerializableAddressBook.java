package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_TRANSACTIONS = "Transactions list contains duplicate transaction(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedTxn> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and transactions.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("transactions") List<JsonAdaptedTxn> transactions) {
        this.persons.addAll(persons);
        this.transactions.addAll(transactions);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        transactions.addAll(source.getTransactionList().stream().map(JsonAdaptedTxn::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedTxn jsonAdaptedTxn : transactions) {
            Transaction transaction = jsonAdaptedTxn.toModelType();
            if (addressBook.hasTransaction(transaction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSACTIONS);
            }
            addressBook.addTransaction(transaction);
        }
        return addressBook;
    }

}
