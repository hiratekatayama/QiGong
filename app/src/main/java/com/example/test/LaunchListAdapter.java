package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LaunchListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<LaunchItem> mArrayList = null;

    LaunchAppListener mLaunchAppListener = null;
    public interface LaunchAppListener{
        void onLaunch(Intent intent);
    }

    public void setLaunchAppListener(LaunchAppListener _mLaunchAppListener) {
        this.mLaunchAppListener = _mLaunchAppListener;
    }

    public LaunchListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_launch_other_app_sample_list,parent,false);
        TextView packageNameText = (TextView) convertView.findViewById(R.id.packageName);
        TextView classNameText = (TextView) convertView.findViewById(R.id.className);
        Button button = (Button) convertView.findViewById(R.id.launch);
        final String packageName = mArrayList.get(position).getPackageName();
        final String className = mArrayList.get(position).getClassName();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mArrayList.get(position).isLaunchble()){
                    Intent intent = new Intent();
                    if(packageName != null && className!=null && mLaunchAppListener != null){
                        intent.setClassName(packageName,className);
                        mLaunchAppListener.onLaunch(intent);
                    }
                }
            }
        });

        if(mArrayList.get(position).isLaunchble()){
            packageNameText.setText(packageName);
            classNameText.setText(className);
        }else{
            packageNameText.setText(packageName);
            button.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }


    public void setmArrayList(ArrayList<LaunchItem> mArrayList) {
        this.mArrayList = mArrayList;
    }

}
