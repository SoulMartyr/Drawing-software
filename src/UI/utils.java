package UI;

import Shape.*;

import java.util.Vector;

public class utils {
    public static Shape2D ActionSwitch(Vector<Vertex> vertices, Function func) {
        switch (func) {
            case Line -> {
                return new Line(vertices);
            }
            case Triangle -> {
                return new Triangle(vertices);
            }
            case RightTriangle -> {
                return new RightTriangle(vertices);
            }
            case Rectangle -> {
                return new Rectangle(vertices);
            }
            case Circle -> {
                return new Circle(vertices);
            }
            case RoundedRectangle -> {
                return new RoundedRectangle(vertices);
            }
            case Quad -> {
                return new Quad(vertices);
            }
            case Polygon -> {
                return new Polygon(vertices, Function.Polygon.getVerticesNum());
            }
            case Brush -> {
                return new Brush(vertices);
            }
            case Eraser -> {
                return new Eraser(vertices);
            }
        }
        return null;
    }

    public static void PressedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.addVertex(x, y);
                shape2D.addVertex(x, y);
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.addVertex(x, y);
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() != 0) {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.addVertex(x, y);
                shape2D.addVertex(x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                    shape2D.addVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    if (shape2D.getVerticesNum() == 2) {
                        shape2D.addVertex(x, y);
                        shape2D.addVertex(x, y);
                    } else {
                        shape2D.addVertex(x, y);
                    }
                }
            }
            case Polygon -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                }
                shape2D.addVertex(x, y);
            }
            case Brush, Eraser -> {
                shape2D.addVertex(x, y);
            }
        }
    }

    public static void ReleasedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle, Brush, Eraser -> {
                //shape2D.changeVertex(1, x, y);
                shape2DVec.add(shape2D.cloneShape2D());
                shape2D.clear();
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 2) {
                    //shape2D.changeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 3) {
                    //shape2D.changeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() == 2) {
                    //shape2D.changeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 4) {
                    //shape2D.changeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 2) {
                    // shape2D.changeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 4) {
                    // shape2D.changeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 5) {
                    //shape2D.changeVertex(4, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Polygon -> {
                //shape2D.changeVertex(shape2D.getVerticesNum() - 1, x, y);
                if (shape2D.getVerticesNum() == Function.Polygon.getVerticesNum()) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
        }

    }


    public static void DraggedSwitch(Shape2D shape2D, Function func, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.changeVertex(1, x, y);
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 3)
                    shape2D.changeVertex(2, x, y);
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 4)
                    shape2D.changeVertex(2, x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 4)
                    shape2D.changeVertex(2, x, y);
                if (shape2D.getVerticesNum() == 5)
                    shape2D.changeVertex(4, x, y);
            }
            case Polygon -> {
                shape2D.changeVertex(shape2D.getVerticesNum() - 1, x, y);
            }
            case Brush, Eraser -> {
                shape2D.addVertex(x, y);
            }
        }
    }
}
