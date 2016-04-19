class Position {

	private int h, v;

	public Position(int h, int v) {
		this.h = h;
		this.v = v;
	}

	public String getPos() {
		String h = new String();
		String v = new String();
		h = h + this.h;
		v = v + this.v;

		if (h.length() == 1) {
			h = " " + h;
		}
		if (v.length() == 1) {
			v = " " + v;
		}
		return "(" + h + "," + v + ")";
	}

	public int getPosh() {
		return h;
	}

	public int getPosv() {
		return v;
	}

	public void addPosh(int a) {
		h += a;
	}

	public void addPosv(int a) {
		v += a;
	}
}