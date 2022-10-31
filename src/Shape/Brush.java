package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Brush extends Shape2D {
    public Brush(Vector<Vertex> vertices) {
        super(vertices);
    }


    public Brush(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor) {
        super(vertices, verticesNum, lineWidth, drawColor);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
        for (int i = 1; i < _verticesNum; i++) {
            path.lineTo(_vertices.get(i).getX(), _vertices.get(i).getY());
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Brush(_vertices, _verticesNum, _lineWidth, _drawColor);
    }
}
