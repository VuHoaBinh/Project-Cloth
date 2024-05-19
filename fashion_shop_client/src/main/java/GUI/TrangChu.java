package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Utils;

public class TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public TrangChu() {
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(Utils.getImageIcon("Shop (1).png"));
		lblBackground.setBounds(40, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		this.add(lblBackground);
	}

}
