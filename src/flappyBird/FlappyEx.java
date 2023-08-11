package flappyBird;

import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FlappyEx extends JFrame {

	public FlappyEx() {
		
		initUI();
	}
	
	private void initUI() {
		
		add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("Flappy Bird");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			FlappyEx ex = new FlappyEx();
			ex.setVisible(true);
		});
	}

}

