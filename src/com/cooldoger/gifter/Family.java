package com.cooldoger.gifter;

import java.util.*;

public class Family {
    private final String lastName;
    private List<Person> members;

    // Assume family member shared the same name
    public Family(final String lastName) {
        this.lastName = lastName;
        this.members = new ArrayList<Person>();
    }

    public String getLastName() {
        return this.lastName;
    }

    public void addMember(Person p) {
        this.members.add(p);
        p.setFamily(this);
    }

    public List<Person> getMembers() {
        return this.members;
    }

    public int size() {
        return this.members.size();
    }
}
