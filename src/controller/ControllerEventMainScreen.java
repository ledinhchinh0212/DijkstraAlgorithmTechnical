package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.algorithm.DijkstraAlgorithmMethod;
import model.data.Data;
import model.invalid.InvalidErrorCheck;
import model.invalid.InvalidIO;
import model.roadmap.RoadMap;
import view.AboutMeScreen;
import view.MainScreen;
import view.RoadMapScreen;
import view.SettingMain;

public class ControllerEventMainScreen implements KeyListener, ActionListener{
	private MainScreen main;
	private DijkstraAlgorithmMethod dijkstra;
	private Data data;
	private boolean checkOn;
	private boolean checkOnSetting;
	private AboutMeScreen aboutMe;
	private SettingMain setting;
	private RoadMap roadmap;
	private RoadMapScreen roadmapScreen ;
	
	public ControllerEventMainScreen(MainScreen main) {
		this.main = main;
		this.dijkstra = new DijkstraAlgorithmMethod();
		this.data = new Data();
		this.checkOn = false;
		this.checkOnSetting = false;
		this.aboutMe = new AboutMeScreen();
		this.setting = new SettingMain();
		this.roadmap = new RoadMap();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	// action
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == main.buttonActionWorkingMain) {
			
			dijkstra.setCharInHead(false);
			boolean check = main.directed.isSelected();
			boolean active = true;
			String cmp = main.textArea.getText();
			dijkstra.ResetArray();
			int[][] graph = dijkstra.ReturnArray();
			boolean InvalidError = InvalidErrorCheck.getInstance().CheckError(cmp, graph, check, dijkstra);
			boolean cHead = this.dijkstra.isCharInHead();
			
			
			
			if(InvalidError) { // part in area table
				System.out.println("Yes");
			} else {
				MessengerErrorL();
				System.out.println("NO --");
				active = false;
			}
			try {
				int N_Dinh = 0;
				int M_Canh = 0;
				N_Dinh =  Integer.parseInt(main.textFieldDinh.getText());
				M_Canh = Integer.parseInt(main.textFieldCanh.getText());
				this.dijkstra.setCanh(M_Canh);
				this.dijkstra.setDinh(N_Dinh);
				int start = -1;
				start = CheckStart(start, graph, N_Dinh);
				System.out.println("start: " + start);
				
				String SourceString = main.textFieldSourceV.getText();
				String TargetString = main.textFieldTargetV.getText();
				trimSpaces(SourceString);
				trimSpaces(TargetString);
				
				
				if(SourceString.isEmpty()) {
					MessengerErrorL();
					active = false;
				}
				else {
					if(TargetString.isEmpty()) {
						roadmap.setAllToTarget(true);
					}
					else {
						roadmap.setAllToTarget(false);
					}
				}
				int sourceInt = 0;
				if(cHead && InvalidErrorCheck.getInstance().CaseHead(SourceString)) {
					char src = SourceString.charAt(0);
					src -= 64;
					sourceInt = (int)src;
				}
				else {
					sourceInt = Integer.parseInt(SourceString);
				}
				if(cHead && InvalidErrorCheck.getInstance().CaseHead(TargetString)) {
					char tg = TargetString.charAt(0);
					tg -= 64;
					int num = (int)tg;
					roadmap.setTarget(num);
					System.out.println("Thanh cong" + roadmap.getTarget());
				}
				else if(!SolveSorceAndTarget(SourceString, TargetString) && !cHead) {
					MessengerErrorL();
					active = false;
				}
				else if(InvalidErrorCheck.getInstance().isAllDigits(TargetString)) {
					roadmap.setTarget(Integer.parseInt(TargetString));
				}
				// nếu 1 trong 2 thoả mãn là upper char và cHead
				// và nếu cả 2 đều cùng là chuỗi số hoặc upperString (nói riêng)
				// và target phải luôn luôn khác rỗng
				// thì active = true; A ab##
				
				roadmap.setSource(sourceInt);
				if(active) {
					roadmap.setDinh(N_Dinh);
					MatrixShowContext(true);
					BuildingTextLabel(graph, start, cHead);
					dijkstra.dijkstra(graph, sourceInt, start);
					if(this.roadmapScreen != null && this.roadmapScreen.isVisible()) {
						this.roadmapScreen.dispose();
					}
					roadmapScreen = new RoadMapScreen(this.dijkstra, start, roadmap, main, cHead);
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.println("Throw");
				MessengerErrorL();
			}
			LoadingDataField();
			
		}
		if(e.getSource() == main.buttonCLearData) {
			// xoa
			main.textFieldDinh.setText("");
			main.textFieldCanh.setText("");
			main.textFieldSourceV.setText("");
			main.textFieldTargetV.setText("");
			main.textArea.setText("");
			MatrixShowContext(false);
			main.testLabel.setText("Bảng ma trận");
			main.testLabel.setBackground(null);
		}
		if(e.getSource() == main.buttonRestoreData) {
			main.textFieldDinh.setText(data.getFieldDinh());
			main.textFieldCanh.setText(data.getFieldCanh());
			main.textFieldSourceV.setText(data.getFieldSourceV());
			main.textFieldTargetV.setText(data.getFieldTargetV());
			main.textArea.setText(data.getTextAreaInput());
		}
		// about me
		if(e.getSource() == main.buttonAboutMeMain) {
			if(!this.checkOn) {
				checkOn = true;
				aboutMe.setVis(checkOn);
			}
			else {
				checkOn = false;
				aboutMe.setVis(checkOn);
			}
		}
		if(e.getSource() == main.buttonSettingMain) {
			if(!this.checkOnSetting) {
				checkOnSetting = true;
				setting.setVis(checkOnSetting);
			}
			else {
				checkOnSetting = false;
				setting.setVis(checkOnSetting);
			}
		}
	}
	public void LoadingDataField() {
		// luu vao data;
		data.setFieldDinh(main.textFieldDinh.getText());
		data.setFieldCanh(main.textFieldCanh.getText());
		data.setFieldSourceV(main.textFieldSourceV.getText());
		data.setFieldTargetV(main.textFieldTargetV.getText());
		data.setTextAreaInput(main.textArea.getText());
	}
	
