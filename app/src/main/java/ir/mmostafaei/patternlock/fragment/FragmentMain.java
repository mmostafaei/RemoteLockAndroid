package ir.mmostafaei.patternlock.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.bcgdv.asia.lib.connectpattern.ConnectPatternView;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.DataHolderCustom;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.dialog.LoadingDialog;
import ir.mmostafaei.patternlock.dialog.MenuDialog;
import ir.mmostafaei.patternlock.dialog.ShowMessageDialog;
import ir.mmostafaei.patternlock.socket.OpenSocket;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;


public class FragmentMain extends Fragment {

  private static final String TAG = FragmentMain.class.getSimpleName();
  View view;

  Shimmer shimmer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_main, container, false);

    TypefaceUtil.overrideFonts(MyApplication.context, view);

    final ConnectPatternView connect = (ConnectPatternView) view.findViewById(R.id.connect1);
    final ShimmerTextView txtWrongCode = (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
    final AnimatedCircleLoadingView animatedCircleLoadingView = (AnimatedCircleLoadingView) view.findViewById(R.id.circle_loading_view);
    final LinearLayout llOpenDoor = (LinearLayout) view.findViewById(R.id.llOpenDoor);
    final LinearLayout llSettings = (LinearLayout) view.findViewById(R.id.llSettings);


    shimmer = new Shimmer();
    shimmer.start(txtWrongCode);

    txtWrongCode.setTextColor(Color.parseColor("#22ff33"));
    txtWrongCode.setText("جهت ورود الگو را وارد کنید");
    connect.setVisibility(View.GONE);
    connect.animateIn();


    connect.setOnConnectPatternListener(new ConnectPatternView.OnConnectPatternListener() {
      @Override
      public void onPatternEntered(ArrayList<Integer> result) {
        Log.i(TAG, "onPatternEntered: reuslt" + result);
        String numberString = "";
        for (int number : result) {
          numberString = numberString + (number + 1);
        }

        if (numberString.equals(DataHolderCustom.getInstance().password)) {
          txtWrongCode.setVisibility(View.GONE);
          connect.animateOut();
        } else {
          txtWrongCode.setTextColor(Color.parseColor("#ff0000"));
          txtWrongCode.setText("الگو اشتباه است");
          txtWrongCode.setVisibility(View.VISIBLE);
        }
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
        connect.setVisibility(View.GONE);
        llSettings.setVisibility(View.VISIBLE);
        llOpenDoor.setVisibility(View.VISIBLE);
      }
    });


    llOpenDoor.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        OpenSocket.Listener listener = new OpenSocket.Listener() {
          @Override
          public void onDataRecived(String data) {
            LoadingDialog.ldialog.dismiss();
            Log.e(TAG, "onDataRecived: " + data);
            if (data.equals("DoorOpend")) {
              data = "درب با موفقیت باز شد";
            } else {
              data = "باز کردن درب با مشکل روبرو شد";
            }
            ShowMessageDialog.show(data);
          }

          @Override
          public void onFailedSocketConnection() {
            LoadingDialog.ldialog.dismiss();
            ShowMessageDialog.show("لطفا از ارتباط دستگاه خود به وایفای مطمُن شوید.");
          }

        };
        OpenSocket openSocket = new OpenSocket();
        openSocket.dataToSend("OpenDoor=" + DataHolderCustom.getInstance().password)
          .listener(listener)
          .start();
        LoadingDialog.makeLoder();

      }
    });


    llSettings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MenuDialog.show();
      }
    });


    return view;
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    shimmer.cancel();
  }
}