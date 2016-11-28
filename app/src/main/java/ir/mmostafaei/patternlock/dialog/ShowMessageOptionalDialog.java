package ir.mmostafaei.patternlock.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;


/***
 * Created by moshen mostafaei on ${06/10/2016}.
 */

public class ShowMessageOptionalDialog {

  private static final String TAG = ShowMessageOptionalDialog.class.getSimpleName();
  public static Button btnOtherOption;
  public static Button btnClose;
  public static Dialog dialog;

  public static void show(String message) {
    dialog = new Dialog(MyApplication.currentActivity);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_show_error);
    dialog.setCancelable(false);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
    dialog.getWindow().setAttributes(wlp);
    TypefaceUtil.overrideFonts(MyApplication.context, dialog.getWindow().getDecorView());
    btnClose = (Button) dialog.findViewById(R.id.btnClose);
    btnOtherOption = (Button) dialog.findViewById(R.id.btnOtherOption);
    TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
    txtMessage.setText(message);

    dialog.show();
  }

}



