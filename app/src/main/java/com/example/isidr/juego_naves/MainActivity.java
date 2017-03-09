package com.example.isidr.juego_naves;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    MyRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView surface = new GLSurfaceView(this);
        renderer = new MyRenderer();
        surface.setRenderer(renderer);
        setContentView(surface);
    }

    //Este método hará que las figuras del renderer se muevan cuando toques la pantalla
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        float x1, y1;
        float angle, angleRadians;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                //Guarda la posición tocada
                final float x = ev.getX();
                final float y = ev.getY();
                //Transforma el axis del canvas a un axis de OpenGL (invierte la Y)
                x1 = x;
                y1 = 1200 - y;
                //Calcula el nuevo ángulo del cañón
                angle = (float) (Math.atan2(y1, x1) * 180) / (float) Math.PI;
                renderer.rotarCanyon(angle);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();
                x1 = x;
                y1 = 1200 - y;
                angle = (float) (Math.atan2(y1, x1) * 180) / (float) Math.PI;
                renderer.rotarCanyon(angle);
                break;
            }
            case MotionEvent.ACTION_UP: {
                final float x = ev.getX();
                final float y = ev.getY();
                x1 = x;
                y1 = 1200 - y;
                angleRadians = (float) Math.atan2(y1, x1);
                renderer.dispara(angleRadians);
                break;
            }
        }

        return true;
    }
}
