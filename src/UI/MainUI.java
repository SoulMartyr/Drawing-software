package UI;

import java.awt.*;

import javax.swing.*;
import java.util.*;


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
            _btnCircle, _btnPolygon, _btnBrush, _btnEraser, _btnColorChooser,_colorViewer;
    private Vector<JButton> _toolDrawBtnVec, _toolOptBtnVec;

    private Container _container;

    /**
     * 初始化窗口与控件
     */
    public MainUI() {
        super("Painting");
        InitSizeAndPos();
        InitMenuBar();
        InitToolBar();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        setLocation((int) (width - width * 3 / 4) / 2, (int) (height - height * 3 / 4) / 2);
    }

    /**
     * 初始化菜单栏
     */
    private void InitMenuBar() {
        this._menuFile = new JMenu("File");
        this._menuOperate = new JMenu("Operate");
        this._menuView = new JMenu("View");

        this._menuOpen = new JMenuItem("Open");
        this._menuSave = new JMenuItem("Save");
        this._menuCancel = new JMenuItem("Cancel");
        this._menuRedo = new JMenuItem("Redo");
        this._menuZoomIn = new JMenuItem("ZoomIn");
        this._menuZoomOut = new JMenuItem("ZoomOut");

        this._menuFile.add(this._menuOpen);
        this._menuFile.add(this._menuSave);
        this._menuOperate.add(this._menuCancel);
        this._menuOperate.add(this._menuRedo);
        this._menuView.add(this._menuZoomIn);
        this._menuView.add(this._menuZoomOut);

        this._menubar = new JMenuBar();
        this._menubar.add(this._menuFile);
        this._menubar.add(this._menuOperate);
        this._menubar.add(this._menuView);

        setJMenuBar(this._menubar);
    }


    private void InitToolBar() {
        String[] _toolDrawBtnStr = {"Line", "Curve", "Triangle", "Rectangle", "RoundedRectangle",
                "Circle", "Polygon"};
        String[] _toolOptBtnStr = {"Brush", "Eraser", "ColorChooser","ColorViewer"};


        this._toolDrawBtnVec = new Vector<JButton>();
        this._btnLine = new JButton();
        this._toolDrawBtnVec.add(this._btnLine);
        this._btnCurve = new JButton();
        this._toolDrawBtnVec.add(this._btnCurve);
        this._btnTriangle = new JButton();
        this._toolDrawBtnVec.add(this._btnTriangle);
        this._btnRectangle = new JButton();
        this._toolDrawBtnVec.add(this._btnRectangle);
        this._btnRoundedRectangle = new JButton();
        this._toolDrawBtnVec.add(this._btnRoundedRectangle);
        this._btnCircle = new JButton();
        this._toolDrawBtnVec.add(this._btnCircle);
        this._btnPolygon = new JButton();
        this._toolDrawBtnVec.add(this._btnPolygon);

        for (int i = 0; i < this._toolDrawBtnVec.size(); i++) {
            JButton btn = this._toolDrawBtnVec.get(i);
            btn.setName(_toolDrawBtnStr[i]);
            btn.setIcon(new ImageIcon("src/icon/" + _toolDrawBtnStr[i] + ".png"));
        }

        this._toolOptBtnVec = new Vector<JButton>();
        this._btnBrush = new JButton();
        this._toolOptBtnVec.add(this._btnBrush);
        this._btnEraser = new JButton();
        this._toolOptBtnVec.add(this._btnEraser);
        this._btnColorChooser = new JButton();
        this._toolOptBtnVec.add(this._btnColorChooser);
        this._colorViewer= new JButton("");
        this._toolOptBtnVec.add(this._colorViewer);

        for (int i = 0; i < this._toolOptBtnVec.size(); i++) {
            JButton btn = this._toolOptBtnVec.get(i);
            btn.setName(_toolOptBtnStr[i]);
            if(_toolOptBtnStr[i].equals("ColorViewer")){
                btn.setText("     ");
                btn.setFont(new Font("宋体", 1, 30));
                btn.setOpaque(true);
                btn.setEnabled(false);
                btn.setBackground(Color.black);
            }
            else
                btn.setIcon(new ImageIcon("src/icon/" + _toolOptBtnStr[i] + ".png"));

        }


        this._toolBar = new JToolBar("ToolBar");

        this._toolBar.add(new JLabel("Tool"));
        this._toolBar.addSeparator();
        this._toolBar.add(this._btnBrush);
        this._toolBar.add(this._btnEraser);
        this._toolBar.addSeparator();
        this._toolBar.add(new JLabel("Listener"));
        this._toolBar.addSeparator();
        this._toolBar.add(this._btnLine);
        this._toolBar.add(this._btnCurve);
        this._toolBar.add(this._btnTriangle);
        this._toolBar.add(this._btnRectangle);
        this._toolBar.add(this._btnRoundedRectangle);
        this._toolBar.add(this._btnCircle);
        this._toolBar.add(this._btnPolygon);
        this._toolBar.addSeparator();
        this._toolBar.add(new JLabel("Color Chooser"));
        this._toolBar.addSeparator();
        this._toolBar.add(this._btnColorChooser);
        this._toolBar.addSeparator();
        this._toolBar.add(new JLabel("Current Color"));
        this._toolBar.addSeparator();
        this._toolBar.add(this._colorViewer);
        this._toolBar.addSeparator();

        _container = getContentPane();
        _container.setLayout(new BorderLayout());
        _container.add(_toolBar, BorderLayout.NORTH);

    }

    public Color GetColor() {
        return this._colorViewer.getBackground();
    }
}
