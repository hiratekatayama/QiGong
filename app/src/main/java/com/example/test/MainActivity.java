package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<LaunchItem> launchItemsAdapter = null;
    private ArrayList<LaunchItem> launchItems = new ArrayList<LaunchItem>();
    private LaunchListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        private CheckBox mCheckBoxOrientation;
        private TextView mAzimuthText, mPitchText, mRollText;
        private float[] mAccelerationValue = new float[3];
        private float[] mGeoMagneticValue = new float[3];
        private float[] mOrientationValue = new float[3];
        private float[] mInRotationMatrix = new float[9];
        private float[] mOutRotationMatrix = new float[9];
        private float[] mInClinationMatrix = new float[9];


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_other_app_sample_list);

        PackageManager pm = getPackageManager();
        List<PackageInfo> pckInfoList = pm.getInstalledPackages(
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        for(PackageInfo pckInfo : pckInfoList){
            System.out.println("test");
            System.out.println(pckInfo);
            LaunchItem oLaunchItem = null;
            if(pm.getLaunchIntentForPackage(pckInfo.packageName) != null){
                String packageName = pckInfo.packageName;
                String className = pm.getLaunchIntentForPackage(pckInfo.packageName).getComponent().getClassName()+"";
                Log.i("起動可能なパッケージ名",packageName);
                Log.i("起動可能なクラス名",className);
                oLaunchItem = new LaunchItem(true,packageName,className);
            }else{
                Log.i("----------起動不可能なパッケージ名",pckInfo.packageName);
                oLaunchItem = new LaunchItem(false,pckInfo.packageName,null);
            }
            launchItems.add(oLaunchItem);
        }

        mListAdapter = new LaunchListAdapter(this.getApplicationContext());
        mListAdapter.setmArrayList(launchItems);
        mListAdapter.setLaunchAppListener(new LaunchListAdapter.LaunchAppListener() {
            @Override
            public void onLaunch(Intent intent) {
                startActivity(intent);
            }
        });

        ListView launchListLayout = (ListView) findViewById(R.id.launchListLayout);
        launchListLayout.setAdapter(mListAdapter);

    }
}
