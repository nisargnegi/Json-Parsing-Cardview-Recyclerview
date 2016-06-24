package nisarg.volley_5;

/**
 * Created by ravi negi on 17-06-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


/**
 * Created by ravi negi on 17-06-2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    Context c;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data, Context c) {
        this.dataSet = data;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //view.setOnClickListener(MainActivityJSON.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getUser_id());
        textViewVersion.setText(dataSet.get(listPosition).getDescrip());
       // imageView.setImageResource(dataSet.get(listPosition).getDrawable());
       // Glide.with(c).load("http://goo.gl/gEgYUd").into(imageView);
        Picasso.with(c)
                .load(dataSet.get(listPosition).getDrawable())
                .placeholder(R.drawable.ic_launcher) //this is optional the image to display while the url image is downloading
                .error(R.drawable.error)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



}



