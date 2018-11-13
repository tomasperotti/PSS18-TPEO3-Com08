package PowerUp;

import java.awt.Image;
import javax.swing.ImageIcon;
import Entity.Player;
import Main.Game;
import Shield.Shield;
import Shield.ShieldPowerUp;

public class PowerUpShield extends PowerUp {

	public PowerUpShield(int x, int y, Game g) {
		super(x, y, g);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resources/PowerUp/pw_shield_00.png"));
		this.icon = new ImageIcon(img.getImage().getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_DEFAULT));
	}

	@Override
	public void addPowerUp(Player player) {
		super.addPowerUp(player);
		Shield s = new ShieldPowerUp(player);
		player.setShield(s);
		game.addDeadEntity(this);
	}

}
