package com.shamsid.packageinformerdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.shamsid.packageinformer.core.PackageInformer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    final PackageInformer packageInformer = new PackageInformer (this);

    RecyclerView  recyclerView = (RecyclerView) findViewById (R.id.rv_application);
    recyclerView.setHasFixedSize (true);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager (this);
    recyclerView.setLayoutManager (linearLayoutManager);
    ApplicationAdapter applicationAdapter = new ApplicationAdapter (packageInformer.getAllPackageInfos (5),this);
    recyclerView.setAdapter (applicationAdapter);

    recyclerView.addOnItemTouchListener(
        new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
          @Override public void onItemClick(View view, int position) {
            Intent intent = new Intent (MainActivity.this,ApplicationDetailActivity.class);
            intent.putExtra ("package_detail",packageInformer.getAllPackageInfos (5).get (position));
            startActivity (intent);
          }
        })
    );
}
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }
}
