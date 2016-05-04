import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class FeldGrafik extends Frame {

	int feld, abst, obj, oentf = 90;
	private int y, x, zx, zy;
	private Panzer[] PListe1;
	private Panzer[] PListe2;
	private Hindernis[] HListe;

	public FeldGrafik(int y, int x, Panzer[] Liste1, Panzer[] Liste2,
			Hindernis[] Liste3) {
		super("Panzerbrecher - Das Spiel");
		zx = x;
		zy = y;
		this.y = 900;
		feld = (this.y - 60) / y;
		this.x = feld * x + 20;
		abst = feld * 2 / 3;
		obj = feld * 1 / 3;

		PListe1 = Liste1;
		PListe2 = Liste2;
		HListe = Liste3;

		addWindowListener(new FensterFunktion());
		setLocation(200, 200);
		setBackground(Color.white);
		setSize(this.x, this.y);
		setVisible(true);

	}

	public void paint(Graphics g) {

		int oben = getInsets().top;
		int unten = getInsets().bottom;
		int links = getInsets().left;
		int rechts = getInsets().right;

		int feldt, feldu;
		int abst1 = getInsets().left;
		int abst2 = abst1 + abst1;

		// Horizontale Striche
		g.drawLine(links, oben, x - rechts, oben);
		g.drawLine(links, y - unten, x - rechts, y - unten);
		g.setColor(Color.lightGray);
		for (int t = 1; t < zy; t++) {
			g.drawLine(links, t * feld + oben, feld / 4 + links, t * feld
					+ oben);
			g.drawLine(x - rechts - feld / 4, t * feld + oben, x - rechts, t
					* feld + oben);
			for (int u = 1; u < zx; u++) {
				g.drawLine(links + u * feld - feld / 4, t * feld + oben, abst2
						+ u * feld + feld / 4, t * feld + oben);
			}

		} // end of for
		g.setColor(Color.black);
		// Vertikale Striche
		g.drawLine(links, oben, links, y - unten);
		g.drawLine(x - rechts, oben, x - rechts, y - unten);
		g.setColor(Color.lightGray);
		for (int t = 1; t < zx; t++) {

			g.drawLine(links + t * feld, oben, links + t * feld, feld / 4
					+ oben);
			g.drawLine(links + t * feld, y - unten - feld / 4,
					links + t * feld, y - unten);

			for (int u = 1; u < zy; u++) {

				g.drawLine(links + t * feld, u * feld - feld / 4 + oben, links
						+ t * feld, u * feld + feld / 4 + oben);
			}
		} // end of for
		for (int t = 0; t < PListe1.length; t++) {
			g.setColor(Color.green);
			switch (PListe1[t].getTyp().getTypNr()) {
			case 0:
				GrafikNormalerPanzer(PListe1[t].getPos().getPosX(), PListe1[t]
						.getPos().getPosY(), g);
				break;
			case 1:
				GrafikSchwererPanzer(PListe1[t].getPos().getPosX(), PListe1[t]
						.getPos().getPosY(), g);
				break;
			case 2:
				GrafikPanzerjaeger(PListe1[t].getPos().getPosX(), PListe1[t]
						.getPos().getPosY(), g);
				break;
			} // end of switch
		} // end of for

		for (int t = 0; t < PListe2.length; t++) {
			g.setColor(Color.red);
			switch (PListe2[t].getTyp().getTypNr()) {
			case 0:
				GrafikNormalerPanzer(PListe2[t].getPos().getPosX(), PListe2[t]
						.getPos().getPosY(), g);
				break;
			case 1:
				GrafikSchwererPanzer(PListe2[t].getPos().getPosX(), PListe2[t]
						.getPos().getPosY(), g);
				break;
			case 2:
				GrafikPanzerjaeger(PListe2[t].getPos().getPosX(), PListe2[t]
						.getPos().getPosY(), g);
				break;
			} // end of switch
				// g.fillRect((PListe2[t].getPos().getPosX() * obj)
				// + ((PListe2[t].getPos().getPosX() + 1) * abst), oentf
				// + (PListe2[t].getPos().getPosY() * obj)
				// + ((PListe2[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for

		for (int t = 0; t < HListe.length; t++) {
			g.setColor(Color.black);
			if (HListe[t].getArt() == 0) {
				GrafikBerg(HListe[t].getPos().getPosX(), HListe[t].getPos()
						.getPosY(), g);
			} else {
				GrafikFluss(HListe[t].getPos().getPosX(), HListe[t].getPos()
						.getPosY(), g);
			}

		} // end of for
	}

	private void GrafikNormalerPanzer(int x, int y, Graphics g) {
		int bildx = getInsets().left + x * obj + (x) * abst + abst / 2;
		int bildy = getInsets().top + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx + (obj * 1 / 3), bildy, obj * 1 / 3, obj * 2 / 3);

	}

	private void GrafikSchwererPanzer(int x, int y, Graphics g) {
		int bildx = getInsets().left + x * obj + (x) * abst + abst / 2;
		int bildy = getInsets().top + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx, bildy, obj, obj);

	}

	private void GrafikPanzerjaeger(int x, int y, Graphics g) {
		int bildx = getInsets().left + x * obj + (x) * abst + abst / 2;
		int bildy = getInsets().top + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx, bildy, obj * 3 / 4, obj * 3 / 4);

	}

	private void GrafikFluss(int x, int y, Graphics g) {
		g.setColor(Color.blue);
		int bildx = getInsets().left + x * obj + (x) * abst + abst / 2;
		int bildy = getInsets().top + (y * obj) + (y) * abst + abst / 2;

		int[] xp1 = { bildx, bildx + obj * 1 / 9, bildx + obj * 2 / 9,
				bildx + obj * 3 / 9, bildx + obj * 4 / 9, bildx + obj * 5 / 9,
				bildx + obj * 6 / 9, bildx + obj * 7 / 9, bildx + obj * 8 / 9,
				bildx + obj };
		int[] yp1 = { bildy + obj * 1 / 3, bildy + obj * 1 / 3 + 1,
				bildy + obj * 1 / 3 + 3, bildy + obj * 1 / 3 + 1,
				bildy + obj * 1 / 3 + 0, bildy + obj * 1 / 3 - 1,
				bildy + obj * 1 / 3 - 3, bildy + obj * 1 / 3 - 1,
				bildy + obj * 1 / 3 };
		g.drawPolyline(xp1, yp1, 9);
		for (int i = 0; i < 9; i++) {
			yp1[i] += 1;
		}
		g.drawPolyline(xp1, yp1, 9);
		for (int i = 0; i < 9; i++) {
			yp1[i] += obj * 1 / 3;
		}
		g.drawPolyline(xp1, yp1, 9);
		for (int i = 0; i < 9; i++) {
			yp1[i] += 1;
		}
		g.drawPolyline(xp1, yp1, 9);
	}

	public void GrafikBerg(int x, int y, Graphics g) {
		g.setColor(Color.black);
		int bildx = getInsets().left + x * obj + (x) * abst + abst / 2;
		int bildy = getInsets().top + (y * obj) + (y) * abst + abst / 2;

		int[] xp = { bildx, (bildx + obj / 4), (bildx + obj / 2),
				(bildx + obj * 3 / 4), bildx + obj };
		int[] yp = { bildy + obj, bildy, (bildy + obj / 2), (bildy + obj / 4),
				bildy + obj };

		g.fillPolygon(xp, yp, 5);
	}
}
