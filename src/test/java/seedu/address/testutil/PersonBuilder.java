package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "female";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_COMPANY = "Tesleh";
    public static final String DEFAULT_INDUSTRY = "automobile";
    public static final String DEFAULT_OCCUPATION = "engineer";
    public static final String DEFAULT_JOBTITLE = "industrial engineer";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";
    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Company company;
    private Industry industry;
    private Occupation occupation;
    private JobTitle jobTitle;
    private Address address;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        company = new Company(DEFAULT_COMPANY);
        industry = new Industry(DEFAULT_INDUSTRY);
        occupation = new Occupation(DEFAULT_OCCUPATION);
        jobTitle = new JobTitle(DEFAULT_JOBTITLE);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        gender = personToCopy.getGender();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        company = personToCopy.getCompany();
        industry = personToCopy.getIndustry();
        occupation = personToCopy.getOccupation();
        jobTitle = personToCopy.getJobTitle();
        address = personToCopy.getAddress();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code Person} that we are building.
     */
    public PersonBuilder withIndustry(String industry) {
        this.industry = new Industry(industry);
        return this;
    }

    /**
     * Sets the {@code Occupation} of the {@code Person} that we are building.
     */
    public PersonBuilder withOccupation(String occupation) {
        this.occupation = new Occupation(occupation);
        return this;
    }

    /**
     * Sets the {@code Job Title} of the {@code Person} that we are building.
     */
    public PersonBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }


    public Person build() {
        return new Person(name, gender, phone, email, company, industry, occupation, jobTitle, address, remark, tags);
    }
}
