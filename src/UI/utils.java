package UI;

import Shape.*;

import java.util.Vector;

public class utils {
    public static Shape2D ActionSwitch(Vector<Vertex> vertices, Function func) {
        System.out.println(vertices.size());
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
        }
        return null;
    }

    public static void PressedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.AddVertex(0, x, y);
                shape2D.AddVertex(1, x, y);
            }
            case Triangle, Curve -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(0, x, y);
                    shape2D.AddVertex(1, x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    shape2D.AddVertex(2, x, y);
                }
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(0, x, y);
                    shape2D.AddVertex(1, x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    shape2D.AddVertex(2, x, y);
                    shape2D.AddVertex(3, x, y);
                }
            }
            case RoundedRectangle -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.AddVertex(0, x, y);
                    shape2D.AddVertex(1, x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    if (shape2D.GetVerticesNum() == 2) {
                        shape2D.AddVertex(2, x, y);
                        shape2D.AddVertex(3, x, y);
                    } else {
                        shape2D.AddVertex(4, x, y);
                    }
                }
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
        }
    }
}
