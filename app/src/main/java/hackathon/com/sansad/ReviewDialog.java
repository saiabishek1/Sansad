package hackathon.com.sansad;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import hackathon.com.sansad.models.api.AddReview.AddReviewModel;
import hackathon.com.sansad.models.mp.MpData;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by utk994 on 24-Apr-16.
 */public class ReviewDialog extends AppCompatActivity {
    public static String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_dialog);
        SQLiteDBHelper helper = new SQLiteDBHelper(this);
        final String session = helper.getUserDetails().getSession().getSessionid();
        final String token = helper.getUserDetails().getSession().getToken();
        final AppCompatEditText reviewEditText = (AppCompatEditText) findViewById(R.id.review_edit_text);
        AppCompatButton submitButton = (AppCompatButton) findViewById(R.id.submit_button);
        final AppCompatRatingBar ratingBar = (AppCompatRatingBar) findViewById(R.id.review_rating_bar);
        final ProgressDialog saveProgressDialog = new ProgressDialog(this);
        saveProgressDialog.setMessage("Adding...");
        saveProgressDialog.setCancelable(false);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProgressDialog.show();
                String review = reviewEditText.getText().toString();
                String rating = String.valueOf(ratingBar.getRating());
                apiClient.getAPI().addReview(session, token, id, review, rating, new Callback<AddReviewModel>() {

                    @Override
                    public void success(AddReviewModel addReviewModel, Response response) {
                        if (addReviewModel.getCode() == 200 && addReviewModel.getResponse().getError() == 0) {
                            Toast.makeText(ReviewDialog.this, "Review Added", Toast.LENGTH_SHORT).show();
                            ReviewDialog.this.onBackPressed();
                            saveProgressDialog.dismiss();
                        } else {
                            Log.d("error", addReviewModel.getResponse().getMessage());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        saveProgressDialog.dismiss();
                    }
                });
            }
        });
    }
}

