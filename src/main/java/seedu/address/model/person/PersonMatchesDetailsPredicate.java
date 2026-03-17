package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.FilterDetails;
import seedu.address.model.tag.Tag;

/**
 * Tests whether a {@code Person} matches the details specified in a {@link FilterDetails}.
 */
public class PersonMatchesDetailsPredicate implements Predicate<Person> {

    private final FilterDetails filterDetails;

    /**
     * Creates a {@code PersonMatchesDetailsPredicate} with the given {@code FilterDetails}.
     * <br>
     * @param filterDetails person details to be used for matching.
     */
    public PersonMatchesDetailsPredicate(FilterDetails filterDetails) {
        this.filterDetails = Objects.requireNonNull(filterDetails);
    }

    /**
     * Returns true if the person matches all the details specified in the {@link FilterDetails}.
     *
     * @param person the person to be tested against the filter details
     * @return true if the person matches all the details specified in the filterDetails, false otherwise
     */
    @Override
    public boolean test(Person person) {
        return isNameMatch(person)
                & isFuzzyMatch(person.getEmail().value, filterDetails.getEmailKeywords())
                & isFuzzyMatch(person.getPhone().value, filterDetails.getPhoneNumberKeywords())
                & isExactMatch(person.getRoomNumber().value, filterDetails.getRoomNumberKeywords())
                & isFuzzyMatch(person.getStudentId().value, filterDetails.getStudentIdKeywords())
                & isFuzzyMatch(person.getEmergencyContact().value,
                filterDetails.getEmergencyContactKeywords())
                & isExactMatchTags(person.getYear(), filterDetails.getTagYearKeywords())
                & isFuzzyMatchTags(person.getMajor(), filterDetails.getTagMajorKeywords())
                & isExactMatchTags(person.getGender(), filterDetails.getTagGenderKeywords());
    }

    /**
     * Checks if the person's name matches any of the keywords specified in {@code FilterDetails}.
     * Name matching is done using {@link NameContainsKeywordsPredicate#test(Person)}.
     */
    private boolean isNameMatch(Person person) {
        if (filterDetails.getNameKeywords().isEmpty()) {
            return true;
        }
        List<String> listOfKeywords = filterDetails.getNameKeywords().stream().toList();
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(listOfKeywords);
        return predicate.test(person);
    }

    /**
     * Checks if the given {@code personValue} matches any of the {@code keywords} exactly (case-insensitive).
     */
    private boolean isExactMatch(String personValue, Set<String> keywords) {
        assert keywords != null : "keywords set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (personValue.isEmpty()) {
            return false;
        }
        assert keywords != null : "keywords set should be non-null";
        return StringUtil.equalsAnyIgnoreCase(personValue, keywords);
    }

    /**
     * Checks if the given {@code personValue} matches any of the {@code keywords} via fuzzy matching or
     * substring matching (case-insensitive).
     * Fuzzy matching allows for minor typos or differences. Substring matching checks if the keyword is
     * contained within the value.
     */
    private boolean isFuzzyMatch(String personValue, Set<String> keywords) {
        assert keywords != null : "keywords set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (personValue.isEmpty()) {
            return false;
        }
        return StringUtil.fuzzyMatchesAnyIgnoreCase(personValue, keywords);
    }

    /**
     * Checks if any of the {@code personTags} match any of the {@code keywords}.
     * Fuzzy matching allows for minor typos or differences.
     * Substring matching checks if the keyword is contained within the value.
     */
    private boolean isFuzzyMatchTags(Set<Tag> personTags, Set<String> keywords) {
        assert keywords != null : "tag keyword set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (personTags.isEmpty()) {
            return false;
        }
        return personTags
                .stream()
                .map(tag -> tag.getTagName())
                .anyMatch(tag -> StringUtil.fuzzyMatchesAnyIgnoreCase(tag, keywords));
    }

    /**
     * Checks if any of the {@code personTags} exactly match any of the {@code keywords} (case-insensitive).
     *
     * @param personTags The set of tags from the person.
     * @param keywords The set of keywords to match against.
     * @return True if any tag exactly matches any keyword, false otherwise.
     */
    private boolean isExactMatchTags(Set<Tag> personTags, Set<String> keywords) {
        assert keywords != null : "tag keyword set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (personTags.isEmpty()) {
            return true;
        }
        return personTags
                .stream()
                .map(tag -> tag.getTagName())
                .anyMatch(tag -> StringUtil.equalsAnyIgnoreCase(tag, keywords));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PersonMatchesDetailsPredicate)) {
            return false;
        }

        PersonMatchesDetailsPredicate otherPredicate = (PersonMatchesDetailsPredicate) other;
        return Objects.equals(this.filterDetails, otherPredicate.filterDetails);
    }

    @Override
    public String toString() {
        return "Filter Details:"
                + filterDetails.toString();
    }
}
