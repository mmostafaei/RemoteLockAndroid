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
import android.util.Log;
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
import ir.mmostafaei.patternlock.app.DataHolderCustom;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.app.PrefManager;

import ir.mmostafaei.patternlock.dialog.ConnectToLockWifiGuideDialog;
import ir.mmostafaei.patternlock.dialog.ShowMessageOptionalDialog;
import ir.mmostafaei.patternlock.fragment.ChangePatternLevel1;
import ir.mmostafaei.patternlock.fragment.FragmentMain;
import ir.mmostafaei.patternlock.socket.OpenSocket;
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

    llMain = (LinearLayout) findViewById(R.id.llMain);
    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
      .findViewById(android.R.id.content)).getChildAt(0);

    TypefaceUtil.overrideFonts(MyApplication.currentActivity, viewGroup);


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
        OpenSocket.Listener listener = new OpenSocket.Listener() {
          @Override
          public void onDataRecived(String data) {
            Log.e(TAG, "onDataRecived: pass" + data);
            data = data.replace("DoorPass=", "");
            data = data.trim();

            DataHolderCustom.getInstance().password = data;

            Fragment fragment;
            String fragmentFlag;
            if (data.equals("474210000")) {
              fragment = new ChangePatternLevel1();
              fragmentFlag = ChangePatternLevel1.class.getSimpleName();
            } else {
              fragment = new FragmentMain();
              fragmentFlag = FragmentMain.class.getSimpleName();
            }
            ChangerFragment.replaceSplashFragments(fragment, fragmentFlag, false);

          }

          @Override
          public void onFailedSocketConnection() {
            ShowMessageOptionalDialog.show("لطفا از وصل بودن دستگاه خود به وایفای مطمُن شوید");
            ShowMessageOptionalDialog.btnOtherOption.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                ConnectToLockWifiGuideDialog.show();
              }
            });
            ShowMessageOptionalDialog.btnClose.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                ShowMessageOptionalDialog.dialog.dismiss();
                MyApplication.currentActivity.finish();
              }
            });
          }
        };

        OpenSocket openSocket = new OpenSocket();
        openSocket.dataToSend("GetPass=").listener(listener).start();

      }
    }, 2000);
  }


  @Override
  public void onBackPressed() {

  }

}
