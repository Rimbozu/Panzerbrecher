class Hindernis {

	private Position pos;
	private int art;

	public Hindernis(int h, int v, int art) {
		pos = new Position(h, v);
		this.art = art;
	}

	public int getArt() {
		return art;
	}

	public Position getPos() {
		return pos;
	}
}