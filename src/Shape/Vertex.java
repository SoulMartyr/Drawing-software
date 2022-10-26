package Shape;

public class Vertex {
    int _x, _y;

    public Vertex() {
        _x = 0;
        _y = 0;
    }

    public Vertex(int x, int y) {
        _x = x;
        _y = y;
    }

    public void SetX(int x) {
        _x = x;
    }

    public void SetY(int y) {
        _y = y;
    }

    public void SetXY(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int calDistance2(Vertex v1, Vertex v2) {
        return (v1._x - v2._x) * (v1._x - v2._x) + (v1._y - v2._y) * (v1._y - v2._y);
    }

}
