package ir.mmostafaei.patternlock.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bcgdv.asia.lib.connectpattern.ConnectPatternView;

import java.util.ArrayList;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.ChangerFragment;
import ir.mmostafaei.patternlock.app.DataHolderCustom;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.dialog.ChangePatternGuideDialog;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;

/***
 * Created by mohsen on 11/28/2016.
 */
public class ChangePatternLevel1 extends Fragment {

  private static final String TAG = ChangePatternLevel1.class.getSimpleName();
  View view;
  String numberString;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_change_pattern_level_1, container, false);

    TypefaceUtil.overrideFonts(MyApplication.context, view);

    final ConnectPatternView connect = (ConnectPatternView) view.findViewById(R.id.connect);
    Button btnCancell = (Button) view.findViewById(R.id.btnCancell);
    final TextView txtResponse = (TextView) view.findViewById(R.id.txtResponse);
    String password = DataHolderCustom.getInstance().password;

    if (password.equals("47421")) {
      btnCancell.setVisibility(View.GONE);
    }
    MyApplication.handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        ChangePatternGuideDialog.show();
      }
    }, 500);

    btnCancell.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new FragmentMain();
        ChangerFragment.replaceSplashFragments(fragment, FragmentMain.class.getSimpleName(), false);
      }
    });


    connect.setOnConnectPatternListener(new ConnectPatternView.OnConnectPatternListener() {
      @Override
      public void onPatternEntered(ArrayList<Integer> result) {
        Log.i(TAG, "onPatternEntered: reuslt" + result);
        numberString = "";
        for (int number : result) {
          numberString = numberString + (number + 1);
        }

        if (numberString.length() < 5) {
          txtResponse.setText("الگو انتخاب شده کوتاه می باشد");
          return;
        }
        if (numberString.length() > 5) {
          txtResponse.setText("الگو انتخاب شده بزرگتر حد مجاز می باشد");
          return;
        }

        connect.animateOut();
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
        Fragment fragment = new ChangePatternLevel2();
        Bundle args = new Bundle();
        args.putString("tempPass", numberString);
        fragment.setArguments(args);
        ChangerFragment.replaceSplashFragments(fragment, ChangePatternLevel2.class.getSimpleName(), true);
      }
    });

    connect.setVisibility(View.GONE);
    connect.animateIn();


    return view;
  }
}
