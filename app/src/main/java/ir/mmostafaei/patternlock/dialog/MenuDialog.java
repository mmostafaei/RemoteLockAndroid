package ir.mmostafaei.patternlock.dialog;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.ChangerFragment;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.fragment.ChangePatternLevel1;
import ir.mmostafaei.patternlock.fragment.FragmentMain;

/**
 * Created by mohsen on 11/28/2016.
 */
public class MenuDialog {


  public static void show() {
    final Dialog dialog = new Dialog(MyApplication.currentActivity, R.style.PauseDialog);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_menu);
    dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

    LinearLayout llClose = (LinearLayout) dialog.findViewById(R.id.llClose);
    Button btnPassword = (Button) dialog.findViewById(R.id.btnPassword);
    llClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }
    });

    btnPassword.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
        Fragment fragment = new ChangePatternLevel1();
        ChangerFragment.replaceSplashFragments(fragment, ChangePatternLevel1.class.getSimpleName(), true);
      }
    });

    dialog.show();
  }
}
