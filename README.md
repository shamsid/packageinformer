# PackageInformer
[![](https://jitpack.io/v/shamsid/packageinformer.svg)](https://jitpack.io/#shamsid/packageinformer)

PackageInformer is a library that will help you out with retrieving package related information of applications packages
are currently installed on device. It will provide all the necessary metadata and information by calling single method .It removes the boilerplate code when you want to get information about the package.

## Setup

> To use this library you must use **minSdkVersion >=16**.
- In your project level build.gradle file add the following code
```java
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

- In your app level build.gradle file add this line
```java
dependencies {
	        compile 'com.github.shamsid:packageinformer:v1.0.0'
	}
```

## Usage

```java
public class ApplicationDetailActivity extends AppCompatActivity {

  private ImageView mApplicationIcon;
  private TextView  mApplicationName ,mVersionCode;


  @Override protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);

    setContentView (R.layout.activity_application_detail);

    Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null){
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    PackageInformer packageInformer = new PackageInformer (this);


    mApplicationIcon = (ImageView) findViewById (R.id.img_app_icon);
    mApplicationName = (TextView) findViewById (R.id.tv_app_name);
    mVersionCode = (TextView) findViewById (R.id.tv_version_code);

    PackagInfo packagInfoList = (PackagInfo) getIntent ().getParcelableExtra ("package_detail");

    mApplicationIcon.setImageDrawable (packageInformer.getPackageDrawable (packagInfoList.getPackageName ()));
    mApplicationName.setText (packageInformer.getAppNameForPkg (packagInfoList.getPackageName ())!=null?
        packageInformer.getAppNameForPkg (packagInfoList.getPackageName ()):packagInfoList.getPackageName ());
    mVersionCode.setText (packagInfoList.getVersionCode ());

    Log.v("TAG",packageInformer.getPermissions (packagInfoList.getPackageName ()));
    Log.v("TAG",packageInformer.getReceivers (packagInfoList.getPackageName ()));
    Log.v("TAG",packageInformer.getServices (packagInfoList.getPackageName ()));
    Log.v("TAG",packageInformer.getProvider (packagInfoList.getPackageName ()));
    
    
  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // handle arrow click here
    if (item.getItemId() == android.R.id.home) {
      finish(); // close this activity and return to preview activity (if there is any)
    }

    return super.onOptionsItemSelected(item);
  }
}
```
## License

```
MIT License

Copyright (c) 2017 Shamsher Ahmed

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```