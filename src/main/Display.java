package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display {
    
	private Graffix g;
	public final int frameWidth = 1600;
	public final int frameHeight = 1000;
	private JFrame frame;
	
	public Display(){
		g = new Graffix();
		initFrame();
	}
	
	public void initFrame(){
        frame = new JFrame("Hexagon Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
        frame.add(g);
        frame.addKeyListener(g.kl);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public class Graffix extends JPanel{ 
		private static final long serialVersionUID = 1L;
		public KeyListen kl;
		
		public Graffix(){
			kl = new KeyListen();
		}
		
		public void paintComponent(Graphics g){
			Graphics g2 = (Graphics2D) g;
			for(int i=0;i<Constants.gridWidth;i++){
				for(int j=0;j<Constants.gridHeight;j++){
					g2.setColor(Main.board[i][j].getC());
					g2.fillPolygon(Main.board[i][j]);
					g2.setColor(Color.BLACK);
					g2.drawPolygon(Main.board[i][j]);
				}
			}
			for(int i=0;i<Main.players.length;i++){
				Main.players[i].render(g2);
			}
		}

		class KeyListen extends KeyAdapter{
			public void keyReleased(KeyEvent e){
				switch(e.getKeyCode()){
					case 'Q': Main.players[0].move(1);break;
					case 'W': Main.players[0].move(2);break;
					case 'E': Main.players[0].move(3);break;
					case 'A': Main.players[0].move(4);break;
					case 'S': Main.players[0].move(5);break;
					case 'D': Main.players[0].move(6);break;
					case 'U': Main.players[1].move(1);break;
					case 'I': Main.players[1].move(2);break;
					case 'O': Main.players[1].move(3);break;
					case 'J': Main.players[1].move(4);break;
					case 'K': Main.players[1].move(5);break;
					case 'L': Main.players[1].move(6);break;
				default: break;//this is just placeholder for multiplayer
				}
			}
		}//end of KeyListener
	}//end of Graphics
}
