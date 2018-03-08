package xiphosapps.openglplayground;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Test1 extends BaseRenderer{

    private final int FLOAT_SIZE    = 4; //float size in bytes=4
    private final int POS_DATA_SIZE = 3;
    private final int VERTEX_DATA_STRIDE = (POS_DATA_SIZE) * FLOAT_SIZE;
    private final int LOC_POS = 1;

    private final FloatBuffer vertexData;
    private int shaderProgramHandle;

    public Test1() {

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
        return "1.Simple Triangle";
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);

        GLES30.glClearColor(0.7f, 1.0f, 0.7f, 1.0f);

        shaderProgramHandle = Helper.LoadShaderFromAsset("simple_shader");
        GLES30.glUseProgram(shaderProgramHandle);

        GLES30.glEnableVertexAttribArray(LOC_POS);
        vertexData.position(0);
        GLES30.glVertexAttribPointer(LOC_POS, POS_DATA_SIZE, GLES30.GL_FLOAT, false, VERTEX_DATA_STRIDE, vertexData);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);



        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);
    }
}