package factory;

import com.github.javafaker.Faker;
import model.User;

public class UserFactory {
    static Faker faker = new Faker();
    public static User validUser(){
        return new User(faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(6,8)
        );
    }
    public static User incorrectPassword() {
        return new User(faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(3,5)
        );
    }
}
