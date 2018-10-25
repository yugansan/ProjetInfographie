import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import MyMath.Matrix;
import MyMath.Vector;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

public class mainProjection {
	static WorldObject obj;
	static int sizegen= (int)Math.pow(2,8)+1;
	static int rotX=29;
	static int rotY=0;

	static int posX= sizegen/2;
	static int posY= sizegen*5;
	static int posZ= sizegen*30;

	static int choix=1;
	static class Clavier implements KeyListener{
		public void keyTyped(KeyEvent e){

		}
		public void keyPressed(KeyEvent e){
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				rotX-=1;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				rotX+=1;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				rotY-=1;
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				rotY+=1;
			}
			if(e.getKeyCode()==KeyEvent.VK_Z){
				posY-=sizegen/2;
			}
			if(e.getKeyCode()==KeyEvent.VK_S){
				posY+=sizegen/2;
			}
			if(e.getKeyCode()==KeyEvent.VK_Q){
				posX-=sizegen/2;
			}
			if(e.getKeyCode()==KeyEvent.VK_D){
				posX+=sizegen/2;
			}
			if(e.getKeyCode()==KeyEvent.VK_R){
				posZ-=sizegen/2;
			}
			if(e.getKeyCode()==KeyEvent.VK_F){
				posZ+=sizegen/2;
			}

			if(e.getKeyCode()==KeyEvent.VK_P){
				GenerationPerlin gen= new GenerationPerlin(sizegen, sizegen);
				gen.generate();
				obj= new Carte(gen.tab, 30,30);
			}
			if(e.getKeyCode()==KeyEvent.VK_O){
				GenerationDiamantCarre gen= new GenerationDiamantCarre(sizegen,sizegen);
				gen.generate();
				obj= new Carte(gen.tab,30,30);
			}

			if(e.getKeyCode()==KeyEvent.VK_I){
				GenCarte gen= new GenCarte(sizegen);
				obj= new Carte(gen.getGererateCarte(),30,30);
			}

		}

		public void keyReleased(KeyEvent e){

		}

	}
	public static void createUI() {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BorderLayout());
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		MyPanel mp = new MyPanel((int)screenSize.getWidth(),(int)screenSize.getHeight() );

		p.add(mp, BorderLayout.CENTER);
		f.pack();
		f.setFocusable(true);
		f.addKeyListener(new Clavier());

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Runnable r = () -> {
			GenerationDiamantCarre gen= new GenerationDiamantCarre(sizegen, sizegen);
			gen.generate();

			obj= new Carte(gen.tab, 30,30);

			while (true) {
				f.setFocusable(true);
				obj.resetTransform();
				obj.addTransform(Matrix.createRotationX(Math.PI*2/100*rotX));

				obj.addTransform(Matrix.createRotationY(Math.PI*2/100*rotY));
				//deplacement
				obj.addTransform(Matrix.createTranslation(new Vector(posX,posY,posZ)));

				mp.setWorldObject(obj.getTransformedObject(), new Vector(0,0,100));
			}
		};
		Thread t = new Thread(r);
		t.start();

	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->createUI());
	}

}
