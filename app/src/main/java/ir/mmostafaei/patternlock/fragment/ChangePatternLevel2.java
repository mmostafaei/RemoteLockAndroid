package ir.mmostafaei.patternlock.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcgdv.asia.lib.connectpattern.ConnectPatternView;

import java.util.ArrayList;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.app.ChangerFragment;
import ir.mmostafaei.patternlock.app.DataHolderCustom;
import ir.mmostafaei.patternlock.app.MyApplication;
import ir.mmostafaei.patternlock.dialog.LoadingDialog;
import ir.mmostafaei.patternlock.dialog.ShowMessageDialog;
import ir.mmostafaei.patternlock.socket.OpenSocket;
import ir.mmostafaei.patternlock.type_face.TypefaceUtil;

/***
 * Created by mohsen on 11/28/2016.
 */
public class ChangePatternLevel2 extends Fragment {

  private static final String TAG = ChangePatternLevel2.class.getSimpleName();
  View view;
  String tempPass;
  boolean isNewPatternSet;
  String password;
  ConnectPatternView connectPatternView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_change_pattern_level_2, container, false);

    TypefaceUtil.overrideFonts(MyApplication.context, view);

    connectPatternView = (ConnectPatternView) view.findViewById(R.id.connect1);
    Button btnCancell = (Button) view.findViewById(R.id.btnCancell);
    Button btnChangePattern = (Button) view.findViewById(R.id.btnChangePattern);
    LinearLayout llBack = (LinearLayout) view.findViewById(R.id.llBack);
    final LinearLayout llPattern = (LinearLayout) view.findViewById(R.id.llPattern);
    LinearLayout llCancell = (LinearLayout) view.findViewById(R.id.llCancell);
    final TextView txtResponse = (TextView) view.findViewById(R.id.txtResponse2);
    password = DataHolderCustom.getInstance().password;
    Bundle args = getArguments();
    tempPass = args.getString("tempPass");

    llBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getActivity().getFragmentManager().popBackStack();
      }
    });

    btnCancell.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new FragmentMain();
        ChangerFragment.replaceSplashFragments(fragment, FragmentMain.class.getSimpleName(), false);
      }
    });

    if (password.equals("47421")) {
      llCancell.setVisibility(View.GONE);
    }

    connectPatternView.setOnConnectPatternListener(new ConnectPatternView.OnConnectPatternListener() {
      @Override
      public void onPatternEntered(ArrayList<Integer> result) {
        Log.i(TAG, "onPatternEntered: reuslt" + result);
        String numberString = "";
        for (int number : result) {
          numberString = numberString + (number + 1);
        }

        Log.i(TAG, "onPatternEntered: last number : " + numberString);
        if (!numberString.equals(tempPass)) {
          txtResponse.setTextColor(Color.RED);
          txtResponse.setText("الگو های وارد شده تطابق ندارند");
          isNewPatternSet = false;
          return;
        }
        isNewPatternSet = true;
        txtResponse.setText("جهت ذخیره الگو تایید را بزنید");
        txtResponse.setTextColor(Color.GREEN);

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
        ShowMessageDialog.show("الگو با موفقیت تغییر کرد");
        connectPatternView.setVisibility(View.GONE);
        llPattern.setVisibility(View.GONE);
      }
    });

    connectPatternView.setVisibility(View.GONE);
    connectPatternView.animateIn();

    btnChangePattern.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isNewPatternSet) {
          changePass();
        } else {
          txtResponse.setTextColor(Color.RED);
          txtResponse.setText("لطفا الگو را دوباره وارد کنید");
        }

      }
    });

    return view;
  }

  private void changePass() {
    OpenSocket.Listener listener = new OpenSocket.Listener() {
      @Override
      public void onDataRecived(String data) {
        LoadingDialog.ldialog.dismiss();
        if (data.equals("DoorPassChenged")) {
          DataHolderCustom.getInstance().password = tempPass;
          connectPatternView.animateOut();
        } else {
          ShowMessageDialog.show("تغییر الگو با مشکل روبرو شد.");
        }

      }

      @Override
      public void onFailedSocketConnection() {
        LoadingDialog.ldialog.dismiss();
        ShowMessageDialog.show("مشکل در برقرای ارتباط با قفل لطفا ارتباط دستگاه خود با وایفای را بررسی کنید");
      }
    };

    OpenSocket openSocket = new OpenSocket();
    openSocket.listener(listener).dataToSend("ChangePass=" + password + "," + tempPass).start();
    LoadingDialog.makeLoder();

  }


}
