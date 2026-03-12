package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.FilterDetails;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonMatchesDetailsPredicate implements Predicate<Person> {
    FilterDetails filterDetails;

    @Override
    public boolean test(Person person) {
        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesDetailsPredicate)) {
            return false;
        }

        PersonMatchesDetailsPredicate otherPersonMatchesDetailsPredicate = (PersonMatchesDetailsPredicate) other;
        return nameKeywords.equals(otherPersonMatchesDetailsPredicate.nameKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", nameKeywords).toString();
    }
}
