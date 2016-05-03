import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class FeldGrafik extends Frame {
	int abst = 31, obj = 11, oentf = 90;
	private int y, x, zx, zy;
	private Panzer[] PListe1;
	private Panzer[] PListe2;
	private Hindernis[] HListe;

	public FeldGrafik(int y, int x, Panzer[] Liste1, Panzer[] Liste2,
			Hindernis[] Liste3) {
		super("Panzerbrecher - Das Spiel");
		zx = x;
		zy = y;
		this.x = x * obj + (x + 1) * abst;
		this.y = y * obj + (y + 1) * abst + oentf + 10;
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
		int feldt, feldu;
		int abst1 = (abst + 1) / 2;
		int abst2 = obj + abst1;
		int abst3 = abst1 - obj;
		int abst4 = abst + obj;

		// Horizontale Striche
		g.drawString("Fenstergröße: " + x + " x " + y + "", 20, 70);
		g.drawLine(abst1, abst1 + oentf, x - abst1, abst1 + oentf);
		g.drawLine(abst1, abst1 + zy * abst4 + oentf, x - abst1, abst1 + zy
				* abst4 + oentf);
		g.setColor(Color.lightGray);
		for (int t = 1; t < zy; t++) {
			g.drawLine(abst1, abst1 + t * abst4 + oentf, abst2, abst1 + t
					* abst4 + oentf);
			g.drawLine(x - abst2, abst1 + t * abst4 + oentf, x - abst1, abst1
					+ t * abst4 + oentf);
			for (int u = 1; u < zx; u++) {
				g.drawLine(abst3 + u * abst4, abst1 + t * abst4 + oentf, abst2
						+ u * abst4, abst1 + t * abst4 + oentf);
				feldt = t - 1;
				feldu = u - 1;
			}

		} // end of for
		g.setColor(Color.black);
		// Vertikale Striche
		g.drawLine(abst1, abst1 + oentf, abst1, abst1 + zy * abst4 + oentf);
		g.drawLine(abst1 + zx * abst4, abst1 + oentf, abst1 + zx * abst4, abst1
				+ zy * abst4 + oentf);
		g.setColor(Color.lightGray);
		for (int t = 1; t < zx; t++) {

			g.drawLine(abst1 + t * abst4, abst1 + oentf, abst1 + t * abst4,
					abst2 + oentf);
			g.drawLine(abst1 + t * abst4, y - 25 - obj, abst1 + t * abst4,
					y - 25);

			for (int u = 1; u < zy; u++) {

				g.drawLine(abst1 + t * abst4, abst3 + u * abst4 + oentf, abst1
						+ t * abst4, abst2 + u * abst4 + oentf);
			}
		} // end of for
		for (int t = 0; t < PListe1.length; t++) {
			g.setColor(Color.blue);
			g.fillRect((PListe1[t].getPos().getPosX() * obj)
					+ ((PListe1[t].getPos().getPosX() + 1) * abst), oentf
					+ (PListe1[t].getPos().getPosY() * obj)
					+ ((PListe1[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for

		for (int t = 0; t < PListe2.length; t++) {
			g.setColor(Color.red);
			g.fillRect((PListe2[t].getPos().getPosX() * obj)
					+ ((PListe2[t].getPos().getPosX() + 1) * abst), oentf
					+ (PListe2[t].getPos().getPosY() * obj)
					+ ((PListe2[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for

		for (int t = 0; t < HListe.length; t++) {
			g.setColor(Color.black);
			g.fillRect((HListe[t].getPos().getPosX() * obj)
					+ ((HListe[t].getPos().getPosX() + 1) * abst), oentf
					+ (HListe[t].getPos().getPosY() * obj)
					+ ((HListe[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for
	}
}
