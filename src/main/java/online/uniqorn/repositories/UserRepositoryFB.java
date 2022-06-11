package online.uniqorn.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;
import online.uniqorn.models.User;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepositoryFB extends FirebaseRepository {

    public UserRepositoryFB(){
        if(!FirebaseRepository.isInitialized()){
            try {
                super.initializeFirebase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public User loginUser(String name,String password) throws AuthenticationException, ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection("Users");
        Query query = users.whereEqualTo("ID", name).whereEqualTo("Passcode",password);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = null;
        documents = querySnapshot.get().getDocuments();
        if(documents.size()<1) throw new AuthenticationException("Invalid user");
        return documents.get(0).toObject(User.class);
    }
}
