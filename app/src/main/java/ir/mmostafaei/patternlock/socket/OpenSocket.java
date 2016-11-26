package ir.mmostafaei.patternlock.socket;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import ir.mmostafaei.patternlock.app.MyApplication;

/***
 * Created by mohsen on 11/26/2016.
 */

public class OpenSocket {

  private static final String TAG = OpenSocket.class.getSimpleName();
  private Listener listener;
  private Handler handler = MyApplication.handler;
  private String dataToSend;
  private String ip = "192.168.3.99";
  private int port = 8899;
  private int timeOut = 1000;

  public interface Listener {
    void onDataRecived(String data);
  }

  public OpenSocket listener(Listener listener) {
    this.listener = listener;
    return this;
  }

  public OpenSocket handler(Handler handler) {
    this.handler = handler;
    return this;
  }

  public OpenSocket dataToSend(String dataToSend) {
    this.dataToSend = dataToSend;
    return this;
  }

  public OpenSocket ip(String ip) {
    this.ip = ip;
    return this;
  }

  public OpenSocket port(int port) {
    this.port = port;
    return this;
  }

  public OpenSocket timeOut(int timeOut) {
    this.timeOut = timeOut;
    return this;
  }

  public void start() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        stabiliseConnection();
      }
    }).start();
  }

//    int port = MyApplication.sharedpreferences.getInt(MyApplication.PORT, 8899);
//    String ip = MyApplication.sharedpreferences.getString(MyApplication.IP, "192.168.4.1");
//    int ipTimeOut = MyApplication.sharedpreferences.getInt(MyApplication.IP_TIMEOUT, 2000);

  private void stabiliseConnection() {
    Socket socket = new Socket();
    try {
      socket.bind(null);
      socket.connect((new InetSocketAddress(ip, port)), timeOut);
      OutputStream stream = socket.getOutputStream();
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      dataToSend = dataToSend + "\r\n";
      stream.write(dataToSend.trim().getBytes());
      if (listener != null) {
        String message = inputStream.readLine();
        onDataRecived(message);
      }
    } catch (final IOException e) {
      Log.e(TAG, "getSocket: error on socket " + e.getMessage());
      onStablishSocketFaild();
    } finally {
      closeSocket(socket);
    }

  }


  private void onDataRecived(final String message) {
    if (message != null) {
      handler.post(new Runnable() {
        @Override
        public void run() {
          listener.onDataRecived(message);
        }
      });
    }
  }

  private void onStablishSocketFaild() {
    handler.post(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MyApplication.context, "مشکلی در ارتباط با سرور به وجود امد لطفا ارتباط گوشی با شبکه را بررسی کنید : ",
          Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void closeSocket(Socket socket) {
    if (socket.isConnected()) {
      try {
        socket.close();
        Log.w(TAG, "getSocket: socket is shuting down now!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
