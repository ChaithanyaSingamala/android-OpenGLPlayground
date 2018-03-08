package xiphosapps.openglplayground;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpenGLESActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;
    private TestRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderer = new TestRenderer();

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(3);
        glSurfaceView.setRenderer(renderer);

        setContentView(glSurfaceView);
    }
}
