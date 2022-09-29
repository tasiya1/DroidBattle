
public class Fairy extends Droid{
	
	public Fairy(int type, int damage, int farness) {
		super(type, damage, farness);
	}
	
	@Override
	public int attack(Droid droid) {
 		if ((droid.getTeam() != this.getTeam()) && (droid.getHealth() > 0) && (this.getHealth() > 0)) { //якщо це ворожий дроїд і він "живий"

			droid.setHealth(this.getDamage());
			System.out.print("\n\tФея " + this.getName() + " вдарила дроїда "+ droid.getName());
			
			if (droid.getHealth() <= 0) {
				System.out.println(".\n\n\tДроїд " + droid.getName() + " вилетів з гри.");
				return 0;
			}
			else System.out.println( " та забрала у нього -" + this.getDamage() +" XP. Тепер у нього " + droid.getHealth() + " XP.");
				return 1;
 		}
 		if (droid.getHealth() <= 0) {
			System.out.println("\tДроїд " + droid.getName() + " вилетів з гри.");
			return 0;
 		}
 		return 1;  //повернений результат цієї функції використовується в режимі одиночної гри
 	}
	
	@Override
	public int itsType() {
		return 1;
	}
}
