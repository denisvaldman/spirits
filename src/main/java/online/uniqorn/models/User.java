package online.uniqorn.models;

public class User {
    private String ID;
    private String Passcode;
    private String secret;

    public User() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPasscode() {
        return Passcode;
    }

    public void setPasscode(String passcode) {
        this.Passcode = passcode;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
