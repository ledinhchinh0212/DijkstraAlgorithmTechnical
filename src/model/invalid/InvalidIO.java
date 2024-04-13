package model.invalid;

public class InvalidIO {
	static public InvalidIO getInstance() {
		return new InvalidIO();
	}
	public String KhongHopLe() {
		return "Nhập không hợp lệ !";
	}
	public String CanhInvalid() {
		return "Chưa nhập cạnh";
	}
}
