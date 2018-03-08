package xiphosapps.openglplayground;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OpenGLESActivity extends AppCompatActivity {

    private BaseGLSurfaceView glSurfaceView;
    private BaseRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_opengles);

        RelativeLayout canvas = findViewById(R.id.canvas);

        renderer = new Test2();

        glSurfaceView = new BaseGLSurfaceView(this);
        glSurfaceView.setBaseRenderer(renderer);
        canvas.addView(glSurfaceView);

        TextView txtGroundTitle = (TextView)findViewById(R.id.txtGroundTitle);
        txtGroundTitle.setText(renderer.getTitle());
    }
}
