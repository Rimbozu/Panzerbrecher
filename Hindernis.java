class Hindernis {

	private Position pos;
	private int art;

	public Hindernis(int y, int x, int art) {
		pos = new Position(y, x);
		this.art = art;
	}

	// 0 = Berg
	// 1 = Fluss
	public int getArt() {
		return art;
	}

	public Position getPos() {
		return pos;
	}
}