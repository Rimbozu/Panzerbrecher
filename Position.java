class Position {

	private int y, x;

	public Position(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public String getPos() {
		String y = new String();
		String x = new String();
		y = y + this.y;
		x = x + this.x;

		if (y.length() == 1) {
			y = " " + y;
		}
		if (x.length() == 1) {
			x = " " + x;
		}
		return "(" + y + "," + x + ")";
	}

	public int getPosY() {
		return y;
	}

	public int getPosX() {
		return x;
	}

	public void addPosY(int a) {
		y += a;
	}

	public void addPosX(int a) {
		x += a;
	}
}