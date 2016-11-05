import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

public class EventManager implements KeyListener, MouseListener {
	FeldGrafik feld = null;
	Infofenster info = null;
	Point2D mSelectedPoint;

	public void register(FeldGrafik f) {
		feld = f;
	}

	public void register(Infofenster f) {
		info = f;
	}

	// key events
	public void keyPressed(KeyEvent event) {
		// int k=event.getKeyCode();
		// System.out.println("Key-Event="+k);
		// switch (k) {
		// case KeyEvent.VK_D : // taste 'D' gedrueckt
		// break;
		// case KeyEvent.VK_ESCAPE : // taste 'Esc' gedrueckt
		// break;
		// default:
		// }
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getSource() == feld) {
			mSelectedPoint = null;
			int mex = 0, mey = 0;
			mex = e.getX();
			mey = e.getY();

			int x = ((mex - feld.links) / feld.feld);
			int y = ((mey - feld.oben) / feld.feld);

			feld.FeldSelect(x, y);
			info.FeldSelect(x, y);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}
}
