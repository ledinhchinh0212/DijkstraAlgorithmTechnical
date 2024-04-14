package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.algorithm.DijkstraAlgorithmMethod;
import model.roadmap.RoadMap;

import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;

public class RoadMapScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private MainScreen main; // check
	private JLabel targetLabelScreen;
	private DijkstraAlgorithmMethod dijkstra;
	private int start;
	private RoadMap roadmap;
	private JLabel sourceLabelScreen;
	private boolean cHead;

	public RoadMapScreen(DijkstraAlgorithmMethod dijkstra, int start, RoadMap roadmap, MainScreen main, boolean cHead) {
		this.dijkstra = dijkstra;
		this.start = start;
		this.roadmap = roadmap;
		//this.main = main;
		this.cHead = cHead;
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoadMapScreen.class.getResource("/Image/website-builder.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBounds(50, 100, 462, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel sourceLabel = new JLabel("Source: ");
		sourceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sourceLabel.setBounds(10, 11, 64, 39);
		contentPane.add(sourceLabel);
		
		sourceLabelScreen = new JLabel("");
		sourceLabelScreen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sourceLabelScreen.setBounds(84, 11, 64, 39);
		contentPane.add(sourceLabelScreen);
		
		targetLabelScreen = new JLabel("");
		targetLabelScreen.setBackground(new Color(255, 255, 255));
		targetLabelScreen.setHorizontalAlignment(SwingConstants.CENTER);
		targetLabelScreen.setFont(new Font("Tahoma", Font.BOLD, 16));
		targetLabelScreen.setBounds(10, 46, 426, 371);
		targetLabelScreen.setOpaque(true);
		contentPane.add(targetLabelScreen);
		setVisible(true);
		printSource();
		if(this.roadmap.isAllToTarget()) {
			printTargetVetex();
		}
		else {
			printOneTargetVetex();
		}
	}
	
	public void printSource() {
		if(cHead) {
			char ch = this.roadmap.CHeadUpperChar(this.roadmap.getSource());
			this.sourceLabelScreen.setText(ch + "");
		}
		else {
			this.sourceLabelScreen.setText(this.roadmap.getSource() + "");
		}
	}

	public void printOneTargetVetex() {
		int i = this.roadmap.getTarget();
		StringBuilder labelText = new StringBuilder("<html><pre>");
		if(this.cHead) {
			char ch = ' '; 
			ch = this.roadmap.CHeadUpperChar(i);
			labelText.append("To " + ch + ": ");
		}
		else {
			labelText.append("To " + i + ": ");
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		int t = i;
		System.out.println(i + " " + this.roadmap.getN());
		System.out.println(this.roadmap.getTarget());
		if(this.dijkstra.dist[i] == Integer.MAX_VALUE ||
			i > this.roadmap.getN() || this.roadmap.getTarget() > this.roadmap.getN()) {
			
			System.out.println("Lon hon");
			labelText.append("(Không có đường đi)");
			labelText.append("<br>");
		}
		else {
			while(roadmap.getSource() != t) {
				list.add(t);
				if(this.dijkstra.parent[t] != -1) {
					t = this.dijkstra.parent[t];
				}
				else {
					break;
				}
			}
			list.add(roadmap.getSource());
			labelText.append("\t");
			for (int j = list.size() - 1; j >= 0; j--) {
				if(cHead) {
					char cha = this.roadmap.CHeadUpperChar(list.get(j));
					labelText.append(cha + " ");
				}
				else {
					labelText.append(list.get(j) + " ");
					System.out.println(this.dijkstra.dist[list.get(j)]);
				}
			}
			labelText.append("(Chi phí " + this.dijkstra.dist[i] + ")");
			labelText.append("<br>");
			labelText.append("<pre></html>");
		}
		this.targetLabelScreen.setText(labelText.toString());
	}
	
	public void printTargetVetex() {
		int N = this.dijkstra.getDinh();
		if(start == 0) {
			StringBuilder labelText = new StringBuilder("<html><pre>");
			for (int i = 0; i < N; i++) {
				if(i == roadmap.getSource()) {
					continue;
				}
				if(this.cHead) {
					char ch = ' ';
					ch = this.roadmap.CHeadUpperChar(i);
					labelText.append("To " + ch + ": ");
				}
				else {
					labelText.append("To " + i + ": ");
				}
				//labelText.append("To " + i + ": ");
				//labelText.append("<br>");
				ArrayList<Integer> list = new ArrayList<Integer>();
				int t = i;
				if(this.dijkstra.dist[i] == Integer.MAX_VALUE) {
					labelText.append("(Không có đường đi)");
					labelText.append("<br>");
					continue;
				}
				while(roadmap.getSource() != t) {
					list.add(t);
					if(this.dijkstra.parent[t] != -1) {
						t = this.dijkstra.parent[t];
					}
					else {
						break;
					}
				}
				list.add(roadmap.getSource());
				//System.out.print("Path: ");
				labelText.append("\t");
				for (int j = list.size() - 1; j >= 0; j--) {
					if(cHead) {
						char cha = this.roadmap.CHeadUpperChar(list.get(j));
						labelText.append(cha + " ");
					}
					else {
						labelText.append(list.get(j) + " ");
					}
				}
				labelText.append("(Chi phí " + this.dijkstra.dist[i] + ")");
				labelText.append("<br>");
			}
			labelText.append("<pre></html>");
			this.targetLabelScreen.setText(labelText.toString());
		}
		else if(start == 1) {
			StringBuilder labelText = new StringBuilder("<html><pre>");
			for (int i = 1; i <= N; i++) {
				if(i == roadmap.getSource()) {
					continue;
				}
				if(this.cHead) {
					char ch = ' ';
					ch = this.roadmap.CHeadUpperChar(i);
					labelText.append("To " + ch + ": ");
				}
				else {
					labelText.append("To " + i + ": ");
				}
				//labelText.append("<br>");
				ArrayList<Integer> list = new ArrayList<Integer>();
				int t = i;
				if(this.dijkstra.dist[i] == Integer.MAX_VALUE) {
					labelText.append("(Không có đường đi)");
					labelText.append("<br>");
					continue;
				}
				while(roadmap.getSource() != t) {
					list.add(t);
					if(this.dijkstra.parent[t] != -1) {
						t = this.dijkstra.parent[t];
					}
					else {
						break;
					}
				}
				list.add(roadmap.getSource());
				//System.out.print("Path: ");
				labelText.append("\t");
				for (int j = list.size() - 1; j >= 0; j--) {
					if(cHead) {
						char cha = this.roadmap.CHeadUpperChar(list.get(j));
						labelText.append(cha + " ");
					}
					else {
						labelText.append(list.get(j) + " ");
					}
				}
				labelText.append("(Chi phí " + this.dijkstra.dist[i] + ")");
				labelText.append("<br>");
			}
			labelText.append("<pre></html>");
			this.targetLabelScreen.setText(labelText.toString());
		}
	}
}
