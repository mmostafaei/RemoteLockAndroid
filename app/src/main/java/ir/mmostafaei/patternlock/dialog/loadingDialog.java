package ir.mmostafaei.patternlock.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.MyApplication;


public class LoadingDialog {

  public static Dialog ldialog;

  public static void makeLoder() {
    ldialog = new Dialog(MyApplication.currentActivity);
    ldialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    ldialog.setContentView(R.layout.loder_dialog);
    ldialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams wlp = ldialog.getWindow().getAttributes();
    ldialog.getWindow().setAttributes(wlp);
    ldialog.setOnKeyListener(new Dialog.OnKeyListener() {

      @Override
      public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          ldialog.dismiss();
        }
        return false;
      }
    });
//    ldialog.setCancelable(false);
    ldialog.show();
  }
}