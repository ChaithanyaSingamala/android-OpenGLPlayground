package xiphosapps.openglplayground;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TestRenderer implements GLSurfaceView.Renderer {

    TestApplication test;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        test = new Test1();
        test.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(100, 100, width-100, height-100);

        Log.d("test/debug", "w=" +width + "h="+height);


    }

    @Override
    public void onDrawFrame(GL10 gl) {

        test.update();
        test.render();
    }
}
