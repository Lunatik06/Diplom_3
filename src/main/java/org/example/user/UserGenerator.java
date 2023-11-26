package org.example.user;

import com.github.javafaker.Faker;

public class UserGenerator {

    static Faker faker = new Faker();

    public static User random() {
        return new User(faker.internet().emailAddress(), faker.number().digits(6), faker.name().firstName());
    }
}