	public void BuildingTextLabel(int[][] graph, int start, boolean cHead) {
		// set backgroud before build
		main.testLabel.setBackground(Color.WHITE);
		main.testLabel.setOpaque(true);
		//
		if(start == 0) { 
			StringBuilder labelText = new StringBuilder("<html><pre>");
			for(int i = 0; i < this.dijkstra.getDinh(); i++) {
				for(int j = 0; j < this.dijkstra.getDinh(); j++) {
					labelText.append(graph[i][j] + "  ");
				}
				labelText.append("<br>");
			}
			labelText.append("<pre></html>");
			this.main.testLabel.setText(labelText.toString());
		}
		else if(start == 1) {
			StringBuilder labelText = new StringBuilder("<html><pre>");
			for(int i = 1; i <= this.dijkstra.getDinh(); i++) {
				for(int j = 1; j <= this.dijkstra.getDinh(); j++) {
					labelText.append(graph[i][j] + "  ");
				}
				labelText.append("<br>");
			}
			labelText.append("<pre></html>");
			this.main.testLabel.setText(labelText.toString());
		}
		
		
	}
	
	public void Test(boolean charInHead) {
		System.out.println(charInHead + "");
	}
	
	public void khongHopLe() {
		String eWhat = InvalidIO.getInstance().KhongHopLe();
		main.labelInvalid.setText(eWhat);
	}
	
	public void MatrixShowContext(boolean check) {
		if(check) {
			main.matrixShowLabel.setText("Biểu diễn bằng ma trận");
		}
		else {
			main.matrixShowLabel.setText("");
		}
	}
	
	public void MessengerErrorL() {
		khongHopLe();
		InvalidErrorCheck.getInstance().TimeSetErrorCPN(main.labelInvalid);
	}
	
	public boolean SolveSorceAndTarget(String Source, String Target) {
		if(!InvalidErrorCheck.getInstance().isAllDigits(Source)) {
			return false;
		}
		if(!InvalidErrorCheck.getInstance().isAllDigits(Target) && !Target.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public int CheckStart(int start, int[][] graph, int dinh) {
		boolean check = true;
		for(int i = 0; i < dinh; i++) {
			if(graph[0][i] != 0) {
				check = false;
				// start co the bang 0
			}
		}
		if(!check) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String trimSpaces(String s) {
	    return s.trim().replaceAll("\\s+", " ");
	}
}
