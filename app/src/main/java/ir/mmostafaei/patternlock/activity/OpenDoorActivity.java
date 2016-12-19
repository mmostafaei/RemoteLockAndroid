package ir.mmostafaei.patternlock.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.bcgdv.asia.lib.connectpattern.ConnectPatternView;

import java.util.ArrayList;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.DataHolderCustom;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.dialog.LoadingDialog;
import ir.mmostafaei.patternlock.dialog.ShowMessageDialog;
import ir.mmostafaei.patternlock.socket.OpenSocket;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;

public class OpenDoorActivity extends AppCompatActivity {

  private static final String TAG = OpenDoorActivity.class.getSimpleName();

  @Override
  protected void onResume() {
    super.onResume();
    MyApplication.currentActivity = this;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final ConnectPatternView connect = (ConnectPatternView) findViewById(R.id.connect1);

    assert connect != null;
    connect.setOnConnectPatternListener(new ConnectPatternView.OnConnectPatternListener() {
      @Override
      public void onPatternEntered(ArrayList<Integer> result) {
        Log.i(TAG, "onPatternEntered: reuslt" + result);
        String numberString = "";
        for (int number : result) {
          numberString = numberString + (number + 1);
        }

        OpenSocket.Listener listener = new OpenSocket.Listener() {
          @Override
          public void onDataRecived(String data) {
            LoadingDialog.ldialog.dismiss();
            Log.e(TAG, "onDataRecived: " + data);
            if (data.equals("DoorOpend")) {
              data = "درب با موفقیت باز شد";
              showDoorOpenSuccessfully(data);
            } else {
              data = "الگو اشتباه است";
              ShowMessageDialog.show(data);
            }
          }

          @Override
          public void onFailedSocketConnection() {
            LoadingDialog.ldialog.dismiss();
            ShowMessageDialog.show("لطفا از ارتباط دستگاه خود به وایفای مطمُن شوید.,");
          }

        };
        OpenSocket openSocket = new OpenSocket();
        openSocket
          .dataToSend("OpenDoor=" + numberString)
          .listener(listener)
          .start();
        LoadingDialog.makeLoder();
        Log.i(TAG, "onPatternEntered: last number : " + numberString);

      }

      @Override
      public void onPatternAbandoned() {

      }

      @Override
      public void animateInStart() {

      }

      @Override
      public void animateInEnd() {

      }

      @Override
      public void animateOutStart() {

      }

      @Override
      public void animateOutEnd() {
      }
    });

  }




  public  void showDoorOpenSuccessfully(String message) {
    final Dialog dialog = new Dialog(MyApplication.currentActivity);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_show_message);
    dialog.setCancelable(false);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
    dialog.getWindow().setAttributes(wlp);
    TypefaceUtil.overrideFonts(MyApplication.context, dialog.getWindow().getDecorView());
     Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
    TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
    txtMessage.setText(message);
    btnClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
        MyApplication.currentActivity.finish();
      }
    });
    dialog.show();
  }

}
