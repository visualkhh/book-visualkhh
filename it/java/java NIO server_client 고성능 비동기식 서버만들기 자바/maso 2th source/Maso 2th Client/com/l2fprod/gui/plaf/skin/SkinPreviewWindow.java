package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import com.l2fprod.gui.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.*;
import com.l2fprod.gui.plaf.skin.impl.kde.*;
import com.l2fprod.util.WindowUtils;

/**
 * SkinPreview Window.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:09 $
 */
public class SkinPreviewWindow extends SkinWindow {

    public static void main(String[] args) throws Exception {

	Skin skin = null;
	if (args.length > 1)
	    skin = new CompoundSkin(SkinLookAndFeel.loadSkin(args[0]),
				    SkinLookAndFeel.loadSkin(args[1]));
	else
	    skin = SkinLookAndFeel.loadSkin(args[0]);

	SkinLookAndFeel.setSkin(skin);

	UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");

    }

    public SkinPreviewWindow() {
	super();

	JTabbedPane tabs = new JTabbedPane();
	getContentPane().add("Center", tabs);
	tabs.addTab("Buttons", new ButtonTest());
	tabs.addTab("CheckBox", new CheckBoxTest());
	tabs.addTab("Radio", new RadioTest());
	tabs.addTab("Progress", new ProgressTest());
	tabs.addTab("Desktop", new InternalTest());
	tabs.addTab("Scrollbars", new ScrollTest());
	tabs.addTab("List", new ListTest());
	tabs.addTab("Table", new TableTest());

	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("File");
	menu.add(new JCheckBoxMenuItem("Check Box ?"));
	JMenu submenu = new JMenu("Sub menu");
	submenu.add(new JMenuItem("Hi!"));
	menu.add(submenu);
	menu.add(new JMenuItem("Exit"));	
	menubar.add(menu);

	setJMenuBar(menubar);
       
	WindowUtils.sizeTo(this, 0.5d, 0.5d);
	WindowUtils.centerOnScreen(this);

	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent event) {
		    SkinPreviewWindow.this.dispose();
		}
	    });
    }

    protected JComponent createNorthPanel() {
	try {
	    Class acClass = javax.swing.JComponent.class;
	    UIManager.put("ClassLoader", SkinWindowButtonUI.class.getClassLoader());
	    UIManager.put("WindowButtonUI",
			  "com.l2fprod.gui.plaf.skin.SkinWindowButtonUI");
	    UIManager.put(SkinWindowButtonUI.class,
			  SkinWindowButtonUI.class.getMethod("createUI", new Class[]{acClass}));
	} catch (Exception e) { e.printStackTrace(); }
	return new SkinWindowTitlePane(this);
    }

    static class ButtonTest extends JPanel {
	ButtonTest() {
	    setLayout(new BorderLayout());
	    add("North", new JButton("North"));
	    add("East", new JButton("East"));
	    add("South", new JButton("South"));
	    add("West", new JButton("West"));
	    JButton b = new JButton("Center");
	    b.setEnabled(false);
	    add("Center", b);
	}
    }

    static class CheckBoxTest extends JPanel {
	CheckBoxTest() {
	    setLayout(new BorderLayout());
	    Box p = new Box(BoxLayout.Y_AXIS);
	    //	    JPanel p = new JPanel();
	    //	    p.setLayout(new GridLayout(4, 1));
	    p.add(new JCheckBox("Hello ???"));
	    p.add(Box.createVerticalStrut(3));
	    p.add(new JComboBox(new String[]{"1", "2", "4","8"}));
	    p.add(Box.createVerticalStrut(3));	    
	    JComboBox editable = new JComboBox(new String[]{"1", "2", "4","8"});
	    editable.setEditable(true);
	    p.add(editable);

	    p.add(Box.createVerticalStrut(3));
	    JPanel toggles = new JPanel();
	    toggles.setLayout(new FlowLayout());
	    ButtonGroup group = new ButtonGroup();
	    JToggleButton button;
	    button = new JToggleButton("Hello 1");
	    toggles.add(button);
	    group.add(button);
	    button = new JToggleButton("Hello 2");
	    toggles.add(button);
	    group.add(button);
	    button = new JToggleButton("Hello 3");
	    toggles.add(button);
	    group.add(button);
	    p.add(toggles);
	    
	    add("North", p);
	}
    }

    static class RadioTest extends JPanel {
	RadioTest() {
	    ButtonGroup group = new ButtonGroup();
	    setLayout(new GridLayout(3, 1));
	    JRadioButton button;
	    button = new JRadioButton("Hello 1");
	    add(button);
	    group.add(button);
	    button = new JRadioButton("Hello 2");
	    add(button);
	    group.add(button);
	    button = new JRadioButton("Hello 3");
	    add(button);
	    group.add(button);
	}
    }

    static class ProgressTest extends JPanel {
	ProgressTest() {
	    setLayout(new BorderLayout());
	    final BoundedRangeModel model = new DefaultBoundedRangeModel(0,0,0,100);

	    JProgressBar progress = new JProgressBar(model);
	    add("South", progress);

	    JSlider slider = new JSlider(model);
	    add("North", slider);

	    slider = new JSlider(JSlider.VERTICAL);
	    slider.setModel(model);
	    add("West", slider);

	    progress = new JProgressBar(JProgressBar.VERTICAL);
	    progress.setModel(model);
	    add("East", progress);

	    Thread th = new Thread() {
		    public void run() {
			try {
			    while (true) {
				Thread.sleep(50);
				model.setValue(model.getValue() + 2);
				if (model.getValue() >= 100) {
				    Thread.sleep(1000);
				    model.setValue(0);
				}
			    }
			} catch (Exception e) {}
		    }
		};
	    th.start();
	}
    }

    static class InternalTest extends JPanel {
	InternalTest() {
	    setLayout(new BorderLayout());
	    JDesktopPane desk = new JDesktopPane();
	    add("Center", new JScrollPane(desk));
	    desk.putClientProperty("JDesktopPane.backgroundEnabled", Boolean.TRUE);

	    JInternalFrame frame = new JInternalFrame("A Frame", true, true, true, true);
	    frame.getContentPane().add(new JButton("Ola"));
	    frame.setVisible(true);
	    frame.setSize(200,100);
	    desk.add(frame);

	    frame = new JInternalFrame("An other Frame", true, true, true, true);
	    frame.getContentPane().add(new JButton("Hello"));
	    frame.setMaximizable(false);
	    frame.setVisible(true);
	    frame.setSize(200,200);
	    frame.setLocation(50,50);
	    desk.add(frame);
	}
    }

    static class ScrollTest extends JPanel {
	ScrollTest() {
	    setLayout(new BorderLayout());
	    add("Center", new JScrollPane(new JTree()));
	}
    }

    static class ListTest extends JPanel {
	ListTest() {
	    setLayout(new BorderLayout());
	    String[] values = new String[50];
	    for (int i = 0, c = values.length; i < c; i++)
		values[i] = "Item " + i;
	    add("Center", new JScrollPane(new JList(values)));
	}
    }

    static class TableTest extends JPanel {
	TableTest() {
	    setLayout(new BorderLayout());
	    String[] columns = new String[4];
	    String[][] rowData = new String[10][4];
	    for (int i = 0, c = rowData[0].length; i < c; i++) {	
		columns[i] = "Column" + i;
		for (int j = 0, d = rowData.length; j < d; j++)
		    rowData[j][i] = "Cell(" + i + ", " + j + ")";
	    }

	    add("Center", new JScrollPane(new JTable(rowData, columns)));
	}
    }

}

