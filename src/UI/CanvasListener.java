//package UI;
//
//import java.awt.*;
//import java.awt.Shape;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import Shape.*;
//
//public class CanvasListener extends MouseAdapter {
//    Function _func;
//    Graphics2D _g;
//    Vertex[] _vertices;
//    Shape2D _shape;
//
//    public CanvasListener(Function func, Graphics2D graphics) {
//        super();
//        _func = func;
//        _g = graphics;
//        switch (_func) {
//            case Line -> _vertices = new Vertex[2];
//            case Triangle -> _vertices = new Vertex[3];
//        }
//    }
//
//    public void mousePressed(MouseEvent e) {
//        switch (_func) {
//            case Line -> {
//                if (!_vertices[0].isAssigned()) {
//                    _vertices[0].SetXY(e.getX(), e.getY());
//                    System.out.println("p1" + e.getX() + " " + e.getY());
//                } else {
//                    _vertices[1].SetXY(e.getX(), e.getY());
//                    System.out.println("p2" + e.getX() + " " + e.getY());
//                    _shape = new Line(_vertices);
//                }
//
//            }
//            case Triangle -> {
//            }
//        }
//        _g.draw(_shape.generatePath());
//    }
//
//    public void mouseReleased(MouseEvent e) {
//        switch (_func) {
//            case Line -> {
//                _vertices[1].SetXY(e.getX(), e.getY());
//                System.out.println("r" + e.getX() + " " + e.getY());
//                _shape = new Line(_vertices);
//            }
//            case Triangle -> {
//            }
//        }
//        _g.draw(_shape.generatePath());
//    }
//}
