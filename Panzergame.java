import java.util.Scanner;

public class Panzergame {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Infofenster IFenster = new Infofenster();
		EventManager event = new EventManager();

		boolean richtig = true;
		int y = 0, x = 0, c, PAnz, HAnz, Py, Px, spiel = 0;
		int[] TypenWert = new int[4];
		int Restpunkte = 0;
		int T1P1, T2P1, T3P1, T1P2, T2P2, T3P2;
		String[] tstring = new String[20];

		Hindernis[] Hindernisliste;
		Panzer[] PanzerlisteP1, PanzerlisteP2;

		tstring[0] = "Willkommen zum Panzerspiel.";
		tstring[1] = "2 Spieler Spielmodus";
		tstring[2] = "Wie Breit soll das Spielfeld sein?";
		IFenster.writeString(tstring);

		y = HelpFunktion.EingabeInt(5, 20, IFenster, 2); // Spielfeldgröße
															// abfragen

		IFenster.writeString("Wie Lang soll das Spielfeld sein?", 2);
		x = HelpFunktion.EingabeInt(y, 30, IFenster, 2);

		c = x * y;

		IFenster.writeString(
				"Wie wie viele Panzerpunkte möchten sie haben? (wenig-viele)",
				2); // Panzeranzahl
					// abfragen
		PAnz = HelpFunktion.EingabeInt(1, c / 20, IFenster, 2);

		IFenster.writeString("\nWie wie viele Hindernisse möchten sie haben?",
				2); // Hindernisse
					// abfragen

		HAnz = HelpFunktion.EingabeInt(0, c / 10, IFenster, 2);

		TypenWert[0] = c;
		TypenWert[1] = c / (PAnz * 2);
		TypenWert[2] = TypenWert[1] * 2;
		TypenWert[3] = TypenWert[1] / 10 * 15;

		tstring[0] = "Spieler 1 Panzerauswahl:"; // Panzerauswahl Spieler 1

		HelpFunktion.showPanzertypen(TypenWert, tstring);
		IFenster.writeString(tstring);
		IFenster.writeString("Anzahl Typ 1 Panzer:", 5);
		T1P1 = HelpFunktion.EingabeInt(0, TypenWert[0] / TypenWert[1],
				IFenster, 5);
		Restpunkte = TypenWert[0] - T1P1 * TypenWert[1];
		IFenster.writeString(Restpunkte + " Punkte noch vorhanden", 5);
		IFenster.writeString("Anzahl Typ 2 Panzer:", 6);
		T2P1 = HelpFunktion.EingabeInt(0, (Restpunkte) / TypenWert[2],
				IFenster, 6);
		Restpunkte -= T2P1 * TypenWert[2];
		IFenster.writeString(Restpunkte + " Punkte noch vorhanden", 5);
		IFenster.writeString("Anzahl Typ 3 Panzer:", 6);
		T3P1 = HelpFunktion.EingabeInt((Restpunkte) / TypenWert[3],
				(Restpunkte) / TypenWert[3], IFenster, 6);

		Restpunkte = TypenWert[0];
		IFenster.writeString("Spieler 2 Panzerauswahl:", 0); // Panzerauswahl
																// Spieler 2

		HelpFunktion.showPanzertypen(TypenWert);

		IFenster.writeString("Anzahl Typ 1 Panzer:", 5);
		T1P2 = HelpFunktion.EingabeInt(0, TypenWert[0] / TypenWert[1],
				IFenster, 5);
		Restpunkte -= T1P2 * TypenWert[1];
		IFenster.writeString(Restpunkte + " Punkte noch vorhanden", 5);
		IFenster.writeString("Anzahl Typ 2 Panzer:", 6);
		T2P2 = HelpFunktion.EingabeInt(0, (Restpunkte) / TypenWert[2],
				IFenster, 6);
		Restpunkte -= T2P2 * TypenWert[2];
		IFenster.writeString(Restpunkte + " Punkte noch vorhanden", 5);
		IFenster.writeString("Anzahl Typ 3 Panzer:", 6);
		T3P2 = HelpFunktion.EingabeInt((Restpunkte) / TypenWert[3],
				(Restpunkte) / TypenWert[3], IFenster, 6);

		PanzerlisteP1 = new Panzer[T1P1 + T2P1 + T3P1]; // 1. Inizialisieren
		PanzerlisteP2 = new Panzer[T1P2 + T2P2 + T3P2];
		Hindernisliste = new Hindernis[HAnz];

		Spielfeld Feld1 = new Spielfeld(y, x, PanzerlisteP1, PanzerlisteP2,// ConsolenFeld
																			// erzeugen
				Hindernisliste);
		FeldGrafik Fenster = new FeldGrafik(y, x, PanzerlisteP1, PanzerlisteP2,// Grafikfeld
																				// erzeugen
				Hindernisliste);

		Fenster.register(event);
		Fenster.register(IFenster);
		IFenster.register(event);
		IFenster.register(Fenster);
		event.register(Fenster);
		event.register(IFenster);

		for (int i = 0; i < PanzerlisteP1.length; i++) { // Panzer Spieler 1
															// erzeugen

			Py = (int) (Math.random() * y);
			Px = (int) (Math.random() * (x * 30 / 100));

			richtig = HelpFunktion.checkListeP(Py, Px, i, PanzerlisteP1);
			if (richtig) {

				if (i + 1 <= T1P1) {
					PanzerlisteP1[i] = new Panzer(Py, Px, 1, 0);
				} else {
					if (i + 1 - T1P1 <= T2P1) {
						PanzerlisteP1[i] = new Panzer(Py, Px, 1, 1);
					} else {
						PanzerlisteP1[i] = new Panzer(Py, Px, 1, 2);
					}// end of if-else
				} // end of if-else

			} else {
				i -= 1;
			} // end of if-else
			richtig = true;
		} // end of for

