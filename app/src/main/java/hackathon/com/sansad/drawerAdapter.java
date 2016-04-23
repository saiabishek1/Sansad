package hackathon.com.sansad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class drawerAdapter extends BaseAdapter {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java
    //String Resource for header View Name


    TextView textView;
    ImageView imageView;
    Context context;

    drawerAdapter(Context context, String[] mNavTitles, int[] mIcons) {
        this.mNavTitles = mNavTitles;
        this.mIcons = mIcons;
        this.context = context;


    }


    @Override
    public int getCount() {

        return mNavTitles.length ;
    }

    @Override
    public Object getItem(int i) {
        return mNavTitles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            view = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        imageView = (ImageView) view.findViewById(R.id.rowIcon);
        textView = (TextView) view.findViewById(R.id.rowText);

        imageView.setImageResource(mIcons[i]);
        textView.setText(mNavTitles[i]);
        return view;


    }


}

