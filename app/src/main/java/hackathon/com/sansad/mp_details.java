package hackathon.com.sansad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class mp_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_details);

        final Intent intent = getIntent();


        final String name =  intent.getStringExtra("name");
        final String debate =    intent.getStringExtra("debate"); //Optional parameters
        final String bills = intent.getStringExtra("bills"); //Optional parameters
        final String questions = intent.getStringExtra("questions"); //Optional parameters
        final String age =  intent.getStringExtra("age"); //Optional parameters
        final String education =  intent.getStringExtra("education"); //Optional parameters
        final String constit =  intent.getStringExtra("constit"); //Optional parameters
        final String state =  intent.getStringExtra("state"); //Optional parameters
        final String house =  intent.getStringExtra("house");//Optional parameters

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mp_details, menu);
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
