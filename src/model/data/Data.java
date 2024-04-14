package model.data;

import java.awt.Color;

import view.MainScreen;
import view.MatrixShowFrame;

public class Data {
	private String fieldCanh;
	private String fieldDinh;
	private String fieldSourceV;
	private String fieldTargetV;
	private String textAreaInput;
	
	private String inputMatrix;
	
	public Data() {
		this.inputMatrix = "";
		this.fieldCanh = this.fieldDinh = this.fieldSourceV = this.fieldTargetV = this.textAreaInput = "";
	}

	public String getFieldCanh() {
		return fieldCanh;
	}
	public void setFieldCanh(String fieldCanh) {
		this.fieldCanh = fieldCanh;
	}
	public String getFieldDinh() {
		return fieldDinh;
	}
	public void setFieldDinh(String fieldDinh) {
		this.fieldDinh = fieldDinh;
	}
	public String getFieldSourceV() {
		return fieldSourceV;
	}
	public void setFieldSourceV(String fieldSourceV) {
		this.fieldSourceV = fieldSourceV;
	}
	public String getFieldTargetV() {
		return fieldTargetV;
	}
	public void setFieldTargetV(String fieldTargetV) {
		this.fieldTargetV = fieldTargetV;
	}
	public String getTextAreaInput() {
		return textAreaInput;
	}
	public void setTextAreaInput(String textAreaInput) {
		this.textAreaInput = textAreaInput;
	}

	public String getInputMatrix() {
		return inputMatrix;
	}

	public void setInputMatrix(String inputMatrix) {
		this.inputMatrix = inputMatrix;
	}

	
	
}
