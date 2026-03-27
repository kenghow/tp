package seedu.address.ui.filter;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Reusable filter field in the {@code FilterPanel}.
 */
public class FilterPanelField extends UiPart<Region> {
    private static final String FXML = "FilterPanelField.fxml";

    private final List<String> keywords;
    private final KeywordsChangedHandler onKeywordsChanged;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField keywordInputField;
    @FXML
    private Label keywordsLabel;
    @FXML
    private FlowPane keywordsFlowPane;

    /**
     * Creates a reusable filter field section.
     */
    public FilterPanelField(String title, String promptText, KeywordsChangedHandler onKeywordsChanged) {
        super(FXML);
        requireNonNull(title);
        requireNonNull(promptText);
        this.onKeywordsChanged = requireNonNull(onKeywordsChanged);
        this.keywords = new ArrayList<>();
        titleLabel.setText(title);
        keywordInputField.setPromptText(promptText);
    }

    /**
     * Replaces the current list of keywords and redraws this field's FlowPane tags.
     */
    public void setKeywords(List<String> updatedKeywords) {
        requireNonNull(updatedKeywords);
        keywords.clear();
        updatedKeywords.stream()
                .map(keyword -> keyword.trim())
                .forEach(this::addKeywordIfAbsent);
        renderKeywords(updatedKeywords);
    }

    /**
     * Handler for when the user presses the Enter key in the keyword input field.
     *
     * It adds the keyword, re-render the FlowPane tags, trigger the
     * {@code onKeywordsChanged} event handler, and finally clears the keyword input field.
     */
    @FXML
    private void handleFieldEntered() {
        String keyword = keywordInputField.getText();
        String trimmedKeyword = keyword.trim();

        if (trimmedKeyword == null || trimmedKeyword.isEmpty()) {
            return;
        }
        // Propose the new keyword list with the added keyword
        List<String> proposedNewKeywords = addKeywordIfAbsent(trimmedKeyword);

        // Trigger the event handler to validate the proposed new keyword list
        List<String> validatedNewKeywords = onKeywordsChanged.handle(List.copyOf(proposedNewKeywords));

        // Update the keyword list with the validated new keyword list
        renderKeywords(validatedNewKeywords);
        keywordInputField.clear();
    }

    private List<String> addKeywordIfAbsent(String keyword) {
        if (!keywords.contains(keyword)) {
            keywords.add(keyword);
        }
        return keywords;
    }

    // Renders the proposed keywords as {@code FilterPanelTag} in the FlowPane
    private void renderKeywords(List<String> proposedKeywords) {
        keywordsFlowPane.getChildren().clear();
        proposedKeywords.forEach(keyword -> keywordsFlowPane.getChildren()
                .add(new FilterPanelTag(keyword, this::handleDeleteTag).getRoot()));
    }

    // Remove tagToDelete from the keyword list
    private void handleDeleteTag(String tagToDelete) {
        if (!keywords.remove(tagToDelete)) {
            return;
        }
        List<String> updatedKeywordList = onKeywordsChanged.handle(List.copyOf(keywords));
        renderKeywords(updatedKeywordList);
    }

    /**
     * Handler for when the keywords in this field are edited.
     */
    @FunctionalInterface
    public interface KeywordsChangedHandler {
        List<String> handle(List<String> keywords);
    }
}
