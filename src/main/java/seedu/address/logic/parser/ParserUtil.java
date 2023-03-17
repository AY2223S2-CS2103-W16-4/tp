package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

//import java.time.format.DecimalStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Industry;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

//import javax.xml.validation.Validator;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        if (!Gender.isValidGender(gender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(gender);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String company} into a {@code Company}.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        if (!Company.isValidCompanyName(company)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(company);
    }

    /**
     * Parses a {@code String occupation} into a {@code Occupation}.
     *
     * @throws ParseException if the given {@code occupation} is invalid.
     */
    public static Occupation parseOccupation(String occupation) throws ParseException {
        requireNonNull(occupation);
        if (!Occupation.isValidOccupation(occupation)) {
            throw new ParseException(Occupation.MESSAGE_CONSTRAINTS);
        }
        return new Occupation(occupation);
    }

    /**
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(jobTitle);
    }

    /**
     * Parses a {@code String industry} into a {@code Industry}.
     *
     * @throws ParseException if the given {@code industry} is invalid.
     */
    public static Industry parseIndustry(String industry) throws ParseException {
        requireNonNull(industry);
        if (!Industry.isValidIndustry(industry)) {
            throw new ParseException(Industry.MESSAGE_CONSTRAINTS);
        }
        return new Industry(industry);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        if (!Description.isValidDescription(description)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    /**
     * Parses a {@code String status} into a {@code TxnStatus}.
     *
     * @throws ParseException if the given {@code txnStatus} is invalid.
     */
    public static TxnStatus parseTxnStatus(String status) throws ParseException {
        requireNonNull(status);
        if (!TxnStatus.isValidTxnStatus(status)) {
            throw new ParseException(TxnStatus.MESSAGE_CONSTRAINTS);
        }
        return new TxnStatus(status);
    }

    /**
     * Parses a {@code String value} into a {@code Value}.
     *
     * @throws ParseException if the given {@code value} is invalid.
     */
    public static Value parseValue(String value) throws ParseException {
        requireNonNull(value);
        if (!Value.isValidValue(value)) {
            throw new ParseException(Value.MESSAGE_CONSTRAINTS);
        }
        return new Value(value);
    }

    /**
     * Parses a {@code String value} into a {@code Value}.
     *
     * @throws ParseException if the given {@code value} is invalid.
     */
    public static Owner parseOwner(String owner) throws ParseException {
        requireNonNull(owner);
        if (!Owner.isValidOwner(owner)) {
            throw new ParseException(Owner.MESSAGE_CONSTRAINTS);
        }
        return new Owner(owner);
    }
}
