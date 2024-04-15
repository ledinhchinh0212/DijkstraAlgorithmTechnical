package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerSettingKey;

import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class SettingMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JToggleButton dijkstraButton;
	public JToggleButton primButton;
	public JToggleButton biGraphButton;
	public MainScreen main;
	public JLabel updateLabel;

	
	public SettingMainView(MainScreen main) {
		this.main = main;
		setIconImage(Toolkit.getDefaultToolkit().getImage(SettingMainView.class.getResource("/Image/website-builder.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 203, 267);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ButtonGroup buttonGroup = new ButtonGroup();
		
		ControllerSettingKey settingKey = new ControllerSettingKey(this);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dijkstraButton = new JToggleButton("Dijkstra");
		dijkstraButton.setSelected(true);
		dijkstraButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dijkstraButton.setBounds(10, 11, 167, 50);
		dijkstraButton.setBackground(new Color(240, 240, 240));
		dijkstraButton.setFocusable(false);
		dijkstraButton.addActionListener(settingKey);
		contentPane.add(dijkstraButton);
		
		primButton = new JToggleButton("PRIM");
		primButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		primButton.setFocusable(false);
		primButton.setBounds(10, 72, 167, 50);
		primButton.setBackground(new Color(240, 240, 240));
		contentPane.add(primButton);
		primButton.addActionListener(settingKey);
		
		biGraphButton = new JToggleButton("Đồ thị 2 phía");
		biGraphButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		biGraphButton.setFocusable(false);
		biGraphButton.setBounds(10, 133, 167, 50);
		biGraphButton.setBackground(new Color(240, 240, 240));
		biGraphButton.addActionListener(settingKey);
		contentPane.add(biGraphButton);
		
		buttonGroup.add(dijkstraButton);
		buttonGroup.add(primButton);
		buttonGroup.add(biGraphButton);
		
		updateLabel = new JLabel("Selected: dijkstra");
		updateLabel.setForeground(new Color(50, 205, 50));
		updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateLabel.setBounds(10, 194, 167, 23);
		contentPane.add(updateLabel);

	}
	
	public void setVis(boolean vis) {
		this.setVisible(vis);
	}
}
