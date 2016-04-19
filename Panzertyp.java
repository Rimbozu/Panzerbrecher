class Panzertyp {

	private int typNr, hp, dmg, maxhp, range;

	public Panzertyp(int typ) {
		typNr = typ;
		switch (typ) {
		case 0:
			hp = maxhp = 10;
			dmg = 5;
			range = 10;
			break;
		case 1:
			hp = maxhp = 15;
			dmg = 10;
			range = 10;
			break;
		case 2:
			hp = maxhp = 5;
			dmg = 10;
			range = 15;
			break;
		} // end of switch
	}

	public int getTypNr() {
		return typNr;
	}

	public int getHP() {
		return hp;
	}

	public int getmaxHP() {
		return maxhp;
	}

	public int getDMG() {
		return dmg;
	}

	public int getRange() {
		return range;
	}

	public void subHP(int damage) {
		hp -= damage;
	}

	public String toString() {
		switch (typNr) {
		case 0:
			return "Normaler Panzer, HP:  " + hp + ", DMG:  " + dmg
					+ ", Range: " + range;
		case 1:
			return "Schwerer Panzer, HP:  " + hp + ", DMG: " + dmg
					+ ", Range: " + range;
		case 2:
			return "Panzerjäger    , HP:   " + hp + ", DMG: " + dmg
					+ ", Range: " + range;
		default:
			return "Typ nicht vorhanden";

		} // end of switch
	}
}