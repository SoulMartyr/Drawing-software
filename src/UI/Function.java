package UI;

public enum Function {
    Eraser, Brush, Line, Curve, Triangle, Rectangle,
    RoundedRectangle, Circle, Polygon, ColorChooser, ColorViewer;

    public static int getVertexNum(Function function) {
        return switch (function) {
            case Line, Circle -> 2;
            case Triangle, Curve -> 3;
            case Rectangle -> 4;
            case RoundedRectangle, Polygon -> 5;
            case Brush, Eraser -> 100;
            default -> 0;
        };
    }

}
