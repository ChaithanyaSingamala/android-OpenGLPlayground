package xiphosapps.openglplayground;

import android.opengl.GLES30;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Test2 extends BaseRenderer{

    private final int FLOAT_SIZE    = 4; //float size in bytes=4
    private final int POS_DATA_SIZE = 3;
    private final int VERTEX_DATA_STRIDE = (POS_DATA_SIZE) * FLOAT_SIZE;
    private final int LOC_POS = 0;
    private final int LOC_UNIFORM_COLOR = 0;


    private final FloatBuffer vertexData;
    private int shaderProgramHandle;

    private float[] color = {1.0f,1.0f,0.0f,1.0f};

    public Test2() {

        float z_value = 0f;

        final float[] vertexDataA = {
                +0.0f, +1.0f, z_value,
                +1.0f, -1.0f, z_value,
                -1.0f, -1.0f, z_value,
        };

        vertexData = ByteBuffer.allocateDirect(vertexDataA.length * FLOAT_SIZE)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertexDataA).position(0);
    }

    @Override
    public String getTitle() {
        return "2.Simple color Triangle";
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        GLES30.glClearColor(0.7f, 1.0f, 0.7f, 1.0f);

        shaderProgramHandle = Helper.LoadShaderFromAsset("simple_color_shader");
        GLES30.glUseProgram(shaderProgramHandle);

        GLES30.glEnableVertexAttribArray(LOC_POS);
        vertexData.position(0);
        GLES30.glVertexAttribPointer(LOC_POS, POS_DATA_SIZE, GLES30.GL_FLOAT, false, VERTEX_DATA_STRIDE, vertexData);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);


        GLES30.glUniform4fv(LOC_UNIFORM_COLOR, 1, color, 0);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);
    }

    @Override
    public void onTouchMovement(float dx, float dy) {
        super.onTouchMovement(dx, dy);

        color[0] += dx;
        color[1] += dy;

        Log.d("test/debug", " " + dx + " " + dy + "  " + color[0] + "   " + color[1]);

        if(color[0] > 1.0)
            color[0] = 1.0f;
        if(color[0] < 0.0)
            color[0] = 0.0f;

        if(color[1] > 1.0)
            color[1] = 1.0f;
        if(color[1] < 0.0)
            color[1] = 0.0f;
    }
}