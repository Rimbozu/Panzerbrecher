import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Infofenster extends JFrame {

	private JPanel jPanel1 = new JPanel(null, true);
	private JTextField jTextField1 = new JTextField();
	private JButton jButton1 = new JButton();
	private JButton jButton2 = new JButton();
	private JButton jButton3 = new JButton();
	private JButton jButton4 = new JButton();

	EventManager EM = null;
	FeldGrafik FG = null;
	static Dimension screenDim, winDim;
	public String[] st = new String[20];
	private Panzer sPanzer;
	private int spieler;
	private int Sx, Sy;
	private String eingabewert = null;

	public Infofenster() {
		super("Panzerbrecher - Infofenster");
		screenDim = getToolkit().getScreenSize();
		winDim = new Dimension(800, 450);
		setSize(winDim);
		setLocation(new Point(100, 100));
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		addWindowListener(new FensterFunktion());

		jPanel1.setBounds(0, 0, this.getSize().width, this.getSize().height);
		jPanel1.setOpaque(false);
		cp.add(jPanel1);

		jTextField1.setBounds(10, jPanel1.getHeight() - 80, 100, 40);
		jTextField1.setText("60");
		add(jTextField1);

		jButton1.setBounds(610, 320, 160, 60);
		jButton1.setText("Fahrzeug ausw‰hlen");
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jButton1Clicked(evt);
			}
		});
		jButton1.setVisible(false);
		jPanel1.add(jButton1);

		jButton2.setBounds(610, 30, 160, 60);
		jButton2.setText("Bewegen");
		jButton2.setMargin(new Insets(2, 2, 2, 2));
		jButton2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jButtonClickedAction(evt, 1);
			}
		});
		jButton2.setVisible(false);
		jPanel1.add(jButton2);

		jButton3.setBounds(610, 110, 160, 60);
		jButton3.setText("Schieﬂen");
		jButton3.setMargin(new Insets(2, 2, 2, 2));
		jButton3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jButtonClickedAction(evt, 2);
			}
		});
		jButton3.setVisible(false);
		jPanel1.add(jButton3);

		jButton4.setBounds(130, jPanel1.getHeight() - 80, 160, 40);
		jButton4.setText("Wert akzeptieren");
		jButton4.setMargin(new Insets(2, 2, 2, 2));
		jButton4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				eingabewert = jTextField1.getText();
			}
		});
		jButton4.setVisible(true);
		jPanel1.add(jButton4);

		setBackground(Color.white);
		setForeground(Color.black);
		setVisible(true);
	}

	public void register(FeldGrafik c) {
		FG = c; // event-handler
	}

	public void register(EventManager c) {
		EM = c; // event-handler
		addKeyListener(c); // registrierung fuer key-events
		addMouseListener(c); // registrierung fuer mouse-events
	}

	public String getEingabewert() {
		return eingabewert;
	}

	public void setEingabewert(String s) {
		eingabewert = s;
	}

	public void setPlayer(int i) {
		spieler = i;
	}

	public void writeString(String s, int a) {
		st[a] = s;
		repaint();
	}

	public void writeString(String[] s) {
		for (int i = 0; i < s.length; i++) {
			st[i] = s[i];
		}
		repaint();
	}

	public void FeldSelect(int x, int y) {
		Sx = x;
		Sy = y;
	}

	public void Panzerinfo(Panzer[] p, int n) {
		sPanzer = p[n];
		if (spieler == sPanzer.getPlayer()) {
			jButton1.setVisible(true);
		} else {
			jButton1.setVisible(false);
		}
		st[0] = "Spieler " + spieler + " ist an der Reihe.";
		st[1] = "Dieser Panzer gehˆrt Spieler Nr." + sPanzer.getPlayer();
		st[2] = sPanzer.getTyp().toString();

		repaint();

	}

	public void jButton1Clicked(MouseEvent evt) {
		st[0] = "Spieler " + spieler + " ist an der Reihe.";
		st[1] = "Panzer erfolgreich ausgew‰hlt, Spieler " + sPanzer.getPlayer();
		st[2] = "Was mˆchten Sie nun tuen:";
		jButton2.setVisible(true);
		jButton3.setVisible(true);

		repaint();
	}

	public void jButtonClickedAction(MouseEvent evt, int a) {
		st[0] = "Panzer erfolgreich ausgew‰hlt, Spieler " + sPanzer.getPlayer();
		switch (a) {
		case 1:
			st[1] = "Der Panzer wird sich bewegen.";
			break;
		case 2:
			st[1] = "Der Panzer wird schieﬂen.";
			break;
		}
		st[2] = "";
		jButton2.setVisible(false);
		jButton3.setVisible(false);

		repaint();

	}

	public void paint(Graphics g) {
		g = jPanel1.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
		g.setColor(Color.black);

		for (int i = 0; st[i] != null; i++) {
			g.drawString(st[i], 20, 50 + (i * 30));
		}

	}

}
