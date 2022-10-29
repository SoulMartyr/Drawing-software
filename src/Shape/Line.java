package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Line extends Shape2D {
    public Line(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Line(Vector<Vertex> vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    public Line(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.lineTo(_vertices.get(1).getX(), _vertices.get(1).getY());
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Line(_vertices, _verticesNum,_lineWidth,_color);
    }


}
