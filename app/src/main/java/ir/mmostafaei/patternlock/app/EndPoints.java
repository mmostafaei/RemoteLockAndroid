package ir.mmostafaei.patternlock.app;

/**
 * Created by mohsen on 5/13/2016.
 */

public class EndPoints {


  public static String ip = new PrefManager(MyApplication.currentActivity).getIp();

  // online host
  // public static String ip = "94.183.246.21";


  /***
   * base webservice
   **/

//  public static final String BASE_URL_GCM = "http://" + ip + ":8888/gcmp/";
//  public static final String BASE_URL_WEBSERVICE = ip + "Parsian_webservices/Methods.asmx/";



  /***
   * ONet's
   **/

  public static final String LOGIN_ONET="login";
  public static final String RESET_PASSWORD="sms:reset_password";
  public static final String ACTIVIATE_ACCUNT="user:send_activate_code";
  public static final String FORGET_PASSWORD_TOKEN_ONET="send:forget_password_token";





  /**
   * GCM sender Id
   */

  public static final String SENDER_ID = "296572575008";

  /***************
   * google API
   */

  public static final String GOOGLE_API_GEOCODE_JSON = "http://maps.google.com/maps/api/geocode/json?";
  public static final String GOOGLE_API_PLACES_JSON = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
  public static final String GOOGLE_API_PLACES_DETAILS_JSON = "https://maps.googleapis.com/maps/api/place/details/json?";
  public static final String PLACES_API_KEY = "AIzaSyB9ns8fTuO5KjCV4uSmx6S5rtc08YsBQPE";
}
