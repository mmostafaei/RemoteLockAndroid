package ir.mmostafaei.patternlock.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bcgdv.asia.lib.connectpattern.ConnectPatternView;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.MyApplication;


public class FragmentMain extends Fragment {

  private static final String TAG = FragmentMain.class.getSimpleName();
  View view;
  String imei;
  Shimmer shimmer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_main, container, false);

    final ConnectPatternView connect = (ConnectPatternView) view.findViewById(R.id.connect);
    final ShimmerTextView txtWrongCode = (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
    final AnimatedCircleLoadingView animatedCircleLoadingView = (AnimatedCircleLoadingView) view.findViewById(R.id.circle_loading_view);
    final LinearLayout llOpenDoor=(LinearLayout)view.findViewById(R.id.llOpenDoor);
    final LinearLayout llChangePass=(LinearLayout)view.findViewById(R.id.llChangePass);



    shimmer = new Shimmer();
    shimmer.start(txtWrongCode);

    txtWrongCode.setTextColor(Color.parseColor("#22ff33"));
    txtWrongCode.setText("Draw Pattern");
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

        if (numberString.equals("7423")) {
          txtWrongCode.setVisibility(View.GONE);
          connect.animateOut();
        } else {
          txtWrongCode.setTextColor(Color.parseColor("#ff0000"));
          txtWrongCode.setText("Wrong Pattern!");
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
        llChangePass.setVisibility(View.VISIBLE);
        llOpenDoor.setVisibility(View.VISIBLE);
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