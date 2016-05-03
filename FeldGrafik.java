import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class FeldGrafik extends Frame {
	private int y, x, zx, zy;
	private Panzer[] PListe1;
	private Panzer[] PListe2;
	private Hindernis[] HListe;

	public FeldGrafik(int y, int x, Panzer[] Liste1, Panzer[] Liste2,
			Hindernis[] Liste3) {
		super("Panzerbrecher - Das Spiel");
		zx = x;
		zy = y;
		this.x = x * 5 + (x + 1) * 25;
		this.y = y * 5 + (y + 1) * 25 + 50;
		PListe1 = Liste1;
		PListe2 = Liste2;
		HListe = Liste3;

		addWindowListener(new FensterFunktion());
		setLocation(200, 200);
		setBackground(Color.lightGray);
		setSize(this.x, this.y);
		setVisible(true);

	}

	public void paint(Graphics g) {
		int abst = 25, obj = 5, oentf = 40;

		for (int t = 0; t <= zy; t++) {
			g.drawLine(13, 13 + t * 30 + oentf, x - 13, 13 + t * 30 + oentf);
		} // end of for

		for (int t = 0; t <= zx; t++) {
			g.drawLine(13 + t * 30, 13 + oentf, 13 + t * 30, y - 22);
		} // end of for

		for (int t = 0; t < PListe1.length; t++) {
			g.drawOval((PListe1[t].getPos().getPosX() * obj)
					+ ((PListe1[t].getPos().getPosX() + 1) * abst), oentf
					+ (PListe1[t].getPos().getPosY() * obj)
					+ ((PListe1[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for

		for (int t = 0; t < PListe2.length; t++) {
			g.drawRect((PListe2[t].getPos().getPosX() * obj)
					+ ((PListe2[t].getPos().getPosX() + 1) * abst), oentf
					+ (PListe2[t].getPos().getPosY() * obj)
					+ ((PListe2[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for

		for (int t = 0; t < HListe.length; t++) {
			g.fillRect((HListe[t].getPos().getPosX() * obj)
					+ ((HListe[t].getPos().getPosX() + 1) * abst), oentf
					+ (HListe[t].getPos().getPosY() * obj)
					+ ((HListe[t].getPos().getPosY() + 1) * abst), obj, obj);
		} // end of for
	}
}
