package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDUSTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withGender("female")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withCompany("company")
            .withIndustry("industry")
            .withOccupation("occupation")
            .withJobTitle("job title")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withGender("male")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withCompany("company")
            .withIndustry("industry")
            .withOccupation("occupation")
            .withJobTitle("job title")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withGender("male")
            .withPhone("95352563").withEmail("heinz@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withGender("male")
            .withPhone("87652533").withEmail("cornelia@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title").withAddress("10th street")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withGender("female").withEmail("werner@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withGender("female")
            .withPhone("9482427").withEmail("lydia@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withGender("male")
            .withPhone("9482442").withEmail("anna@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withGender("male").withPhone("8482424")
            .withEmail("stefan@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withGender("female")
            .withPhone("8482131").withEmail("hans@example.com").withCompany("company")
            .withIndustry("industry").withOccupation("occupation").withJobTitle("job title")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCompany(VALID_COMPANY_AMY)
            .withIndustry(VALID_INDUSTRY_AMY).withOccupation(VALID_OCCUPATION_AMY).withJobTitle(VALID_JOBTITLE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withCompany(VALID_COMPANY_BOB)
            .withIndustry(VALID_INDUSTRY_BOB).withOccupation(VALID_OCCUPATION_BOB).withJobTitle(VALID_JOBTITLE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
