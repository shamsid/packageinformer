package com.shamsid.packageinformer.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Helper {


  private boolean isServiceRunning(Class<?> serviceClass,Context context) {
    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
      if (serviceClass.getName().equals(service.service.getClassName())) {
        return true;
      }
    }
    return false;
  }

  public String generateHashkey(Context context){
    String hash_key = "";
    try {

      PackageInfo info = context.getPackageManager().getPackageInfo(
          "com.shamsid.loginmanager",
          PackageManager.GET_SIGNATURES);
      for (Signature signature : info.signatures) {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());

        hash_key= ""+ Base64.encodeToString(md.digest(),Base64.NO_WRAP);
      }
    } catch (PackageManager.NameNotFoundException e) {
      Log.d("NameNotFoundException", e.getMessage(), e);
    } catch (NoSuchAlgorithmException e) {
      Log.d("NoSuchAlException", e.getMessage(), e);
    }
    finally {
      return hash_key;
    }
  }


}
