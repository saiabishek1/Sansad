package hackathon.com.sansad;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

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

   Drawable defdrawable;

    String pageid;
    int attendancce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_profile);


        attendancce=(int)Math.random()*100;

          defdrawable = getResources().getDrawable(R.drawable.profile);




        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        LinearLayout lay = (LinearLayout) findViewById(R.id.lin3);

        YoYo.with(Techniques.FadeIn).duration(1000).playOn(lay);


        final Intent intent = getIntent();


        name = intent.getStringExtra("name");

        new GetContacts().execute(name);

        debat = intent.getStringExtra("debate"); //Optional parameters
        bill = intent.getStringExtra("bills"); //Optional parameters
        question = intent.getStringExtra("questions"); //Optional parameters
        final String age = intent.getStringExtra("age"); //Optional parameters
        final String education = intent.getStringExtra("education"); //Optional parameters
        final String constit = intent.getStringExtra("constit"); //Optional parameters
        final String state = intent.getStringExtra("state"); //Optional parameters
        final String house = intent.getStringExtra("house");//Optional parameters

        this.setTitle(name);
        profilePic = (ImageView) findViewById(R.id.viewpropic);
        Drawable d = getResources().getDrawable(R.drawable.profile);
        profilePic.setImageDrawable(d);


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
                dialog.setTitle("Statistic for "+name);

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                TextView text1 = (TextView) dialog.findViewById(R.id.textView2);
                TextView text2 = (TextView) dialog.findViewById(R.id.textView3);
                TextView text3 = (TextView) dialog.findViewById(R.id.textView4);
                text.setText("Attendance = "+(String.valueOf((attendancce))));
                text1.setText("Debates = "+(String.valueOf((debat))));
                text1.setText("Questions = "+(String.valueOf((question))));
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


            String url2 = "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&pithumbsize=150&titles=" + query;

            // Making a request to url and getting response
            String jsonStr2 = sh.makeServiceCall(url2, ServiceHandler.GET);


            Log.d("Response: ", "> " + jsonStr2);

            if (jsonStr != null) {
                try {


                    JSONObject jsonObj = new JSONObject(jsonStr2);
                    JSONObject abs = jsonObj.getJSONObject("query");
                    JSONObject pages = abs.getJSONObject("pages");
                    JSONObject thumbnail = pages.getJSONObject(pageid);
                    JSONObject thumb = thumbnail.getJSONObject("thumbnail");
                    finalurl = thumb.getString("source");

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }


            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {


            super.onPostExecute(result);
            viewbio.setVisibility(View.VISIBLE);
            viewbio.setText(bio);
            YoYo.with(Techniques.FadeIn).delay(300).playOn(viewbio);

            ImageLoader.getInstance().loadImage(finalurl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    Drawable drawable = new BitmapDrawable(getResources(), loadedImage);


                 //  if (drawable == null)
                    //    drawable = defdrawable;

                    profilePic.setImageDrawable(drawable);


                }

            });

        }
    }
}