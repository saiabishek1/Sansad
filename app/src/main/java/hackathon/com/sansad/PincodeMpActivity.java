package hackathon.com.sansad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.lang.reflect.Type;

import de.hdodenhof.circleimageview.CircleImageView;

public class PincodeMpActivity extends Fragment {


    Button b;
    EditText t;
    Context context;

    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pincode_mp, null, false);

        t = (EditText) rootView.findViewById(R.id.pincode_text);
        b = (Button) rootView.findViewById(R.id.accept_pincode);
        context = getActivity().getApplicationContext();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pincode = t.getText().toString();
                Log.d("PINCODE____------>", String.valueOf(pincode.length()));
                RetrieveArt obj = new RetrieveArt();
                obj.execute(pincode);

            }
        });



        return rootView;
    }



        class RetrieveArt extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... pincode) {
            String district = "";
            String pin1 = pincode[0];
            try {
                Log.d("PINCODE____------>", pin1);
                Uri uri = Uri.parse("http://postalpincode.in/api/pincode/" + pin1);

                HttpGet httpGet = new HttpGet(String.valueOf(uri));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                HttpClient httpClient = new DefaultHttpClient();
                String request = null;

                request = httpClient.execute(httpGet, responseHandler);

                JSONObject root = null;

                root = new JSONObject(request);
                JSONArray postoffice = root.getJSONArray("PostOffice");
                district = postoffice.getJSONObject(0).getString("District");
                Log.d("DISTRICT______------>", district);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return district;
        }


        protected void onPostExecute(final String district) {
            Toast.makeText(context, "DISTRICT______------>"+district, Toast.LENGTH_LONG).show();

        }

    }
}



