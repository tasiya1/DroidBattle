
import java.util.Random;

public class Nightingale extends Droid {
	
	public Nightingale(int type, int damage, int farness) {
		super(type, damage, farness);
	}
	
	
	@Override
	public int attack(Droid droid) {
		Random rand = new Random();
		int x, y;
		
 		if ((droid.getTeam() != this.getTeam()) && (droid.getHealth() > 0) && (this.getHealth() > 0)) { //якщо це ворожий дроїд і він "живий"

			droid.setHealth(this.getDamage());
			System.out.println("\n\tСоловей " + this.getName() + " чікнув дроїда " + droid.getName() + " та забрав у нього -" + this.getDamage() + 
					" XP. Тепер у нього " + droid.getHealth() + " XP.");

			x = rand.nextInt(21);
			y = rand.nextInt(21);			
			this.setX(x);
			this.setY(y);
			
			System.out.println("\n\tСоловей " + this.getName() + " телепортувався до [" + this.getX() + ", " + this.getY() + "].");

			return 1; //повернений результат цієї функції використовується в режимі одиночної гри
 		}
 		return 0;
 		}
 	
	@Override
	public int itsType() {
		return 3;
	}
}
