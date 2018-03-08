package xiphosapps.openglplayground;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BaseRenderer implements GLSurfaceView.Renderer{
    protected int width;
    protected int height;
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        width = w;
        height = h;
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);

    }

    public String getTitle(){
        return "Using Base renderer";
    }
}
