package com.shamsid.packageinformerdemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.shamsid.packageinformer.core.PackageInformer;
import com.shamsid.packageinformer.model.PackagInfo;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ApplicationDetailActivity extends Activity {

  private ImageView mApplicationIcon;
  private TextView  mApplicationName ,mVersionCode;


  @Override protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.single_app_detail);

    PackageInformer packageInformer = new PackageInformer (this);


    mApplicationIcon = (ImageView) findViewById (R.id.img_app_icon);
    mApplicationName = (TextView) findViewById (R.id.tv_app_name);
    mVersionCode = (TextView) findViewById (R.id.tv_version_code);

    PackagInfo packagInfoList = (PackagInfo) getIntent ().getParcelableExtra ("package_detail");

    mApplicationIcon.setImageDrawable (packageInformer.getPackageDrawable (packagInfoList.getPackageName ()));
    mApplicationName.setText (packageInformer.getAppNameForPkg (packagInfoList.getPackageName ())!=null?
        packageInformer.getAppNameForPkg (packagInfoList.getPackageName ()):packagInfoList.getPackageName ());
    mVersionCode.setText (packagInfoList.getVersionCode ());



    RecyclerView recyclerViewPermissions = (RecyclerView) findViewById (R.id.rv_permission);
    RecyclerView recylerViewReceivers = (RecyclerView) findViewById (R.id.rv_receivers);
    RecyclerView recylerViewProviders = (RecyclerView) findViewById (R.id.rv_providers);
    RecyclerView recylerViewServices = (RecyclerView) findViewById (R.id.rv_services);
    RecyclerView recylerViewSActivities = (RecyclerView) findViewById (R.id.rv_activities);


    recyclerViewPermissions.setHasFixedSize (true);
    recylerViewReceivers.setHasFixedSize (true);
    recylerViewProviders.setHasFixedSize (true);
    recylerViewServices.setHasFixedSize (true);
    recylerViewSActivities.setHasFixedSize (true);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager (this);
    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager (this);
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager (this);
    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager (this);
    LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager (this);

    recyclerViewPermissions.setLayoutManager (linearLayoutManager);
    recylerViewReceivers.setLayoutManager (linearLayoutManager1);
    recylerViewProviders.setLayoutManager (linearLayoutManager2);
    recylerViewServices.setLayoutManager (linearLayoutManager3);
    recylerViewSActivities.setLayoutManager (linearLayoutManager4);

    try {

      AppInformationAdapter applicationAdapter = new AppInformationAdapter (packageInformer.getPermissions (packagInfoList.getPackageName ()));
      AppInformationAdapter receiversAdapter = new AppInformationAdapter (packageInformer.getReceivers (packagInfoList.getPackageName ()));
      AppInformationAdapter providerAdapter = new AppInformationAdapter (packageInformer.getProvider (packagInfoList.getPackageName ()));
      AppInformationAdapter activitiesAdapter = new AppInformationAdapter (packageInformer.getActivities (packagInfoList.getPackageName ()));
      AppInformationAdapter servicesAdapter = new AppInformationAdapter (packageInformer.getServices (packagInfoList.getPackageName ()));

      recyclerViewPermissions.setAdapter (applicationAdapter);
      recylerViewReceivers.setAdapter (receiversAdapter);
      recylerViewProviders.setAdapter (providerAdapter);
      recylerViewServices.setAdapter (servicesAdapter);
      recylerViewSActivities.setAdapter (activitiesAdapter);


    }catch (PackageManager.NameNotFoundException e){
      e.printStackTrace ();
    }
  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

}
