package online.uniqorn.repositories;

import online.uniqorn.models.User;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserRepositoryLocal {

    public void saveUser(User user){
        Preferences userPreferences = Preferences.userRoot().node("/spirits");
        userPreferences.put("ID", user.getID());
        userPreferences.put("Passcode", user.getPasscode());
        userPreferences.put("secret", user.getSecret());
    }

    public boolean userExist() {
        try {
            if(!Preferences.userRoot().nodeExists("/spirits")){
                return false;
            }
        } catch (BackingStoreException e) {
            return false;
        }
        return true;
    }
}
