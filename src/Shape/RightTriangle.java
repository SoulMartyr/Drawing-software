package Shape;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class RightTriangle extends Shape2D {
    public RightTriangle(Vector<Vertex> vertices) {
        super(vertices);
    }

    public RightTriangle(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path = new Line(_vertices, _verticesNum).generatePath();
        }
        if (_verticesNum == 3) {
            _vertices.set(2, calThirdVerTex());
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.lineTo(_vertices.get(1).getX(), _vertices.get(1).getY());
            path.lineTo(_vertices.get(2).getX(), _vertices.get(2).getY());
            path.closePath();
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new RightTriangle(_vertices, _verticesNum, _lineWidth, _color);
    }

    private Vertex calThirdVerTex() {
        int x1 = _vertices.get(0).getX(), y1 = _vertices.get(0).getY();
        int x2 = _vertices.get(1).getX(), y2 = _vertices.get(1).getY();
        int x3 = _vertices.get(2).getX();
        int y3 = (int) ((x3 - x2) * (x1 - x2) / (y2 - y1) + y2);
        return new Vertex(x3, y3);
    }
}
