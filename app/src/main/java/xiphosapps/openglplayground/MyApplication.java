package xiphosapps.openglplayground;

import android.app.Application;

/**
 * Created by SCHAITHA on 3/2/2018.
 */

public class MyApplication extends Application {

    public static boolean debug = true;
    public static String TAG = "GLPlayGround/";

    public static MyApplication app;

    int testCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }
}
