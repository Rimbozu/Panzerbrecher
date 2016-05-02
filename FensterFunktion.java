import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FensterFunktion extends WindowAdapter {
	public FensterFunktion() {
	}

	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		System.exit(0);
	}
}
