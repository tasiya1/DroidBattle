
public class Elf extends Droid{

	private int potatoAmount; //запаси картоплі
	
	public Elf(int type, int damage, int farness) {
		super(type, damage, farness);
		this.potatoAmount = 10;
	}
	
	public int getPotatoAmount () {return this.potatoAmount;}
	
	public void setPotatoAmount (int potato_amount) {this.potatoAmount -= potato_amount;}
	public void renewPotatoAmount() {this.potatoAmount = 10;}
	
	@Override
	public int attack(Droid droid) {
		
 		if ((droid.getTeam() != this.getTeam()) && (droid.getHealth() > 0) && (this.getHealth() > 0) && (this.getPotatoAmount() > 0)) { //якщо це ворожий дроїд і він "живий"

			droid.setHealth(this.getDamage());
			this.setPotatoAmount(1);
			System.out.print("\n\tЕльф " + this.getName() + " жбурнув картоплею  у дроїда ");
			
			if (droid.getHealth() <= 0) {
				System.out.println(droid.getName() + ".\n\tДроїд " + droid.getName() + "вилетів з гри.");
				return 0;
			}
			else System.out.println(droid.getName() + " та забрав у нього -" + this.getDamage() + 
					" XP. Тепер у нього " + droid.getHealth() + " XP,  а в ельфа " + this.getName() + " " + this.getPotatoAmount() + " картоплин.");
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
		return 2;
	}
}
