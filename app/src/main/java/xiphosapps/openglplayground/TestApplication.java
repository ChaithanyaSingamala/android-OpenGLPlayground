package xiphosapps.openglplayground;

public interface TestApplication {

    boolean init();
    boolean update();
    boolean render();

    boolean onViewportChanged(int width, int height);
}
