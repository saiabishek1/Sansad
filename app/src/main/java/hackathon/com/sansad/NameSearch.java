package hackathon.com.sansad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NameSearch extends Activity {

    Integer id;
    Integer age;

    String constituency;
    Integer debates;
    String education_details;
    ;
    String education_qualifications;
    String elected;
    String first_name;
    ;
    String house;
    ;
    String last_name;
    String in_office;
    String mp_id;
    String party;
    Integer questions;
    Integer private_bills;
    String state;
    String gender;
    String score;


    public String loadJSONFromAsset() {
        String json = null;


        try {
            InputStream is = NameSearch.this.getAssets().open("mpskilist.json");
            //InputStream is = .getApplicationContext().getAssets().open("mpskilist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namesearch);
        final EditText nameSearch1 = (EditText) findViewById(R.id.nameSearch1);
        Button Search = (Button) findViewById(R.id.btnSearch);
        final TextView name1, name2, name3,name4,name5;
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    name1.setText(" ");
                    name2.setText(" ");
                    name3.setText(" ");
                    name4.setText(" ");
                    name5.setText(" ");
                    JSONArray obj = new JSONArray(loadJSONFromAsset());
                    System.out.println("asdasdasd");
                    ArrayList<mp> formList = new ArrayList<>();
                    int j = 0;
                    String s = nameSearch1.getText().toString();
                    String [] firstnames=new String[5];
                    String [] lastnames= new String[5];
                    if (s.isEmpty()) {
                        System.out.println("enter keyword");
                        Toast.makeText(getApplicationContext(), "enter keyword to search", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < obj.length(); i = i + 1) {
                            System.out.println("sds");
                            JSONObject jo_inside = obj.getJSONObject(i);


                            id = jo_inside.getInt("id");
                            age = jo_inside.getInt("age");

                            constituency = jo_inside.getString("constituency");
                            debates = jo_inside.getInt("debates");
                            education_details = jo_inside.getString("education_details");
                            ;

                            ;
                            elected = jo_inside.getString("elected");
                            ;
                            first_name = jo_inside.getString("first_name");
                            ;
                            house = jo_inside.getString("house");
                            ;
                            last_name = jo_inside.getString("last_name");
                            ;
                            in_office = jo_inside.getString("in_office");
                            ;
                            mp_id = jo_inside.getString("mp_id");
                            ;
                            party = jo_inside.getString("party");
                            ;
                            questions = jo_inside.getInt("questions");
                            ;
                            private_bills = jo_inside.getInt("private_bills");
                            ;
                            state = jo_inside.getString("state");
                            ;
                            gender = jo_inside.getString("gender");
                            ;
                            score = jo_inside.getString("score");


                            Log.d("NAME_______------> ", first_name);
                            s = s.toLowerCase();
                            first_name = first_name.toLowerCase();
                            last_name = last_name.toLowerCase();

                            if (s.equals(first_name) || s.equals(last_name)) {
                                System.out.println("yes");

                                System.out.println("j value is");
                                System.out.println(j);
                                if (j == 0) {
                                    System.out.println("in j=0");
                                    name1.setText(first_name + " " + last_name + ": " + score);


                                }
                                if (j == 1) {
                                    name2.setText(first_name + " " + last_name + " :" + " " + score);
                                }
                                if (j == 2) {
                                    name3.setText(first_name + " " + last_name + ": " + " " + score);
                                }
                                if (j == 3) {
                                    name4.setText(first_name + " " + last_name + " :" + " " + score);
                                }
                                if (j == 4) {
                                    name5.setText(first_name + " " + last_name + " :" + " " + score);
                                }
                                j++;

                            }


                        }
                    }




      name1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent myIntent = new Intent(NameSearch.this, mp_profile.class);
                            myIntent.putExtra("name", first_name + " " + last_name); //Optional parameters
                            myIntent.putExtra("debate", debates); //Optional parameters
                            myIntent.putExtra("bills", private_bills); //Optional parameters
                            myIntent.putExtra("questions", questions); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education_details); //Optional parameters
                            myIntent.putExtra("constit", constituency); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house); //Optional parameters
                            startActivity(myIntent);

                        }
                    });
                    name2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(NameSearch.this, mp_profile.class);
                            myIntent.putExtra("name", first_name + " " + last_name); //Optional parameters
                            myIntent.putExtra("debate", debates); //Optional parameters
                            myIntent.putExtra("bills", private_bills); //Optional parameters
                            myIntent.putExtra("questions", questions); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education_details); //Optional parameters
                            myIntent.putExtra("constit", constituency); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house); //Optional parameters
                            startActivity(myIntent);
                        }
                    });
                    name3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(NameSearch.this, mp_profile.class);
                            myIntent.putExtra("name", first_name + " " + last_name); //Optional parameters
                            myIntent.putExtra("debate", debates); //Optional parameters
                            myIntent.putExtra("bills", private_bills); //Optional parameters
                            myIntent.putExtra("questions", questions); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education_details); //Optional parameters
                            myIntent.putExtra("constit", constituency); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house); //Optional parameters
                            startActivity(myIntent);
                        }
                    });
                    name4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(NameSearch.this, mp_profile.class);
                            myIntent.putExtra("name", first_name + " " + last_name); //Optional parameters
                            myIntent.putExtra("debate", debates); //Optional parameters
                            myIntent.putExtra("bills", private_bills); //Optional parameters
                            myIntent.putExtra("questions", questions); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education_details); //Optional parameters
                            myIntent.putExtra("constit", constituency); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house); //Optional parameters
                            startActivity(myIntent);
                        }
                    });
                    name5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(NameSearch.this, mp_profile.class);
                            myIntent.putExtra("name", first_name + " " + last_name); //Optional parameters
                            myIntent.putExtra("debate", debates); //Optional parameters
                            myIntent.putExtra("bills", private_bills); //Optional parameters
                            myIntent.putExtra("questions", questions); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education_details); //Optional parameters
                            myIntent.putExtra("constit", constituency); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house); //Optional parameters
                            startActivity(myIntent);
                        }
                    });


                    //Add your values in your `ArrayList` as below:


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
