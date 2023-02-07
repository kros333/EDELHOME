package com.EDELHOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WorkerGridAdapter  extends BaseAdapter
{
    private Context mContext;
    private List<Worker> workers;

    public WorkerGridAdapter(Context context, List<Worker> worker)
    {
        this.mContext = context;
        this.workers = worker;
    }

    @Override
    public int getCount() {
        return workers.size();
    }

    @Override
    public Object getItem(int position) {
        return workers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.grid_worker, parent, false);
        } else {
            grid = (View) convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(R.id.imageView);
        ImageView quadView = (ImageView) grid.findViewById(R.id.imageView1);
        TextView nameView = grid.findViewById(R.id.name);
        Worker worker = workers.get(position);

        imageView.setImageResource(worker.getImage());
        quadView.setImageResource(R.drawable.quad_green);
        nameView.setText(worker.getName());

        return grid;
    }
}
