package data.techPanda;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;

public class AccountData {
    public static AccountData getAccountData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_PATH + "/Account.json"), AccountData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("webEmail")
    private String webEmailServer;
    @JsonProperty("password")
    private String password;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getWebEmailServer() {
        return webEmailServer;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
