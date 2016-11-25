package ir.mmostafaei.patternlock.activity;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.ChangerFragment;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.app.PrefManager;

import ir.mmostafaei.patternlock.fragment.FragmentMain;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;


public class ActivitySplash extends AppCompatActivity {


  private static final String TAG = ActivitySplash.class.getSimpleName();


  public static boolean splashActivityRuning;

  @Override
  protected void onResume() {
    super.onResume();
    MyApplication.currentActivity = this;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  LinearLayout llMain;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.BLACK);
    }

//    llMain = (LinearLayout) findViewById(R.id.llMain);
//    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
//      .findViewById(android.R.id.content)).getChildAt(0);
//
//    TypefaceUtil.overrideFonts(MyApplication.currentActivity, viewGroup);


    navigation();

  }







  @Override
  public void onStart() {
    super.onStart();
    splashActivityRuning = true;
  }

  @Override
  public void onStop() {
    super.onStop();
    splashActivityRuning = false;
  }



  private void navigation() {
    MyApplication.handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Fragment fragment = new FragmentMain();
        ChangerFragment.replaceSplashFragments(fragment, FragmentMain.class.getSimpleName(), false);
      }
    }, 2000);
  }




  @Override
  public void onBackPressed() {

  }

}
