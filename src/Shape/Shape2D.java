package Shape;

import java.awt.geom.GeneralPath;

public class Shape2D implements Cloneable {
    protected Vertex[] _vertices;
    protected int _verticesNum;

    public Shape2D() {
        this(null, 0);
    }

    public Shape2D(Vertex[] vertices) {
        this(vertices.clone(), 0);
    }

    public Shape2D(Vertex[] vertices, int verticesNum) {
        _vertices = vertices.clone();
        _verticesNum = verticesNum;
    }

    public GeneralPath generatePath() {
        return null;
    }

    public void SetVertex(int index, int x, int y) {
        _verticesNum += 1;
        _vertices[index] = new Vertex(x, y);
    }

    public void ChangeVertex(int index, int x, int y) {
        _vertices[index] = new Vertex(x, y);
    }

    public int GetVerticesNum() {
        return _verticesNum;
    }

    public Shape2D cloneShape2D() {
        return new Shape2D(_vertices, _verticesNum);
    }

    public void clear() {
        _vertices = new Vertex[_verticesNum];
        _verticesNum = 0;
    }

}
