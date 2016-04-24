package hackathon.com.sansad;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.github.clans.fab.FloatingActionButton;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import hackathon.com.sansad.models.api.getReviews.GetReviewsModel;
import hackathon.com.sansad.models.api.getReviews.Review;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by utk994 on 31-Oct-15.
 */
public class mp_profile extends ActionBarActivity {

    RadarChart mChart;

    String debat, bill, question, name = "";

    String bio;

    TextView viewbio;

    String finalurl;

    ImageView profilePic;


    String pageid;
    int attendancce;

    String id;
    String img;

    RelativeLayout reviews, profile, news;

    BottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_profile);


        viewbio = (TextView) findViewById(R.id.viewuserbio);

        viewbio.setVisibility(View.INVISIBLE);




        reviews = (RelativeLayout) findViewById(R.id.reviews_rl);
        profile = (RelativeLayout) findViewById(R.id.profile_rl);
        news = (RelativeLayout) findViewById(R.id.news_rl);

        profile.setVisibility(View.VISIBLE);


        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        LinearLayout lay = (LinearLayout) findViewById(R.id.lin3);

        YoYo.with(Techniques.FadeIn).duration(500).playOn(lay);


        final Intent intent = getIntent();


        name = intent.getStringExtra("name");



        debat = intent.getStringExtra("debate"); //Optional parameters
        bill = intent.getStringExtra("bills"); //Optional parameters
        question = intent.getStringExtra("questions"); //Optional parameters
        final String age = intent.getStringExtra("age"); //Optional parameters
        final String education = intent.getStringExtra("education"); //Optional parameters
        final String constit = intent.getStringExtra("constit"); //Optional parameters
        final String state = intent.getStringExtra("state"); //Optional parameters
        final String house = intent.getStringExtra("house");//Optional parameters

        final String pic = intent.getStringExtra("img");
        id = intent.getStringExtra("id");

        this.setTitle(name);


        new GetContacts().execute(name);


        profile.setVisibility(View.VISIBLE);
        reviews.setVisibility(View.GONE);
        news.setVisibility(View.GONE);

        attendancce = (int) Math.random() * 100;

        profilePic = (ImageView) findViewById(R.id.viewpropic);

        Picasso.with(mp_profile.this).load(pic).into(profilePic);


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mp_profile.this, mp_details.class);
                myIntent.putExtra("name", name); //Optional parameters
                myIntent.putExtra("debate", debat); //Optional parameters
                myIntent.putExtra("bills", bill); //Optional parameters
                myIntent.putExtra("questions", question); //Optional parameters
                myIntent.putExtra("age", age); //Optional parameters
                myIntent.putExtra("education", education); //Optional parameters
                myIntent.putExtra("constit", constit); //Optional parameters
                myIntent.putExtra("state", state); //Optional parameters
                myIntent.putExtra("house", house);//Optional parameters

                mp_profile.this.startActivity(intent);
            }
        });


        TextView viewuser = (TextView) findViewById(R.id.viewuname);
        viewbio = (TextView) findViewById(R.id.viewuserbio);

        viewbio.setVisibility(View.INVISIBLE);


        viewuser.setText(name);


        mChart = (RadarChart) findViewById(R.id.chart);


        mChart.setDescription("");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.75f);
        mChart.setWebAlpha(100);


        setData();

        XAxis xAxis = mChart.getXAxis();


        YAxis yAxis = mChart.getYAxis();

        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setStartAtZero(true);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);




        bottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation2);
        bottomNavigation.setDefaultSelectedIndex(1);
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1) {

                if (i == R.id.bbn_item5) {
                    news.setVisibility(View.GONE);
                    reviews.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.GONE);
                    final ListView listView = (ListView) findViewById(R.id.reviews);




                    SQLiteDBHelper helper = new SQLiteDBHelper(mp_profile.this);
                    String session = helper.getUserDetails().getSession().getSessionid();
                    String token = helper.getUserDetails().getSession().getToken();

                    apiClient.getAPI().getReviews(session, token, id, new Callback<GetReviewsModel>() {
                        @Override
                        public void success(GetReviewsModel getReviewsModel, Response response) {

                            ArrayList<Review> reviews = new ArrayList<Review>();
                            reviews.addAll(getReviewsModel.getResponse().getData());
                            reviewsAdapter adapter = new reviewsAdapter(mp_profile.this, reviews);

                            listView.setAdapter(adapter);

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                    final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.review_fab);


                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ReviewDialog.id = id;
                                    startActivity(new Intent(mp_profile.this, ReviewDialog.class));
                                }
                            });
                            final Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    fab.setVisibility(View.GONE);
                                }
                            };
                            final Handler handler = new Handler(new Handler.Callback() {
                                @Override
                                public boolean handleMessage(Message msg) {
                                    return true;
                                }
                            });
                            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(AbsListView view, int scrollState) {
                                    if (scrollState == 1)
                                        fab.setVisibility(View.VISIBLE);
                                    else {
                                        handler.removeCallbacks(runnable);
                                        handler.postDelayed(runnable, 1500);
                                    }
                                }

                                @Override
                                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                                }
                            });


                        }
                    });
                }

                if (i == R.id.bbn_item4) {


                    new GetContacts().execute(name);

                    profile.setVisibility(View.VISIBLE);
                    reviews.setVisibility(View.GONE);
                    news.setVisibility(View.GONE);

                    attendancce = (int) Math.random() * 100;

                    profilePic = (ImageView) findViewById(R.id.viewpropic);

                    Picasso.with(mp_profile.this).load(pic).into(profilePic);


                    profilePic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(mp_profile.this, mp_details.class);
                            myIntent.putExtra("name", name); //Optional parameters
                            myIntent.putExtra("debate", debat); //Optional parameters
                            myIntent.putExtra("bills", bill); //Optional parameters
                            myIntent.putExtra("questions", question); //Optional parameters
                            myIntent.putExtra("age", age); //Optional parameters
                            myIntent.putExtra("education", education); //Optional parameters
                            myIntent.putExtra("constit", constit); //Optional parameters
                            myIntent.putExtra("state", state); //Optional parameters
                            myIntent.putExtra("house", house);//Optional parameters

                            mp_profile.this.startActivity(intent);
                        }
                    });


                    TextView viewuser = (TextView) findViewById(R.id.viewuname);
                    viewbio = (TextView) findViewById(R.id.viewuserbio);

                    viewbio.setVisibility(View.INVISIBLE);


                    viewuser.setText(name);


                    mChart = (RadarChart) findViewById(R.id.chart);


                    mChart.setDescription("");

                    mChart.setWebLineWidth(1.5f);
                    mChart.setWebLineWidthInner(0.75f);
                    mChart.setWebAlpha(100);


                    setData();

                    XAxis xAxis = mChart.getXAxis();


                    YAxis yAxis = mChart.getYAxis();

                    yAxis.setLabelCount(5, false);
                    yAxis.setTextSize(9f);
                    yAxis.setStartAtZero(true);

                    Legend l = mChart.getLegend();
                    l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

                    l.setXEntrySpace(7f);
                    l.setYEntrySpace(5f);


                }

                if (i == R.id.bbn_item3) {

                    profile.setVisibility(View.VISIBLE);
                    reviews.setVisibility(View.GONE);
                    news.setVisibility(View.GONE);


                }
            }


            @Override
            public void onMenuItemReselect(@IdRes int i, int i1) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I"
    };

    public void setData() {

        float mult = 150;
        int cnt = 4;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        // drawn above each other.


        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }


        yVals2.add(new Entry(78, 0));
        yVals2.add(new Entry(24, 1));
        yVals2.add(new Entry((float) 0.62, 2));
        yVals2.add(new Entry(118, 3));


        String[] mParties = new String[]{
                "Attendance", "Debates", "Bills", "Questions",
        };


        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < 4; i++)
            xVals.add(mParties[i % mParties.length]);
        RadarDataSet set1 = new RadarDataSet(yVals1, name);
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "Averages");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);

        data.setValueTextSize(8f);
        data.setDrawValues(false);

        mChart.setData(data);

        mChart.invalidate();

        mChart.setClickable(true);

        mChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                // custom dialog
                final Dialog dialog = new Dialog(mp_profile.this);
                dialog.setContentView(R.layout.dialog_box);
                dialog.setTitle("Statistic for " + name);

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                TextView text1 = (TextView) dialog.findViewById(R.id.textView2);
                TextView text2 = (TextView) dialog.findViewById(R.id.textView3);
                TextView text3 = (TextView) dialog.findViewById(R.id.textView4);
                text.setText("Attendance = " + (String.valueOf((attendancce))));
                text1.setText("Debates = " + (String.valueOf((debat))));
                text1.setText("Questions = " + (String.valueOf((question))));
                text1.setText("Questions = " + (String.valueOf((bill))));


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });

    }

    private class GetContacts extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... arg0) {
            // Creating service handler class instance

            ServiceHandler sh = new ServiceHandler();
            String name = arg0[0];
            name = name.replaceAll(" ", "%20");

            String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + name;

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);


            String name1 = arg0[0].replace(" ", "_");

            String url1 = "https://en.wikipedia.org/w/api.php?action=parse&format=json&page=" + name1;

            // Making a request to url and getting response
            String jsonStr1 = sh.makeServiceCall(url1, ServiceHandler.GET);


            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject2 = new JSONObject(jsonStr1);
                    JSONObject parse = jsonObject2.getJSONObject("parse");
                    pageid = parse.getString("pageid");


                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject abs = jsonObj.getJSONObject("query");
                    JSONObject pages = abs.getJSONObject("pages");
                    JSONObject final1 = pages.getJSONObject(pageid);
                    bio = final1.getString("extract");

                    Log.d("BIOOOOO", bio);
                    // Getting JSON Array node

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            String query = null;
            try {
                query = URLEncoder.encode(name1, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {


            super.onPostExecute(result);
            viewbio.setVisibility(View.VISIBLE);
            viewbio.setText(bio);


            //  if (drawable == null)


        }
    }
}