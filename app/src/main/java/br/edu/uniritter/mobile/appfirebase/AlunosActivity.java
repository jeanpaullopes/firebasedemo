package br.edu.uniritter.mobile.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunosActivity extends AppCompatActivity {
    private final String TAG = "AlunosActivy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> aluno = new HashMap<>();
        aluno.put("ra", 1);
        aluno.put("nome", "Zequinha");
        aluno.put("email", "zequinha@gmail.com");
        //db.collection("alunos").add(aluno);
        db.collection("alunos").document("zequinha").set(aluno)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               Log.d(TAG, "onComplete: "+task.isSuccessful());
                                           }
                                       }
                );
        db.collection("alunos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete: "+task.getResult());
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}