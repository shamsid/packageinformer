package com.shamsid.packageinformer.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.shamsid.packageinformer.model.PackagInfo;
import java.util.ArrayList;
import java.util.List;

public class PackageInformer {


  public static final  int SERVICES = 2;
  public static final  int RECEIVERS = 3;
  public static final  int PERMISSIONS = 4;
  public static final  int PACKAGES = 5;
  public static final  int ACTIVITITES = 6;
  public static final  int PROVIDERS = 7;


  private PackageManager packageManager;
  private Context context;


  public PackageInformer (Context context) {
    this.context = context;
    this.packageManager = context.getPackageManager ();
  }

  /**
   * getActivities will take packageName and returns list of Activities in that  package
   * @params String packageName Package Name of the activity
   * @return ArrayList<String> Returns the list of Activity in that package
   * @throws PackageManager.NameNotFoundException
   */

  public ArrayList<String> getActivities(String packageName) throws
      PackageManager.NameNotFoundException{
    ArrayList<String>  activityList = new ArrayList<> ();
    PackageInfo packageInfo = getPackageInfo (packageName,PackageManager.GET_ACTIVITIES);
    if(packageInfo.activities !=null){

      for (int i = 0;i<packageInfo.activities.length;i++){
        activityList.add (packageInfo.activities[i].name);
      }
    }

    return activityList;
  }

  /**
   *
   * @param packageName  Name of package for which receivers are needed.
   * @return Array of all the services which are available in package
   * @throws PackageManager.NameNotFoundException
   */

  public ArrayList<String> getReceivers(String packageName) throws
      PackageManager.NameNotFoundException{
    ArrayList<String> receiverList = new ArrayList<> ();
    PackageInfo packageInfo = getPackageInfo (packageName,packageManager.GET_RECEIVERS);

    if(packageInfo.receivers!= null){
      for (int i = 0; i< packageInfo.receivers.length;i++){
        receiverList.add (packageInfo.receivers[i].name);
      }
    }

    return receiverList;
  }

  /**
   *
   * @param packageName Name of package for which services are needed.
   * @return Array of all the services which are available in package
   * @throws PackageManager.NameNotFoundException
   */

  public ArrayList<String> getServices(String packageName) throws
      PackageManager.NameNotFoundException{
    ArrayList<String> serviceList = new ArrayList<> ();
    PackageInfo packageInfo = getPackageInfo (packageName,packageManager.GET_SERVICES);

    if(packageInfo.services != null){
      for(int i = 0; i<packageInfo.services.length;i++){
        serviceList.add (packageInfo.services[i].name);
      }
    }
    return serviceList;
  }

  /**
   *
   * @param packageName  Name of package for which providers are needed.
   * @return Array of all the providers which are available in package
   * @throws PackageManager.NameNotFoundException
   */

  public ArrayList<String> getProvider(String packageName) throws
      PackageManager.NameNotFoundException{
    ArrayList<String> providerList = new ArrayList<> ();
    PackageInfo packageInfo = getPackageInfo (packageName,packageManager.GET_PROVIDERS);

    if(packageInfo.providers != null){
      for(int i = 0;i<packageInfo.providers.length;i++){
        providerList.add (packageInfo.providers[i].name);
      }
    }
    return providerList;
  }

  /**
   *
   * @param packageName Name of package for which permissions are needed.
   * @return Array of all the permissions which are required by package
   * @throws PackageManager.NameNotFoundException
   */

  public ArrayList<String> getPermissions(String packageName) throws
      PackageManager.NameNotFoundException{
    ArrayList <String> permissionList = new ArrayList<> ();
    PackageInfo packageInfo = getPackageInfo (packageName,packageManager.GET_PERMISSIONS);


    if(packageInfo.permissions != null){
      for(int i = 0; i<packageInfo.permissions.length;i++){
        permissionList.add (packageInfo.permissions[i].name);
      }
    }
    return permissionList;
  }

  /**
   * getAllPackageInfos will return all the ArrayList according to flag.
   * @param flag int flag will either services, activities, receivers ,permission,providers,package
   * @return PakagInfo List of all packages according to flag
   */

  public ArrayList<PackagInfo> getAllPackageInfos(int flag){

    ArrayList<PackagInfo> packagInfosList = new ArrayList<> ();
    List<PackageInfo> packageInfosList = new ArrayList<> ();

    switch (flag){

      case PACKAGES:
        packageInfosList = packageManager.getInstalledPackages (0);
        break;

      case ACTIVITITES:
        packageInfosList = packageManager.getInstalledPackages (PackageManager.GET_ACTIVITIES);
        break;

      case SERVICES:
        packageInfosList = packageManager.getInstalledPackages (PackageManager.GET_SERVICES);
        break;

      case RECEIVERS:
        packageInfosList = packageManager.getInstalledPackages (PackageManager.GET_RECEIVERS);
        break;

      case PERMISSIONS:
        packageInfosList = packageManager.getInstalledPackages (PackageManager.GET_PERMISSIONS);
        break;

      case PROVIDERS:
        packageInfosList = packageManager.getInstalledPackages (PackageManager.GET_PROVIDERS);
        break;

      default:
        packageInfosList = packageManager.getInstalledPackages (0);
    }

    for (PackageInfo packageInfo:
        packageInfosList) {
      if(!packageInfo.packageName.contains ("com.android")){
        packagInfosList.add (setPackageInfo(packageInfo));
      }
    }

    return packagInfosList;
  }

  /**
   * setPackageInfo will take PackageInfo will set the model class and returns the object.
   * @param packageInfo PackageInfo
   * @return PackagInfo will return updated model class
   */

  private PackagInfo setPackageInfo(PackageInfo packageInfo) throws
      NullPointerException{
    PackagInfo packagInfo = new PackagInfo ();

    packagInfo.setBaseRevisionCode (packageInfo.baseRevisionCode);
    packagInfo.setFirstInstallTime (packageInfo.firstInstallTime);
    packagInfo.setInstallLocation (String.valueOf (packageInfo.installLocation));
    packagInfo.setLastUpdateTime (packageInfo.lastUpdateTime);
    packagInfo.setVersionCode (String.valueOf (packageInfo.versionCode));
    packagInfo.setVersionName (packageInfo.versionName);
    packagInfo.setPackageName (packageInfo.packageName);
    return packagInfo;
  }

  private PackageInfo getPackageInfo(String packageName,int flag) throws
      PackageManager.NameNotFoundException{
    return packageManager.getPackageInfo (packageName,flag);
  }

  public  Drawable getPackageDrawable(String packageName){
    Drawable icon = null;
    try{
      icon = packageManager.getApplicationIcon (packageName);

    }catch (PackageManager.NameNotFoundException nameException ){
      nameException.printStackTrace ();
    }
    return icon;

  }
  public String getAppNameForPkg(String packageName) {
    try {
      PackageInfo packageInfo = packageManager.getPackageInfo (packageName, 0);
      return packageInfo != null ? packageInfo.applicationInfo.loadLabel (packageManager).toString ()
          : null;
    } catch (PackageManager.NameNotFoundException ne) {
      ne.printStackTrace ();
    }
    return null;
  }

}
