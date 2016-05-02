import java.util.Scanner;

public class Panzergame {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Willkommen zum Panzerspiel.\n");

		boolean richtig = true;
		int b = 0, a = 0, c, PAnz, HAnz, h, v, spiel = 0;
		int[] TypenWert = new int[4];
		int Restpunkte = 0;
		int T1P1, T2P1, T3P1, T1P2, T2P2, T3P2;

		Hindernis[] Hindernisliste;
		Panzer[] PanzerlisteP1, PanzerlisteP2;

		System.out.print("2 Spieler Spielmodus");

		System.out.println("\nWie Breit soll das Spielfeld sein? "); // Spielfeldgröße
																		// abfragen
		b = HelpFunktion.EingabeInt(5, 15, input);

		System.out.println("Wie Lang soll das Spielfeld sein? ");
		a = HelpFunktion.EingabeInt(b, 40, input);

		c = a * b;

		System.out
				.println("\nWie wie viele Panzerpunkte möchten sie haben? (wenig-viele)"); // Panzeranzahl
																							// abfragen
		PAnz = HelpFunktion.EingabeInt(1, c / 20, input);

		System.out.println("\nWie wie viele Hindernisse möchten sie haben? "); // Hindernisse
																				// abfragen
		HAnz = HelpFunktion.EingabeInt(0, c / 10, input);

		TypenWert[0] = c;
		TypenWert[1] = c / (PAnz * 2);
		TypenWert[2] = TypenWert[1] * 2;
		TypenWert[3] = TypenWert[1] / 10 * 15;

		System.out.println("\nSpieler 1 Panzerauswahl:"); // Panzerauswahl
															// Spieler 1
		HelpFunktion.showPanzertypen(TypenWert);

		System.out.println("Anzahl Typ 1 Panzer:");
		T1P1 = HelpFunktion.EingabeInt(0, TypenWert[0] / TypenWert[1], input);
		Restpunkte = TypenWert[0] - T1P1 * TypenWert[1];
		System.out.println("\n" + Restpunkte + " Punkte noch vorhanden");
		System.out.println("Anzahl Typ 2 Panzer:");
		T2P1 = HelpFunktion.EingabeInt(0, (Restpunkte) / TypenWert[2], input);
		Restpunkte -= T2P1 * TypenWert[2];
		System.out.println("\n" + Restpunkte + " Punkte noch vorhanden");
		System.out.println("Anzahl Typ 3 Panzer:");
		T3P1 = HelpFunktion.EingabeInt((Restpunkte) / TypenWert[3],
				(Restpunkte) / TypenWert[3], input);

		Restpunkte = TypenWert[0];
		System.out.println("\nSpieler 2 Panzerauswahl:"); // Panzerauswahl
															// Spieler 2
		HelpFunktion.showPanzertypen(TypenWert);

		System.out.println("Anzahl Typ 1 Panzer:");
		T1P2 = HelpFunktion.EingabeInt(0, TypenWert[0] / TypenWert[1], input);
		Restpunkte -= T1P2 * TypenWert[1];
		System.out.println("\n" + Restpunkte + " Punkte noch vorhanden");
		System.out.println("Anzahl Typ 2 Panzer:");
		T2P2 = HelpFunktion.EingabeInt(0, (Restpunkte) / TypenWert[2], input);
		Restpunkte -= T2P2 * TypenWert[2];
		System.out.println("\n" + Restpunkte + " Punkte noch vorhanden");
		System.out.println("Anzahl Typ 3 Panzer:");
		T3P2 = HelpFunktion.EingabeInt((Restpunkte) / TypenWert[3],
				(Restpunkte) / TypenWert[3], input);

		PanzerlisteP1 = new Panzer[T1P1 + T2P1 + T3P1]; // 1. Inizialisieren
		PanzerlisteP2 = new Panzer[T1P2 + T2P2 + T3P2];
		Hindernisliste = new Hindernis[HAnz];

		Spielfeld Feld1 = new Spielfeld(b, a, PanzerlisteP1, PanzerlisteP2,
				Hindernisliste);

		for (int i = 0; i < PanzerlisteP1.length; i++) { // Panzer Spieler 1
															// erzeugen
			h = (int) (Math.random() * b);
			v = (int) (Math.random() * (a * 30 / 100));

			richtig = HelpFunktion.checkListeP(h, v, i, PanzerlisteP1);
			if (richtig) {

				if (i + 1 <= T1P1) {
					PanzerlisteP1[i] = new Panzer(h, v, 1, 0);
				} else {
					if (i + 1 - T1P1 <= T2P1) {
						PanzerlisteP1[i] = new Panzer(h, v, 1, 1);
					} else {
						PanzerlisteP1[i] = new Panzer(h, v, 1, 2);
					}// end of if-else
				} // end of if-else

			} else {
				i -= 1;
			} // end of if-else
			richtig = true;
		} // end of for

