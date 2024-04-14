package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEventMainScreen;
import controller.ControllerMatrixScreenKey;
import model.data.Data;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import java.awt.Color;

public class MatrixShowFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextArea textArea;
	public MainScreen main;
	public Data data;

	public MatrixShowFrame(MainScreen main) {
		this.main = main;
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/Image/website-builder.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 310, 300);
		this.setResizable(false);
		this.setTitle("Điền ma trận kề");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel inputMaTrixScreen = new JLabel("INPUT MATRIX");
		inputMaTrixScreen.setBackground(new Color(240, 255, 240));
		inputMaTrixScreen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		inputMaTrixScreen.setHorizontalAlignment(SwingConstants.CENTER);
		inputMaTrixScreen.setBounds(0, 0, 294, 33);
		inputMaTrixScreen.setOpaque(true);
		contentPane.add(inputMaTrixScreen);
		
		//
		ControllerMatrixScreenKey key = new ControllerMatrixScreenKey(this);
		
		textArea = new JTextArea();
		textArea.addKeyListener(key);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 34, 294, 227);
		contentPane.add(scrollPane);
		
	}
	public void setVis(boolean vis) {
		clear();
		this.setVisible(vis);
	}
	
	public void clear() {
		this.textArea.setText("");
	}
	
	public void setData(Data data) {
		this.data = data;
	}
}
