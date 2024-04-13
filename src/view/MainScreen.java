package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ControllerEventMainScreen;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textFieldDinh;
	public JTextField textFieldCanh;
	public JTextField textFieldSourceV;
	public JTextField textFieldTargetV;
	public JButton buttonActionWorkingMain;
	public JLabel labelInvalid;
	public JTextArea textArea;
	public JLabel testLabel;
	public JRadioButton directed;
	public JRadioButton unDirected;
	public JButton buttonCLearData;
	public JButton buttonRestoreData;
	public JButton buttonAboutMeMain;
	public JLabel matrixShowLabel;
	public JButton buttonSettingMain;
	
	public MainScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/Image/website-builder.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Ứng dụng tìm đường đi ngắn nhất v1.0");
		setBounds(450, 50, 631, 620);
		
		// implements controller Event Main Screen
		ControllerEventMainScreen handMainScreen  = new ControllerEventMainScreen(this);
		//ControllerActionMainScreen handActionMainScreen = new ControllerActionMainScreen(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 21));
		textArea.addKeyListener(handMainScreen);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(38, 194, 116, 376);
		contentPane.add(scrollPane);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldDinh = new JTextField();
		textFieldDinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldDinh.setBounds(38, 157, 48, 26);
		contentPane.add(textFieldDinh);
		textFieldDinh.setColumns(10);
		textFieldDinh.addKeyListener(handMainScreen);
		
		textFieldCanh = new JTextField();
		textFieldCanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCanh.setColumns(10);
		textFieldCanh.setBounds(106, 157, 48, 26);
		contentPane.add(textFieldCanh);
		textFieldCanh.addKeyListener(handMainScreen);
		
		buttonActionWorkingMain = new JButton("Kiểm tra");
		buttonActionWorkingMain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonActionWorkingMain.setFocusable(false);
		buttonActionWorkingMain.setBounds(187, 537, 89, 33);
		contentPane.add(buttonActionWorkingMain);
		buttonActionWorkingMain.addActionListener(handMainScreen);
		
		JLabel labelDinh = new JLabel("Đỉnh");
		labelDinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelDinh.setHorizontalAlignment(SwingConstants.CENTER);
		labelDinh.setBounds(40, 132, 46, 20);
		contentPane.add(labelDinh);
		
		JLabel labelCanh = new JLabel("Cạnh");
		labelCanh.setHorizontalAlignment(SwingConstants.CENTER);
		labelCanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelCanh.setBounds(106, 132, 46, 20);
		contentPane.add(labelCanh);
		
		textFieldSourceV = new JTextField();
		textFieldSourceV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSourceV.setColumns(10);
		textFieldSourceV.setBounds(177, 157, 48, 26);
		contentPane.add(textFieldSourceV);
		
		JLabel labelSourceV = new JLabel("Đi từ");
		labelSourceV.setHorizontalAlignment(SwingConstants.CENTER);
		labelSourceV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelSourceV.setBounds(177, 132, 46, 20);
		contentPane.add(labelSourceV);
		
		JLabel labelTargetV = new JLabel("Đi đến");
		labelTargetV.setHorizontalAlignment(SwingConstants.LEFT);
		labelTargetV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTargetV.setBounds(174, 194, 76, 20);
		contentPane.add(labelTargetV);
		
		textFieldTargetV = new JTextField();
		textFieldTargetV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTargetV.setColumns(10);
		textFieldTargetV.setBounds(177, 225, 48, 26);
		contentPane.add(textFieldTargetV);
		
		buttonSettingMain = new JButton("Setting");
		buttonSettingMain.setFocusable(false);
		buttonSettingMain.setBounds(505, 537, 100, 33);
		buttonSettingMain.addActionListener(handMainScreen);
		contentPane.add(buttonSettingMain);
		
		JButton buttonHelpMain = new JButton("Trợ giúp ?");
		buttonHelpMain.setFocusable(false);
		buttonHelpMain.setBounds(505, 493, 100, 33);
		contentPane.add(buttonHelpMain);
		
		buttonAboutMeMain = new JButton("About me");
		buttonAboutMeMain.setFocusable(false);
		buttonAboutMeMain.setBounds(505, 449, 100, 33);
		buttonAboutMeMain.addActionListener(handMainScreen);
		contentPane.add(buttonAboutMeMain);
		
		labelInvalid = new JLabel("");
		labelInvalid.setForeground(new Color(255, 0, 0));
		labelInvalid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelInvalid.setHorizontalAlignment(SwingConstants.LEFT);
		labelInvalid.setBounds(294, 537, 185, 33);
		contentPane.add(labelInvalid);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		
		
		testLabel = new JLabel("Bảng ma trận");
		testLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		testLabel.setBounds(248, 66, 357, 251);
		//testLabel.setEditable(false);
		contentPane.add(testLabel);
		
		directed = new JRadioButton("Có hướng");
		directed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		directed.setBounds(43, 17, 109, 33);
		directed.setFocusable(false);
		contentPane.add(directed);
		
		unDirected = new JRadioButton("Vô hướng");
		unDirected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		unDirected.setBounds(45, 48, 109, 39);
		unDirected.setSelected(true);
		unDirected.setFocusable(false);
		contentPane.add(unDirected);
		buttonGroup.add(directed);
		buttonGroup.add(unDirected);
		
		buttonCLearData = new JButton("Clear");
		buttonCLearData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonCLearData.setFocusable(false);
		buttonCLearData.setBounds(187, 493, 89, 33);
		buttonCLearData.addActionListener(handMainScreen);
		contentPane.add(buttonCLearData);
		
		buttonRestoreData = new JButton("Restore");
		buttonRestoreData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonRestoreData.setFocusable(false);
		buttonRestoreData.setBounds(187, 449, 89, 33);
		buttonRestoreData.addActionListener(handMainScreen);
		contentPane.add(buttonRestoreData);
		
		matrixShowLabel = new JLabel("");
		matrixShowLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matrixShowLabel.setBounds(272, 37, 231, 25);
		contentPane.add(matrixShowLabel);
		
//		JLabel chinh = new JLabel("2");
//		chinh.setHorizontalAlignment(SwingConstants.CENTER);
//		chinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		chinh.setBounds(367, 40, 174, 174);
//		contentPane.add(chinh);
//		StringBuilder labelText = new StringBuilder("<html>");
//		for(int i = 0; i < 3; i++) {
//			for(int j = 0; j < 3; j++) {
//				labelText.append("*" );
//			}
//			labelText.append("<br>");
//		}
//		labelText.append("</html>");
//		chinh.setText(labelText.toString());
		
		//this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
