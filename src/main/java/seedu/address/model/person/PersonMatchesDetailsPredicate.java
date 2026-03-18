package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.FilterDetails;

/**
 * Tests whether a {@code Person} matches the details specified in a {@link FilterDetails}.
 */
public class PersonMatchesDetailsPredicate implements Predicate<Person> {

    private final FilterDetails filterDetails;

    /**
     * Creates a {@code PersonMatchesDetailsPredicate} with the given {@code FilterDetails}.
     */
    public PersonMatchesDetailsPredicate(FilterDetails filterDetails) {
        this.filterDetails = Objects.requireNonNull(filterDetails);
    }

    @Override
    public boolean test(Person person) {
        return isNameMatch(person)
                & isFuzzyMatch(person.getEmail().value, filterDetails.getEmailKeywords())
                & isFuzzyMatch(person.getPhone().value, filterDetails.getPhoneNumberKeywords())
                & isExactMatch(person.getRoomNumber().value, filterDetails.getRoomNumberKeywords())
                & isFuzzyMatch(person.getStudentId().value, filterDetails.getStudentIdKeywords())
<<<<<<<<< Temporary merge branch 1
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
=========
                & isExactMatch(person.getEmergencyContact().value, filterDetails.getEmergencyContactKeywords())
                & matchesExactTags(person, filterDetails.getTagYearKeywords())
                & matchesExactTags(person, filterDetails.getTagMajorKeywords())
                & matchesExactTags(person, filterDetails.getTagGenderKeywords());
    }

>>>>>>>>> Temporary merge branch 2
    private boolean isNameMatch(Person person) {
        if (filterDetails.getNameKeywords().isEmpty()) {
            return true;
        }
<<<<<<<<< Temporary merge branch 1
        List<String> listOfKeywords = filterDetails.getNameKeywords().stream().toList();
=========
>>>>>>>>> Temporary merge branch 2
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(listOfKeywords);
        return predicate.test(person);
    }

<<<<<<<<< Temporary merge branch 1
    /**
     * Checks if the given {@code personValue} matches any of the {@code keywords} exactly (case-insensitive).
     */
    private boolean isExactMatch(String personValue, Set<String> keywords) {
=========
    private boolean isExactMatch(String fieldValue, Set<String> keywords) {
        assert keywords != null : "keywords set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (fieldValue.isEmpty()) {
            return false;
        }
        String lower = fieldValue.toLowerCase(Locale.ROOT);
        return keywords.stream().map(k -> k.toLowerCase(Locale.ROOT)).anyMatch(lower::equals);
    }

    /**
     * Checks if the given {@code fieldValue} matches any of the {@code keywords} via substring matching
     * (case-insensitive).
     */
    private boolean isFuzzyMatch(String fieldValue, Set<String> keywords) {
        assert keywords != null : "keywords set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        if (fieldValue.isEmpty()) {
            return false;
        }
        String lower = fieldValue.toLowerCase(Locale.ROOT);
        return keywords.stream().map(k -> k.toLowerCase(Locale.ROOT)).anyMatch(lower::contains);
    }

    private boolean matchesFuzzyTags(Person person, Set<String> keywords) {
        assert keywords != null : "tag keyword set should be non-null";
        if (keywords.isEmpty()) {
            return true;
        }
        return person.getTags().values().stream().anyMatch(tag -> {
            String lowerTag = tag.tagName.toLowerCase(Locale.ROOT);
            return keywords.stream()
                    .map(k -> k.toLowerCase(Locale.ROOT))
                    .anyMatch(lowerTag::contains);
        });
>>>>>>>>> Temporary merge branch 2
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
<<<<<<<<< Temporary merge branch 1
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
     * Checks if any of the person's tags exactly match any of the {@code keywords} (case-insensitive).
     */
    private boolean matchesExactTags(Person person, Set<String> keywords) {
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
                .anyMatch(tag -> StringUtil.equalsAnyIgnoreCase(tag, keywords));
=========
        }
        return person.getTags().values().stream()
                .anyMatch(tag -> keywords.stream()
                        .anyMatch(keyword -> tag.tagName.equalsIgnoreCase(keyword)));
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
        return new ToStringBuilder(this)
                .add("filterDetails", filterDetails)
                .toString();
    }
}
