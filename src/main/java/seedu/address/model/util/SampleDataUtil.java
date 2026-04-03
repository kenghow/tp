package seedu.address.model.util;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagType;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an array of sample {@code Person} objects with realistic data,
     * including tags (year, gender, major) and demerit incidents.
     */
    public static Person[] getSamplePersons() {
        return new Person[] {
            new PersonBuilder()
                    .withName("Alex Yeoh")
                    .withPhone("87438807")
                    .withEmail("alexyeoh@example.com")
                    .withStudentId("A0485321Y")
                    .withRoomNumber("4-A")
                    .withEmergencyContact("98765432")
                    .withRemark("My best friend")
                    .withTags(
                            new Object[] {TagType.YEAR, "2"},
                            new Object[] {TagType.GENDER, "he/him"},
                            new Object[] {TagType.MAJOR, "Computer Science"})
                    .withDemeritIncidents(
                            new Object[] {31, 1, "Left fan on overnight"},
                            new Object[] {28, 1, "Common area left untidy"})
                    .build(),
            new PersonBuilder()
                    .withName("Bernice Yu")
                    .withPhone("99272758")
                    .withEmail("berniceyu@example.com")
                    .withStudentId("A1123456Z")
                    .withRoomNumber("15-R")
                    .withEmergencyContact("91234567")
                    .withTags(
                            new Object[] {TagType.YEAR, "1"},
                            new Object[] {TagType.GENDER, "she/her"},
                            new Object[] {TagType.MAJOR, "Business"})
                    .build(),
            new PersonBuilder()
                    .withName("Charlotte Oliveiro")
                    .withPhone("93210283")
                    .withEmail("charlotte@example.com")
                    .withStudentId("A1246354T")
                    .withRoomNumber("3-D")
                    .withEmergencyContact("87654321")
                    .withRemark("Really funny person")
                    .withTags(
                            new Object[] {TagType.YEAR, "4"},
                            new Object[] {TagType.GENDER, "they/them"},
                            new Object[] {TagType.MAJOR, "Psychology"})
                    .withDemeritIncidents(
                            new Object[] {18, 1, "Quiet-hours visitor warning"})
                    .build(),
            new PersonBuilder()
                    .withName("David Li")
                    .withPhone("91031282")
                    .withEmail("lidavid@example.com")
                    .withStudentId("A0148321W")
                    .withRoomNumber("10-C")
                    .withEmergencyContact("12345678")
                    .withTags(
                            new Object[] {TagType.YEAR, "3"},
                            new Object[] {TagType.GENDER, "he/him"},
                            new Object[] {TagType.MAJOR, "Mechanical Engineering"})
                    .withDemeritIncidents(
                            new Object[] {21, 2, "Repeated excessive noise"},
                            new Object[] {25, 1, "Corridor obstruction"})
                    .build(),
            new PersonBuilder()
                    .withName("Irfan Ibrahim")
                    .withPhone("92492021")
                    .withEmail("irfan@example.com")
                    .withStudentId("A1436528Q")
                    .withRoomNumber("5-B")
                    .withEmergencyContact("56789012")
                    .withTags(
                            new Object[] {TagType.YEAR, "2"},
                            new Object[] {TagType.GENDER, "he/him"},
                            new Object[] {TagType.MAJOR, "Information Security"})
                    .build(),
            new PersonBuilder()
                    .withName("Roy Balakrishnan")
                    .withPhone("92624417")
                    .withEmail("royb@example.com")
                    .withStudentId("A0246835Z")
                    .withRoomNumber("12-D")
                    .withEmergencyContact("23456789")
                    .withRemark("Allergic to shellfish")
                    .withTags(
                            new Object[] {TagType.YEAR, "5"},
                            new Object[] {TagType.GENDER, "he/him"},
                            new Object[] {TagType.MAJOR, "Law"})
                    .withDemeritIncidents(
                            new Object[] {26, 1, "Unauthorized appliance found"},
                            new Object[] {26, 2, "Repeated unauthorized appliance use"})
                    .build()
        };
    }

    /**
     * Returns a sample address book populated with the sample persons.
     *
     * @throws DataLoadingException if an error occurs while loading the sample data
     */
    public static ReadOnlyAddressBook getSampleAddressBook() throws DataLoadingException {
        AddressBook sampleAb = new AddressBook();
        try {
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
        } catch (IllegalArgumentException e) {
            throw new DataLoadingException(e);
        }
        return sampleAb;
    }
}
