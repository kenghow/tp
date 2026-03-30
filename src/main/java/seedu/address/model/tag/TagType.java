package seedu.address.model.tag;

import java.util.Locale;

/**
 * Represents the type of a Tag in the address book.
 * Guarantees: immutable; name is valid
 */
public enum TagType {

    YEAR( "^[1-6]$"),
    MAJOR( "[\\p{Alnum} ]*[\\p{Alnum}]+[\\p{Alnum} ]*"),
    GENDER("^(she/her|he/him|they/them)$");

    private final String validationRegex;


    TagType( String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public boolean isValidTagName(String tagName) {
        if (tagName == null) {
            return true;
        }
        return tagName.matches(validationRegex);
    }
}
