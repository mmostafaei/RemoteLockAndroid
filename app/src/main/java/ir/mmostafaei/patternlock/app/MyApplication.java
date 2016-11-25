package ir.mmostafaei.patternlock.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Handler;
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
  }


  private void initTypeface() {
    IraSanS = Typeface.createFromAsset(getAssets(), IRANSANS);
  }

}
