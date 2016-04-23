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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hackathon.com.sansad.models.mp.MpData;

/**
 * Created by utk994 on 28/04/15.
 */
public class CustomAdapter extends BaseAdapter implements Filterable  {


    Context context;

    List<MpData> rowItem;
    List<MpData> filter_items;
    private ItemFilter mFilter = new ItemFilter();

    CustomAdapter(Context context, List<MpData> rowItem ) {
        this.context = context;
        this.rowItem = rowItem;

        filter_items = new ArrayList<MpData>();
        if(!rowItem.isEmpty())
        {
            filter_items.addAll(rowItem);

        }
    }

    @Override
    public int getCount() {

        return filter_items.size();
    }

    @Override
    public MpData getItem(int position) {

        return filter_items.get(position);
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




       MpData row_pos = filter_items.get(position);
        // setting the image resource and title


        name.setText(row_pos.getFirst_name()+" "+row_pos.getLast_name());
        /*CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(row_pos.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS); */
        if(!row_pos.getConstituency().equals("0"))
            constit.setText(row_pos.getConstituency());
        else
            constit.setText("Rajya Sabha");

        points.setText(row_pos.getScore());

        rank.setText(String.valueOf(position+1));
        if(!row_pos.getImgurl().isEmpty())
        Picasso.with(context).load(row_pos.getImgurl()).into(profilePic);

        return convertView;

    }


    public Filter getFilter() {
        return mFilter;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<MpData> list = rowItem;

            int count = list.size();
            final ArrayList<MpData> nlist = new ArrayList<MpData>(count);

            MpData filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                String name = filterableString.getFirst_name()+" "+filterableString.getLast_name();
                if (name.toLowerCase().contains(filterString) ||  filterableString.getConstituency().toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filter_items = (ArrayList<MpData>) results.values;

            notifyDataSetChanged();
        }

    }
}




