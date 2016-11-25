package ir.mmostafaei.patternlock.type_face;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import  ir.mmostafaei.patternlock.app.MyApplication;


public class TypefaceUtil {

    /**
     * @param context just for bk uses and in normal we don't use this param
     * @param v is root view or just root view group <br>
     * <b>Ex in activity :  <b/><br>
     * <b> ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
     .findViewById(android.R.id.content)).getChildAt(0);</b>
     * <br>
     * <b>Ex in fragment :  just use view of fragment <b/><br>
     *  mohsen mostafaei 2014
    * */

    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(MyApplication.IraSanS);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }

}