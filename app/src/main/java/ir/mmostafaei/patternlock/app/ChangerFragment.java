package ir.mmostafaei.patternlock.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class ChangerFragment {


  public static void replaceSplashFragments(Fragment fragment, String flag, boolean b) {
    FragmentManager fragmentManager = MyApplication.currentActivity.getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if (b) {
      fragmentTransaction.addToBackStack(null);
    }

    fragmentTransaction.replace(android.R.id.content, fragment, flag);
    fragmentTransaction.commit();
  }

//  public static void replaceMainFragments(Fragment fragment, String flag, boolean b) {
//    FragmentManager fragmentManager = MyApplication.currentActivity.getFragmentManager();
//    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//    if (b) {
//      fragmentTransaction.addToBackStack(null);
//    }
//
//    fragmentTransaction.replace(R.id.frame_container, fragment, flag);
//    fragmentTransaction.commit();
//  }


}
