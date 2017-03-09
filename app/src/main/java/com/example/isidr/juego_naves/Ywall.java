package com.example.isidr.juego_naves;

/*Created by Isidro on 25/02/2016.*/

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Ywall {

    private FloatBuffer vertexBuffer = null;

    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2 * 2 * 4);
    private float vertexList[] = {
            0.0f, MyRenderer.yDalt,
            0.0f, MyRenderer.yBaix
    };

    public Ywall() {
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(vertexList);
        vertexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, this.vertexBuffer);
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 2);
    }
}
