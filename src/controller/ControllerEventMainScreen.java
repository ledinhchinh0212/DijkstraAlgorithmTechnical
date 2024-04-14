package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import model.algorithm.DijkstraAlgorithmMethod;
import model.data.Data;
import model.invalid.InvalidErrorCheck;
import model.invalid.InvalidIO;
import model.roadmap.RoadMap;
import view.AboutMeScreen;
import view.MainScreen;
import view.MatrixShowFrame;
import view.RoadMapScreen;

public class ControllerEventMainScreen implements KeyListener, ActionListener{
	private MainScreen main;
	private DijkstraAlgorithmMethod dijkstra;
	private Data data;
	private boolean checkOn;
	private AboutMeScreen aboutMe;
	private RoadMap roadmap;
	private RoadMapScreen roadmapScreen ;
	private MatrixShowFrame mtr;
	private boolean MaTrix;
	private boolean activation;

	
	public boolean isActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	public ControllerEventMainScreen(MainScreen main) {
		this.main = main;
		this.dijkstra = new DijkstraAlgorithmMethod();
		this.checkOn = false;
		this.aboutMe = new AboutMeScreen();
		this.roadmap = new RoadMap();
		this.MaTrix = false;
		this.mtr = new MatrixShowFrame(main);
		this.data = new Data();
		this.activation = true;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public String getDuLieuTho(String s) {
		String copy = "";
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(ch == ' ' || ch == '\n') {
				continue;
			}
			copy += ch;
		}
		return copy;
	}
	
