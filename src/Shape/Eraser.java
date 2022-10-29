package Shape;

import java.awt.Color;
import java.util.Vector;

public class Eraser extends Brush{
    public Eraser(Vector<Vertex> vertices) {
        super(vertices);
    }


    public Eraser(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Eraser(_vertices, _verticesNum, _lineWidth, _color);
    }
}
