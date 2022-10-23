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

    public boolean isAssigned() {
        return _x >= 0 && _y >= 0;
    }

}
