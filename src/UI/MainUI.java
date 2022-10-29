package UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import java.util.*;

import Shape.*;

/**
 * @author LiuJiayuan
 * @version 1.0
 */
public class MainUI extends JFrame {
    private JMenuBar _menubar;
    private JMenu _menuFile, _menuOperate, _menuView;
    private JMenuItem _menuOpen, _menuSave, _menuCancel, _menuRedo, _menuZoomIn, _menuZoomOut;

    private JToolBar _toolBar;
    private JButton _btnLine, _btnCurve, _btnTriangle, _btnRectangle, _btnRoundedRectangle,
            _btnCircle, _btnPolygon, _btnBrush, _btnEraser, _btnColorChooser, _colorViewer;
    private Vector<JButton> _toolBtnVec;


    private Container _container;
    private JPanel _canvasPanel;
    private Canvas _canvas;

    private Function _func;
    private Graphics2D _graphics;
    private Vector<Vertex> _vertices;
    private BufferStrategy _strategy;
    private Shape2D _shape2D;
    private Vector<Shape2D> _shape2DVec;

    /**
     * 初始化窗口与控件
     */
    public MainUI() {
        super("Painting");
        InitSizeAndPos();
        InitMenuBar();
        InitToolBar();
        InitCanvas();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        InitBtnListener();
        InitCanvasListener();
    }

    /**
     * 依据屏幕大小初始化窗口大小<br>
     * 窗口位于屏幕中心且长宽均为屏幕长宽的0.75
     */
    private void InitSizeAndPos() {
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolKit.getScreenSize();
        double width = screenDimension.getWidth();
        double height = screenDimension.getHeight();
        setSize((int) width * 3 / 4, (int) height * 3 / 4);
        setLocationRelativeTo(null);
    }

    /**
     * 初始化菜单栏
     */
    private void InitMenuBar() {
        _menuFile = new JMenu("File");
        _menuOperate = new JMenu("Operate");
        _menuView = new JMenu("View");

        _menuOpen = new JMenuItem("Open");
        _menuSave = new JMenuItem("Save");
        _menuCancel = new JMenuItem("Cancel");
        _menuRedo = new JMenuItem("Redo");
        _menuZoomIn = new JMenuItem("ZoomIn");
        _menuZoomOut = new JMenuItem("ZoomOut");

        _menuFile.add(_menuOpen);
        _menuFile.add(_menuSave);
        _menuOperate.add(_menuCancel);
        _menuOperate.add(_menuRedo);
        _menuView.add(_menuZoomIn);
        _menuView.add(_menuZoomOut);

        _menubar = new JMenuBar();
        _menubar.add(_menuFile);
        _menubar.add(_menuOperate);
        _menubar.add(_menuView);

        setJMenuBar(_menubar);
    }


    private void InitToolBar() {
        String[] _toolBtnStr = {"Eraser", "Brush", "Line", "Curve", "Triangle", "Rectangle", "RoundedRectangle",
                "Circle", "Polygon", "ColorChooser", "ColorViewer"};


        _toolBtnVec = new Vector<>();
        //操作类
        _btnEraser = new JButton();
        _toolBtnVec.add(_btnEraser);
        //绘图类
        _btnBrush = new JButton();
        _toolBtnVec.add(_btnBrush);
        _btnLine = new JButton();
        _toolBtnVec.add(_btnLine);
        _btnCurve = new JButton();
        _toolBtnVec.add(_btnCurve);
        _btnTriangle = new JButton();
        _toolBtnVec.add(_btnTriangle);
        _btnRectangle = new JButton();
        _toolBtnVec.add(_btnRectangle);
        _btnRoundedRectangle = new JButton();
        _toolBtnVec.add(_btnRoundedRectangle);
        _btnCircle = new JButton();
        _toolBtnVec.add(_btnCircle);
        _btnPolygon = new JButton();
        _toolBtnVec.add(_btnPolygon);
        //颜色类
        _btnColorChooser = new JButton();
        _toolBtnVec.add(_btnColorChooser);
        _colorViewer = new JButton("");
        _toolBtnVec.add(_colorViewer);

        for (int i = 0; i < _toolBtnVec.size(); i++) {
            JButton btn = _toolBtnVec.get(i);
            String functionName = Function.values()[i].toString();
            if (functionName.equals("ColorViewer")) {
                btn.setText("     ");
                btn.setFont(new Font("宋体", 1, 30));
                btn.setOpaque(true);
                btn.setEnabled(false);
                btn.setBackground(Color.black);
            } else {
                btn.setText(functionName);
                btn.setFont(new Font("宋体", 1, 0));
                btn.setIcon(new ImageIcon("src/icon/" + _toolBtnStr[i] + ".png"));

            }
        }


        _toolBar = new JToolBar("ToolBar");

        for (JButton btn : _toolBtnVec) {
            switch (btn.getText()) {
                case "Eraser" -> {
                    _toolBar.add(new JLabel("Tool"));
                    _toolBar.addSeparator();
                }
                case "Brush" -> {
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Draw"));
                    _toolBar.addSeparator();
                }
                case "ColorChooser" -> {
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Color Chooser"));
                    _toolBar.addSeparator();
                }
                case "ColorViewer" -> {
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Current Color"));
                    _toolBar.addSeparator();
                }
            }
            _toolBar.add(btn);
        }

        _container = getContentPane();
        _container.setLayout(new BorderLayout());
        _container.add(_toolBar, BorderLayout.NORTH);

    }

    private void InitCanvas() {
        _canvasPanel = new JPanel();
        _canvasPanel.setLayout(new GridLayout());
        _canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                g.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
                Graphics2D g2D = (Graphics2D) g;
                for (Shape2D shape2D : _shape2DVec) {
                    g2D.draw(shape2D.generatePath());
                }
            }
        };
        _canvas.setBackground(Color.WHITE);
        _canvas.setSize(new Dimension(this.getWidth(), this.getHeight()));
        _canvasPanel.add(_canvas);
        _container.add(_canvasPanel);

        _shape2DVec = new Vector<>();
        _func = Function.Line;
    }

    private void InitBtnListener() {
        for (JButton btn : _toolBtnVec) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    _func = Function.valueOf(e.getActionCommand());
                    _vertices = new Vector<Vertex>(Function.getVertexNum(_func));
                    _shape2D = utils.ActionSwitch(_vertices, _func);
                }
            });
        }
    }

    private void InitCanvasListener() {
        _canvas.createBufferStrategy(2);
        _strategy = _canvas.getBufferStrategy();
        _graphics = (Graphics2D) _canvas.getGraphics();
        _canvas.addMouseListener(new CanvasListener());
        _canvas.addMouseMotionListener(new CanvasListener());

    }

    private class CanvasListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (_shape2D == null)
                return;

            utils.PressedSwitch(_shape2D, _func, _shape2DVec, e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (_shape2D == null)
                return;

            utils.ReleasedSwitch(_shape2D, _func, _shape2DVec, e.getX(), e.getY());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (_shape2D == null)
                return;
            do {
                do {
                    Graphics2D graphics = (Graphics2D) _strategy.getDrawGraphics();

                    graphics.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
                    for (Shape2D shape2D : _shape2DVec) {
                        graphics.draw(shape2D.generatePath());
                    }

                    utils.DraggedSwitch(_shape2D, _func, e.getX(), e.getY());
                    graphics.draw(_shape2D.generatePath());

                    graphics.dispose();

                } while (_strategy.contentsRestored());
                _strategy.show();

            } while (_strategy.contentsLost());
        }

    }

    public Color GetColor() {
        return _colorViewer.getBackground();
    }

}
