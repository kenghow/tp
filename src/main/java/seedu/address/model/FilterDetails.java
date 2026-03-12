package seedu.address.model;

import java.util.List;
import java.util.Set;

/**
 * Stores the details of the filter to be applied to the address book.
 */
public class FilterDetails {
    private Set<String> nameKeywords;
    private Set<String> emailKeywords;
    private Set<String> phoneNumberKeywords;
    private Set<String> roomNumberKeywords;
    private Set<String> studentIdKeywords;
    private Set<String> emergencyContactKeywords;
    private Set<String> tagKeywords;
    private Set<String> tagYearKeywords;
    private Set<String> tagMajorKeywords;
    private Set<String> tagGenderKeywords;

    public FilterDetails() {
    }

    // ==================== Setters ======================
    public void setNameKeywords(List<String> nameKeywords) {
        this.nameKeywords = nameKeywords;
    }

    public void setEmailKeywords(List<String> emailKeywords) {
        this.emailKeywords = emailKeywords;
    }

    public void setPhoneNumberKeywords(List<String> phoneNumberKeywords) {
        this.phoneNumberKeywords = phoneNumberKeywords;
    }

    public void setRoomNumberKeywords(List<String> roomNumberKeywords) {
        this.roomNumberKeywords = roomNumberKeywords;
    }

    public void setStudentIdKeywords(List<String> studentIdKeywords) {
        this.studentIdKeywords = studentIdKeywords;
    }

    public void setEmergencyContactKeywords(List<String> emergencyContactKeywords) {
        this.emergencyContactKeywords = emergencyContactKeywords;
    }

    public void setTagKeywords(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    public void setTagYearKeywords(List<String> tagYearKeywords) {
        this.tagYearKeywords = tagYearKeywords;
    }

    public void setTagMajorKeywords(List<String> tagMajorKeywords) {
        this.tagMajorKeywords = tagMajorKeywords;
    }

    public void setTagGenderKeywords(List<String> tagGenderKeywords) {
        this.tagGenderKeywords = tagGenderKeywords;
    }

    // ==================== Getters ======================
    public List<String> getNameKeywords() {
        return nameKeywords;
    }

    public List<String> getEmailKeywords() {
        return emailKeywords;
    }

    public List<String> getPhoneNumberKeywords() {
        return phoneNumberKeywords;
    }

    public List<String> getRoomNumberKeywords() {
        return roomNumberKeywords;
    }

    public List<String> getStudentIdKeywords() {
        return studentIdKeywords;
    }

    public List<String> getEmergencyContactKeywords() {
        return emergencyContactKeywords;
    }

    public List<String> getTagKeywords() {
        return tagKeywords;
    }

    public List<String> getTagYearKeywords() {
        return tagYearKeywords;
    }

    public List<String> getTagMajorKeywords() {
        return tagMajorKeywords;
    }

    public List<String> getTagGenderKeywords() {
        return tagGenderKeywords;
    }
}
