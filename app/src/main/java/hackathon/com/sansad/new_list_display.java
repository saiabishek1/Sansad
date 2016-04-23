package hackathon.com.sansad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.quinny898.library.persistentsearch.SearchBox;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hackathon.com.sansad.models.mp.MpData;
import hackathon.com.sansad.models.mp.MpResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class new_list_display extends Activity {

    ArrayList<MpData> mpsList;
    ImageLoader imageLoader;

    DynamicListView list;
    CustomAdapter adapter;
    SwingLeftInAnimationAdapter animationAdapter;
    private List<RowItem> rowItems;

    ArrayList<String> name ;

    SearchBox search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_display);

        search =(SearchBox) findViewById(R.id.searchbox1);


        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        String jsonMps = appSharedPrefs.getString("MyObject", "");
        final Type type = new TypeToken<List<mp>>() {
        }.getType();
        Gson gson = new Gson();
    SQLiteDBHelper helper = new SQLiteDBHelper(this);
        String session = helper.getUserDetails().getSession().getSessionid();
        String token = helper.getUserDetails().getSession().getToken()
                ;
        apiClient.getAPI().getMp(session, token, new Callback<MpResponse>() {
            @Override
            public void success(MpResponse mpResponse, Response response) {

                mpsList = new ArrayList<MpData>();
               name = new ArrayList<String>();

                mpsList.addAll(mpResponse.getResponse().getData());

                Collections.sort(mpsList, new CustomComparator());
                Collections.reverse(mpsList);


                for (int i = 0; i < mpsList.size(); i++) {
                    String constit = mpsList.get(i).getConstituency();

                    String points = mpsList.get(i).getScore();
                    if (constit.equals("0"))
                        constit = "";

                    if (points.equals("0"))
                        points = "";

                    else points = points + " points";


                    name.add( mpsList.get(i).getFirst_name() + " " + mpsList.get(i).getLast_name());

                    name.get(i).replaceAll(" ", "_");

                    final Drawable defdrawable = getResources().getDrawable(R.drawable.profile);




                    RowItem row1 = new RowItem(mpsList.get(i).getFirst_name() + " " + mpsList.get(i).getLast_name(), constit, points, "#" + (i + 1), mpsList.get(i).getImgurl());
                    rowItems.add(row1);
                }
                adapter = new CustomAdapter(new_list_display.this, rowItems);

                adapter.notifyDataSetChanged();


                animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                animationAdapter.notifyDataSetChanged();

                animationAdapter.setAbsListView(list);

                list.setAdapter(animationAdapter);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntent = new Intent(new_list_display.this,mp_profile.class);
                        myIntent.putExtra("name", rowItems.get(position).getName()); //Optional parameters
                        myIntent.putExtra("debate", mpsList.get(position).getDebates()); //Optional parameters
                        myIntent.putExtra("bills", mpsList.get(position).getPrivate_bills()); //Optional parameters
                        myIntent.putExtra("questions", mpsList.get(position).getQuestions()); //Optional parameters
                        myIntent.putExtra("age", mpsList.get(position).getAge()); //Optional parameters
                        myIntent.putExtra("education", mpsList.get(position).getEducation_details()); //Optional parameters
                        myIntent.putExtra("constit", mpsList.get(position).getConstituency()); //Optional parameters
                        myIntent.putExtra("state",  mpsList.get(position).getState()); //Optional parameters
                        myIntent.putExtra("house", mpsList.get(position).getHouse()); //Optional parameters



                        new_list_display.this.startActivity(myIntent);


                    }
                });





            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        imageLoader = ImageLoader.getInstance();


        list = (DynamicListView) findViewById(R.id.list);


        list.setDivider(null);
        list.setDividerHeight(0);


        list.setScrollingCacheEnabled(true);


        rowItems = new ArrayList<RowItem>();


    }





    public class CustomComparator implements Comparator<MpData> {
        @Override
        public int compare(MpData o1, MpData o2) {
            return Integer.valueOf(o1.getScore()).compareTo(Integer.valueOf(o2.getScore()));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (isAdded() && requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == new_list_display.this.RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
