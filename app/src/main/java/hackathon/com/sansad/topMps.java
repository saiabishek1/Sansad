package hackathon.com.sansad;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.ButtonFloat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.dynamicbox.DynamicBox;

/**
 * Created by utk994 on 31-Oct-15.
 */
public class topMps extends Fragment implements AdapterView.OnItemClickListener {
        SwipeRefreshLayout mSwipeRefreshLayout;




        int pos;

        RetreiveItems mTask;




        String[] name = new String[60];

        Integer[] constit = new Integer[60];
        String[] rank = new String[60];

        Date[] points = new Date[60];

    public List<mp> mpsList;


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



    SharedPreferences appSharedPrefs = PreferenceManager
            .getDefaultSharedPreferences(this.getActivity().getApplicationContext());
    SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
    Gson gson = new Gson();
    String jsonMps = appSharedPrefs.getString("MyObject", "");
    Type type = new TypeToken<List<mp>>(){}.getType();
    mpsList = gson.fromJson(jsonMps, type);

    Collections.sort(mpsList, new CustomComparator());
    Collections.reverse(mpsList);





}

    public class CustomComparator implements Comparator<mp> {
        @Override
        public int compare(mp o1, mp o2) {
            return Integer.valueOf(o1.getScore()).compareTo(Integer.valueOf(o2.getScore()));
        }
    }



    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, null, false);


        imageLoader = ImageLoader.getInstance();

	Button search= (Button)rootView.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NameSearch.class);
                startActivity(i);

            }
        });

        list = (DynamicListView) rootView.findViewById(R.id.list);


        list.setDivider(null);
        list.setDividerHeight(0);


        list.setScrollingCacheEnabled(true);


        return rootView;


        }


@Override
public void onActivityCreated(Bundle savedInstanceState) {

        mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();


        mSwipeRefreshLayout = (SwipeRefreshLayout) mActivity.findViewById(R.id.swipe_container);
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




        tv = (TextView) mActivity.findViewById(R.id.empty);

        if (savedInstanceState == null) {

        mTask = (RetreiveItems) new RetreiveItems().execute();
        box.showCustomView("loading");


        }


        list.setOnItemClickListener(this);

        list.setEmptyView(mActivity.findViewById(R.id.empty));


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
             myIntent.putExtra("name", rowItems.get(position).getName()); //Optional parameters
             myIntent.putExtra("debate", mpsList.get(position).getDebates()); //Optional parameters
             myIntent.putExtra("bills", mpsList.get(position).getPrivate_bills()); //Optional parameters
             myIntent.putExtra("questions", mpsList.get(position).getQuestions()); //Optional parameters
             myIntent.putExtra("age", mpsList.get(position).getAge()); //Optional parameters
             myIntent.putExtra("education", mpsList.get(position).getEducation_details()); //Optional parameters
             myIntent.putExtra("constit", mpsList.get(position).getConstituency()); //Optional parameters
             myIntent.putExtra("state",  mpsList.get(position).getState()); //Optional parameters
             myIntent.putExtra("house", mpsList.get(position).getHouse()); //Optional parameters



         topMps.this.startActivity(myIntent);





        }


public class RetreiveItems extends AsyncTask<String, Void, List<RowItem>> {


    @Override
    protected List<RowItem> doInBackground(String... urls) {


        if (!(isOnline())) {

            box.showCustomView("noNet");


        }




        rowItems = new ArrayList<RowItem>();


        final Drawable defdrawable = getResources().getDrawable(R.drawable.profile);

        ServiceHandler sh = new ServiceHandler();




        for ( int i=0;i<20;i++)
        { String constit = mpsList.get(i).getConstituency();

            String points  = mpsList.get(i).getScore();
            if (constit.equals("0"))
                constit = "";

            if (points.equals("0"))
                points = "";

            else points = points+ " points";


            name[i] = mpsList.get(i).getFirst_name()+" "+mpsList.get(i).getLast_name();

            name[i] = name[i].replaceAll(" ", "_");




                                RowItem row1 = new RowItem(mpsList.get(i).getFirst_name()+" "+mpsList.get(i).getLast_name(), constit, points, "#"+(i +1), defdrawable);
                                rowItems.add(row1);







            }


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

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }












    } public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}