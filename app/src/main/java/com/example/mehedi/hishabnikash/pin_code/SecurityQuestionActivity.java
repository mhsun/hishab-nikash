package com.example.mehedi.hishabnikash.pin_code;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehedi.hishabnikash.AboutActivity;
import com.example.mehedi.hishabnikash.CreditsActivity;
import com.example.mehedi.hishabnikash.MainActivity;
import com.example.mehedi.hishabnikash.R;

public class SecurityQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstAnswer, secondAnswer, thirdAnswer;
    private Button btnSetQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null)
            actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initializing the components
        init();

        // setting up the listeners
        setListener();
    }


    /*
    * this method is responsible for initializing all the components that have interaction with the
    * activity
    * @param void
    * @return void
    * */
    public void init () {
        firstAnswer = findViewById(R.id.et_securityQuestionOne);
        secondAnswer = findViewById(R.id.et_securityQuestionTwo);
        thirdAnswer = findViewById(R.id.et_securityQuestionThree);
        btnSetQuestion = findViewById(R.id.btn_setSecurityQuestion);
    }


    /*
     * this method is responsible for setting up the listeners
     * @param void
     * @return void
     * */
    public void setListener () {
        btnSetQuestion.setOnClickListener(this);
    }


    /*
     * this method is responsible for clearing all the EditText fields of the form
     * @param void
     * @return void
     * */
    public void clearFields () {
        firstAnswer.setText("");
        secondAnswer.setText("");
        thirdAnswer.setText("");
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_setSecurityQuestion) {
            String questionOne = firstAnswer.getText().toString().trim();
            String questionTwo = secondAnswer.getText().toString().trim();
            String questionThree = thirdAnswer.getText().toString().trim();

            if (questionOne.isEmpty() || questionTwo.isEmpty() || questionThree.isEmpty()) {
                Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("HNPIN", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstAnswer", questionOne);
                editor.putString("secondAnswer", questionTwo);
                editor.putString("thirdAnswer", questionThree);
                editor.commit();

                clearFields();

                Toast.makeText(this, "You have successfully set your security questions", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
            finish();
        } else if (item.getItemId() == R.id.credits) {
            startActivity(new Intent(this, CreditsActivity.class));
            finish();
        } else if (item.getItemId() == R.id.set_pin) {
            startActivity(new Intent(this, PinCodeActivity.class));
            finish();
        } else if (item.getItemId() == R.id.change_pin) {
            startActivity(new Intent(this, ChangePinActivity.class));
            finish();
        } else if (item.getItemId() == R.id.set_security_question) {
            startActivity(new Intent(this, SecurityQuestionActivity.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
