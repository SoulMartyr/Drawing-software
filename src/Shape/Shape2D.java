package Shape;

import java.awt.geom.GeneralPath;

public class Shape2D {
    protected Vertex[] _vertices;

    public Shape2D() {
        _vertices = new Vertex[1];
        _vertices[0] = new Vertex();
    }

    public Shape2D(Vertex[] vertices) {
        _vertices = vertices.clone();
    }

    public GeneralPath generatePath() {
        return null;
    }

    ;
}