	public boolean CheckTextField() {
		String dinh = this.main.textFieldDinh.getText();
		String source = this.main.textFieldSourceV.getText();
		String target = this.main.textFieldTargetV.getText();
		if(source.isEmpty()) {
			return false;
		}
		if(dinh.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void ActionRoad() {
		dijkstra.setCharInHead(false);
		boolean check = main.directed.isSelected();
		boolean active = true;
		String cmp = main.textArea.getText();;
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
			SourceString = SourceString.trim();
			TargetString = TargetString.trim();
			
			
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
			
			roadmap.setSource(sourceInt);
			if(active) {
				roadmap.setDinh(N_Dinh);
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
	
	public void ActionRoadToMaTrix(String duLieu) {
		boolean active = true;
		System.out.println(duLieu);
		if(duLieu.isEmpty()) {
			active = false;
			this.main.labelInvalid.setText("Dữ liệu rỗng");
			InvalidErrorCheck.getInstance().TimeSetErrorCPN(this.main.labelInvalid);
		}
		else{
			duLieu = duLieu.trim();
			duLieu = duLieu + " ";
			
			String duLieuTho = getDuLieuTho(duLieu);
			System.out.println(duLieuTho + " : du lieu tho");
			if(!InvalidErrorCheck.getInstance().isAllDigits(duLieuTho)) {
				MessengerErrorL();
				active = false;
			}
			if(!CheckTextAreaHopLe(duLieu) && active) {
				active = false;
				MessengerErrorL();
			}
		}
		if(!CheckTextField() && active) {
			active = false;
			main.labelInvalid.setText("Nhập thiếu dữ kiện");
			InvalidErrorCheck.getInstance().TimeSetErrorCPN(main.labelInvalid);
		}
		int[][] arr = new int[1001][1001];
		if(active) {
			truyenMang(arr, duLieu);
		}
		if(!this.isActivation()) { // from truyen mang
			MessengerErrorL();
			active = false;
		}
		String source = this.main.textFieldSourceV.getText();
		String target = this.main.textFieldTargetV.getText();
		source = source.trim();
		target = target.trim();
		if(!InvalidErrorCheck.getInstance().isAllDigits(source)) {
			MessengerErrorL();
			active = false;
		}
		if(target.isEmpty() && active) {
			this.roadmap.setAllToTarget(true);
		}
		else if(!InvalidErrorCheck.getInstance().isAllDigits(target) && active) {
			MessengerErrorL();
			active = false;
		}
		if(active) {
			this.setActivation(true);
			String dinh = this.main.textFieldDinh.getText();
			int Dinh = Integer.parseInt(dinh);
			this.dijkstra.setDinh(Dinh);
			this.dijkstra.ResetArray();
			for(int i = 0; i < Dinh; i++) {
				for(int j = 0; j < Dinh; j++) {
					this.dijkstra.arr[i][j] = arr[i][j];
				}
			}
			for(int i = 0; i < Dinh; i++) {
				for(int j = 0; j < Dinh; j++) {
					System.out.print(this.dijkstra.arr[i][j] + " - ");
				}
				System.out.println();
			}
			BuildingTextLabel(this.dijkstra.arr, 0, false);
			int Source = Integer.parseInt(source);
			int Target = 0;
			if(!target.isEmpty()) {
				Target = Integer.parseInt(target);
			}
			this.roadmap.setDinh(Dinh);
			this.roadmap.setSource(Source);
			this.roadmap.setTarget(Target);
			this.dijkstra.dijkstra(arr, Source, 0);
			roadmapScreen = new RoadMapScreen(dijkstra, 0, roadmap, main, false);
			// hiển thị danh sách kề
			this.main.textArea.setText("");
			String copy = "";
			for(int i = 0; i < Dinh; i++) {
				for(int j = 0; j < Dinh; j++) {
					if(this.dijkstra.arr[i][j] > 0 &&  i > j) {
						copy = copy + i + " " + j + " " + this.dijkstra.arr[i][j] + "\n";
					}
				}
			}
			System.out.println("copy");
			System.out.println(copy);
			// xử lí chuỗi
			this.main.textArea.setText(copy);
			for(int i = 0; i < copy.length(); i++) {
				
			}
		}
	
	}
	
	public char convert(int i) {
		return (char) (i + 48);
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
			ActionRoad();
		}
		if(e.getSource() == main.buttonCLearData) {
			LoadingDataField();
			// xoa
			if(this.mtr.isVisible()) {
				this.mtr.textArea.setText("");
			}
			else {
				main.textFieldDinh.setText("");
				main.textFieldCanh.setText("");
				main.textFieldSourceV.setText("");
				main.textFieldTargetV.setText("");
				main.textArea.setText("");
				main.testLabel.setText("BẢNG MA TRẬN");
				main.testLabel.setBackground(Color.decode("#7BC9FF"));
			}
			
		}
		if(e.getSource() == main.buttonRestoreData) {
			if(data == null) {
				main.labelInvalid.setText("Dữ liệu trống");
				InvalidErrorCheck.getInstance().TimeSetErrorCPN(main.labelInvalid);
			}
			else if(data.getFieldDinh().isEmpty() && 
					data.getFieldCanh().isEmpty() &&
					data.getFieldSourceV().isEmpty() &&
					data.getFieldTargetV().isEmpty() &&
					data.getTextAreaInput().isEmpty()
					
				) {
				main.labelInvalid.setText("Không có dữ liệu");
				InvalidErrorCheck.getInstance().TimeSetErrorCPN(main.labelInvalid);
			}
			else {
				main.textFieldDinh.setText(data.getFieldDinh());
				main.textFieldCanh.setText(data.getFieldCanh());
				main.textFieldSourceV.setText(data.getFieldSourceV());
				main.textFieldTargetV.setText(data.getFieldTargetV());
				main.textArea.setText(data.getTextAreaInput());
				
			}
		}
		// about me
		if(e.getSource() == main.buttonAboutMeMain) {
			if(!this.checkOn) {
				this.main.buttonAboutMeMain.setBorder(BorderFactory.createLineBorder(Color.decode("#A3FFD6"), 2));
			}
			else {
				this.main.buttonAboutMeMain.setBorder(null);
			}
			this.aboutMe.setVis(!checkOn);
			this.checkOn = !this.checkOn;
		}
		if(e.getSource() == main.changeMatrixSet) {
			if(!this.MaTrix) {
				this.mtr.setData(data);
				VoHieuHoaField();
				// kiểm tra road map screen đã hiện hay chưa
				if(this.roadmapScreen != null && this.roadmapScreen.isVisible()) {
					this.roadmapScreen.setVisible(false);
				}
				//
				this.mtr.setVis(true);
				GiaoDienMaTran();
				
			}
			else {
				// press to exit screen and open another
				HuyVoHieuHoa();
				this.mtr.dispose();
				this.mtr.setVis(false);
				GiaoDienDanhSachKe();
				// check
				ActionRoadToMaTrix(this.mtr.data.getInputMatrix());
				this.mtr.data.setInputMatrix("");
			}
			this.MaTrix = !this.MaTrix;
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
		main.testLabel.setForeground(Color.black);
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
	
	public void khongHopLe() {
		String eWhat = InvalidIO.getInstance().KhongHopLe();
		main.labelInvalid.setText(eWhat);
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
	
	public void GiaoDienMaTran() {
		this.main.changeMatrixSet.setBackground(Color.decode("#A3FFD6"));
		this.main.changeMatrixSet.setForeground(Color.black);
		main.changeMatrixSet.setText("Nhập ma trận / Huỷ");
		this.main.matrixShowLabel.setText("Biểu diễn bằng danh sách kề");
		this.main.testLabel.setText("");
		this.main.testLabel.setBackground(Color.white);
	}
	public void GiaoDienDanhSachKe() {
		main.changeMatrixSet.setText("Chuyển sang ma trận");
		this.main.matrixShowLabel.setText("Biểu diễn bằng ma trận kề");
		this.main.testLabel.setText("BẢNG MA TRẬN");
		this.main.testLabel.setBackground(Color.decode("#7BC9FF"));
		this.main.changeMatrixSet.setForeground(Color.white);
		this.main.changeMatrixSet.setBackground(Color.decode("#4477CE"));
	}
	
	public void VoHieuHoaField() {
		this.main.textFieldCanh.setEditable(false);
		this.main.textFieldCanh.setBackground(Color.white);
		
		this.main.textFieldDinh.setBackground(Color.white);
		
		this.main.textFieldSourceV.setBackground(Color.white);
		
		this.main.textFieldTargetV.setBackground(Color.white);
		
		this.main.textArea.setEditable(false);
		this.main.textArea.setBackground(Color.decode("#7BC9FF"));
		
		this.main.labelDinh.setForeground(Color.decode("#A3FFD6"));
		this.main.labelSourceV.setForeground(Color.decode("#A3FFD6"));
		this.main.labelTargetV.setForeground(Color.decode("#A3FFD6"));
		
		this.main.buttonActionWorkingMain.setEnabled(false);
		
	}
	
	public void HuyVoHieuHoa() {
		this.main.textFieldCanh.setEditable(true);
		this.main.textFieldDinh.setEditable(true);
		this.main.textFieldSourceV.setEditable(true);
		this.main.textFieldTargetV.setEditable(true);
		this.main.textArea.setEditable(true);
		this.main.textArea.setBackground(Color.white);
		this.main.labelDinh.setForeground(Color.white);
		this.main.labelSourceV.setForeground(Color.white);
		this.main.labelTargetV.setForeground(Color.white);
		this.main.buttonActionWorkingMain.setEnabled(true);
	}

	// kiểm tra khuôn khổ (đỉnh, hàng .. )
	public boolean CheckTextAreaHopLe(String duLieu) {
		System.out.println("-------");
		System.out.println(duLieu);
		System.out.println("-------");
		int count = 0; int idx = 0;
		int demDinh = 0;
		for(int i = 0; i < duLieu.length(); i++) {
			if(duLieu.charAt(i) == '\n') {
				demDinh++;
				idx = i;
				break;
			}
			else if(duLieu.charAt(i) == ' ') {
				if(i == duLieu.length() - 1) {
					demDinh++;
				}
				continue;
			}
			else {
				if(i == duLieu.length() - 1 || duLieu.charAt(i + 1) == ' ' || duLieu.charAt(i + 1) == '\n') {
					idx = i;
					count++;
				}
			}
		}
		int bad = 0;
		//System.out.println(idx + 1 + " " + duLieu.charAt(idx + 1) + " " + count);
		System.out.println("end");
		boolean check = false;
		for(int i = idx + 1; i < duLieu.length(); i++) {
			if(duLieu.charAt(i) == '\n') {
				// update
				if(i != duLieu.length() - 1) {
					demDinh++;
				}
				bad++;
				if(bad != count) {
					System.out.println(bad + "my bad");
					return false;
				}
				else {
					bad = 0;
				}
				continue;
			}
			else if(duLieu.charAt(i) == ' ') {
				
				if(check || i == duLieu.length() - 1) {
					bad++;
					check = false;
				}
				continue;
			}
			else {
				check = true;
				if(i == duLieu.length() - 1) {
					bad++;
				}
			}
		}
		if(bad != count && bad != 0) {
			System.out.println(count + " : " + bad);
			return false;
		}
		System.out.println(count + " : count");
		System.out.println(bad + " : bad");
		System.out.println((demDinh + 1) + " : dinh");
		if(count != demDinh + 1) {
			return false;
		}
		return true;
	}
	
	// kiểm tra có đồng bộ chữ cái và số không (đồng thời thiết lập) // không cần dùng đến nữa
	public boolean CheckCHeadError(String duLieu, boolean cHead) {
		// sau khi thoả mãn điều kiện n x n hình
		// n x n không chênh lệch số lượng nhau
		/*
		 * mục tiêu:
		 * - xác định cHead (true, false)
		 * - xác định start
		 * - truyền vào mảng trong trường hợp cùng kiểu dữ liệu n x n
		 * */
//		int cmp = 0;
//		String sum = "";
//		boolean check = false;
//		for(int i = 0; i < duLieu.length(); i++) {
//			if(duLieu.charAt(i)== ' ' || duLieu.charAt(i) == '\n' || i == duLieu.length() - 1) {
//				if(!check) {
//					continue; // continue nếu chưa có ký tự nào cả
//				}
//				if(cmp == 0) {
//					if(InvalidErrorCheck.getInstance().isAllDigits(sum)) {
//						cmp = 1;
//					}
//					else if(InvalidErrorCheck.getInstance().isAllUpperCase(sum)) {
//						cmp = 2;
//					}
//					else {
//						cmp = -1;
//						return false;
//						// error;
//						// Không phải cả 2 loại trên
//					}
//				}
//				else if(cmp == 1) { // trước đó là chuỗi số
//					if(InvalidErrorCheck.getInstance().isAllUpperCase(sum)) {
//						cmp = -1;
//						return false;
//					}
//					if(!InvalidErrorCheck.getInstance().isAllDigits(sum)) {
//						cmp = -1;
//						return false;
//					}
//				}
//				else if(cmp == 2) { // trước đó là chữ cái in hoa
//					if(InvalidErrorCheck.getInstance().isAllDigits(sum)) {
//						cmp = -1;
//						return false;
//					}
//					if(!InvalidErrorCheck.getInstance().isAllUpperCase(sum)) {
//						cmp = -1;
//						return false;
//					}
//				}
//				// Xử lí phần start
//				sum = ""; // cập nhật lại sum
//				check = false;
//			}
//			else {
//				sum += duLieu.charAt(i);
//				check = true;
//			}
//		}
//		// xử lí phần start
//		
		return true;
	}

	// truyền mảng
	public void truyenMang(int arr[][], String duLieu) {
		// chỉ cần truyền mảng, đã xác định được start
		boolean check = false;
		String sum = "";
		int ix = 0, jx = 0;
		// kqtam source and target
		String dinh = this.main.textFieldDinh.getText();
		int Dinh = 0;
		dinh = dinh.trim();
		if(!InvalidErrorCheck.getInstance().isAllDigits(dinh)) {
			//ERROR CHECK
			System.out.println("ERROR CHECK DINH");
			this.setActivation(false);
			return;
		}
		else {
			Dinh = Integer.parseInt(dinh);
		}
		for(int i = 0; i < duLieu.length(); i++) {
			if(duLieu.charAt(i)== ' ' || duLieu.charAt(i) == '\n' || i == duLieu.length() - 1) {
				if(!check) {
					continue; // continue nếu chưa có ký tự nào cả
				}
				int num = Integer.parseInt(sum);
				arr[ix][jx] = num;
				jx++;
				if(jx >= Dinh) {
					ix++;
					jx = 0;
				}
				sum = ""; // cập nhật lại sum
				check = false;
			}
			else {
				sum += duLieu.charAt(i);
				check = true;
			}
		}
//		for(int i = 0; i < Dinh; i++) {
//			for(int j = 0; j < Dinh; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
	
	}
}