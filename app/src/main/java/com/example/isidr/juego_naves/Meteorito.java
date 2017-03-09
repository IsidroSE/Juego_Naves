package com.example.isidr.juego_naves;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*Created by Isidro on 26/02/2016.*/

public class Meteorito {

    private FloatBuffer vertexBuffer = null;

    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 2 * 4);

    private float vertexList[] = {
            -1, 1,
            1,1,
            1,-1,
            -1,-1
    };

    public Meteorito(){
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(vertexList);
        vertexBuffer.position(0);
    }


    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, this.vertexBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
    }

}
