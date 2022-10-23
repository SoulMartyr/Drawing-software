package UI;

public enum Function {
    Eraser, Brush, Line, Curve, Triangle, Rectangle,
    RoundedRectangle, Circle, Polygon, ColorChooser, ColorViewer;

    public static int getVertexNum(Function function) {
        return switch (function) {
            case Brush, Eraser -> 1;
            case Line, Curve, Circle -> 2;
            case Triangle -> 3;
            case Rectangle, RoundedRectangle -> 4;
            case Polygon -> 5;
            default -> 0;
        };
    }

}
