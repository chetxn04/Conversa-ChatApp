package com.example.chatapplicationnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtName : EditText;
    private lateinit var edtEmail : EditText;
    private lateinit var edtPassword : EditText;
    private lateinit var btnSignUp : Button;
    private lateinit var mAuth: FirebaseAuth;
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        edtName = findViewById(R.id.edt_name) ;
        edtEmail = findViewById(R.id.edt_email) ;
        edtPassword = findViewById(R.id.edt_password) ;
        btnSignUp = findViewById(R.id.btnSignUp) ;
        mAuth = FirebaseAuth.getInstance() ;




        btnSignUp.setOnClickListener{
            val name = edtName.text.toString() ;
            val email = edtEmail.text.toString() ;
            val password  = edtPassword.text.toString() ;

            signupuser(name , email,password)
        }
    }

    private fun signupuser(name : String , email : String , password : String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home activity
                    addUsertoDatabase(name , email , mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp , MainActivity::class.java)
                    finish()
                    startActivity(intent) ;


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"Some error has occurred .",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUsertoDatabase(name : String, email : String , uid : String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name , email , uid))


    }


}