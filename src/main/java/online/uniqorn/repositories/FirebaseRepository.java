package online.uniqorn.repositories;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


import java.io.IOException;
import java.io.InputStream;

public class FirebaseRepository {

    private static boolean initialized = false;

    public static boolean isInitialized() {
        return initialized;
    }

    public static void setInitialized(boolean initialized) {
        FirebaseRepository.initialized = initialized;
    }

    public void initializeFirebase() throws IOException {
        InputStream serviceAccount = null;
        serviceAccount = getFileFromResourceAsStream("serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spirits-c39ab-default-rtdb.europe-west1.firebasedatabase.app")
                .build();
        FirebaseApp.initializeApp(options);
        setInitialized(true);
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
