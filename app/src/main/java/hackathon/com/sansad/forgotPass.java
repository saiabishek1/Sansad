package hackathon.com.sansad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


    public class forgotPass extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgot_pass);



            final TextView emailreset = (TextView) findViewById(R.id.emailreset);


            Button submitbut = (Button) findViewById(R.id.submit);


            submitbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final ProgressDialog progressDialog = ProgressDialog.show(forgotPass.this, "", "Sending you an email...");

                    new Thread() {

                        public void run() {

                            try {

                                String emailtxt = emailreset.getText().toString();

                                        if (true) {
                                            progressDialog.dismiss();
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "An email was sent with instructions on how to reset your password.",
                                                    Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(
                                                    forgotPass.this,
                                                    LoginSignup.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            // An email was successfully sent with reset instructions.
                                        } else {
                                            // Something went wrong. Look at the ParseException to see what's up.
                                            progressDialog.dismiss();
                                            Toast.makeText(

                                                    getApplicationContext(),
                                                    "Sorry an error occured",
                                                    Toast.LENGTH_SHORT).show();
                                        }





                            } catch (Exception e) {
                                progressDialog.dismiss();

                                Log.e("tag", e.getMessage());

                            }



                        }



                    }.start();

                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }