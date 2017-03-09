package com.example.isidr.juego_naves;

/*Created by Isidro on 25/02/2016.*/

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Letra {

    //Número de puntos a pintar
    private int numIndices;
    //Variable que, pasados los puntos a pintar, se utilizará para pintarlos en la pantalla
    private ShortBuffer indexBuffer = null;
    //Variable que se utilizará para asignar un buffer con las opciones de pintado
    FloatBuffer vertexBuffer = null;
    //Variable que señalará el ordén en el que se pintaran los puntos
    private short index[];

    //Guarda el tamaño del array de vertices en bytes
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(9 * 2 * 4);

    //Variable donde se guardarán los puntos a pintar
    private float vertexList[] = {
            //0     //1     //2
            -1,1,   0,1,    1,1,
            //3     //4     //5     //Lista de puntos
            -1,0,   0,0,    1,0,
            //6     //7     //8
            -1,-1,   0,-1,  1,-1

    };

    public Letra (char lletra) {

        //Aquí pondremos el órden de los puntos para formar una letra
        switch(lletra){
            case 'A': {
                this.index = new short[]{6, 0, 2, 8, 5, 3};
                break;
            }
            case 'B': {

                break;
            }
            case 'C': {
                this.index = new short[]{2, 0, 6, 8};
                break;
            }
            case 'D': {
                this.index = new short[]{6, 0, 1, 5, 7, 6};
                break;
            }
            case 'E': {
                this.index = new short[]{2, 0, 3, 4,3, 6, 8};
                break;
            }
            case 'F': {

                break;
            }
            case 'G': {
                this.index = new short[]{2, 0, 6, 8, 5,4};
                break;
            }
            case 'H': {

                break;
            }
            case 'I': {
                this.index = new short[]{0, 2, 1, 7, 6, 8};
                break;
            }
            case 'J': {

                break;
            }
            case 'K':{
                this.index = new short[]{0, 3, 2, 3,6, 3,8};
                break;
            }
            case 'L': {

                break;
            }
            case 'M': {

                break;
            }
            case 'N': {

                break;
            }
            case 'O': {
                this.index = new short[]{0, 2, 8, 6, 0};
                break;
            }
            case 'P': {

                break;
            }
            case 'R': {
                this.index = new short[]{6, 0, 2, 5, 3, 8};
                break;
            }
            case 'S': {
                this.index = new short[]{2, 0, 3, 5, 8, 6};
                break;
            }
            case 'T': {

                break;
            }
            case 'U': {

                break;
            }
            case 'V': {

                break;
            }
            case 'X': {

                break;
            }
            case 'Y': {

                break;
            }
            case 'Z': {

                break;
            }
        }

        //PUNTOS A PINTAR
        //Para que el buffer utilize la órden estándar, es decir, de 0 hasta el final
        byteBuffer.order(ByteOrder.nativeOrder());
        //Le asignamos las opciones al vertexBuffer pasándole el buffer de floats anterior
        vertexBuffer = byteBuffer.asFloatBuffer();
        //Ponemos los puntos a pintar
        vertexBuffer.put(vertexList);
        //Y le decimos que comience desde la posición 0
        vertexBuffer.position(0);


        //ASIGNAR EL ÓRDEN EN EL QUE SE PINTARAN LOS PUNTOS
        //Sacamos el número de indices a pintar con el length
        numIndices = this.index.length;

        //Le pasamos al buffer el número de puntos a pintar multiplicado por el número de dimensiones
        ByteBuffer elByteBuffer = ByteBuffer.allocateDirect(numIndices*2);
        //Para que el buffer utilize la órden estándar, es decir, de 0 hasta el final
        elByteBuffer.order(ByteOrder.nativeOrder());

        //Le asignamos las opciones al indexBuffer diciéndole el tamaño y el tipo de dato que va a recibir (array de shorts)
        indexBuffer = elByteBuffer.asShortBuffer();
        //Le pasamos el index con el órden de pintado
        indexBuffer.put(index);
        //Y le decimos que empieze en la posición 0
        indexBuffer.position(0);
    }

    public void draw(GL10 gl){

        /*Asignamos el buffer con las opciones especificadas anteriormente
        (2 dimensiones, float, empieza en 0 y el buffer de vertex*/
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, this.vertexBuffer);

        //Ancho de la linia
        gl.glLineWidth(5);

        /*Pintamos los elementos utilizando una linea que va de un punto a otro, le asignamos la cantidad de puntos a pintar,
        * el tipo de dato en el que se guarda el órden de puntos a pintar y el el index definido anteriormente*/
        gl.glDrawElements(GL10.GL_LINE_STRIP, this.numIndices, GL10.GL_UNSIGNED_SHORT, this.indexBuffer);
    }

}
