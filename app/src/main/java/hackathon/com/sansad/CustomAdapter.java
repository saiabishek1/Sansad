package hackathon.com.sansad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by utk994 on 28/04/15.
 */
public class CustomAdapter extends BaseAdapter {


    Context context;
    List<RowItem> rowItem;

    CustomAdapter(Context context, List<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;

    }

    @Override
    public int getCount() {

        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView constit = (TextView) convertView
                .findViewById(R.id.constituency);
        TextView rank = (TextView) convertView
                .findViewById(R.id.rank);

        TextView points = (TextView) convertView
                .findViewById(R.id.points);

        ImageView profilePic = ( ImageView) convertView
                .findViewById(R.id.profilePic);




        RowItem row_pos = rowItem.get(position);
        // setting the image resource and title


        name.setText(row_pos.getName());
        /*CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(row_pos.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS); */

        constit.setText(row_pos.getConstit());

        points.setText(row_pos.getPoints());

        rank.setText(row_pos.getRank());



        profilePic.setImageDrawable(row_pos.getProfilePic());



        return convertView;

    }

}