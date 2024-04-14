package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.data.Data;
import view.MainScreen;
import view.MatrixShowFrame;

public class ControllerMatrixScreenKey implements KeyListener{
	private MatrixShowFrame showFrame;
	
	public ControllerMatrixScreenKey(MatrixShowFrame showFrame) {
		this.showFrame = showFrame;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String query = this.showFrame.textArea.getText();
		this.showFrame.data.setInputMatrix(query);
		System.out.println(this.showFrame.data.getInputMatrix());
	}
}
