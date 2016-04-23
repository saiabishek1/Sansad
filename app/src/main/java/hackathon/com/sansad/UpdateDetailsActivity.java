package hackathon.com.sansad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import chipset.potato.Potato;
import hackathon.com.sansad.models.api.LoginModel;
import hackathon.com.sansad.models.api.SignupModel;
import hackathon.com.sansad.models.api.UserLoggedIn;
import hackathon.com.sansad.models.signIn;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class UpdateDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fbdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final AppCompatEditText phnoEditText = (AppCompatEditText) findViewById(R.id.phno_edit_text);
        final AppCompatEditText emailEditText = (AppCompatEditText) findViewById(R.id.email_edit_text);
        final AppCompatSpinner locationSpinner = (AppCompatSpinner) findViewById(R.id.location_spinner);
        final String name = getIntent().getStringExtra("name");
        final String userName = getIntent().getStringExtra("userName");
        final String email = getIntent().getStringExtra("email");
        final String gender = getIntent().getStringExtra("gender");
        final String password = getIntent().getStringExtra("password");
        final String profile_pic = getIntent().getStringExtra("profile_pic");
        ArrayList<String> locations = new ArrayList<>();
        if (locations.isEmpty()) {
            locations.add("Manipal");
        }
        if (email.isEmpty()) {
            emailEditText.setVisibility(View.VISIBLE);
        }

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Updating...");
        mProgressDialog.setCancelable(false);
        AppCompatButton submitButton = (AppCompatButton) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailnew = email;
                String userNameNew = userName;

                if (emailEditText.getVisibility() == View.VISIBLE) {
                    emailnew = emailEditText.getText().toString();
                    if (emailnew.isEmpty())
                        emailEditText.setError("Please enter your email address");
                }
                if (!emailnew.isEmpty()) {
                    if (Potato.potate().Utils().isInternetConnected(getApplicationContext())) {
                        mProgressDialog.show();
                        final String finalUserNameNew = userNameNew;
                        apiClient.getAPI().userSignup(name, userNameNew, emailnew, gender, password, profile_pic, new Callback<SignupModel>() {
                            @Override
                            public void success(final SignupModel signupModel, Response response) {
                                if (signupModel.getCode() == 200 && signupModel.getResponse().getError() == 0) {
                                    apiClient.getAPI().userLogin(finalUserNameNew, password, new Callback<LoginModel>() {
                                        @Override
                                        public void success(LoginModel loginModel, retrofit.client.Response response) {
                                            if (loginModel.getCode() == 200 && loginModel.getResponse().getError() == 0) {
                                                mProgressDialog.dismiss();
                                                UserLoggedIn user = loginModel.getResponse().getData().getUser();
                                                SQLiteDBHelper helper = new SQLiteDBHelper(UpdateDetailsActivity.this);
                                                helper.addUser(user.getName(), user.getUsername(), user.getEmail(),
                                                        user.getGender(), user.getPhone(), user.getLocation(), user.getProfileImg(),
                                                        user.getLevel(), user.getTotalCredits(), user.getRemainingCredits(), 100,
                                                        loginModel.getResponse().getData().getSession().getSessionid(),
                                                        loginModel.getResponse().getData().getSession().getToken());
                                                startActivity(new Intent(UpdateDetailsActivity.this, new_list_display.class)
                                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                            } else {
                                                Snackbar.make(getWindow().getDecorView().getRootView(),
                                                        loginModel.getResponse().getMessage(), Snackbar.LENGTH_LONG).show();
                                                mProgressDialog.dismiss();
                                            }
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Log.d("error", "login after sign up" + error.toString());
                                            mProgressDialog.dismiss();
                                            error.printStackTrace();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                mProgressDialog.dismiss();
                                Toast.makeText(UpdateDetailsActivity.this, "Looks like the email is already in use. Please try some other account.", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
                    } else {
                        Snackbar.make(getCurrentFocus(), "Please connect to the internet", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateDetailsActivity.this, signIn.class));
    }
}
