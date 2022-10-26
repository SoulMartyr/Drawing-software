package Shape;

import java.awt.geom.GeneralPath;

public class Rectangle extends Shape2D {
    public Rectangle(Vertex[] vertices) {
        super(vertices);
    }

    public Rectangle(Vertex[] vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices[0].getX(), _vertices[0].getY());
            path.lineTo(_vertices[1].getX(), _vertices[1].getY());
        }
        if (_verticesNum == 4) {
            path.moveTo(_vertices[0].getX(), _vertices[0].getY());
            path.lineTo(_vertices[1].getX(), _vertices[1].getY());

            _vertices[2] = calThirdVerTex();
            path.lineTo(_vertices[2].getX(), _vertices[2].getY());

            _vertices[3] = calForthVerTex();
            path.lineTo(_vertices[3].getX(), _vertices[3].getY());
            path.closePath();
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Rectangle(_vertices, _verticesNum);
    }

    private Vertex calThirdVerTex() {
        int x1 = _vertices[0].getX(), y1 = _vertices[0].getY();
        int x2 = _vertices[1].getX(), y2 = _vertices[1].getY();
        int x3 = _vertices[2].getX();
        int y3 = (int) ((x3 - x2) * (x1 - x2) / (y2 - y1) + y2);
        return new Vertex(x3, y3);
    }


    private Vertex calForthVerTex() {
        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                x -= _vertices[i].getX();
                y -= _vertices[i].getY();
            } else {
                x += _vertices[i].getX();
                y += _vertices[i].getY();
            }

        }
        return new Vertex(x, y);
    }


}
