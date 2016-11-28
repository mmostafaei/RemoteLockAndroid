package ir.mmostafaei.patternlock.app;

import android.location.Location;

/*** Created by mohsen mostafaei on 29/06/2016.*/

public class DataHolderCustom {

  private static final DataHolderCustom INSTANCE = new DataHolderCustom();
  public String password;


    public static DataHolderCustom getInstance() {
      return INSTANCE;
  }

}
