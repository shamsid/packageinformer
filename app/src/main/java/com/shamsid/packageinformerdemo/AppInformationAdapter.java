package com.shamsid.packageinformerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by shamsheR on 21/02/17.
 */

public class AppInformationAdapter extends RecyclerView.Adapter<AppInformationAdapter.ViewHolder> {

  private List<String> mInfoList;


  public AppInformationAdapter(List<String> infoList){
    this.mInfoList = infoList;
  }

  @Override public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.information,parent,false);
    ViewHolder viewHolder = new ViewHolder (view);
    return viewHolder;
  }
  @Override public int getItemCount () {
    return mInfoList.size ();
  }

  @Override public void onBindViewHolder (ViewHolder holder, int position) {
      holder.mInfos.setText (mInfoList.get (position));
  }



  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mInfos;
    public ViewHolder (View itemView) {
      super (itemView);
      mInfos = (TextView) itemView.findViewById (R.id.tv_infos);

    }
  }
}
