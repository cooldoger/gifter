package com.cooldoger.gifter;

public class Person {
    private final String firstName;
    private final String lastName;
    private final String email;
    private Person recipient;
    private Person giver;

    public Person(final String firstName, final String lastName, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Person getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Person getGiver() {
        return this.giver;
    }

    public void setGiver(Person giver) {
        this.giver = giver;
    }
}
