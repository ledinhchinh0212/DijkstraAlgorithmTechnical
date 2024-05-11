package view;

import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AboutMeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean vis = true;

	public boolean isVis() {
		return vis;
	}

	public void setVis(boolean vis) {
		this.vis = vis;
		this.setVisible(vis);
	}

	public AboutMeScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutMeScreen.class.getResource("/Image/website-builder.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(95, 100, 368, 200);
		this.setTitle("About me");
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 332, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel faceScreenInformation = new JLabel("New label");
		faceScreenInformation.setIcon(new ImageIcon(AboutMeScreen.class.getResource("/Image/chinhjpg (2).jpg")));
		faceScreenInformation.setBounds(0, 0, 77, 69);
		panel.add(faceScreenInformation);
		
		JLabel labelMeScreen = new JLabel("Lê Đình Chinh");
		labelMeScreen.setBackground(new Color(255, 255, 255));
		labelMeScreen.setHorizontalAlignment(SwingConstants.CENTER);
		labelMeScreen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelMeScreen.setBounds(75, 0, 257, 69);
		labelMeScreen.setBackground(Color.WHITE);
		labelMeScreen.setOpaque(true);
		panel.add(labelMeScreen);
		
		JLabel myGitHudLabel = new JLabel("My GitHub:");
		myGitHudLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myGitHudLabel.setBounds(10, 91, 83, 31);
		contentPane.add(myGitHudLabel);
		
		JLabel linkGitHub = new JLabel("<html><a href='https://github.com/ledinhchinh0212'>ledinhchinh0212</a></html>");
		linkGitHub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		linkGitHub.setHorizontalAlignment(SwingConstants.LEFT);
		linkGitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
		linkGitHub.setBounds(103, 97, 246, 21);
		linkGitHub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new java.net.URI("https://github.com/ledinhchinh0212"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
		contentPane.add(linkGitHub);
	}
}