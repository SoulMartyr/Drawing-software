package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Rectangle extends Shape2D {
    public Rectangle(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Rectangle(Vector<Vertex> vertices, int verticesNum) {
        super(vertices, verticesNum);
    }
    public Rectangle(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
    }
    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path = new Line(_vertices, _verticesNum).generatePath();
        }
        if (_verticesNum == 4) {
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.lineTo(_vertices.get(1).getX(), _vertices.get(1).getY());

            _vertices.set(2, calThirdVerTex());
            path.lineTo(_vertices.get(2).getX(), _vertices.get(2).getY());

            _vertices.set(3, calForthVerTex());
            path.lineTo(_vertices.get(3).getX(), _vertices.get(3).getY());
            path.closePath();
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Rectangle(_vertices, _verticesNum, _lineWidth, _color);
    }


    protected Vertex calThirdVerTex() {
        int x1 = _vertices.get(0).getX(), y1 = _vertices.get(0).getY();
        int x2 = _vertices.get(1).getX(), y2 = _vertices.get(1).getY();
        int x3 = _vertices.get(2).getX();
        int y3 = (int) ((x3 - x2) * (x1 - x2) / (y2 - y1) + y2);
        return new Vertex(x3, y3);
    }


    protected Vertex calForthVerTex() {
        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                x -= _vertices.get(i).getX();
                y -= _vertices.get(i).getY();
            } else {
                x += _vertices.get(i).getX();
                y += _vertices.get(i).getY();
            }

        }
        return new Vertex(x, y);
    }


}
