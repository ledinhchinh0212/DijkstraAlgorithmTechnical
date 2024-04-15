package model.algorithm;

public class DFSAlgorithmMethod {
	private int Dinh;
	private int Canh;
	private final int maxn = 1001;
	public int array[][];
	private int color[]; // 1: RED, 2: BLUE
	private int start;
	
	public DFSAlgorithmMethod() {
		this.array = new int[maxn][maxn];
		this.color = new int[maxn];
		this.Dinh = this.Canh = this.start = 0;
	}
	
	public void updateArray() {
		if(Dinh != 0 && Canh != 0) {
			for(int i = 0; i <= Dinh; i++) {
				this.color[i] = -1;
			}
		}
	}
	
	public boolean DFS(int dinh, int mau) {
		color[dinh] = mau;
		for(int i = this.start; i < this.Dinh + this.start; i++) {
			if(this.array[dinh][i] != 0) {
				if(this.color[i] == -1) {
					if(!DFS(i, 3 - mau)) {
						return false;
					}
				}
				else if(this.color[i] == this.color[dinh]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean CheckLienThong() {
		for(int i = this.start; i < this.Dinh + this.start; i++) {
			if(this.color[i] == -1) {
				if(!DFS(i, 2)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean KiemTraDoThiHaiPhia() {
		updateArray();
		return CheckLienThong();
	}

	public int getDinh() {
		return Dinh;
	}

	public void setDinh(int dinh) {
		Dinh = dinh;
	}

	public int getCanh() {
		return Canh;
	}

	public void setCanh(int canh) {
		Canh = canh;
	}

	public int[][] getArray() {
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public int[] getColor() {
		return color;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
