package xiphosapps.openglplayground;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES30;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Helper {

    final static String shaderResourcePath = "shaders/";

    public static String readFromAsset(String filename){
        Context context = MyApplication.app.getApplicationContext();
        String output = "";
        AssetManager assetManager = context.getAssets();
        try {
            InputStream input = assetManager.open(filename);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            output= new String(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }

        return output;
    }

    public static int LoadShaderFromAsset(String shaderName){
        String fragShaderStr = readFromAsset(shaderResourcePath+shaderName+".frag");
        String vertShaderStr = readFromAsset(shaderResourcePath+shaderName+".vert");

        // Load in the vertex shader.
        int vertexShaderHandle = GLES30.glCreateShader(GLES30.GL_VERTEX_SHADER);

        if (vertexShaderHandle != 0)
        {
            // Pass in the shader source.
            GLES30.glShaderSource(vertexShaderHandle, vertShaderStr);

            // Compile the shader.
            GLES30.glCompileShader(vertexShaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES30.glGetShaderiv(vertexShaderHandle, GLES30.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0)
            {
                Log.e ( "ESShader", GLES30.glGetShaderInfoLog ( vertexShaderHandle ) );
                GLES30.glDeleteShader(vertexShaderHandle);
                vertexShaderHandle = 0;
            }
        }

        if (vertexShaderHandle == 0)
        {
            throw new RuntimeException("Error creating vertex shader.");
        }

        // Load in the vertex shader.
        int fragmentShaderHandle = GLES30.glCreateShader(GLES30.GL_FRAGMENT_SHADER);

        if (fragmentShaderHandle != 0)
        {
            // Pass in the shader source.
            GLES30.glShaderSource(fragmentShaderHandle, fragShaderStr);

            // Compile the shader.
            GLES30.glCompileShader(fragmentShaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES30.glGetShaderiv(fragmentShaderHandle, GLES30.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0)
            {
                Log.e ( "ESShader", GLES30.glGetShaderInfoLog ( fragmentShaderHandle ) );
                GLES30.glDeleteShader(fragmentShaderHandle);
                fragmentShaderHandle = 0;
            }
        }

        if (fragmentShaderHandle == 0)
        {
            throw new RuntimeException("Error creating vertex shader.");
        }

        // Create a program object and store the handle to it.
        int programHandle = GLES30.glCreateProgram();

        if (programHandle != 0)
        {
            // Bind the vertex shader to the program.
            GLES30.glAttachShader(programHandle, vertexShaderHandle);

            // Bind the fragment shader to the program.
            GLES30.glAttachShader(programHandle, fragmentShaderHandle);

            // Bind attributes
            GLES30.glBindAttribLocation(programHandle, 0, "a_Position");
            GLES30.glBindAttribLocation(programHandle, 1, "a_Color");

            // Link the two shaders together into a program.
            GLES30.glLinkProgram(programHandle);

            // Get the link status.
            final int[] linkStatus = new int[1];
            GLES30.glGetProgramiv(programHandle, GLES30.GL_LINK_STATUS, linkStatus, 0);

            // If the link failed, delete the program.
            if (linkStatus[0] == 0)
            {
                GLES30.glDeleteProgram(programHandle);
                programHandle = 0;
            }
        }

        if (programHandle == 0)
        {
            throw new RuntimeException("Error creating program.");
        }

        return programHandle;
    }
}
