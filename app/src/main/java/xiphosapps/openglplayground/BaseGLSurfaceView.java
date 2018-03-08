package xiphosapps.openglplayground;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class BaseGLSurfaceView extends GLSurfaceView {

    private BaseRenderer baseRenderer;

    public BaseGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(3);
     }

    public void setBaseRenderer(BaseRenderer renderer){
        setRenderer(renderer);
        this.baseRenderer = renderer;
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - previousX;
                float dy = y - previousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }
                baseRenderer.onTouchMovement(dx, dy);

            //    mRenderer.setAngle(
             //           mRenderer.getAngle() +
             //                   ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();
        }

        previousX = x;
        previousY = y;
        return true;
    }

}
