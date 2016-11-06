class Spielfeld {

	private int[][] feld;
	private Panzer[] PListe1;
	private Panzer[] PListe2;
	private Hindernis[] HListe;

	public Spielfeld() {
		this(20, 50);
	}

	public Spielfeld(int y, int x) {
		feld = new int[y][x];
	}

	public Spielfeld(int y, int x, Panzer[] Liste1, Panzer[] Liste2,
			Hindernis[] Liste3) {
		this(y, x);
		PListe1 = Liste1;
		PListe2 = Liste2;
		HListe = Liste3;
	}

	public int getSeiteX() {
		return feld.length;
	}

	public int getSeiteY() {
		return feld[0].length;
	}

	public int getPListe1() {
		return PListe1.length;
	}

	public int getPListe2() {
		return PListe2.length;
	}

	public int getHListe() {
		return HListe.length;
	}

	public void Panzerbewegen(Panzer[] Panzerliste1, Panzer[] Panzerliste2,
			int auswahl, int richtung, int kraft, Infofenster IFenster) {
		int hi = 0;
		int[] pg;
		int[] pf;
		Position ziel;
		IFenster.clearString2(10);

		for (int k = 1; k <= kraft; k++) {
			ziel = new Position(Panzerliste1[auswahl].target(richtung, k)
					.getPosY(), Panzerliste1[auswahl].target(richtung, k)
					.getPosX());
			for (int i = 0; i < HListe.length; i++) {
				if (ziel.getPosY() == HListe[i].getPos().getPosY()
						&& ziel.getPosX() == HListe[i].getPos().getPosX()) {
					hi = k;
					break;
				} // end of if
			} // end of for

			if (ziel.getPosY() < 0 || ziel.getPosY() >= getSeiteX()
					|| ziel.getPosX() < 0 || ziel.getPosX() >= getSeiteY()) {
				Panzerliste1[auswahl].move(richtung, (k - 1));
				Panzerliste1[auswahl].getTyp().subHP(kraft / 2);
				IFenster.writeString2(
						"Du bist gegen ein den Kartenrand gefahren und hast "
								+ kraft / 2 + " Schaden genommen!", 0);
				if (Panzerliste1[auswahl].getTyp().getHP() <= 0) {
					IFenster.writeString2("Eigener Panzer wurde zerstört!", 1);
				} // end of if
				break;
			} // end of if

			pg = HelpFunktion.TestPanzer(ziel, Panzerliste2, k); // Gegnerischer
																	// Panzer
																	// Test
			pf = HelpFunktion.TestPanzer(ziel, Panzerliste1, k); // eigener
																	// Panzer
																	// Test

			if (hi > 0) { // Hindernis gerammt
				Panzerliste1[auswahl].move(richtung, hi - 1);
				Panzerliste1[auswahl].getTyp().subHP(kraft / 2);
				IFenster.writeString2(
						"Du bist gegen ein Hindernis gefahren und hast "
								+ kraft / 2 + " Schaden genommen!", 0);
				if (Panzerliste1[auswahl].getTyp().getHP() <= 0) {
					IFenster.writeString2("Eigener Panzer wurde zerstört!", 1);
				} // end of if
				break;
			} // end of if

			if (pg[0] > 0) { // Gegner gerammt
				Panzerliste1[auswahl].move(richtung, pg[0] - 1);
				Panzerliste1[auswahl].getTyp().subHP(kraft / 3);
				Panzerliste2[pg[1]].getTyp().subHP(kraft / 2);
				IFenster.writeString2(
						"Du bist gegen einen gegnerischen Panzer gefahren und hast "
								+ kraft / 3 + " Schaden genommen!", 0);
				IFenster.writeString2("Der gegnerische Panzer hat " + kraft / 2
						+ " Schaden genommen!", 1);
				if (Panzerliste1[auswahl].getTyp().getHP() <= 0) {
					IFenster.writeString2("Eigener Panzer wurde zerstört!", 2);
				} // end of if
				if (Panzerliste2[pg[1]].getTyp().getHP() <= 0) {
					IFenster.writeString2(
							"Gegnerischer Panzer wurde zerstört!", 2);
				} // end of if
				break;
			} // end of if

			if (pf[0] > 0) { // eigenen gerammt
				Panzerliste1[auswahl].move(richtung, pf[0] - 1);
				Panzerliste1[auswahl].getTyp().subHP(kraft / 3);
				Panzerliste1[pf[1]].getTyp().subHP(kraft / 2);
				IFenster.writeString2(
						"Du bist gegen einen eigenen Panzer gefahren und hast "
								+ kraft / 3 + " Schaden genommen!", 0);
				IFenster.writeString2("Der andere Panzer hat " + kraft / 2
						+ " Schaden genommen!", 1);
				if (Panzerliste1[auswahl].getTyp().getHP() <= 0) {
					IFenster.writeString2("Eigener Panzer wurde zerstört!", 2);
				} // end of if
				if (Panzerliste1[pf[1]].getTyp().getHP() <= 0) {
					IFenster.writeString2("Anderer Panzer wurde zerstört!", 2);
				} // end of if
				break;
			} // end of if

			if (k == kraft) { // nichts gerammt
				Panzerliste1[auswahl].move(richtung, kraft);
				break;
			} // end of if
		} // end of for
	}

	public void Panzerschiessen(Panzer[] Panzerliste1, Panzer[] Panzerliste2,
			int auswahl, int richtung, int kraft, Infofenster IFenster) {
		int hi = 0;
		int[] pg;
		int[] pf;
		Position ziel;
		IFenster.clearString2(10);

		for (int k = 1; k <= kraft; k++) {
			ziel = new Position(Panzerliste1[auswahl].target(richtung, k)
					.getPosY(), Panzerliste1[auswahl].target(richtung, k)
					.getPosX());

			for (int i = 0; i < HListe.length; i++) { // Hinderniss Test
				if (HListe[i].getArt() == 0) {
					if (ziel.getPosY() == HListe[i].getPos().getPosY()
							&& ziel.getPosX() == HListe[i].getPos().getPosX()) {
						hi = k;
						break;
					} // end of if
				} // end of if
			} // end of for

			pg = HelpFunktion.TestPanzer(ziel, Panzerliste2, k); // Gegnerischer
																	// Panzer
																	// Test
			pf = HelpFunktion.TestPanzer(ziel, Panzerliste1, k); // eigener
																	// Panzer
																	// Test

			if (ziel.getPosY() < 0 || ziel.getPosY() >= getSeiteX()
					|| ziel.getPosX() < 0 || ziel.getPosX() >= getSeiteY()) {
				IFenster.writeString2(
						"Du hast gegen den Kartenrand geschossen!", 0);
				break;
			} // end of if

			if (hi > 0) { // Hindernis getroffen
				IFenster.writeString2(
						"Du hast gegen ein Hindernis geschossen!", 0);
				break;
			} // end of if

			if (pg[0] > 0) { // Gegner getroffen
				Panzerliste2[pg[1]].getTyp().subHP(
						Panzerliste1[auswahl].getTyp().getDMG() * k / kraft);

				IFenster.writeString2(
						"Du hast einen gegnerischen Panzer getroffen und "
								+ Panzerliste1[auswahl].getTyp().getDMG() * k
								/ kraft + " Schaden gemacht.", 0);
				if (Panzerliste2[pg[1]].getTyp().getHP() <= 0) {
					IFenster.writeString2("Der Panzer wurde zerstört!", 1);
				} // end of if
				break;
			} // end of if

			if (pf[0] > 0) { // eigenen getroffen
				Panzerliste2[pf[1]].getTyp().subHP(
						Panzerliste1[auswahl].getTyp().getDMG() * k / kraft);

				IFenster.writeString2(
						"Du hast einen gegnerischen Panzer getroffen und "
								+ Panzerliste1[auswahl].getTyp().getDMG() * k
								/ kraft + " Schaden gemacht.", 0);
				if (Panzerliste1[pf[1]].getTyp().getHP() <= 0) {
					IFenster.writeString2("Der Panzer wurde zerstört!", 1);
				} // end of if
				break;
			} // end of if

			if (k == kraft) { // nichts getroffen
				IFenster.writeString2("Du hast nichts getroffen.", 0);
				break;
			} // end of if
		} // end of for
	}

	public void print() {
		String bild = new String();
		boolean belegt = false;

		bild = "  ";

		for (int i = 0; i < getSeiteY(); i++) {
			if (i % 5 == 0) {
				bild = bild + i / 5;
			} else {
				bild = bild + " ";
			} // end of if-else
		} // end of for

		System.out.println("\n" + bild);
		bild = "";

		for (int i = 0; i < getSeiteY(); i++) {
			bild = bild + "+";
		} // end of for

		System.out.println(" +" + bild + "+");
		bild = "";

		for (int i = 0; i < getSeiteX(); i++) {
			for (int j = 0; j < getSeiteY(); j++) {
				belegt = false;
				Position hier = new Position(i, j);

				for (int t = 0; t < getPListe1(); t++) {
					if (hier.getPosY() == PListe1[t].getPos().getPosY()
							&& hier.getPosX() == PListe1[t].getPos().getPosX()) {
						if (PListe1[t].getTyp().getHP() <= 0) {
							bild = bild + "h";
						} else {
							switch (PListe1[t].getTyp().getTypNr()) {
							case 0:
								if (PListe1[t].getTyp().getHP() == PListe1[t]
										.getTyp().getmaxHP()) {
									bild = bild + "A";
									break;
								} // end of if
								bild = bild + "a";
								break;
							case 1:
								if (PListe1[t].getTyp().getHP() == PListe1[t]
										.getTyp().getmaxHP()) {
									bild = bild + "B";
									break;
								} // end of if
								bild = bild + "b";
								break;
							case 2:
								if (PListe1[t].getTyp().getHP() == PListe1[t]
										.getTyp().getmaxHP()) {
									bild = bild + "C";
									break;
								} // end of if
								bild = bild + "c";
								break;

							} // end of switch
						} // end of if-else
						belegt = true;
						break;
					}
				} // end of for

				if (belegt == false) {
					for (int t = 0; t < getPListe2(); t++) {
						if (hier.getPosY() == PListe2[t].getPos().getPosY()
								&& hier.getPosX() == PListe2[t].getPos()
										.getPosX()) {
							if (PListe2[t].getTyp().getHP() <= 0) {
								bild = bild + "h";
							} else {
								switch (PListe2[t].getTyp().getTypNr()) {
								case 0:
									if (PListe2[t].getTyp().getHP() == PListe2[t]
											.getTyp().getmaxHP()) {
										bild = bild + "N";
										break;
									} // end of if
									bild = bild + "n";
									break;
								case 1:
									if (PListe2[t].getTyp().getHP() == PListe2[t]
											.getTyp().getmaxHP()) {
										bild = bild + "O";
										break;
									} // end of if
									bild = bild + "o";
									break;
								case 2:
									if (PListe2[t].getTyp().getHP() == PListe2[t]
											.getTyp().getmaxHP()) {
										bild = bild + "P";
										break;
									} // end of if
									bild = bild + "p";
									break;
								} // end of switch
							} // end of if-else
							belegt = true;
							break;
						}
					} // end of for
				} // end of if

				if (belegt == false) {
					for (int t = 0; t < getHListe(); t++) {
						if (hier.getPosY() == HListe[t].getPos().getPosY()
								&& hier.getPosX() == HListe[t].getPos()
										.getPosX()) {

							switch (HListe[t].getArt()) {
							case 0:
								bild = bild + "H";
								break;
							case 1:
								bild = bild + "F";
								break;
							} // end of switch

							belegt = true;
							break;
						}
					} // end of for
				} // end of if

				if (belegt == false) {
					bild = bild + " ";
				} // end of if
			} // end of for

			if (i % 5 == 0) {
				bild = i / 5 + "+" + bild + "+" + i / 5;
			} else {
				bild = " +" + bild + "+";
			} // end of if-else

			System.out.println(bild);
			bild = "";
		} // end of for

		for (int i = 0; i < getSeiteY(); i++) {
			bild = bild + "+";
		} // end of for

		System.out.println(" +" + bild + "+");

		bild = "  ";

		for (int i = 0; i < getSeiteY(); i++) {
			if (i % 5 == 0) {
				bild = bild + i / 5;
			} else {
				bild = bild + " ";
			} // end of if-else
		} // end of for

		System.out.println(bild + "\n");

	}

	public void printLegende() {
		System.out.println("\t\t\tSpieler 1 \tSpieler 2");
		System.out.println("Normaler Panzer: \t A \t\t N");
		System.out.println("Schwerer Panzer: \t B \t\t O");
		System.out.println("Panzerjaeger: \t\t C \t\t P");
	}

}