		richtig = true;
		Py = Px = 0;

		for (int i = 0; i < PanzerlisteP2.length; i++) { // Panzer Spieler 2
															// erzeugen
			Py = (int) (Math.random() * y);
			Px = (int) ((Math.random() * (x * 30 / 100)) + (x * 70 / 100));

			richtig = HelpFunktion.checkListeP(Py, Px, PanzerlisteP1.length,
					PanzerlisteP1);
			if (richtig) {
				richtig = HelpFunktion.checkListeP(Py, Px, i, PanzerlisteP2);
				if (richtig) {
					if (i + 1 <= T1P2) {
						PanzerlisteP2[i] = new Panzer(Py, Px, 2, 0);
					} else {
						if (i + 1 - T1P2 <= T2P2) {
							PanzerlisteP2[i] = new Panzer(Py, Px, 2, 1);
						} else {
							PanzerlisteP2[i] = new Panzer(Py, Px, 2, 2);
						}// end of if-else
					} // end of if-else

				} else {
					i -= 1;
				} // end of if-else
			} else {
				i -= 1;
			} // end of if-else
			richtig = true;
		} // end of for

		richtig = true;
		Py = Px = 0;

		// Hindernisse erzeugen
		for (int i = 0; i < HAnz; i++) {
			Py = (int) (Math.random() * y);
			Px = (int) (Math.random() * (x * 70 / 100) + (x * 15 / 100));

			richtig = HelpFunktion.checkListeP(Py, Px, PanzerlisteP1.length,
					PanzerlisteP1);
			if (richtig) {
				richtig = HelpFunktion.checkListeP(Py, Px,
						PanzerlisteP2.length, PanzerlisteP2);
				if (richtig) {
					richtig = HelpFunktion.checkListeH(Py, Px, i,
							Hindernisliste);
					if (richtig) {

						Hindernisliste[i] = new Hindernis(Py, Px,
								(int) (Math.random() * 2));
					} else {
						i -= 1;
					} // end of if-else
				} else {
					i -= 1;
				} // end of if-else
			} else {
				i -= 1;
			} // end of if-else
			richtig = true;
		} // end of for

		Feld1.print(); // Feld Ausgeben
		Feld1.printLegende();

		while (true) { // Spielbegin
			IFenster.clearString(10);
			IFenster.clearString2(10);
			spiel += 1;
			int spieler, action, auswahl = 0;

			if (spiel % 2 == 1) {
				spieler = 1;
			} else {
				spieler = 2;
			} // end of if-else

			IFenster.setPlayer(spieler);
			while (true) {
				try {
					System.err.wait(100);
				} catch (Exception e) {
					if (IFenster.getHelpbool()) {
						auswahl = IFenster.getAuswahl();
						IFenster.setHelpbool();
						break;
					}
				}
			}
			System.out.println("Auswahlwert:" + auswahl);
			// if (spiel % 2 == 1) {
			// auswahl = HelpFunktion.showPanzerliste(PanzerlisteP1, input);
			// } else {
			// auswahl = HelpFunktion.showPanzerliste(PanzerlisteP2, input);
			// } // end of if-else

			System.out.println("Panzer bewegen(0) oder schießen(1)?"); // Aktionsparameter

			while (true) {
				try {
					System.err.wait(100);
				} catch (Exception e) {
					if (IFenster.getHelpbool()) {
						action = IFenster.getAktion();
						IFenster.setHelpbool();
						break;
					}
				}
			}

			System.out.println("Richtung?(Numpad Richtig)");
			int richtung = HelpFunktion.EingabeInt(1, 9, IFenster, 2);
			IFenster.writeString("Richtung Erfoglreich eingegeben.", 3);
			IFenster.writeString("Kraft eingeben.", 4);

			if (spieler == 1) { // Spieler 1 Anweisungen
				int kraft = HelpFunktion.EingabeInt(1, PanzerlisteP1[auswahl]
						.getTyp().getRange(), IFenster, 4);
				switch (action) {
				case 0: // Panzer bewegen
					Feld1.Panzerbewegen(PanzerlisteP1, PanzerlisteP2, auswahl,
							richtung, kraft, IFenster);
					break;
				case 1: // Panzer schießen
					Feld1.Panzerschiessen(PanzerlisteP1, PanzerlisteP2,
							auswahl, richtung, kraft, IFenster);
					break;

				} // end of switch
			} else { // Spieler 2 Anweisungen
				int kraft = HelpFunktion.EingabeInt(1, PanzerlisteP2[auswahl]
						.getTyp().getRange(), IFenster, 4);
				switch (action) {
				case 0: // Panzer bewegen
					Feld1.Panzerbewegen(PanzerlisteP2, PanzerlisteP1, auswahl,
							richtung, kraft, IFenster);
					break;
				case 1:
					Feld1.Panzerschiessen(PanzerlisteP2, PanzerlisteP1,
							auswahl, richtung, kraft, IFenster);
					break;

				} // end of switch
			} // end of if-else

			Fenster.repaint();
			if (HelpFunktion.TestSpielende(PanzerlisteP1)) {
				IFenster.clearString(10);
				IFenster.writeString("Spiel beendet, Spieler 2 hat gewonnen", 0);
				break;
			} // end of if

			if (HelpFunktion.TestSpielende(PanzerlisteP2)) {
				IFenster.clearString(10);
				IFenster.writeString("Spiel beendet, Spieler 1 hat gewonnen", 0);
				break;
			} // end of if
		} // end of while
	} // end of main
} // end of class Panzergame
