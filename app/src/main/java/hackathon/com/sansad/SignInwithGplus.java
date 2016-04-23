package hackathon.com.sansad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.plus.model.people.Person;
import hackathon.com.sansad.models.api.LoginModel;
import hackathon.com.sansad.models.api.UserLoggedIn;
import hackathon.com.sansad.apiClient;

import retrofit.Callback;
import retrofit.RetrofitError;

public class SignInwithGplus extends AppCompatActivity {
    private String email;
    private String name;
    private int genderInt;
    private String ID;
    private String profile_pic;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_inwith_gplus);

        Bundle b = getIntent().getExtras();
        name = b.getString("name");
        email = b.getString("email");
        ID = b.getString("ID");
        profile_pic = b.getString("pic");
        genderInt = b.getInt("gender");
        Log.d("proG", profile_pic);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        final String userName = email + ID.substring(ID.length() - 3);
        String location = "";
        String phone = "";
        final String password = email;
        final String gender;
        int g = genderInt;
        if (g == Person.Gender.MALE) {
            gender = "male";
        } else if (g == Person.Gender.FEMALE) {
            gender = "female";
        } else {
            gender = "other";
        }
        apiClient.getAPI().userLogin(userName, password, new Callback<LoginModel>() {
            @Override
            public void success(LoginModel loginModel, retrofit.client.Response response) {
                if (loginModel.getCode() == 200 && loginModel.getResponse().getError() == 0) {
                    UserLoggedIn user = loginModel.getResponse().getData().getUser();


                    SQLiteDBHelper helper = new SQLiteDBHelper(SignInwithGplus.this);
                    SharedPreferences.Editor editor = getSharedPreferences("USER_ID", MODE_PRIVATE).edit();
                    editor.putString("userId", user.getId());
                    editor.apply();

                    helper.addUser(user.getName(), user.getUsername(), user.getEmail(),
                            user.getGender(), user.getPhone(), user.getLocation(), user.getProfileImg(),
                            user.getLevel(), user.getTotalCredits(), user.getRemainingCredits(), 100,
                            loginModel.getResponse().getData().getSession().getSessionid(),
                            loginModel.getResponse().getData().getSession().getToken());
                    mProgressDialog.dismiss();
                    startActivity(new Intent(SignInwithGplus.this, new_list_display.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else if (loginModel.getCode() == 200 && loginModel.getResponse().getError() == 1) {
                    Intent intent = new Intent(SignInwithGplus.this, UpdateDetailsActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("userName", userName);
                    intent.putExtra("email", email);
                    intent.putExtra("gender", gender);
                    intent.putExtra("password", password);
                    intent.putExtra("profile_pic", profile_pic);
                    startActivity(intent);
                } else {
                    Snackbar.make(getWindow().getDecorView().getRootView(),
                            loginModel.getResponse().getMessage(), Snackbar.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mProgressDialog.dismiss();
            }
        });

    }
}
