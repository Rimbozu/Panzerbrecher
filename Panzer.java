class Panzer {

	private Position pos;
	private Panzertyp typ;
	private int player;

	public Panzer(int y, int x, int player, int typ) {
		pos = new Position(y, x);
		this.player = player; // 1, 2
		this.typ = new Panzertyp(typ);
	}

	public Position getPos() {
		return pos;
	}

	public int getPlayer() {
		return player;
	}

	public Panzertyp getTyp() {
		return typ;
	}

	public void move(int r, int entf) {
		switch (r) {
		case 8:
			pos.addPosY(-entf);
			break;
		case 9:
			pos.addPosY(-entf);
			pos.addPosX(+entf);
			break;
		case 6:
			pos.addPosX(+entf);
			break;
		case 3:
			pos.addPosX(+entf);
			pos.addPosY(+entf);
			break;
		case 2:
			pos.addPosY(+entf);
			break;
		case 1:
			pos.addPosY(+entf);
			pos.addPosX(-entf);
			break;
		case 4:
			pos.addPosX(-entf);
			break;
		case 7:
			pos.addPosX(-entf);
			pos.addPosY(-entf);
			break;

		} // end of switch
	}

	public Position target(int r, int entf) {
		Position ziel = new Position(0, 0);
		switch (r) {
		case 8:
			ziel.addPosY(getPos().getPosY() - entf);
			ziel.addPosX(getPos().getPosX());
			break;
		case 9:
			ziel.addPosY(getPos().getPosY() - entf);
			ziel.addPosX(getPos().getPosX() + entf);
			break;
		case 6:
			ziel.addPosY(getPos().getPosY());
			ziel.addPosX(getPos().getPosX() + entf);
			break;
		case 3:
			ziel.addPosY(getPos().getPosY() + entf);
			ziel.addPosX(getPos().getPosX() + entf);
			break;
		case 2:
			ziel.addPosY(getPos().getPosY() + entf);
			ziel.addPosX(getPos().getPosX());
			break;
		case 1:
			ziel.addPosY(getPos().getPosY() + entf);
			ziel.addPosX(getPos().getPosX() - entf);
			break;
		case 4:
			ziel.addPosY(getPos().getPosY());
			ziel.addPosX(getPos().getPosX() - entf);
			break;
		case 7:
			ziel.addPosY(getPos().getPosY() - entf);
			ziel.addPosX(getPos().getPosX() - entf);
			break;
		} // end of switch

		return ziel;
	}
}