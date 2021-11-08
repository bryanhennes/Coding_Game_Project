package com.zybooks.codinggameproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText dobEdit;
    EditText emailEdit;
    EditText userNameEdit;
    EditText passwordEdit;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesUsers;
    public static final String MYPREFUSERS = "MY_PREF_USERS_FILE";
    public static final String MYPREF = "MY_PREF_FILE";
    public static final String FIRSTNAME = "FIRST_NAME_KEY";
    public static final String LASTNAME = "LAST_NAME_KEY";
    public static final String DOB = "DOB_KEY";
    public static final String EMAIL = "EMAIL_KEY";
    public static final String USERNAME = "USERNAME_KEY";
    public static final String PASSWORD = "PASSWORD_KEY";
    String firstName;
    String lastName;
    String dob;
    String userName;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assign edittext fields
        firstNameEdit = (EditText) findViewById(R.id.first_name_field);
        lastNameEdit = (EditText) findViewById(R.id.last_name_field);
        dobEdit = (EditText) findViewById(R.id.dob_field);
        emailEdit = (EditText) findViewById(R.id.email_field);
        userNameEdit = (EditText) findViewById(R.id.username_field_edit);
        passwordEdit = (EditText) findViewById(R.id.password_field_edit);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        sharedPreferencesUsers = getSharedPreferences(MYPREFUSERS, Context.MODE_PRIVATE);
    }

    //save data once done registering
    public void saveData(View view) {
        //as long as fields are not blank, save data as strings and return to login page
        if (!fieldEmpty(firstNameEdit) && !fieldEmpty(lastNameEdit) && !fieldEmpty(dobEdit) && !fieldEmpty(emailEdit) && !fieldEmpty(userNameEdit) && !fieldEmpty(passwordEdit) ) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            firstName = firstNameEdit.getText().toString();
            lastName = lastNameEdit.getText().toString();
            dob = dobEdit.getText().toString();
            userName = userNameEdit.getText().toString();
            email = emailEdit.getText().toString();
            password = passwordEdit.getText().toString();
            editor.putString(FIRSTNAME, firstName);
            editor.putString(LASTNAME, lastName);
            editor.putString(DOB, dob);
            editor.putString(USERNAME, userName);
            editor.putString(EMAIL, email);
            editor.putString(PASSWORD, password);
            editor.commit();
            editor.apply();
            returnToLogin(view);



            //return to login page
        }
        else {
            Toast.makeText(RegisterActivity.this, "Cannot leave any fields blank", Toast.LENGTH_LONG).show(); //prompt user to not leave fields blank
            highlightEmptyFields(firstNameEdit);
            highlightEmptyFields(lastNameEdit);
            highlightEmptyFields(dobEdit);
            highlightEmptyFields(emailEdit);
            highlightEmptyFields(userNameEdit);
            highlightEmptyFields(passwordEdit);

        }
        //String userNameCheck = userNameEdit.getText().toString();





    }

    //once successfully registered, return to startGameActivity to log in, and putExtra users information
    public void returnToLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        intent.putExtra("savedUsername", sharedPreferences.getString(USERNAME, ""));
        intent.putExtra("savedPassword", sharedPreferences.getString(PASSWORD, ""));
        intent.putExtra("savedFirstName", sharedPreferences.getString(FIRSTNAME, ""));
        intent.putExtra("savedLastName", sharedPreferences.getString(LASTNAME, ""));
        intent.putExtra("savedDOB", sharedPreferences.getString(DOB, ""));
        intent.putExtra("savedEmail", sharedPreferences.getString(EMAIL, ""));
        this.startActivity(intent);
    }


    //check if field is empty or not
    public boolean fieldEmpty(EditText field) {
        String fieldText = field.getText().toString();
        boolean result = false;
        if (fieldText.matches("")) // if field is empty
            result = true;
        return result;
    }

    //highlight any empty fields red
    public void highlightEmptyFields(EditText field) {
        String fieldText = field.getText().toString();

        if (fieldText.matches("")) { // if field is empty
            field.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else{
            field.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }


    public void saveToSharedPreferences(SharedPreferences.Editor editor1, String firstname1, String lastname1, String dob1, String username1, String email1, String password1){
        editor1.putString(FIRSTNAME, firstname1);
        editor1.putString(LASTNAME, lastname1);
        editor1.putString(DOB, dob1);
        editor1.putString(USERNAME, username1);
        editor1.putString(EMAIL, email1);
        editor1.putString(PASSWORD, password1);
        editor1.apply();
        //returnToLogin(view);
    }

}