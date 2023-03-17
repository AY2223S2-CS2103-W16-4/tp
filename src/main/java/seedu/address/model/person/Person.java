package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Gender gender;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Company company;
    private final Industry industry;
    private final Occupation occupation;
    private final JobTitle jobTitle;
    private final Set<Tag> tags = new HashSet<>();

    private final Remark remark;
    private final LeadStatus status;
    /**
     * Every field must be present and not null. By default, the LeadStatus is "Uncontacted".
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Company company, Industry industry,
                  Occupation occupation, JobTitle jobTitle, Address address, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, gender, phone, email, company, industry, occupation, jobTitle, address, tags, remark);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.industry = industry;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = new LeadStatus(LeadStatusName.UNCONTACTED.getLabel());
    }

    /**
     * Constructor for a person with a given LeadStatus.
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Company company, Industry industry,
                  Occupation occupation, JobTitle jobTitle, Address address, Remark remark, Set<Tag> tags,
                     LeadStatus status) {
        requireAllNonNull(name, gender, phone, email, company, industry, occupation, jobTitle, address, tags, remark,
                status);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.industry = industry;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = status;

    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Company getCompany() {
        return company;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public LeadStatus getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getIndustry().equals(getIndustry())
                && otherPerson.getOccupation().equals(getOccupation())
                && otherPerson.getJobTitle().equals(getJobTitle())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, company, industry, occupation, jobTitle, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Gender: ")
                .append(getGender())
                .append("; Email: ")
                .append(getEmail())
                .append("; Company: ")
                .append(getCompany())
                .append("; Industry: ")
                .append(getIndustry())
                .append("; Occupation: ")
                .append(getOccupation())
                .append("; Job Title: ")
                .append(getJobTitle())
                .append("; Address: ")
                .append(getAddress())
                .append(" Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
