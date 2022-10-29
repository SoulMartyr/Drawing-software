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
            case Rectangle -> {
                return new Rectangle(vertices);
            }
            case Circle -> {
                return new Circle(vertices);
            }
            case RoundedRectangle -> {
                return new RoundedRectangle(vertices);
            }
            case Curve -> {
                return new QuadCurve(vertices);
            }
            case Polygon -> {
                return new Polygon(vertices);
            }
            case Brush -> {
                return new Brush(vertices);
            }
        }
        return null;
    }

    public static void PressedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.AddVertex(x, y);
                shape2D.AddVertex(x, y);
            }
            case Triangle, Curve -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.AddVertex(x, y);
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() != 0) {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.AddVertex(x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(x, y);
                    shape2D.AddVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    if (shape2D.GetVerticesNum() == 2) {
                        shape2D.AddVertex(x, y);
                        shape2D.AddVertex(x, y);
                    } else {
                        shape2D.AddVertex(x, y);
                    }
                }
            }
            case Polygon -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(x, y);
                }
                shape2D.AddVertex(x, y);
            }
            case Brush -> {
                shape2D.AddVertex(x, y);
            }
        }
    }

    public static void ReleasedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.ChangeVertex(1, x, y);
                shape2DVec.add(shape2D.cloneShape2D());
                shape2D.clear();
            }
            case Triangle, Curve -> {
                if (shape2D.GetVerticesNum() == 2) {
                    shape2D.ChangeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.GetVerticesNum() == 3) {
                    shape2D.ChangeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 2) {
                    shape2D.ChangeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.GetVerticesNum() == 4) {
                    shape2D.ChangeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case RoundedRectangle -> {
                if (shape2D.GetVerticesNum() == 2) {
                    shape2D.ChangeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.GetVerticesNum() == 4) {
                    shape2D.ChangeVertex(2, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.GetVerticesNum() == 5) {
                    shape2D.ChangeVertex(4, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Polygon -> {
                shape2D.ChangeVertex(shape2D.GetVerticesNum() - 1, x, y);
                if (shape2D.GetVerticesNum() == Function.Polygon.getVerticesNum()) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Brush -> {
                shape2D.ChangeVertex(shape2D.GetVerticesNum() - 1, x, y);
                shape2DVec.add(shape2D.cloneShape2D());
                shape2D.clear();
            }
        }

    }


    public static void DraggedSwitch(Shape2D shape2D, Function func, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.ChangeVertex(1, x, y);
            }
            case Triangle, Curve -> {
                if (shape2D.GetVerticesNum() == 2)
                    shape2D.ChangeVertex(1, x, y);
                if (shape2D.GetVerticesNum() == 3)
                    shape2D.ChangeVertex(2, x, y);
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 2)
                    shape2D.ChangeVertex(1, x, y);
                if (shape2D.GetVerticesNum() == 4)
                    shape2D.ChangeVertex(2, x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.GetVerticesNum() == 2)
                    shape2D.ChangeVertex(1, x, y);
                if (shape2D.GetVerticesNum() == 4)
                    shape2D.ChangeVertex(2, x, y);
                if (shape2D.GetVerticesNum() == 5)
                    shape2D.ChangeVertex(4, x, y);
            }
            case Polygon -> {
                shape2D.ChangeVertex(shape2D.GetVerticesNum() - 1, x, y);
            }
            case Brush -> {
                shape2D.AddVertex(x, y);
            }
        }
    }
}
