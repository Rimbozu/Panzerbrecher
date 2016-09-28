import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

public class Infofenster extends Frame {
	EventManager EM = null;
	FeldGrafik FG = null;
	static Dimension screenDim, winDim;
	public String[] st = new String[20];
	int Sx, Sy;

	public Infofenster() {
		super("Panzerbrecher - Infofenster");
		screenDim = getToolkit().getScreenSize();
		winDim = new Dimension(800, 450);
		setSize(winDim);
		setLocation(new Point(100, 100));
		setVisible(true);
		addWindowListener(new FensterFunktion());

		setBackground(Color.white);
		setForeground(Color.black);
	}

	public void register(FeldGrafik c) {
		FG = c; // event-handler
	}

	public void register(EventManager c) {
		EM = c; // event-handler
		addKeyListener(c); // registrierung fuer key-events
		addMouseListener(c); // registrierung fuer mouse-events
	}

	public void writeString(String s, int a) {
		st[a] = s;

	}

	public void FeldSelect(int x, int y) {
		Sx = x;
		Sy = y;
	}

	public void Panzerinfo(Panzer[] p, int n) {
		st[0] = "Dieser Panzer gehört Spieler Nr." + p[n].getPlayer();
		st[1] = p[n].getTyp().toString();
		repaint();
	}

	public void paint(Graphics g) {

		g.drawString(st[0], 20, 50);
		g.drawString(st[1], 20, 80);
		// for (int i = 0; st[i] != null; i++) {
		// g.drawString(st[i], 20, 50 + (i * 30));
		// }
	}

}
