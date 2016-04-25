package hackathon.com.sansad;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hackathon.com.sansad.models.mp.MpData;
import hackathon.com.sansad.models.tags.Data;

/**
 * Created by utk994 on 24-Apr-16.
 */
public class tagAdapter extends BaseAdapter  {


    Context context;

    List<Data> rowItem;
    List<Data> filter_items;

      tagAdapter(Context context, List<Data> rowItem ) {
        this.context = context;
        this.rowItem = rowItem;

        filter_items = new ArrayList<Data>();
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
    public Data getItem(int position) {

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
            convertView = mInflater.inflate(R.layout.tag_list_item, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.tag_name);





        Data row_pos = filter_items.get(position);
        // setting the image resource and title


        name.setText(row_pos.getTag());

        return convertView;

    }



}




