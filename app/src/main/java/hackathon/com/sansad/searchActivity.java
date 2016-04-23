package hackathon.com.sansad;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.ArrayList;

import chipset.potato.Potato;
import hackathon.com.sansad.models.api.Data;
import hackathon.com.sansad.models.mp.MpData;
import hackathon.com.sansad.models.mp.MpResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by utk994 on 24-Apr-16.
 */

public class searchActivity extends AppCompatActivity  {
    CustomAdapter adapter;
    DynamicListView listView;
    ArrayList<RowItem> mps;
    ArrayList<MpData> mpList;

    SQLiteDBHelper helper;
    EditText search;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        helper = new SQLiteDBHelper(this);
        mps=new ArrayList<RowItem>();

        search = (EditText) findViewById(R.id.search_toolbar);
        search.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        listView = (DynamicListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(searchActivity.this,mp_profile.class);

                myIntent.putExtra("name", adapter.getItem(position).getFirst_name()+" "+adapter.getItem(position).getLast_name()); //Optional parameters
                myIntent.putExtra("debate", adapter.getItem(position).getDebates()); //Optional parameters
                myIntent.putExtra("bills", adapter.getItem(position).getPrivate_bills()); //Optional parameters
                myIntent.putExtra("questions", adapter.getItem(position).getQuestions()); //Optional parameters
                myIntent.putExtra("age", adapter.getItem(position).getAge()); //Optional parameters
                myIntent.putExtra("education", adapter.getItem(position).getEducation_details()); //Optional parameters
                myIntent.putExtra("constit", adapter.getItem(position).getConstituency()); //Optional parameters
                myIntent.putExtra("state",  adapter.getItem(position).getState()); //Optional parameters
                myIntent.putExtra("house", adapter.getItem(position).getHouse()); //Optional parameters

                startActivity(myIntent);


            }
        });
        if (Potato.potate().Utils().isInternetConnected(searchActivity.this)) {
            Log.d("async condition", "executing");
            LoadData loadData = new LoadData(helper.getUserDetails());
            loadData.execute();
        }
    }


    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onStop() {
         super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public searchActivity getActivity() {
        return searchActivity.this;
    }

    public class LoadData extends AsyncTask<Void, Void, Void> {
        Data data;

        LoadData(Data data1) {
            data = data1;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("async", "executing");
           apiClient.getAPI().getMp(data.getSession().getSessionid(), data.getSession().getToken(),
                     new Callback<MpResponse>() {
                        @Override
                        public void success(MpResponse mp_response, Response response) {
                            if (mp_response.getCode().equals("200") && mp_response.getResponse().getError().equals("0")) {

                                ArrayList<MpData> places = mp_response.getResponse().getData();
                                mpList = new ArrayList<MpData>();
                                mpList.addAll(places);


                                adapter = new CustomAdapter(searchActivity.this,mpList);

                                listView.setAdapter(adapter);
                                search.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        Log.d("searching text", s.toString());
                                        adapter.getFilter().filter(s.toString());

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {


                                    }
                                });

                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            error.printStackTrace();
                        }
                    });
            return null;
        }

    }
}
