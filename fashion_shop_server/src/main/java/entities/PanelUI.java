package entities;

import javax.swing.JPanel;

public class PanelUI {
	private int index;
	private int indexSubmenu;
	private JPanel jPanel;
	private String title;

	public PanelUI(JPanel jPanel, String title, int index, int indexSubmenu) {
		super();
		this.jPanel = jPanel;
		this.title = title;
		this.index = index;
		this.indexSubmenu = indexSubmenu;
	}

	public int getIndex() {
		return index;
	}

	public int getIndexSubmenu() {
		return indexSubmenu;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public String getTitle() {
		return title;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setIndexSubmenu(int indexSubmenu) {
		this.indexSubmenu = indexSubmenu;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
