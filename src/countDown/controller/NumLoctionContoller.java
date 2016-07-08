package countDown.controller;

public class NumLoctionContoller {
	private int x, y;
	private int number;
	
	public NumLoctionContoller() {}
	
	public NumLoctionContoller(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
