package com.shamsid.packageinformerdemo;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shamsheR on 21/02/17.
 */

public class PackageInfomerApplication extends Application{
  @Override public void onCreate () {
    super.onCreate ();
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/roboto.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build());
  }
}