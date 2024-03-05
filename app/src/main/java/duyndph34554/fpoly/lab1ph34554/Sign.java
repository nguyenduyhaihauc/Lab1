package duyndph34554.fpoly.lab1ph34554;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtemail, edtpassword, edtrepassword;
    private Button btnDangKy;
    private TextView txtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign);

        mAuth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.edtEmailSg);
        edtpassword = findViewById(R.id.edtPassSg);
        edtrepassword = findViewById(R.id.edtRePassSg);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtBack = findViewById(R.id.txtBack);

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign.this, Login.class);
                startActivity(intent);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                String repassword = edtrepassword.getText().toString();

                if (email.equals("") || password.equals("") || repassword.equals("")){
                    Toast.makeText(Sign.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(repassword)){
                    Toast.makeText(Sign.this, "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(Sign.this, Login.class);
                                    startActivity(intent);
                                    Toast.makeText(Sign.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Sign.this, "Đăng ký thất bại",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });



    }
}