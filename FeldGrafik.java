import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

public class FeldGrafik extends Frame {

	EventManager EM = null;
	Infofenster F = null;
	public int oben, unten, links, rechts;

	static Dimension screenDim;
	int feld, abst, obj, oentf = 50;
	private int y, x, spalten, zeilen, Sx, Sy;
	private Panzer[] PListe1;
	private Panzer[] PListe2;
	private Hindernis[] HListe;

	public FeldGrafik(int y, int x, Panzer[] Liste1, Panzer[] Liste2,
			Hindernis[] Liste3) {
		super("Panzerbrecher - Das Spiel");
		spalten = x;
		zeilen = y;
		this.y = 900;
		feld = (this.y - 60) / y;
		this.x = feld * x + 20;
		abst = feld * 2 / 3;
		obj = feld * 1 / 3;

		PListe1 = Liste1;
		PListe2 = Liste2;
		HListe = Liste3;

		screenDim = getToolkit().getScreenSize();
		setBackground(Color.white);
		setForeground(Color.black);
		setSize(this.x, this.y);
		setLocation(new Point((screenDim.width - x) / 2,
				(screenDim.height - y) / 2));
		setVisible(true);
		addWindowListener(new FensterFunktion());

		oben = getInsets().top;
		unten = getInsets().bottom;
		links = getInsets().left;
		rechts = getInsets().right;

	}

	public void register(EventManager c) {
		EM = c; // event-handler
		addKeyListener(c); // registrierung fuer key-events
		addMouseListener(c); // registrierung fuer mouse-events
	}

	public void register(Infofenster c) {
		F = c; // event-handler
	}

	public void FeldSelect(int x, int y) {
		Sx = x;
		Sy = y;
		repaint();
	}

	public void paint(Graphics g) {

		g.setColor(Color.gray);
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++)
				g.drawRect(links + (j * feld), oben + (i * feld), feld, feld);
		}

		g.setColor(Color.YELLOW);
		g.drawRect(links + (Sx * feld), oben + (Sy * feld), feld, feld);

		for (int t = 0; t < PListe1.length; t++) {

			if (PListe1[t].getPos().getPosX() == Sx
					&& PListe1[t].getPos().getPosY() == Sy) {
				F.Panzerinfo(PListe1, t);
			}

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

			if (PListe2[t].getPos().getPosX() == Sx
					&& PListe2[t].getPos().getPosY() == Sy) {
				F.Panzerinfo(PListe2, t);
			}

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
		int bildx = links + x * obj + (x) * abst + abst / 2;
		int bildy = oben + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx + (obj * 1 / 3), bildy, obj * 1 / 3, obj * 2 / 3);

	}

	private void GrafikSchwererPanzer(int x, int y, Graphics g) {
		int bildx = links + x * obj + (x) * abst + abst / 2;
		int bildy = oben + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx, bildy, obj, obj);

	}

	private void GrafikPanzerjaeger(int x, int y, Graphics g) {
		int bildx = links + x * obj + (x) * abst + abst / 2;
		int bildy = oben + (y * obj) + (y) * abst + abst / 2;

		g.fillRect(bildx, bildy, obj * 3 / 4, obj * 3 / 4);

	}

	private void GrafikFluss(int x, int y, Graphics g) {
		g.setColor(Color.blue);
		int bildx = links + x * obj + (x) * abst + abst / 2;
		int bildy = oben + (y * obj) + (y) * abst + abst / 2;

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
		int bildx = links + x * obj + (x) * abst + abst / 2;
		int bildy = oben + (y * obj) + (y) * abst + abst / 2;

		int[] xp = { bildx, (bildx + obj / 4), (bildx + obj / 2),
				(bildx + obj * 3 / 4), bildx + obj };
		int[] yp = { bildy + obj, bildy, (bildy + obj / 2), (bildy + obj / 4),
				bildy + obj };

		g.fillPolygon(xp, yp, 5);
	}
}
