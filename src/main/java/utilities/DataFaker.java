package utilities;

import com.github.javafaker.Faker;

public class DataFaker {
    Faker faker;

    public DataFaker() {
        faker = new Faker();
    }
    public static DataFaker getDataFaker(){
        return new DataFaker();
    }
    public String getFirstName(){
        return faker.address().firstName();
    }
    public String getLastName(){
        return faker.address().lastName();
    }
    public String getCityName(){
        return faker.address().cityName();
    }
    public String getAddress(){
        return faker.address().streetAddress();
    }
    public String getStateName(){
        return faker.address().zipCode();
    }
    public String getZipCode(){
        return faker.address().firstName();
    }
    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }
    public String getPassword(){
        return faker.internet().password();
    }
}
