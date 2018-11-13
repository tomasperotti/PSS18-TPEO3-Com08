package Shot;

import java.awt.Image;
import javax.swing.ImageIcon;
import Main.Game;
import Visitor.Visitor;
import Visitor.VisitorShotEnemy;

public class EnemyShot extends Shot {

	public EnemyShot(int x, int y, Game g) {
		super(x, y, g);
		damage = 20;
		visitor = new VisitorShotEnemy();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resources/Shots/basic_shot_01.png"));
		this.icon = new ImageIcon(img.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_DEFAULT));
	}

	@Override
	public void accept(Visitor v) {
		v.visitEnemyShot(this);
	}
	
	@Override
	public void update() {
		rectangle.y += speed;
		super.update();
	}

}