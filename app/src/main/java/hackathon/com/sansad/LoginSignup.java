package hackathon.com.sansad;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;


public class LoginSignup extends Activity {
    // Declare Variables
    Button loginbutton;
    TextView signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;
    private MobileServiceClient mClient;
    private MobileServiceTable<userdetails> mToDoTable;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.activity_login_signup);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        try {
            mClient = new MobileServiceClient(
                    "https://sansad.azure-mobile.net/",
                    "tMUuarybjmudtoSINqaIwVXitErkHn53",
                    this

            );
            mToDoTable = mClient.getTable(userdetails.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        /*
        Item item = new Item();
        item.Text = "Awesome item";
        mClient.getTable(Item.class).insert(item, new TableOperationCallback<Item>() {
            public void onCompleted(Item entity, Exception exception, ServiceFilterResponse response) {
                if (exception == null) {
                    // Insert succeeded
                    Toast.makeText(getApplicationContext(),"sadasd",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"srrrr",Toast.LENGTH_SHORT).show();
                    // Insert failed
                }
            }
        });
        */

        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.login);
        signup = (  TextView) findViewById(R.id.signuptxt);

        // Login Button Click Listener
        loginbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View arg0) {

                final ProgressDialog progressDialog = ProgressDialog.show(LoginSignup.this, "", "Logging In...");

                new Thread() {

                    public void run() {



                            usernametxt = username.getText().toString();
                            passwordtxt = password.getText().toString();
                            if(usernametxt.equals("")||passwordtxt.equals("")){
                                Snackbar.make(arg0,"UserName and password not entered",Snackbar.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else{


                                boolean flag=false;

                                    try {
                                        System.out.println("Sdsd");
                                        final MobileServiceList<userdetails> result = mToDoTable.where().execute().get();
                                        for(userdetails u:result) {
                                            if (u.username.equals(usernametxt)&&(u.password.equals(passwordtxt)))
                                                flag=true;

                                        }

                                        System.out.println("Sdsdasdasda");





                                    } catch (InterruptedException e) {
                                        e.printStackTrace();

                                    } catch (ExecutionException e) {
                                        e.printStackTrace();

                                    }

                                  if(flag){

                                    progressDialog.dismiss();

                                    // If user exist and authenticated, send user to Welcome.class
                                      Intent intent = new Intent(
                                              LoginSignup.this,
                                              central.class);
                                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                      Bundle basket=new Bundle();
                                      basket.putString("uname", usernametxt);
                                      intent.putExtras(basket);


                                    startActivity(intent);

                                    finish();
                                } else {
                                      Snackbar.make(arg0,"UserName and password don't match",Snackbar.LENGTH_SHORT).show();
                                    System.out.println("swrong");
                                    progressDialog.dismiss();


                                }


                            }
                            // Send data to Parse.com for verification


                                                                                   }
                                    }.start();

                        }

                    });




                // Retrieve the text entered from the EditText





        // Sign up Button Click Listener


        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {

                    loginbutton.performClick();
                }
                return false;
            }
        });






        signup.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {

                Intent intent = new Intent(
                        LoginSignup.this, SignupActivity.class);
                startActivity(intent);

            }


        });




    }


}







