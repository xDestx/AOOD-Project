package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.util.SoundMaster;

public class MainMenu extends JFrame {
	private static final int WIDTH = 300, HEIGHT = 400;
	private static JPanel panel;
	private static JButton playGame;
	private static Game game;

	public static void main(String[] args) {
		MainMenu mm = new MainMenu();
	}

	public MainMenu() {
		super();
		SoundMaster.init();
		this.setTitle("Tales of Gorizia Launcher");
		panel = new JPanel();
		playGame = new JButton("Play");
		playGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread() {
					@Override
					public void run() {
						Game.playNewGame();
					}
				};
				t.start();
				setVisible(false);
			}
		});
		this.setSize(WIDTH, HEIGHT);
		panel.add(playGame);
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
