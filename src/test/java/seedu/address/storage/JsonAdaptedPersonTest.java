package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Industry;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENDER = "girl";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_COMPANY = "T@sleh";
    private static final String INVALID_INDUSTRY = "@utomobile";
    private static final String INVALID_OCCUPATION = "@gent";
    private static final String INVALID_JOBTITLE = "5t@ff";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_COMPANY = BENSON.getCompany().toString();
    private static final String VALID_INDUSTRY = BENSON.getIndustry().toString();
    private static final String VALID_OCCUPATION = BENSON.getOccupation().toString();
    private static final String VALID_JOBTITLE = BENSON.getJobTitle().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_COMPANY,
                        VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, null, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, null, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, null,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_COMPANY,
                        VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                        VALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIndustry_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, INVALID_INDUSTRY, VALID_OCCUPATION, VALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Industry.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIndustry_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_COMPANY,
                        null, VALID_OCCUPATION, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Industry.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidOccupation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, INVALID_OCCUPATION, VALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Occupation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullOccupation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_COMPANY,
                        VALID_INDUSTRY, null, VALID_JOBTITLE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Occupation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_COMPANY, VALID_INDUSTRY, VALID_OCCUPATION, INVALID_JOBTITLE,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_COMPANY,
                        VALID_INDUSTRY, VALID_OCCUPATION, null, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
