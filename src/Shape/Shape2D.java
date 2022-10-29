package Shape;

import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Shape2D  {
    protected Vector<Vertex> _vertices;
    protected int _verticesNum;

    public Shape2D() {
        this(null, 0);
    }

    public Shape2D(Vector<Vertex> vertices) {
        this(vertices, 0);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum) {
        _vertices = new Vector<>(vertices);
        _verticesNum = verticesNum;
    }

    public GeneralPath generatePath() {
        return null;
    }

    public void AddVertex(int x, int y) {
        _verticesNum += 1;
        _vertices.add(new Vertex(x, y));
    }

    public void ChangeVertex(int index, int x, int y) {
        _vertices.set(index,new Vertex(x, y));
    }

    public int GetVerticesNum() {
        return _verticesNum;
    }

    public Shape2D cloneShape2D() {
        return new Shape2D(_vertices, _verticesNum);
    }

    public void clear() {
        _vertices = new Vector<>(_verticesNum);
        _verticesNum = 0;
    }

}
