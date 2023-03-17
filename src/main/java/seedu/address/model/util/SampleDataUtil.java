package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Industry;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                new Gender("male"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Company("Pizza House"),
                new Industry("food and beverage"),
                new Occupation("purchaser"),
                new JobTitle("procurement officer"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                EMPTY_REMARK,
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"),
                new Gender("female"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Company("Tesleh"),
                new Industry("automobile"),
                new Occupation("engineer"),
                new JobTitle("industrial engineer"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),
                new Gender("female"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Company("Mac King"),
                new Industry("food and beverage"),
                new Occupation("entrepreneur"),
                new JobTitle("CEO"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                EMPTY_REMARK,
                getTagSet("neighbours")),
            new Person(new Name("David Li"),
                new Gender("male"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Company("Googol"),
                new Industry("information technology"),
                new Occupation("data scientist"),
                new JobTitle("tech lead"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                EMPTY_REMARK,
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"),
                new Gender("male"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Company("SalesPunch"),
                new Industry("software"),
                new Occupation("software developer"),
                new JobTitle("software developer"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                EMPTY_REMARK,
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"),
                new Gender("male"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Company("NUS"),
                new Industry("education"),
                new Occupation("professor"),
                new JobTitle("research instructor"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                EMPTY_REMARK,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
