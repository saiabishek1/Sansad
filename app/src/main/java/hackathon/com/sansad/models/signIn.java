package hackathon.com.sansad.models;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.util.Arrays;

import chipset.potato.Potato;
import hackathon.com.sansad.R;
import hackathon.com.sansad.SQLiteDBHelper;
import hackathon.com.sansad.SignInWithFBActivity;
import hackathon.com.sansad.SignInwithGplus;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class signIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private boolean mIntentInProgress;
    SQLiteDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        getPermissionToGetAccounts();
        helper = new SQLiteDBHelper(this);
        LoginButton fbLoginButton = (LoginButton) findViewById(R.id.sign_in_fb);
        fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Potato.potate().Utils().isInternetConnected(getApplicationContext()))
                    Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_SHORT);
            }
        });
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))
                .build();
        if (!Potato.potate().Preferences().getSharedPreferenceBoolean(this, "loggedout")) {
            mGoogleApiClient.connect();
        } else {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }
        }

        AppCompatButton gsignInButton = (AppCompatButton) findViewById(R.id.gsign_in_button);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (status != ConnectionResult.SUCCESS) {
            //Success! Do what you want
            gsignInButton.setVisibility(View.GONE);
        }
        gsignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.GET_ACCOUNTS)
                        != PackageManager.PERMISSION_GRANTED) {

                    // The permission is NOT already granted.
                    // Check if the user has been asked about this permission already and denied
                    // it. If so, we want to give more explanation about why the permission is needed.
                    if (ActivityCompat.shouldShowRequestPermissionRationale(signIn.this,
                            Manifest.permission.GET_ACCOUNTS)) {
                        // Show our own UI to explain to the user why we need to read the contacts
                        // before actually requesting the permission and showing the default UI
                        Toast.makeText(signIn.this, "Please grant permissions to access google accounts", Toast.LENGTH_SHORT).show();
                    }

                    // Fire off an async request to actually get the permission
                    // This will show the standard permission request dialog UI
                    ActivityCompat.requestPermissions(signIn.this, new String[]{Manifest.permission.GET_ACCOUNTS},
                            GET_ACCOUNT_PERMISSIONS_REQUEST);
                } else {
                    if (Potato.potate().Utils().isInternetConnected(signIn.this)) {
                        signIn();
                    } else {
                        Toast.makeText(signIn.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        final PackageManager pm = getPackageManager();
        callbackManager = CallbackManager.Factory.create();
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            if (helper.getRowCount() > 0)
                startActivity(new Intent (this, SignInWithFBActivity.class));
            else
                LoginManager.getInstance().logOut();
        }
        fbLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        if (Potato.potate().Utils().isInternetConnected(getApplicationContext())) {
            fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    SignInWithFBActivity.mLoginResult = loginResult;
                    startActivity(new Intent(signIn.this, SignInWithFBActivity.class));
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {
                    error.printStackTrace();
                }
            });
        } else {
            Toast.makeText( this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }


    private void signIn() {
        mGoogleApiClient.connect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
            return;
        } else if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onResume() {
        super.onResume();
        AccessToken.getCurrentAccessToken();
        Log.d("resume current token", "" + AccessToken.getCurrentAccessToken());
        Profile.fetchProfileForCurrentAccessToken();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress && result.hasResolution()) {
            try {
                mIntentInProgress = true;
                startIntentSenderForResult(result.getResolution().getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
        String ID = person.getId();
        String name = person.getDisplayName();
        String profile_pic = person.getImage().getUrl();
        Log.d("pro", profile_pic);
        profile_pic = profile_pic.substring(0,
                profile_pic.length() - 2)
                + "200";
        int genderInt = person.getGender();
        Intent intent = new Intent( this, SignInwithGplus.class);
        intent.putExtra("email", email);
        intent.putExtra("ID", ID);
        intent.putExtra("name", name);
        intent.putExtra("pic", profile_pic);
        intent.putExtra("gender", genderInt);
        startActivity(intent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    /* protected void onStart() {
         super.onStart();
         mGoogleApiClient.connect();
     }

     protected void onStop() {
         super.onStop();

         if (mGoogleApiClient.isConnected()) {
             mGoogleApiClient.disconnect();
         }
     }*/
    private static final int GET_ACCOUNT_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermissionToGetAccounts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS},
                    GET_ACCOUNT_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == GET_ACCOUNT_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "get accounts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "get accounts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}