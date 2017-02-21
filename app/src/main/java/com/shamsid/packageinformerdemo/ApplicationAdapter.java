package com.shamsid.packageinformerdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shamsid.packageinformer.core.PackageInformer;
import com.shamsid.packageinformer.model.PackagInfo;
import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

  private List<PackagInfo> mPackageList;
  private PackageInformer mPackageInformer;

  public ApplicationAdapter( List<PackagInfo> packageList,Context context){
    this.mPackageList = packageList;
    this.mPackageInformer = new PackageInformer (context);
  }

  @Override public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.single_item,
        parent,false);
    ViewHolder viewHolder = new ViewHolder (view);

    return viewHolder;
  }

  @Override public void onBindViewHolder (ViewHolder holder, int position) {
    holder.applicationName.setText (mPackageInformer.getAppNameForPkg (mPackageList.get (position).getPackageName ())!=null?
        mPackageInformer.getAppNameForPkg (mPackageList.get (position).getPackageName ()):mPackageList.get (position).getPackageName ());
    holder.applicationVersionCode.setText (mPackageList.get (position).getVersionCode ());
    holder.applicationIcon.setImageDrawable (mPackageInformer.getPackageDrawable (mPackageList.
        get (position).getPackageName ()));
  }

  @Override public int getItemCount () {
    return mPackageList.size ();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView applicationIcon;
    TextView applicationName,applicationVersionCode;

    public ViewHolder (View itemView) {
      super (itemView);
      applicationIcon = (ImageView) itemView.findViewById (R.id.img_app_icon);
      applicationName = (TextView) itemView.findViewById (R.id.tv_app_name);
      applicationVersionCode = (TextView) itemView.findViewById (R.id.tv_version_code);

     }
  }
}
