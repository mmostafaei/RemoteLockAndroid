package ir.mmostafaei.patternlock.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Vibrator;

import java.util.Locale;



/***
 * Created by mohsen on 21/11/2016.
 */


public class MyApplication extends Application {
  private static final String TAG = MyApplication.class.getSimpleName();
  public static Context context;
  public static Activity currentActivity;
  public static Handler handler;
  private static final String IRANSANS = "fonts/IRANSans.otf";

  public static Typeface IraSanS;


  public static SharedPreferences sharedpreferences;
  public static SharedPreferences.Editor preferencesEditor;
  public static final String             myPREFERENCES            = "myPrefs";

  public static final String             IP_TIMEOUT               = "ipTimeOut";
  public static final String             PORT                     = "port";
  public static final String             IP                       = "ip";
  public static Vibrator                 vibe;

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
    handler = new Handler();
    initTypeface();
    String languageToLoad = "fa_";
    Locale locale = new Locale(languageToLoad);
    Locale.setDefault(locale);
    Configuration config = new Configuration();
    config.locale = locale;
    context.getResources().updateConfiguration(config,
      context.getResources().getDisplayMetrics());

    sharedpreferences = getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);
    preferencesEditor = sharedpreferences.edit();
    vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
  }


  private void initTypeface() {
    IraSanS = Typeface.createFromAsset(getAssets(), IRANSANS);
  }

}
