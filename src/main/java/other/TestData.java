//Убрал служебные классы из test
//исправил название пакета
package other;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getName() {
        return faker.name().firstName();
    }

    public String getPassword() {
        return faker.number().digits(6);
    }

    public String getShortPassword() {
        return faker.number().digits(5);
    }

}
