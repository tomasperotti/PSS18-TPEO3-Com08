package Weapon;

import java.awt.Point;
import Main.Game;
import Shot.PlayerShot;
import Shot.Shot;

public class WeaponPlayer extends Weapon {
	
	public WeaponPlayer(Game g) {
		super(g);
	}

	@Override
	public void shoot(Point pos) {
		Shot s = new PlayerShot(pos.x + 15, pos.y, game);
		game.addEntity(s);
	}
}
