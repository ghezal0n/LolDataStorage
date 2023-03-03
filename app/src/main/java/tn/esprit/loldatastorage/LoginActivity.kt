package tn.esprit.loldatastorage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ShareActionProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val PREF_NAME = "LOGIN_PREF_LOL"
const val LOGIN = "LOGIN"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBRED = "IS_REMEMBRED"

class LoginActivity : AppCompatActivity() {

    lateinit var txtLogin: TextInputEditText
    lateinit var txtLayoutLogin: TextInputLayout

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout

    lateinit var cbRememberMe: CheckBox
    lateinit var btnLogin: MaterialButton

    //TODO 1 "Declare a var of SharedPreferences"
    lateinit var sharedpref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtLogin = findViewById(R.id.txtLogin)
        txtLayoutLogin = findViewById(R.id.txtLayoutLogin)

        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)

        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)

        //TODO 2 "Initialize the var of SharedPreferences"
        sharedpref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        //TODO 3 "Test in the SharedPreferences if there's data"
//        if(sharedpref.contains(LOGIN)){
////            txtLogin.text=sharedpref.getString(LOGIN,"")
//        }
//        else if (sharedpref.contains(PASSWORD)){
////            txtPassword.text=sharedpref.getString(PASSWORD,"")
//        }
//        else {
//            println("EREEEEEEEEEEEEEEEEEEEEEEEEEEEUUUUUUUUR")
        when {
            (sharedpref.getBoolean(IS_REMEMBRED, false)) -> navigate()
        }


        btnLogin.setOnClickListener {
            doLogin()
            println("Username" + txtLogin.text.toString())
            val allEntries: Map<String, *> = sharedpref.getAll()
            for ((key, value) in allEntries) {
                Log.d("map values", key + ": " + value.toString())
            }
        }

    }

    private fun doLogin() {
        if (validate()) {
            if (cbRememberMe.isChecked) {
                //TODO 4 "Edit the SharedPreferences by putting all the data"
                /**
                 * Validate and put data
                 */
                sharedpref.edit().apply {
                    putString(LOGIN, txtLogin.text.toString())
                    putString(PASSWORD, txtPassword.text.toString())
                }.apply()

            } else {
                sharedpref.edit().clear().apply()
            }
            navigate()
        }
    }

    private fun validate(): Boolean {
        txtLayoutLogin.error = null
        txtLayoutPassword.error = null

        if (txtLogin.text!!.isEmpty()) {
            txtLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (txtPassword.text!!.isEmpty()) {
            txtLayoutPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }

    private fun navigate() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
}