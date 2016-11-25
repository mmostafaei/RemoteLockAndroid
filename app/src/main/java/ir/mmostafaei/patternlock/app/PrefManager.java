package ir.mmostafaei.patternlock.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by mohsen mostafaei on 08/07/15.
 */
public class PrefManager {

  // Shared Preferences
  SharedPreferences pref;

  // Editor for Shared preferences
  Editor editor;

  // Context
  Context _context;

  // Shared pref mode
  int PRIVATE_MODE = 0;

  // Shared preferences file name
  private static final String PREF_NAME = "parsian";

  // All Shared Preferences Keys
  private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
  private static final String KEY_MOBILE_NUMBER = "mobile_number";
  private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
  private static final String KEY_IMEI = "imei";
  private static final String KEY_EMAIL = "email";
  private static final String KEY_NAME = "name";
  private static final String KEY_MOBILE = "mobile";
  private static final String KEY_TOKEN = "token";
  private static final String KEY_USER_CODE = "userCode";
  private static final String KEY_MAX_DISTANCE_OF_AVAILABLE_CARS = "max_distance_of_cars";
  private static final String KEY_START_NIGHT_TIME = "start_night_time";
  private static final String KEY_END_NIGHT_TIME = "end_night_time";
  private static final String KEY_DELAY_PRICE = "delay_price";
  private static final String KEY_PRICE_PER_METER = "price_per_meter";
  private static final String KEY_INCOMING_PRICE = "incoming_price";
  private static final String KEY_IP = "ip";


  public PrefManager(Context context) {
    this._context = context;
    pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    editor = pref.edit();
  }

  public void setIsWaitingForSms(boolean isWaiting) {
    editor.putBoolean(KEY_IS_WAITING_FOR_SMS, isWaiting);
    editor.commit();
  }

  public boolean isWaitingForSms() {
    return pref.getBoolean(KEY_IS_WAITING_FOR_SMS, false);
  }


  public void setMobileNumber(String mobileNumber) {
    editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
    editor.commit();
  }

  public String getMobileNumber() {
    return pref.getString(KEY_MOBILE_NUMBER, null);
  }


  public void setUserCode(String userCode) {
    editor.putString(KEY_USER_CODE, userCode);
    editor.commit();
  }

  public String getUserCode() {
    return pref.getString(KEY_USER_CODE, null);
  }


  public void setToken(String token) {
    editor.putString(KEY_TOKEN, token);
    editor.commit();
  }

  public String getToken() {
    return pref.getString(KEY_TOKEN, null);
  }

  /*********************************************************************/
  public void setMaxCarsDistance(int distance) {
    editor.putInt(KEY_MAX_DISTANCE_OF_AVAILABLE_CARS, distance);
    editor.commit();
  }

  public int getMaxCarsDistance() {
    return pref.getInt(KEY_MAX_DISTANCE_OF_AVAILABLE_CARS, 5000);
  }

  public void setStartNightTime(int time) {
    editor.putInt(KEY_START_NIGHT_TIME, time);
    editor.commit();
  }

  public int getStartNightTime() {
    return pref.getInt(KEY_START_NIGHT_TIME, 23);
  }

  public void setEndNightTime(int time) {
    editor.putInt(KEY_END_NIGHT_TIME, time);
    editor.commit();
  }

  public int getEndNightTime() {
    return pref.getInt(KEY_END_NIGHT_TIME, 6);
  }

  public void setDelayPrice(int price) {
    editor.putInt(KEY_DELAY_PRICE, price);
    editor.commit();
  }

  public int getDelayPrice() {
    return pref.getInt(KEY_DELAY_PRICE, 936);
  }

  public void setPricePerMeter(float price) {
    editor.putFloat(KEY_PRICE_PER_METER, price);
    editor.commit();
  }

  public float getPricePerMeter() {
    return pref.getFloat(KEY_PRICE_PER_METER, 4.125f);
  }

  public void setIncomingPrice(int price) {
    editor.putInt(KEY_INCOMING_PRICE, price);
    editor.commit();
  }

  public int getIncomingPrice() {
    return pref.getInt(KEY_INCOMING_PRICE, 19500);
  }

  public void setIp(String ip) {
    editor.putString(KEY_IP, ip);
    editor.commit();
  }

  public String getIp() {
    return pref.getString(KEY_IP, "http://192.168.1.240:8008/");
  }


  public void createLogin(String name, String mobile, String imei, String email) {
    editor.putString(KEY_NAME, name);
    editor.putString(KEY_MOBILE, mobile);
    editor.putString(KEY_IMEI, imei);
    editor.putString(KEY_EMAIL, email);
    editor.commit();
  }

  public boolean isLoggedIn() {
    return pref.getBoolean(KEY_IS_LOGGED_IN, false);
  }

  public void setLoggedIn(boolean isLogin) {
    editor.putBoolean(KEY_IS_LOGGED_IN, isLogin);
    editor.commit();
  }

  public void clearSession() {
    editor.clear();
    editor.commit();
  }

  public HashMap<String, String> getUserDetails() {
    HashMap<String, String> profile = new HashMap<>();
    profile.put("name", pref.getString(KEY_NAME, null));
    profile.put("mobile", pref.getString(KEY_MOBILE, null));
    profile.put("imei", pref.getString(KEY_IMEI, null));
    profile.put("email", pref.getString(KEY_EMAIL, null));
    return profile;
  }
}
