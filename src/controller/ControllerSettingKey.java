package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SettingMainView;

public class ControllerSettingKey implements ActionListener{

	private SettingMainView setting;
	
	public ControllerSettingKey(SettingMainView setting) {
		this.setting = setting;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.setting.primButton.isSelected()) {
			this.setting.main.setTitle("Tìm cây khung cực tiểu");
			this.setting.updateLabel.setText("Selected: prim");
		}
		if(this.setting.dijkstraButton.isSelected()) {
			this.setting.main.setTitle("Ứng dụng tìm đường đi ngắn nhất v1.0");
			this.setting.updateLabel.setText("Selected: dijkstra");
		}
		if(this.setting.biGraphButton.isSelected()) {
			this.setting.main.setTitle("Kiểm tra đồ thị 2 phía");
			this.setting.updateLabel.setText("Selected: đồ thị 2 phía");
		}
	}

}
