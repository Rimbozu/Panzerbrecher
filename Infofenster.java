import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

public class Infofenster extends Frame {
	static Dimension screenDim, winDim;
	public String[] st = new String[20];

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

	public void writeString(String s, int a) {
		st[a] = s;

	}

	public void paint(Graphics g) {

		for (int i = 0; st[i] != null; i++) {
			g.drawString(st[i], 20 + i, 50 + (i * 30));
		}
	}

}
