package hackathon.com.sansad;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hackathon.com.sansad.models.api.getReviews.Review;

/**
 * Created by utk994 on 24-Apr-16.
 */
public class reviewsAdapter extends BaseAdapter {
    ArrayList<Review> reviews;
    Context mContext;
    public reviewsAdapter(Context context, ArrayList<Review> reviewArrayList){
        reviews  =new ArrayList<>();
        reviews.addAll(reviewArrayList);
        mContext =context;
    }
    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Review getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(getItem(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.review_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();


            viewHolder.nameTv.setText("-" + getItem(position).getReviewedBy());

        viewHolder.reviewTv.setText(getItem(position).getReview());
        viewHolder.ratingTv.setText(getItem(position).getRating());
        return convertView;
    }
    static class ViewHolder {
        TextView nameTv;
        TextView reviewTv;
        TextView ratingTv;
        public ViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.name_text_view);
            reviewTv =  (TextView)view.findViewById(R.id.review_text_view);
            ratingTv = (TextView)view.findViewById(R.id.rating_text_view);
        }
    }

}
