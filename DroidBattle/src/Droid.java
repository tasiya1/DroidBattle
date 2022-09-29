import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class Droid {
 	private String name; //ім'я дроїда
 	private int health; //healthbar
 	private int damage; //ушкодження
 	private int id; //унікальний номер дроїда
 	private int team; //команда
 	private int type; //тип дроїда
 	private int farness; //дальнітсь ураження
 	private int x, y; //положення дроїда в координатах

 	public Droid (int type, int damage, int farness) {
 		Scanner sc = new Scanner(System.in);
 		System.out.print("\tІм'я дроїда-> "); 
 		this.name = sc.nextLine();
 	 	this.health = 100; 
 	 	this.damage = damage;
 	 	this.id = hashCode();
 	 	this.team = 0; //по дефолту встановлюється 0
 	 	this.type = type;
 	 	this.farness = farness;
 	}
 	
 	//getter
 	public String getName() {return this.name;}
 	public int getHealth() {return this.health;}
 	public int getDamage() {return this.damage;}
 	public int getId() {return this.id;}
 	public int getTeam() {return this.team;}
 	public int getFarness() {return this.farness;}
 	public int getX() {return this.x;}
 	public int getY() {return this.y;}
 	
 	//setter
 	public void setName (String name) {this.name = name;}
 	public void setHealth (int health) {this.health -= health;} //здоров'я забирається відповідно від ушкодження
 	public void setDamage (int damage) {this.damage += damage;}
 	public void setId (int id) {this.id = id;}
 	public void setTeam (int team) {this.team = team;}
 	public void setFarness (int farness) {this.farness = farness;}
 	public void setX (int x) {this.x = x;}
 	public void setY (int y) {this.y = y;}
 	
 	public void renewHealth () {this.health = 100;}
 	
 	public int attack(Droid droid) {return 0;} //в похідних класах відбувається особиста варіація цього методу
 	
 	public boolean checkZone(Droid droid) {
 		/*чи дроїд знаходиться в підходящій відстані від цього дроїда*/
 		if (Math.sqrt(Math.pow(this.getX() - droid.getX(), 2) + (Math.pow(this.getY() - droid.getY(), 2))) <= this.getFarness())
 						return true;
 		return false;
 	}
 	
 	public int itsType() {return 0;}
 	
 	public void printInfo() {
 		String [] types = {"Фея", "Ельф", "Соловей", "Вампір"};
 		
 		System.out.print("\n\n\tІм'я: " + this.name);
 		System.out.print("\n\tТип: " + types[this.type-1]);
 		System.out.print("\n\tID: " + this.id);
 		System.out.print("\n\tУшкодження: " + this.damage);
 		System.out.print("\n\tДальність поля: " + this.farness);
 		//System.out.print("\n\tLength of step: " + this.step);
 	}
}

