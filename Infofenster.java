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
	private JButton ButtonAuswahl = new JButton();
	private JButton ButtonBewegen = new JButton();
	private JButton ButtonSchieﬂen = new JButton();
	private JButton jButton4 = new JButton();

	EventManager EM = null;
	FeldGrafik FG = null;
	static Dimension screenDim, winDim;
	private String[] st = new String[20];
	private String[] st2 = new String[20];
	private Panzer sPanzer;
	private int spieler;
	private int aktion;
	private int auswahl;
	private int Sx, Sy;
	private Boolean helpbool = false;
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

		ButtonAuswahl.setBounds(610, 320, 160, 60);
		ButtonAuswahl.setText("Fahrzeug ausw‰hlen");
		ButtonAuswahl.setMargin(new Insets(2, 2, 2, 2));
		ButtonAuswahl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				helpbool = true;
				ButtonPanzerauswahl(evt);
			}
		});
		ButtonAuswahl.setVisible(false);
		jPanel1.add(ButtonAuswahl);

		ButtonBewegen.setBounds(610, 30, 160, 60);
		ButtonBewegen.setText("Bewegen");
		ButtonBewegen.setMargin(new Insets(2, 2, 2, 2));
		ButtonBewegen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				helpbool = true;
				ButtonAction(evt, 0);
			}
		});
		ButtonBewegen.setVisible(false);
		jPanel1.add(ButtonBewegen);

		ButtonSchieﬂen.setBounds(610, 110, 160, 60);
		ButtonSchieﬂen.setText("Schieﬂen");
		ButtonSchieﬂen.setMargin(new Insets(2, 2, 2, 2));
		ButtonSchieﬂen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				helpbool = true;
				ButtonAction(evt, 1);
			}
		});
		ButtonSchieﬂen.setVisible(false);
		jPanel1.add(ButtonSchieﬂen);

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

	public int getAktion() {
		return aktion;
	}

	public int getAuswahl() {
		return auswahl;
	}

	public boolean getHelpbool() {
		return helpbool;
	}

	public void setEingabewert(String s) {
		eingabewert = s;
	}

	public void setHelpbool() {
		helpbool = false;
	}

	public void setPlayer(int i) {
		spieler = i;
	}

	public void clearString(int a) {
		for (int i = 0; i < a; i++) {
			st[i] = "";
		}
		repaint();
	}

	public void clearString2(int a) {
		for (int i = 0; i < a; i++) {
			st2[i] = "";
		}
		repaint();
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

	public void writeString2(String s, int a) {
		st2[a] = s;
		repaint();
	}

	public void FeldSelect(int x, int y) {
		Sx = x;
		Sy = y;
	}

	public void Panzerinfo(Panzer[] p, int n) {
		sPanzer = p[n];
		if (spieler == sPanzer.getPlayer()) {
			ButtonAuswahl.setVisible(true);
			auswahl = n;
		} else {
			ButtonAuswahl.setVisible(false);
		}
		st[0] = "Spieler " + spieler + " ist an der Reihe.";
		st[1] = "Dieser Panzer gehˆrt Spieler Nr." + sPanzer.getPlayer();
		st[2] = sPanzer.getTyp().toString();

		repaint();

	}

	public void ButtonPanzerauswahl(MouseEvent evt) {
		st[0] = "Spieler " + spieler + " ist an der Reihe.";
		st[1] = "Panzer erfolgreich ausgew‰hlt, Spieler " + sPanzer.getPlayer();
		st[2] = "Was mˆchten Sie nun tuen:";
		ButtonBewegen.setVisible(true);
		ButtonSchieﬂen.setVisible(true);
		ButtonAuswahl.setVisible(false);
		repaint();
	}

	public void ButtonAction(MouseEvent evt, int a) {
		clearString(10);
		st[0] = "Panzer erfolgreich ausgew‰hlt, Spieler " + sPanzer.getPlayer();
		st[2] = "Geben Sie eine Richtung an. (Numpad-Richtung)";
		switch (a) {
		case 0:
			st[1] = "Der Panzer wird sich bewegen.";
			aktion = 0;
			break;
		case 1:
			st[1] = "Der Panzer wird schieﬂen.";
			aktion = 1;
			break;
		}
		ButtonBewegen.setVisible(false);
		ButtonSchieﬂen.setVisible(false);

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

		for (int i = 0; st2[i] != null; i++) {
			g.drawString(st2[i], 120, 65 + (i * 30));
		}

	}

}
