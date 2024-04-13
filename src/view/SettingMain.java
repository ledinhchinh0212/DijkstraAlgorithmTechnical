package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SettingMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void setVis(boolean vis) {
		this.setVisible(vis);
	}
	
	public SettingMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 332, 360);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}
}