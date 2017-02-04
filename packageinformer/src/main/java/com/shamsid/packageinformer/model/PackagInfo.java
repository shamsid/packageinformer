package com.shamsid.packageinformer.model;

import android.os.Parcel;
import android.os.Parcelable;



public  class PackagInfo implements Parcelable {

  public PackagInfo () {
  }

  public long getFirstInstallTime () {
    return firstInstallTime;
  }

  public void setFirstInstallTime (long firstInstallTime) {
    this.firstInstallTime = firstInstallTime;
  }

  public String getInstallLocation () {
    return installLocation;
  }

  public void setInstallLocation (String installLocation) {
    this.installLocation = installLocation;
  }

  public String getPackageName () {
    return packageName;
  }

  public void setPackageName (String packageName) {
    this.packageName = packageName;
  }

  public String getVersionCode () {
    return versionCode;
  }

  public void setVersionCode (String versionCode) {
    this.versionCode = versionCode;
  }

  public String getVersionName () {
    return versionName;
  }

  public void setVersionName (String versionName) {
    this.versionName = versionName;
  }

  public long getLastUpdateTime () {
    return lastUpdateTime;
  }

  public void setLastUpdateTime (long lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public int getBaseRevisionCode () {
    return baseRevisionCode;
  }

  public void setBaseRevisionCode (int baseRevisionCode) {
    this.baseRevisionCode = baseRevisionCode;
  }

  private long firstInstallTime;
  private String installLocation;
  private String packageName;
  private String versionCode;
  private String versionName;
  private long lastUpdateTime;
  private int baseRevisionCode;

  protected PackagInfo (Parcel in) {
    installLocation = in.readString();
    packageName = in.readString();
    versionCode = in.readString();
    versionName = in.readString();
    lastUpdateTime = in.readLong();
    baseRevisionCode = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(installLocation);
    dest.writeString(packageName);
    dest.writeString(versionCode);
    dest.writeString(versionName);
    dest.writeLong(lastUpdateTime);
    dest.writeInt(baseRevisionCode);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<PackagInfo> CREATOR = new Parcelable.Creator<PackagInfo>() {
    @Override
    public PackagInfo createFromParcel(Parcel in) {
      return new PackagInfo (in);
    }

    @Override
    public PackagInfo[] newArray(int size) {
      return new PackagInfo[size];
    }
  };
}
