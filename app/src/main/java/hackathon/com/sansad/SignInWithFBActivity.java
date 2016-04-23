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

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import hackathon.com.sansad.models.api.LoginModel;
import hackathon.com.sansad.models.api.UserLoggedIn;
import hackathon.com.sansad.apiClient;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;

public class SignInWithFBActivity extends AppCompatActivity {
    public static LoginResult mLoginResult;
    ProgressDialog mProgressDialog;
    ProfileTracker mProfileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in_with_fb2);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //Log.d("facebook", mProfile.getFirstName());
        //Log.d("facebook", mProfile.getLastName());
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, GraphResponse response) {
                        Log.d("facebook", object.toString());
                        mProfileTracker = new ProfileTracker() {
                            @Override
                            protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                                mProfileTracker.stopTracking();
                                Profile.setCurrentProfile(profile2);
                            }
                        };
                        mProfileTracker.startTracking();
                        stuff(Profile.getCurrentProfile(), object);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void stuff(Profile mProfile, JSONObject object) {
        final String name = mProfile.getName();

        final String profile_pic = String.valueOf(mProfile.getProfilePictureUri(200, 200));
        final String password = StringUtils.uncapitalize(mProfile.getFirstName()) + "." + StringUtils.uncapitalize(mProfile.getLastName());
        String gender = "";
        String email = "";
        try {
            gender = object.getString("gender");
            email = object.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String userName = email + mProfile.getId().substring(mProfile.getId().length() - 3);
        Log.d("facebook", name);
        Log.d("facebook", userName);
        Log.d("facebook", email);
        Log.d("facebook", gender);
        Log.d("facebook", password);
        final String finalEmail = email;
        final String finalGender = gender;
        if (email.isEmpty())
            userName = mProfile.getFirstName() + mProfile.getLastName() + mProfile.getId().substring(mProfile.getId().length() - 3);
        final String finalUserName = userName;
        apiClient.getAPI().userLogin(userName, password, new Callback<LoginModel>() {
            @Override
            public void success(LoginModel loginModel, retrofit.client.Response response) {
                if (loginModel.getCode() == 200 && loginModel.getResponse().getError() == 0) {
                    UserLoggedIn user = loginModel.getResponse().getData().getUser();
                    SQLiteDBHelper helper = new SQLiteDBHelper(SignInWithFBActivity.this);

                    SharedPreferences.Editor editor = getSharedPreferences("USER_ID", MODE_PRIVATE).edit();
                    editor.putString("userId", user.getId());
                    editor.apply();

                    helper.addUser(user.getName(), user.getUsername(), user.getEmail(),
                            user.getGender(), user.getPhone(), user.getLocation(), user.getProfileImg(),
                            user.getLevel(), user.getTotalCredits(), user.getRemainingCredits(), 100,
                            loginModel.getResponse().getData().getSession().getSessionid(),
                            loginModel.getResponse().getData().getSession().getToken());
                    mProgressDialog.dismiss();
                    startActivity(new Intent(SignInWithFBActivity.this,new_list_display.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else if (loginModel.getCode() == 200 && loginModel.getResponse().getError() == 1) {
                    Intent intent = new Intent(SignInWithFBActivity.this, UpdateDetailsActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("userName", finalUserName);
                    intent.putExtra("email", finalEmail);
                    intent.putExtra("gender", finalGender);
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