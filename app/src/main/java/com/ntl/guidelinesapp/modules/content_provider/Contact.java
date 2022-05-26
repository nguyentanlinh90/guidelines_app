package com.ntl.guidelinesapp.modules.content_provider;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {
    private String id;
    private String displayName;
    private List<PhoneNumber> phoneNumbers;

    public Contact(String id, String displayName, List<PhoneNumber> phoneNumber) {
        this.id = id;
        this.displayName = displayName;
        this.phoneNumbers = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
