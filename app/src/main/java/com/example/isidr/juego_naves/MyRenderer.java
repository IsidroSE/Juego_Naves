package com.example.isidr.juego_naves;

/*Created by Isidro on 25/02/2016.*/

import android.opengl.GLSurfaceView;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {

    //Dimensions de la pantalla
    static float xEsq, xDre, yBaix, yDalt;

    //Barra horizontal
    Xwall xwall;

    //Barra vertical
    Ywall ywall;

    //Letras
    Letra i, s, d, r, o;

    //El cañón
    Canyon canyon;
    float angle;

    //La bala
    Bala bala;
    float angleRadians, xBala, yBala, velocitat;
    boolean disparo;

    //Meteorito
    Meteorito meteorito;
    int mCaidos;
    float yMeteorito, xMeteorito;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        xEsq = -1;
        xDre = 15;
        yBaix = -1.5f;
        yDalt = 20;

        //Barra horizontal
        xwall = new Xwall();

        //Barra vertical
        ywall = new Ywall();

        //Letras que pintaremos
        i = new Letra('I');
        s = new Letra('S');
        d = new Letra('D');
        r = new Letra('R');
        o = new Letra('O');

        //El cañón que disparará a los meteoritos
        canyon = new Canyon();
        angle = 45;

        //La bala
        bala = new Bala();
        xBala = 1;
        yBala = 1;
        velocitat = 0.15f;
        disparo = false;

        //Meteorito
        meteorito = new Meteorito();
        mCaidos = 0;
        yMeteorito = 1;
        xMeteorito = 8;

        //Limpia la pantalla, dejandola toda de color negro
        gl.glClearColor(0, 0, 1, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //Establece las nuevas coordenadas del sistema
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        //Le asignamos las medidas a la pantalla
        gl.glOrthof(xEsq, xDre, yBaix, yDalt, 1, -1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //Pinta la pared horizontal
        gl.glLoadIdentity();
        gl.glColor4f(0, 0, 0, 0);
        xwall.draw(gl);

        //Pinta la pared vertical
        gl.glLoadIdentity();
        gl.glColor4f(0, 0, 0, 0);
        ywall.draw(gl);

        //Pinta las letras
        //I
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(2, 10, 0);
        i.draw(gl);
        //S
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(5, 10, 0);
        s.draw(gl);
        //I
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(8, 10, 0);
        i.draw(gl);
        //D
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(11, 10, 0);
        d.draw(gl);
        //R
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(14, 10, 0);
        r.draw(gl);
        //O
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0);
        gl.glTranslatef(17, 10, 0);
        o.draw(gl);
        // End Pinta las letras

        //Pinta el cañón
        gl.glLoadIdentity();
        gl.glColor4f(1, 0, 1, 0);
        gl.glRotatef(angle, 0, 0, 1);
        canyon.draw(gl);

        //Pinta la bala (si has disparat)
        if (disparo) {
            gl.glLoadIdentity();
            gl.glColor4f(1, 1, 1, 0);
            gl.glTranslatef(xBala, yBala, 0);
            gl.glScalef(0.5f, 0.5f, 0);
            xBala += Math.cos(angleRadians) * velocitat;
            yBala += Math.sin(angleRadians) * velocitat;
            bala.draw(gl);
            if (yBala > yDalt) disparo = false;
        }

        //Pinta el meteorito
        gl.glLoadIdentity();
        gl.glColor4f(0, 1, 0, 0);
        gl.glTranslatef(xMeteorito, yMeteorito, 0);

        if (yMeteorito < yBaix){
            yMeteorito = yDalt;
            xMeteorito = nuevoMeteorito();
        }
        else if((xBala + 0.5 > xMeteorito - 1 && xBala + 0.5 < xMeteorito + 1) &&
                (yBala + 0.5 > yMeteorito - 1 && yBala + 0.5 < yMeteorito + 1)
                || (xBala - 0.5 > xMeteorito - 1 && xBala - 0.5 < xMeteorito + 1)
                && (yBala - 0.5 > yMeteorito - 1 && yBala - 0.5 < yMeteorito + 1)){

            yMeteorito = yDalt;
            xMeteorito = nuevoMeteorito();
            mCaidos++;
        }
        else {
            yMeteorito -= 0.051;
        }
        meteorito.draw(gl);

    }

    //Provoca que el cañón cambie de ángulo y que con ello pueda apuntar a otra dirección
    public void rotarCanyon(float angle) {
        this.angle = angle;
    }

    //Hace que el cañón dispare
    public void dispara(float angleRad) {
        xBala = 1;
        yBala = 1;
        angleRadians = angleRad;
        disparo = true;
    }

    //Genera una nueva posición X para el meteorito
    public float nuevoMeteorito () {
        float ubicacion = 0;

        Random r = new Random();
        ubicacion = (r.nextFloat() * xDre - xEsq) + xEsq;

        return ubicacion;
    }
}
