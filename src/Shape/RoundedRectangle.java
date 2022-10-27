package Shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.GeneralPath;
import java.util.Arrays;

public class RoundedRectangle extends Shape2D {
    public RoundedRectangle(Vertex[] vertices) {
        super(vertices);
    }

    public RoundedRectangle(Vertex[] vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path = new Line(_vertices, _verticesNum).generatePath();
        } else if (_verticesNum == 4) {
            path = new Rectangle(_vertices, _verticesNum).generatePath();
        } else if (_verticesNum == 5) {
            _vertices[2] = calThirdVerTex();
            _vertices[3] = calForthVerTex();

            RoundRectangle2D roundRectangle = new RoundRectangle2D.Double();

            double r = Vertex.calDistance(_vertices[2], _vertices[4]);

            Vertex[] vertices = sortVertices();

            double w = Vertex.calDistance(vertices[1], vertices[2]);
            double h = Vertex.calDistance(vertices[0], vertices[1]);

            roundRectangle.setRoundRect(vertices[0].getX(), vertices[0].getY(), w, h, r, r);
            path = new GeneralPath(roundRectangle);
            double theta = Math.atan((double) (vertices[3].getY() - vertices[0].getY()) / (vertices[3].getX() - vertices[0].getX()));
            System.out.println(theta);
            path.transform(AffineTransform.getRotateInstance(theta, vertices[0].getX(), vertices[0].getY()));
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new RoundedRectangle(_vertices, _verticesNum);
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

    private Vertex[] sortVertices() {
        int CenterX = (_vertices[0].getX() + _vertices[2].getX()) / 2;
        int CenterY = (_vertices[0].getY() + _vertices[2].getY()) / 2;
        int LUIdx = 0;
        for (int i = 0; i < 4; i++) {
            if (_vertices[i].getX() < CenterX && _vertices[i].getY() < CenterY) {
                LUIdx = i;
                break;
            }
        }
        int nextIdx = (LUIdx + 1) % 4;
        int det = (_vertices[LUIdx].getX()-CenterX) * (_vertices[nextIdx].getY()-CenterY) - (_vertices[nextIdx].getX() -CenterX)* (_vertices[LUIdx].getY()-CenterY);
        Vertex[] vertices = new Vertex[5];
        if (det < 0) {
            for (int i = 0; i < 4; i++) {
                vertices[i] = new Vertex(_vertices[(LUIdx + i) % 4]);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                vertices[i] = new Vertex(_vertices[(LUIdx + 4 - i) % 4]);
            }
        }
        return vertices;
    }
}
