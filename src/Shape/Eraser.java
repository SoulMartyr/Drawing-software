package Shape;

import java.util.Vector;

public class Eraser extends Brush{
    public Eraser(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Eraser(Vector<Vertex> vertices, int verticesNum) {
        super(vertices, verticesNum);
    }
    @Override
    public Shape2D cloneShape2D() {
        return new Eraser(_vertices, _verticesNum);
    }
}
