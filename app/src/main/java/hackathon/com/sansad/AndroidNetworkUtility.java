package hackathon.com.sansad;

/**
 * Created by sai on 31/10/15.
 */
        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.util.Log;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.methods.HttpRequestBase;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.params.BasicHttpParams;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;

public class AndroidNetworkUtility {
    private static final String TAG = "AndroidNetworkUtility";

    public boolean isConnected(Context ctx) {
        boolean flag = false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            flag = true;
        }
        return flag;
    }

    public String getHttpResponse(HttpRequestBase request) {
        String result = null;
        InputStream inputStream = null;
        try {
            Log.d(TAG, "about to instantiate DefaultHttpClient");
            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            Log.d(TAG, "got httpClient" + request.getURI().toString());
            HttpResponse httpResponse = httpClient.execute(request);
            Log.d(TAG, "got httpResponse");
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Status Code: " + statusCode);
            String reason = httpResponse.getStatusLine().getReasonPhrase();
            Log.d(TAG, "Reason: " + reason);
            StringBuilder sb = new StringBuilder();
            if (statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                inputStream = entity.getContent();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"), 8);
                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                sb.append(reason);
            }
            result = sb.toString();
            Log.d(TAG, result);
        } catch (UnsupportedEncodingException ex) {
            Log.e(TAG, ex.toString());
            ex.printStackTrace();
        } catch (ClientProtocolException ex1) {
            Log.e(TAG, ex1.toString());
            ex1.printStackTrace();
        } catch (IOException ex2) {
            Log.e(TAG, ex2.toString());
            ex2.printStackTrace();
        } finally {
            Log.d(TAG, "finally .............");
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, result);
        return result;
    }
}