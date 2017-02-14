package com.shamsid.packageinformer.core;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by shamsheR on 14/02/17.
 */

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


}