		richtig = true;
		h = v = 0;

		for (int i = 0; i < PanzerlisteP2.length; i++) { // Panzer Spieler 2
															// erzeugen
			h = (int) (Math.random() * b);
			v = (int) ((Math.random() * (a * 30 / 100)) + (a * 70 / 100));

			richtig = HelpFunktion.checkListeP(h, v, PanzerlisteP1.length,
					PanzerlisteP1);
			if (richtig) {
				richtig = HelpFunktion.checkListeP(h, v, i, PanzerlisteP2);
				if (richtig) {
					if (i + 1 <= T1P2) {
						PanzerlisteP2[i] = new Panzer(h, v, 2, 0);
					} else {
						if (i + 1 - T1P2 <= T2P2) {
							PanzerlisteP2[i] = new Panzer(h, v, 2, 1);
						} else {
							PanzerlisteP2[i] = new Panzer(h, v, 2, 2);
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
		h = v = 0;

		// Hindernisse erzeugen
		for (int i = 0; i < HAnz; i++) {
			h = (int) (Math.random() * b);
			v = (int) (Math.random() * (a * 70 / 100) + (a * 15 / 100));

			richtig = HelpFunktion.checkListeP(h, v, PanzerlisteP1.length,
					PanzerlisteP1);
			if (richtig) {
				richtig = HelpFunktion.checkListeP(h, v, PanzerlisteP2.length,
						PanzerlisteP2);
				if (richtig) {
					richtig = HelpFunktion.checkListeH(h, v, i, Hindernisliste);
					if (richtig) {

						Hindernisliste[i] = new Hindernis(h, v,
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
			boolean exit = false;
			spiel += 1;
			int spieler, auswahl = 0;

			if (spiel % 2 == 1) {
				spieler = 1;
			} else {
				spieler = 2;
			} // end of if-else

			System.out.println("\nSpieler " + spieler + " ist an der Reihe.");
			System.out
					.println("\nMit welchen Panzer möchten Sie die nächste aktion ausführen?");
			System.out.println("Ihre Panzer:\n");

			if (spiel % 2 == 1) {
				auswahl = HelpFunktion.showPanzerliste(PanzerlisteP1, input);
			} else {
				auswahl = HelpFunktion.showPanzerliste(PanzerlisteP2, input);
			} // end of if-else

			System.out.println("Panzer bewegen(0) oder schießen(1)?"); // Aktionsparameter
																		// eingeben
			int action = HelpFunktion.EingabeInt(0, 1, input);
			System.out.println("Richtung?(Numpad Richtig)");
			int richtung = HelpFunktion.EingabeInt(1, 9, input);
			System.out.println("Kraft? ");

			if (spiel % 2 == 1) { // Spieler 1 Anweisungen
				int kraft = HelpFunktion.EingabeInt(1,
						PanzerlisteP1[auswahl - 1].getTyp().getRange(), input);
				switch (action) {
				case 0: // Panzer bewegen
					Feld1.Panzerbewegen(PanzerlisteP1, PanzerlisteP2, auswahl,
							richtung, kraft);
					break;
				case 1: // Panzer schießen
					Feld1.Panzerschiessen(PanzerlisteP1, PanzerlisteP2,
							auswahl, richtung, kraft);
					break;

				} // end of switch
			} else { // Spieler 2 Anweisungen
				int kraft = HelpFunktion.EingabeInt(1,
						PanzerlisteP2[auswahl - 1].getTyp().getRange(), input);
				switch (action) {
				case 0: // Panzer bewegen
					Feld1.Panzerbewegen(PanzerlisteP2, PanzerlisteP1, auswahl,
							richtung, kraft);
					break;
				case 1:
					Feld1.Panzerschiessen(PanzerlisteP2, PanzerlisteP1,
							auswahl, richtung, kraft);
					break;

				} // end of switch
			} // end of if-else

			System.out.print("Aktuelles Spielfeld anzeigen? (ja=1): ");
			int feld = HelpFunktion.EingabeInt(0, 1, input);

			if (feld == 1) {
				Feld1.print();
			} // end of if

			exit = HelpFunktion.TestSpielende(PanzerlisteP1);
			exit = HelpFunktion.TestSpielende(PanzerlisteP2);

			if (exit) {
				break;
			} // end of if
		} // end of while
	} // end of main
} // end of class Panzergame
