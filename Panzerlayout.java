import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

// Autor: %AUTHOR%
// Datum: %DATE%

public class Panzerlayout extends JFrame {
	// Anfang Attribute
	private JButton jButton1 = new JButton();
	public JTextArea Spielfeld = new JTextArea("");
	private JScrollPane SpielfeldScrollPane = new JScrollPane(Spielfeld);
	public JTextArea Infofenster = new JTextArea("");
	private JScrollPane InfofensterScrollPane = new JScrollPane(Infofenster);
	public JTextArea EingabeInfo = new JTextArea("");
	private JScrollPane EingabeInfoScrollPane = new JScrollPane(EingabeInfo);
	public JTextField Eingabe = new JTextField();

	// Ende Attribute

	public Panzerlayout(String title) {
		// Frame-Initialisierung
		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 1270;
		int frameHeight = 720;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		// Anfang Komponenten

		jButton1.setBounds(1000, 550, 225, 100);
		jButton1.setText("Bestätigen");
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		cp.add(jButton1);
		SpielfeldScrollPane.setBounds(30, 50, 950, 600);
		cp.add(SpielfeldScrollPane);
		InfofensterScrollPane.setBounds(1000, 50, 225, 250);
		cp.add(InfofensterScrollPane);
		EingabeInfoScrollPane.setBounds(1000, 350, 225, 100);
		cp.add(EingabeInfoScrollPane);
		Eingabe.setBounds(1000, 450, 225, 50);
		Eingabe.setText("");
		cp.add(Eingabe);
		// Ende Komponenten

		setVisible(true);
	} // end of public Panzerlayout

	// Anfang Methoden

	public void Infoausgabe(String t) {
		Infofenster.insert(t, 0);
	}

	public void jButton1_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfügen
	} // end of jButton1_ActionPerformed

	// Ende Methoden
} // end of class Panzerlayout

