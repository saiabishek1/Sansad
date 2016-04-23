package hackathon.com.sansad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mehdi.sakout.dynamicbox.DynamicBox;


public class central extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    Drawable d;
    drawerAdapter mAdapter;
    private CharSequence mTitle;
    private String[] mDrawerItmes= {"Your Profile","Your Preferences","Your History"};

    String TITLES[] = {"Your Profile","Preferences","History","Logout"};
    int ICONS[] = {R.drawable.userprofileico,R.drawable.preferencesicon,R.drawable.historyicon,R.drawable.logouticon};


    String NAME = "Username";


    View header;
    ImageView headerpic;

    int pos;

    yourfav.RetreiveItems mTask;




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


    private static final String TAG = central.class.getSimpleName();

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = central.this.getAssets().open("mpskilist.json");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle= getIntent().getExtras();
        String nam= bundle.getString("uname");
        NAME=nam;
        super.onCreate(savedInstanceState);
        Drawable d = this.getResources().getDrawable(R.drawable.profile1);
        Arrays.fill(profiles, d);

        DisplayImageOptions mOptionsSimple = new DisplayImageOptions.Builder().resetViewBeforeLoading(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .imageDecoder(new BaseImageDecoder(false))
                .defaultDisplayImageOptions(mOptionsSimple)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);


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
                    .getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

            Gson gson = new Gson();
            String jsonMps = gson.toJson(formList);
            prefsEditor.putString("MyObject", jsonMps);
            prefsEditor.commit();


        } catch (JSONException e) {
            e.printStackTrace();
        }



        setContentView(R.layout.activity_central);



        if (true){  d = getResources().getDrawable(R.drawable.profile);
        }

        else {

            /* byte[] bitmapdata = new byte[0];
            try {
                bitmapdata = user1.getData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

            d = new BitmapDrawable(getResources(), bitmap1); */

        }





        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setDivider(null);
        mDrawerList.setDividerHeight(0);
        LayoutInflater inflater = getLayoutInflater();
        header = inflater.inflate(R.layout.header, mDrawerList, false);
        headerpic = (ImageView) header.findViewById(R.id.drawer_pic);
        headerpic.setImageDrawable(d);


        TextView name =(TextView) header.findViewById(R.id.drawer_name);
        name.setText(NAME);
        mDrawerList.addHeaderView(header, null, false);




        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mAdapter=new drawerAdapter(getApplicationContext(),TITLES,ICONS);

        // Add items to the ListView
        mDrawerList.setAdapter(mAdapter);
        // Set the OnItemClickListener so something happens when a
        // user clicks on an item.
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());




        // Enable ActionBar app icon to behave as action to toggle the NavigationDrawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }

            public void onDrawerOpened(View drawerView) {

                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Set the default content area to item 0
        // when the app opens for the first time
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R. id.root_frame, TabbedFragment.newInstance()).commit();
        }



        //

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        if (drawerOpen) {


        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void open1() {


        if (true){  d = getResources().getDrawable(R.drawable.profile);
        }

        else {

           /* byte[] bitmapdata = new byte[0];
            try {
                bitmapdata = user1.getData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

            d = new BitmapDrawable(getResources(), bitmap1); */

        }


        // OR
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.header, mDrawerList, false);
        ImageView headerpic = (ImageView) header.findViewById(R.id.drawer_pic);
        headerpic.setImageDrawable(d);




        mDrawerLayout.addView(header);
    }






    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private class DrawerItemClickListener implements OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            navigateTo(i);

        }
    }

    private void navigateTo(int position) {

        switch (position) {

            case 1:
                Intent intent = new Intent(this, blank.class);
                Bundle basket=new Bundle();
                basket.putString("uname", NAME);
                intent.putExtras(basket);
                startActivity(intent);

                break;


            case 2:
                Intent intent1 = new Intent(this, blank.class);
                startActivity(intent1);
                break;


            case 3:

                Intent intent2 = new Intent(this,blank.class);
                startActivity(intent2);
                break;


            case 4:

                final ProgressDialog progressDialog = ProgressDialog.show(central.this, "", "Logging Out...");
                new Thread() {

                    public void run() {


                        try {

                            Intent intent3 = new Intent(central.this, LoginSignup.class);
                            startActivity(intent3);
                            finish();
                        } catch (Exception e) {

                            Log.e("tag", e.getMessage());

                        }


                        progressDialog.dismiss();

                    }

                }.start();


                break;






        }

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    Toast mtoast;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            mtoast.cancel();


            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        mtoast= Toast.makeText(this, "Please press back again to exit.", Toast.LENGTH_SHORT);

        mtoast.show();

        mHandler.postDelayed(mRunnable, 2000);
    }


}

