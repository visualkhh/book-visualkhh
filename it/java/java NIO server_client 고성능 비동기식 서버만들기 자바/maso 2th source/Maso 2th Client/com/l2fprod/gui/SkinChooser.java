package com.l2fprod.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ResourceBundle;
import javax.swing.*;

import com.l2fprod.util.OS;
import com.l2fprod.gui.plaf.skin.*;

/**
 * Skin Chooser.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:55 $
 */
public class SkinChooser extends JPanel {

    final static String REFRESH_CMD = "refresh";
    final static String PREVIEW_CMD = "preview";
    final static String GETSKINS_CMD = "getskins";

    private JList skinList;
    private String[] directories;

    private JCheckBox backgroundCheckBox, scrollBarCheckBox;

    private ResourceBundle bundle;

    /**
     * Construct a new SkinChooser pane.
     *
     */
    public SkinChooser() {
	loadResourceBundle();

	setLayout(new BorderLayout(3,3));

	JPanel listPane = new JPanel(new BorderLayout(3,3));

	JPanel buttonPane = new JPanel(new GridLayout(1, 3, 3, 3));

	JButton button = new JButton(bundle.getString("SkinChooser.getskins"));
	buttonPane.add(button);
	button.addActionListener(new GetSkinsAction());
	button.setToolTipText(bundle.getString("SkinChooser.getskins.tip"));

	button = new JButton(bundle.getString("SkinChooser.preview"));
	buttonPane.add(button);
	button.addActionListener(new PreviewAction());

	button = new JButton(bundle.getString("SkinChooser.refresh"));
	buttonPane.add(button);
	button.addActionListener(new RefreshAction());

	listPane.add("Center", new JScrollPane(skinList = new JList()));
	listPane.add("South", buttonPane);

	add("Center", listPane);

	Box optionPane = Box.createVerticalBox();
	optionPane.add(backgroundCheckBox = new JCheckBox(bundle.getString("SkinChooser.enableBackground")));
	optionPane.add(scrollBarCheckBox = new JCheckBox(bundle.getString("SkinChooser.enableScrollBar")));
	add("East", optionPane);
    }

    private void loadResourceBundle() {
        bundle = ResourceBundle.getBundle("com.l2fprod.gui.plaf.skin.resources.skin");
    }

    /**
     * Refresh the skin list.
     *
     * @see #setSkinLocations
     */
    public void refreshList() {
	if ((directories != null) && (directories.length > 0))
	    setSkinLocations(directories);
    }

    /**
     * Set search paths
     *
     * @param directories search paths
     */
    public void setSkinLocations(String[] directories) {
	this.directories = directories;
	Vector skins = new Vector();
	for (int i = 0, c = directories.length; i < c; i++)
	    buildSkinList(skins, new File(directories[i]));
	skinList.setListData(skins);
    }

    /**
     * @return search paths
     */
    public String[] getSkinLocations() {
	return directories;
    }

    /**
     * Recursively traverse <code>directory</code> and add skin files to <code>v</code>.
     * <br>
     * Skin files are added if <code>accept(skinFile)</code> returns <code>true</code>
     *
     * @param v vector to store skin list
     * @param directory the directory to list for skin files
     */
    protected void buildSkinList(Vector v, File directory) {
	if (!directory.isDirectory() || !directory.exists())
	    return;

	String[] files = directory.list();
	File f;
	for (int i = 0, c = files.length; i < c; i++) {
	    f = new File(directory, files[i]);
	    if (f.isDirectory())
		buildSkinList(v, f);
	    else if (accept(f))
		try {
		    v.addElement(f.getCanonicalPath());
		} catch (IOException e) {}
	}
    }    
	
    /**
     * Check if a given file is a skin file.
     * <br>
     * Subclasses can override this method to provide better handling of skin files.
     * <br>
     * The default implementation checks if the file ends with gtkrc or themerc.
     *
     * @param f the file to check
     * @return true if the file is a valid skin file
     */
    protected boolean accept(File f) {
	return (f.isDirectory() == false &&
		(f.getName().endsWith("gtkrc") || f.getName().endsWith("themerc")));
    }

    /**
     * @return the currently selected skins
     */
    public String[] getSelectedSkins() {
	return (String[])skinList.getSelectedValues();
    }

    /**
     * Apply current selection.
     * <br>
     * The method sets the current skin (SkinLookAndFeel.setSkin) then calls UIManager.setLookAndFeel.
     */
    public void apply() throws Exception {
	Object[] values = skinList.getSelectedValues();
	if ((values == null) || (values.length != 2))
	    return;
	
	UIManager.put("JDesktopPane.backgroundEnabled",
		      backgroundCheckBox.isSelected()?Boolean.TRUE:null);
	UIManager.put("ScrollBar.alternateLayout",
		      scrollBarCheckBox.isSelected()?Boolean.TRUE:null);

	Skin skin = new CompoundSkin(SkinLookAndFeel.loadSkin((String)values[0]),
				     SkinLookAndFeel.loadSkin((String)values[1]));
	SkinLookAndFeel.setSkin(skin);

	UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
    }

    private class RefreshAction extends AbstractAction {
	public RefreshAction() { super(REFRESH_CMD); }
	public void actionPerformed(ActionEvent event) { refreshList(); }
    }

    private class GetSkinsAction extends AbstractAction {
	public GetSkinsAction() { super(GETSKINS_CMD); }
	public void actionPerformed(ActionEvent event) {
	    try {
		OS.openDocument(bundle.getString("SkinChooser.getskins.url"));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private class PreviewAction extends AbstractAction {
	public PreviewAction() { super(PREVIEW_CMD); }
	public void actionPerformed(ActionEvent event) {
	    showPreviewWindow();
	}
    }

    protected void showPreviewWindow() {
	Skin oldSkin = SkinLookAndFeel.getSkin();
	LookAndFeel oldLAF = UIManager.getLookAndFeel();

	try {
	    Object[] values = skinList.getSelectedValues();
	    if ((values == null) || (values.length != 2))
		return;
	    
	    
	    Skin skin = new CompoundSkin(SkinLookAndFeel.loadSkin((String)values[0]),
					 SkinLookAndFeel.loadSkin((String)values[1]));
	    
	    SkinLookAndFeel.setSkin(skin);
	    UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
	    
	    SkinWindow window = new SkinPreviewWindow();
	    window.setVisible(true);

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SkinLookAndFeel.setSkin(oldSkin);
	    try {
		UIManager.setLookAndFeel(oldLAF);
	    } catch (UnsupportedLookAndFeelException e) {
	    }
	}
    }


}
