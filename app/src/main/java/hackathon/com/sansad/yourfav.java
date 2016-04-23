package hackathon.com.sansad;

/**
 * Created by utk994 on 31-Oct-15.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import mehdi.sakout.dynamicbox.DynamicBox;

/**
 * Created by utk994 on 31-Oct-15.
 */
public class yourfav extends Fragment implements AdapterView.OnItemClickListener {
    SwipeRefreshLayout mSwipeRefreshLayout;




    int pos;

    RetreiveItems mTask;




    String[] name = new String[60];

    Integer[] constit = new Integer[60];
    String[] rank = new String[60];

    Date[] points = new Date[60];


    int size;
    TextView tv;

    Drawable[] profiles = new Drawable[60];



    DynamicListView list;


    CustomAdapter adapter;
    SwingLeftInAnimationAdapter animationAdapter;
    private List<RowItem> rowItems;

    DynamicBox box;

    ActionBar mActionBar;

    ImageLoader imageLoader;


    protected FragmentActivity mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }


    @Override

    public void onCreate(Bundle savedState) {





        super.onCreate(savedState);

        Drawable d = getActivity().getResources().getDrawable(R.drawable.profile1);
        Arrays.fill(profiles, d);


        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());

            ArrayList<mp> formList = new ArrayList<>();


            for (int i = 0; i <obj.length(); i=i+1) {
                JSONObject jo_inside = obj.getJSONObject(i);

                Integer id= jo_inside.getInt("id");
                Integer age= jo_inside.getInt("age");
               // String attendance= jo_inside.getString("attendance");
                String constituency= jo_inside.getString("constituency");
                Integer debates= jo_inside.getInt("debates");
                String education_details= jo_inside.getString("education_details");;
               // String education_qualifications = jo_inside.getString("education_qualifications");;
                String elected= jo_inside.getString("elected");;
                String first_name =  jo_inside.getString("first_name");;
                String house =  jo_inside.getString("house");;
                String last_name= jo_inside.getString("last_name");;
                String  in_office= jo_inside.getString("in_office");;
                String mp_id=  jo_inside.getString("mp_id");;
                String party= jo_inside.getString("party");;
                Integer questions= jo_inside.getInt("questions");;
                Integer private_bills= jo_inside.getInt("private_bills");;
                String state= jo_inside.getString("state");;
                String gender= jo_inside.getString("gender");;
                String score= jo_inside.getString("score");;

                Log.d("NAME_______------> ",first_name);

                mp Mp = new mp(id,age," ",constituency,debates,education_details,"",elected,first_name,
                gender,house,in_office,last_name,mp_id,party,private_bills,questions,state,score);

                formList.add(Mp);




                //Add your values in your `ArrayList` as below:

            }

            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(this.getActivity().getApplicationContext());
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

            Gson gson = new Gson();
            String jsonMps = gson.toJson(formList);
            prefsEditor.putString("MyObject", jsonMps);
            prefsEditor.commit();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment2, null, false);


        imageLoader = ImageLoader.getInstance();


        list = (DynamicListView) rootView.findViewById(R.id.list2);


        list.setDivider(null);
        list.setDividerHeight(0);


        list.setScrollingCacheEnabled(true);


        return rootView;


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();


        mSwipeRefreshLayout = (SwipeRefreshLayout) mActivity.findViewById(R.id.swipe_container2);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple);


        super.onActivityCreated(savedInstanceState);
        box = new DynamicBox(mActivity, list);

        box.setLoadingMessage("Loading ...");

        View customView = mActivity.getLayoutInflater().inflate(R.layout.nonet, null, false);
        box.addCustomView(customView, "noNet");


        View loadingView = mActivity.getLayoutInflater().inflate(R.layout.loadinglayout, null, false);
        box.addCustomView(loadingView, "loading");


        if (!(isOnline())) {

            box.showCustomView("noNet");

            Button retry = (Button) mActivity.findViewById(R.id.retry);

            if (retry != null) {
                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTask = (RetreiveItems) new RetreiveItems().execute();

                        box.showCustomView("loading");

                    }
                });
            }

        }




        tv = (TextView) mActivity.findViewById(R.id.empty2);

        if (savedInstanceState == null) {

            mTask = (RetreiveItems) new RetreiveItems().execute();
            box.showCustomView("loading");


        }


        list.setOnItemClickListener(this);

        list.setEmptyView(mActivity.findViewById(R.id.empty2));


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                mSwipeRefreshLayout.setRefreshing(true);

                mTask = (RetreiveItems) new RetreiveItems().execute();
                box.showCustomView("loading");



                mSwipeRefreshLayout.setRefreshing(false);
                tv.setVisibility(View.GONE);


            }
        });


        list.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {


                boolean enable = false;
                if (list != null && list.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = list.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = list.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout


                    enable = firstItemVisible && topOfFirstItemVisible;

                    if (enable) mActionBar.show();

                    else mActionBar.hide();

                    mSwipeRefreshLayout.setEnabled(enable);
                    return;
                }


                mSwipeRefreshLayout.setEnabled(true);


            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onDestroy() {
        if (mTask!=null)   mTask.cancel(true);


        super.onDestroy();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {


        Intent myIntent = new Intent(getActivity().getBaseContext(),mp_profile.class);
        myIntent.putExtra("name", "Narendra Modi"); //Optional parameters
        yourfav.this.startActivity(myIntent);



    }


    public class RetreiveItems extends AsyncTask<String, Void, List<RowItem>> {


        @Override
        protected List<RowItem> doInBackground(String... urls) {


            if (!(isOnline())) {

                box.showCustomView("noNet");


            }


            rowItems = new ArrayList<RowItem>();


            final Drawable defdrawable = getResources().getDrawable(R.drawable.profile);

            RowItem row1 = new RowItem("Naredra Modi","Varanasi","1000 Points","#1",defdrawable);

            rowItems.add(row1);
            rowItems.add(row1);
            rowItems.add(row1);
            rowItems.add(row1);

            return rowItems;


        }


        @Override
        protected void onPostExecute(List<RowItem> items) {

            {
                Button retry = (Button) mActivity.findViewById(R.id.retry);



                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mTask = (RetreiveItems) new RetreiveItems().execute();
                    }
                });
            }


            adapter = new CustomAdapter(mActivity, items);

            adapter.notifyDataSetChanged();


            animationAdapter = new SwingLeftInAnimationAdapter(adapter);
            animationAdapter.notifyDataSetChanged();


            if (list != null)

            {
                animationAdapter.setAbsListView(list);


                list.setAdapter(animationAdapter);



                adapter.notifyDataSetChanged();
                animationAdapter.notifyDataSetChanged();

            }





            box.hideAll();


       /* YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(getActivity().findViewById(R.id.buttonFloat));
        swap.setVisibility(View.VISIBLE);


        final SharedPreferences prefs = getActivity().getSharedPreferences(
                "com.asc.neetk.whatsplaying", Context.MODE_PRIVATE);

        Boolean ftime = prefs.getBoolean("ftime", true);

        if (ftime) {
            Intent intent = new Intent(getActivity(), guide.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent); */
        }


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("mpskilist.json");
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







    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}