package ir.mmostafaei.patternlock.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;

/**
 * Created by mohsen on 11/28/2016.
 */
public class ConnectToLockWifiGuideDialog {


  public static void show() {
    final Dialog dialog = new Dialog(MyApplication.currentActivity, R.style.PauseDialog);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_connect_to_lock_wifi_guide);
    dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
    TypefaceUtil.overrideFonts(MyApplication.context, dialog.getWindow().getDecorView());
    LinearLayout llClose = (LinearLayout) dialog.findViewById(R.id.llClose);

    llClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }
    });


    dialog.show();
  }
}
