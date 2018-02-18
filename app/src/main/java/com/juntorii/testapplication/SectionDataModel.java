package com.juntorii.testapplication;

import java.util.List;

/**
 * Created by juntorii on 2018-02-17.
 */

public class SectionDataModel {
    private String headerTitle;
    private List<Person> personList;

    public SectionDataModel(String headerTitle, List<Person> personList) {
        this.headerTitle = headerTitle;
        this.personList = personList;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
