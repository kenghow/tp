package seedu.address.ui.filter;

/**
 * Handler for when a tag is deleted from a filter input.
 * Executes custom logic when the user clicks the delete button on a keyword tag.
 */
@FunctionalInterface
public interface TagDeleteHandler {
    /**
     * Handles the deletion of a tag.
     *
     * @param tagToDelete the keyword text from the tag being deleted
     */
    void handle(String tagToDelete);
}

