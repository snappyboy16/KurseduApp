package com.example.kursedapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class AuthActivity : AppCompatActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        auth = Firebase.auth
        auth.currentUser
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.d("Error", "${e.printStackTrace()}")
            }
        }
    }
    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }
    fun signInGoogle(view: View) {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthGoogle(idToken: String) {
        val credencial = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credencial).addOnCompleteListener{
            if(it.isSuccessful){
                Log.d("Account", "Google Sign In done.")
                toastMessage("Авторизация прошла успешно!")
                intentCourse()
            } else {
                toastMessage("Ошибка авторизации, попробуйте еще раз!")
                Log.d("Account", "Google Sign In error.")
            }
        }
    }
    private fun intentCourse() {
        val intentCourseActivity = Intent(this, CourseActivity::class.java)
        startActivity(intentCourseActivity)
        finish()
    }
    private fun toastMessage(message: String) {
        val toastMe = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toastMe.show()
    }
}
