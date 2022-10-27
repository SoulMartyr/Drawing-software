package Shape;

public class Vertex {
    private int _x, _y;

    public Vertex() {
        _x = 0;
        _y = 0;
    }

    public Vertex(int x, int y) {
        _x = x;
        _y = y;
    }

    public Vertex(Vertex v) {
        _x = v.getX();
        _y = v.getY();
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

    public static double calDistance(Vertex v1, Vertex v2) {
        return Math.sqrt((v1._x - v2._x) * (v1._x - v2._x) + (v1._y - v2._y) * (v1._y - v2._y));
    }

    //向量操作
    public static Vertex calVector(Vertex v1, Vertex v2) {
        return new Vertex(v1._x - v2._x, v1._y - v2._y);
    }

    public static double calAngle(Vertex v1, Vertex v2) {
        double value = (v1._x * v2._x + v1._y * v2._y) / (Math.sqrt(v1._x * v1._x + v1._y * v1._y) * Math.sqrt(v2._x * v2._x + v2._y * v2._y)); // 余弦值
        return Math.acos(value);
    }

}
