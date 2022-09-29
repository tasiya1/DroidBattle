
public class Vampire extends Droid {

boolean visibility;
	
	public Vampire(int type, int damage, int farness) {
		super(type, damage, farness);
	}	
	
	@Override
	public int attack(Droid droid) {
		
		if (droid.itsType() == 1) {
			System.out.println("\n\tВампір " + this.getName() + " ніяк не може побачити фею " + droid.getName());
			return 1;
		}
		
 		if ((droid.getTeam() != this.getTeam()) && (droid.getHealth() > 0) && (this.getHealth() > 0)) { //якщо це ворожий дроїд і він "живий"

			droid.setHealth(this.getDamage());
			System.out.print("\n\tВампір " + this.getName() + " атакував дроїда ");
			
			if (droid.getHealth() <= 0) {
				System.out.println(droid.getName() + ".\n\tДроїд " + droid.getName() + "вилетів з гри.");
				return 0;
			}
			else System.out.println(droid.getName() + " та забрав у нього -" + this.getDamage() + 
					" XP. Тепер у нього " + droid.getHealth() + " XP.");
				return 1;
 		}
 		if (droid.getHealth() <= 0) {
			System.out.println("\tДроїд " + droid.getName() + " вилетів з гри.");
			return 0;
 		}
 		return 1; //повернений результат цієї функції використовується в режимі одиночної гри
 	}
	
	@Override
	public int itsType() {
		return 4;
	}
}

