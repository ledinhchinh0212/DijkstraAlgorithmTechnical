package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ControllerEventMainScreen;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

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
	private JLabel imagePhu;
	public JButton changeMatrixSet;
	public JLabel labelDinh;
	public JLabel labelSourceV;
	public JLabel labelTargetV;
	
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
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#535C91"));
		
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
		buttonActionWorkingMain.setForeground(Color.WHITE);
		buttonActionWorkingMain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonActionWorkingMain.setFocusable(false);
		buttonActionWorkingMain.setBounds(187, 537, 89, 33);
		buttonActionWorkingMain.setBackground(Color.decode("#35155D"));
		contentPane.add(buttonActionWorkingMain);
		buttonActionWorkingMain.addActionListener(handMainScreen);
		
		labelDinh = new JLabel("Đỉnh");
		labelDinh.setForeground(Color.WHITE);
		labelDinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelDinh.setHorizontalAlignment(SwingConstants.CENTER);
		labelDinh.setBounds(40, 132, 46, 20);
		contentPane.add(labelDinh);
		
		JLabel labelCanh = new JLabel("Cạnh");
		labelCanh.setForeground(Color.WHITE);
		labelCanh.setHorizontalAlignment(SwingConstants.CENTER);
		labelCanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelCanh.setBounds(106, 132, 46, 20);
		contentPane.add(labelCanh);
		
		textFieldSourceV = new JTextField();
		textFieldSourceV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSourceV.setColumns(10);
		textFieldSourceV.setBounds(177, 157, 48, 26);
		contentPane.add(textFieldSourceV);
		
		labelSourceV = new JLabel("Đi từ");
		labelSourceV.setForeground(Color.WHITE);
		labelSourceV.setHorizontalAlignment(SwingConstants.CENTER);
		labelSourceV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelSourceV.setBounds(177, 132, 46, 20);
		contentPane.add(labelSourceV);
		
		labelTargetV = new JLabel("Đi đến");
		labelTargetV.setForeground(Color.WHITE);
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
		buttonSettingMain.setForeground(Color.WHITE);
		buttonSettingMain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonSettingMain.setFocusable(false);
		buttonSettingMain.setBounds(505, 537, 100, 33);
		buttonSettingMain.addActionListener(handMainScreen);
		buttonSettingMain.setBackground(Color.decode("#4477CE"));
		contentPane.add(buttonSettingMain);
		
		JButton buttonHelpMain = new JButton("Trợ giúp ?");
		buttonHelpMain.setForeground(Color.WHITE);
		buttonHelpMain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonHelpMain.setFocusable(false);
		buttonHelpMain.setBounds(505, 493, 100, 33);
		buttonHelpMain.setBackground(Color.decode("#4477CE"));
		contentPane.add(buttonHelpMain);
		
		buttonAboutMeMain = new JButton("About me");
		buttonAboutMeMain.setForeground(Color.WHITE);
		buttonAboutMeMain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonAboutMeMain.setFocusable(false);
		buttonAboutMeMain.setBounds(505, 449, 100, 33);
		buttonAboutMeMain.addActionListener(handMainScreen);
		buttonAboutMeMain.setBackground(Color.decode("#4477CE"));
		contentPane.add(buttonAboutMeMain);
		
		labelInvalid = new JLabel("");
		labelInvalid.setForeground(new Color(152, 251, 152));
		labelInvalid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelInvalid.setHorizontalAlignment(SwingConstants.CENTER);
		labelInvalid.setBounds(294, 537, 201, 33);
		contentPane.add(labelInvalid);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		testLabel = new JLabel("BẢNG MA TRẬN");
		testLabel.setForeground(null);
		testLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		testLabel.setBackground(Color.decode("#7BC9FF"));
		testLabel.setOpaque(true);
		testLabel.setBounds(235, 66, 370, 270);
		contentPane.add(testLabel);
		
		directed = new JRadioButton("Có hướng");
		directed.setForeground(new Color(0, 0, 51));
		directed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		directed.setBounds(38, 17, 116, 33);
		directed.setFocusable(false);
		directed.setBackground(Color.decode("#8576FF"));
		contentPane.add(directed);
		
		unDirected = new JRadioButton("Vô hướng");
		unDirected.setForeground(new Color(0, 0, 51));
		unDirected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		unDirected.setBounds(38, 53, 116, 39);
		unDirected.setSelected(true);
		unDirected.setFocusable(false);
		unDirected.setBackground(Color.decode("#8576FF"));
		contentPane.add(unDirected);
		buttonGroup.add(directed);
		buttonGroup.add(unDirected);
		
		buttonCLearData = new JButton("Clear");
		buttonCLearData.setForeground(Color.WHITE);
		buttonCLearData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonCLearData.setFocusable(false);
		buttonCLearData.setBounds(187, 493, 89, 33);
		buttonCLearData.addActionListener(handMainScreen);
		buttonCLearData.setBackground(Color.decode("#4477CE"));
		contentPane.add(buttonCLearData);
		
		buttonRestoreData = new JButton("Restore");
		buttonRestoreData.setForeground(Color.WHITE);
		buttonRestoreData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonRestoreData.setFocusable(false);
		buttonRestoreData.setBounds(187, 449, 89, 33);
		buttonRestoreData.addActionListener(handMainScreen);
		buttonRestoreData.setBackground(Color.decode("#4477CE"));
		contentPane.add(buttonRestoreData);
		
		matrixShowLabel = new JLabel("Biểu diễn bằng ma trận kề");
		matrixShowLabel.setForeground(Color.WHITE);
		matrixShowLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matrixShowLabel.setBounds(235, 21, 231, 25);
		contentPane.add(matrixShowLabel);
		
		JLabel thankForUsing = new JLabel("");
		thankForUsing.setIcon(new ImageIcon(MainScreen.class.getResource("/Image/haa.PNG")));
		thankForUsing.setBackground(new Color(255, 255, 255));
		thankForUsing.setHorizontalAlignment(SwingConstants.CENTER);
		thankForUsing.setForeground(new Color(232, 232, 232));
		thankForUsing.setFont(new Font("Tahoma", Font.PLAIN, 15));
		thankForUsing.setBounds(235, 347, 370, 91);
		thankForUsing.setOpaque(true);
		contentPane.add(thankForUsing);
		
		changeMatrixSet = new JButton("Chuyển qua ma trận");
		changeMatrixSet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		changeMatrixSet.setBounds(294, 449, 201, 77);
		changeMatrixSet.setForeground(Color.WHITE);
		changeMatrixSet.setBackground(Color.decode("#4477CE"));
		changeMatrixSet.setFocusable(false);
		changeMatrixSet.addActionListener(handMainScreen);
		contentPane.add(changeMatrixSet);
		
		imagePhu = new JLabel("New label");
		imagePhu.setIcon(new ImageIcon(MainScreen.class.getResource("/Image/Captur3e.PNG")));
		imagePhu.setBounds(426, 11, 179, 44);
		contentPane.add(imagePhu);
		
		setVisible(true);
	}
}
