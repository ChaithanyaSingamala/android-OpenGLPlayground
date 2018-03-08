package xiphosapps.openglplayground;

import android.opengl.GLES30;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static xiphosapps.openglplayground.MyApplication.debug;

public class Test1 implements TestApplication {

    private static final String TAG = MyApplication.TAG + Test1.class.getSimpleName();

    private float[] cameraMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    private float[] modelMatrix = new float[16];

    String fragShaderStr;
    String vertShaderStr;

    int shaderProgram;
    int mvpUniform;

    private FloatBuffer vertices;

    @Override
    public boolean init() {
        if(debug) Log.d(TAG, "init");

        GLES30.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

        setupCamera();

        GLES30.glBindBuffer ( GLES30.GL_ARRAY_BUFFER, 0 );
        GLES30.glBindBuffer ( GLES30.GL_ELEMENT_ARRAY_BUFFER, 0 );

        shaderProgram = Helper.LoadShaderFromAsset("simple_shader");
        GLES30.glUseProgram(shaderProgram);

     //   mvpUniform = GLES30.glGetUniformLocation(shaderProgram, "mvp");

        float z_value = 0.5f;

        final float[] vertexArray = {

                +0.0f, +1.0f, z_value,
                +1.0f, -1.0f, z_value,
                -1.0f, -1.0f, z_value,
        };

        vertices = ByteBuffer.allocateDirect ( vertexArray.length * 4 )
                .order ( ByteOrder.nativeOrder() ).asFloatBuffer();
        vertices.put ( vertexArray ).position ( 0 );

        int [] bufferId = new int[1];
        GLES30.glGenBuffers(1, bufferId, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, bufferId[0]);

       // GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, vertexArray.length, vertices, GLES30.GL_STATIC_DRAW);

        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 4 * 3, vertices);


        return true;
    }

    void setupCamera(){
        Matrix.setIdentityM(cameraMatrix,0);

        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 1.5f;

        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;

        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        Matrix.setLookAtM(cameraMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

    }

    void setupProjection(int width, int height){

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 10.0f;

        Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);

    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean render() {

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);

        MyApplication.app.testCount ++;

        if(MyApplication.app.testCount % 120 == 0){
            GLES30.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
        }else if(MyApplication.app.testCount % 120 == 60){
            GLES30.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        }

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);

        return true;
    }

    @Override
    public boolean onViewportChanged(int width, int height) {
        setupProjection(width,height);
        return true;
    }
}
