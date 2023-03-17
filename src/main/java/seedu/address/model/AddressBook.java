package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.transaction.PersonTransactionMap;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.UniqueTransactionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTransactionList transactions;
    private final PersonTransactionMap personTransactionRelation;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        transactions = new UniqueTransactionList();
        personTransactionRelation = new PersonTransactionMap();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replace the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //================= person-level operations ==================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //================= transaction-level operations ==================================

    /**
     * Returns true if a transaction with the same identity as {@code transaction} exists in the sales book.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a given transaction to the transaction list.
     * A transaction must have an association with a Person.
     * A person may have zero to many transactions.
     *
     * @param t
     */
    public void addTransaction(Transaction t) {
        requireAllNonNull(t);
        transactions.add(t);

        //personTransactionRelation.addRelation(t);
        //TODO: Add customer-transaction relationship
    }

    /**
     * Replaces the given transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the sales book.
     * The transaction identity of {@code editedTransaction} must not be the same as another existing
     * transaction in the sales book.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);
        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    //================= util methods ==================================

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n"
                + transactions.asUnmodifiableObservableList().size() + "transactions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && transactions.equals(((AddressBook) other).transactions));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + transactions.hashCode();
    } //TODO: check later
}
