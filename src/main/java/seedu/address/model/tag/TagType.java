package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

/**
 * Represents the category of a {@link Tag} in the hall ledger.
 *
 * <p>Each {@code TagType} defines its own validation regex that determines what
 * content is considered valid for tags of that type.
 *
 * <p>Guarantees: immutable; each constant's validation regex is fixed at compile time.
 */
public enum TagType {

    /**
     * Tag representing the resident's year of study.
     * Valid content is a single digit from {@code 1} to {@code 6} inclusive.
     */
    YEAR(List.of("1", "2", "3", "4", "5", "6"), null),

    /**
     * Tag representing the resident's academic major.
     * Valid content is an alphanumeric string; internal spaces between words are permitted,
     * but leading and trailing spaces are not.
     */
    MAJOR(null, "^(?=.{1,100}$)[A-Za-z&]+( [A-Za-z&]+)*$"),

    /**
     * Tag representing the resident's gender pronouns.
     * Valid content is one of {@code she/her}, {@code he/him}, or {@code they/them}.
     * Input is normalised to lowercase before validation — see {@link Tag#getNormalisedTagContent}.
     */
    GENDER(List.of("she/her", "he/him", "they/them"), null);

    private final List<String> allowedValues;
    private final String validationRegex;

    TagType(List<String> allowedValues, String validationRegex) {
        this.allowedValues = allowedValues;
        this.validationRegex = validationRegex;
    }

    /**
     * Checks if the given tag content matches the specified tag type's validation regex.
     *
     * @param tagContent the tag content to validate, or null if no tag is present.
     * @return true if {@code tagContent} is null or matches the validation regex, false otherwise.
     */
    public boolean isValidTagContent(String tagContent) {
        requireNonNull(tagContent);

        // Case 1: Use allowed values (closed set)
        if (allowedValues != null) {
            return allowedValues.contains(tagContent);
        }

        // Defensive check: if allowedValues is null, validationRegex must be non-null
        if (validationRegex == null) {
            throw new IllegalStateException("TagType must have either allowedValues or validationRegex defined.");
        }

        // Case 2: Use regex (open set)
        return tagContent.matches(validationRegex);
    }

    public Optional<List<String>> getAllowedValues() {
        return allowedValues == null
                ? Optional.empty()
                : Optional.of(List.copyOf(allowedValues)); // immutable copy
    }
}
