package model.roadmap;

public class RoadMap {
	private boolean allToTarget;
	private int source;
	private int target;
	private int N;
	
	public int getN() {
		return N;
	}

	public void setDinh(int n) {
		N = n;
	}

	public RoadMap() {
		
	}

	public boolean isAllToTarget() {
		return allToTarget;
	}

	public void setAllToTarget(boolean allToTarget) {
		this.allToTarget = allToTarget;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}
	
	
	public char CHeadUpperChar(int i) {
		return (char) ('A' + (i - 1));
	}
	
}
