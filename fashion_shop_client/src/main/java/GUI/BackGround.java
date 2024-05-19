package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.Utils;

public class BackGround extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLoading;
	private JLabel lblPhanTram;
	private JProgressBar prg;

	/**
	 * Create the frame.
	 */
	public BackGround() {
		setBounds(0, 0, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("CLOTHING SHOP");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(20, 20, 450, 60);
		contentPane.add(lblTitle);
		setLocationRelativeTo(null);

		JLabel lblIcon = new JLabel("");
		
		lblIcon.setIcon(Utils.getImageIcon("Shop.png"));

		lblIcon.setBounds(0, 0, 500, 360);
		contentPane.add(lblIcon);

		prg = new JProgressBar();
		prg.setBounds(0, 389, 450, 11);
		contentPane.add(prg);

		lblLoading = new JLabel("Loading...");
		lblLoading.setForeground(Color.BLACK);
		lblLoading.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoading.setBounds(10, 355, 350, 24);
		contentPane.add(lblLoading);

		lblPhanTram = new JLabel("0%");
		lblPhanTram.setForeground(Color.BLACK);
		lblPhanTram.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPhanTram.setBounds(390, 355, 50, 24);
		contentPane.add(lblPhanTram);
	}

	public void handleOpen(JFrame jFrame) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i <= 10; ++i) {
					try {
						prg.setValue(i * 10);
						lblPhanTram.setText(i * 10 + "%");

						switch (i) {
						case 1:
							lblLoading.setText("Đang kích hoạt các Module...");
							break;
						case 2:
							lblLoading.setText("Đang tải các Module...");
							break;
						case 5:
							lblLoading.setText("Đang kết nối đến Cơ sở dữ liệu...");
							break;
						case 7:
							lblLoading.setText("Kết nối thành công!");
							break;
						case 8:
							lblLoading.setText("Khởi động ứng dụng...");
							break;
						default:
							break;
						}
						sleep(100);

						if (i == 10) {
							jFrame.setAlwaysOnTop(true);
							jFrame.setVisible(true);
							dispose();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();
	}
}
