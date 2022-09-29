import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class Main {

	//TODO
	public static Droid [] droids; //видимість
	
	static void create() {
		Scanner sc = new Scanner(System.in);
		int amount, type;
		System.out.print("\n\tВведіть кількість дроїдів -> ");
		amount = sc.nextInt();
		droids = new Droid [amount];
		System.out.print("\n\tВведіть тип дроїда:\n\t1 - Фея\n\t2 - Ельф\n\t3 - Соловей\n\t4 - Вампір\n\t");
		
		for (int i = 0; i < amount; i++) {
			
			System.out.print("\n\tТип дроїда-> ");
			type = sc.nextInt();
			switch (type) {
			
			case 1:
				droids[i] = new Fairy(type, 20, 5);
				break;
			case 2:
				droids[i] = new Elf(type, 15, 4);
				break;
			case 3:
				droids[i] = new Nightingale(type, 30, 3);
				break;
			case 4:
				droids[i] = new Vampire(type, 25, 5);
				break;
			}
		}
	}
	
	static void showList() {
		
		for (int i = 0; i < droids.length; i++) {
			droids[i].printInfo();
		}
		System.out.println();
	}
	
	static void battleTwo() {
		Scanner sc = new Scanner(System.in);
		String name_container;
		Droid [] two = new Droid [2];
		
		System.out.print("\n\tВиберіть дроїдів для одиночної гри.");
		
		for (int i = 0; i < 2; i++) {
			System.out.print("\n\tІм'я " + (i + 1) + "-го дроїда:\n\t-> ");
			name_container = sc.nextLine();
			
			for (int k = 0; k < droids.length; k++) {
				if (droids[k].getName().equals(name_container)) {
					two[i] = droids[k];
					two[i].setTeam(i);
				}
			}
		}
		
		while ((two[0].getHealth() > 0) && (two[1].getHealth() > 0)) { //поки обидва дроїди живі
			
			if (two[0].getHealth() > 0 && two[0].attack(two[1]) == 0)
					return;
				
			if (two[1].getHealth() > 0 && two[1].attack(two[0]) == 0)
					return;
		}
	}
	
	static void battleTeam() throws Exception {
		
		/*вибір бойових дроїдів---------------------------------------------------------------------------------------*/
		
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		int am1, am2; //amount учасників першої та другої команд
	
		System.out.print("\n\tКількість учасників першої команди -> ");
		am1 = sc.nextInt();
		System.out.print("\n\tКількість учасників другої команди -> ");
		am2 = sc.nextInt();
		sc.nextLine(); //забирання зайвого \n
		
		Droid [] first ;
		Droid [] second ;
		
		System.out.println("\n\tВиберіть дроїдів для гри в команді.");
		first = createTeam(am1, 1);
		second = createTeam(am2, 2);
		
		/*Відновлення дроїдів(якщо користувач вирішив запустити іншу гру з тими самими дроїдами-------------------------------*/
		setDefaults(first);
		setDefaults(second);

		/*Встановлення координат----------------------------------------------------------------------------------------------*/
		setPositionsMode(first, second);
		
		/*Підготовка до гри---------------------------------------------------------------------------------------------------*/
		setUpBeforeBattle(first, second);
		setUpBeforeBattle(second, first);
		
		 /*Початок битви та вибір ходів---------------------------------------------------------------------------------------*/
		int attacker, attacked;
		//boolean move_completed = false;
		
		while ((aliveDroids(first) > 0) && (aliveDroids(second) > 0)) { //поки в обидвох командах є діючі бійці
			
			while (true) { //перевірка, чи вибрані дроїди живі
				 attacker = rand.nextInt(first.length); //вибір дроїдів
				 attacked = rand.nextInt(second.length);
				 if (first[attacker].getHealth() > 0 && second[attacked].getHealth() > 0) //живі? супер, тоді один атакує
					 break;
			}
			 first[attacker].attack(second[attacked]); //атака!
			 									 //дроїд соловей має властивість після кожного свого ходу телепортуватися
			 if (first[attacker].itsType() == 3) //якщо після його телепортування в його полі виявився якийсь ворожий дроїд, то соловей його ослаблює
				 for (int n = 0; n < second.length; n++) {
					 if (first[attacker].checkZone(second[n]))
						 second[n].setDamage(-5);
				 }
			 
			 //якщо у якійсь із команд після ходу не залишилось бійців
			 if ((aliveDroids(first) == 0) || (aliveDroids(second) == 0))
				 break;
			 
			 while (true) {
				 attacker = rand.nextInt(second.length);
				 attacked = rand.nextInt(first.length);
				 if (second[attacker].getHealth() > 0 && first[attacked].getHealth() > 0)
					 break;
			}
			 second[attacker].attack(first[attacked]);
			 for (int n = 0; n < first.length; n++) {
				 if (second[attacker].checkZone(first[n]))
					 first[n].setDamage(-5);
			 }
		 }
		
		if (aliveDroids(second) == 0)
			System.out.println("\n\tВиграла команда №1!!!");
		else System.out.println("\n\tВиграла команда №2!!!");
	}
	
	/*-----------------------------------------------------------------------------------------------------------СТВОРЕННЯ КОМАНД*/
	static Droid[] createTeam(int amount, int team) {
		Scanner sc = new Scanner(System.in);
		String nameContainer;
		System.out.print("\n\tУчасники " + team + "ї команди: ");
		Droid [] droidTeam = new Droid [amount];
			
			for (int i = 0; i < amount; i++) {
				System.out.print("\n\tІм'я " + (i + 1) + "-го дроїда:\n\t-> ");
				nameContainer = sc.nextLine();
				
				for (int j = 0; j < droids.length; j++) {
					if (droids[j].getName().equals(nameContainer)) {
						droidTeam[i] = droids[j];
						droidTeam[i].setTeam(team);
					}
				}
			}
		return droidTeam;
	}
	
	static int aliveDroids(Droid droids[]) { //команда, яка перевіряє чи залишились ще "живі" дроїди
		int amount = 0;
		
		for (int i = 0; i < droids.length; i++) {
			if (droids[i].getHealth() > 0)
				amount++;
		}
		return amount;
	}
	
	static void setUpBeforeBattle(Droid [] one, Droid [] another) {
		/*що тут відбувається: перевірка
		 * якщо дроїд є ельф і він знаходиться в зоні магії феї, якщо є, то він отримує збільшення сили
		 * якщо дроїд  не є вампір та знаходиться у звуковому полі солов'я*/
		for (int i = 0; i < one.length; i++) { //для одної команди
			if (one[i].itsType() == 1) //якщо це фея
				for (int j = 0; j < one.length; j++) { //то ельфик в зоні магії феї отримує силу +20
					if ((one[j].itsType() == 2)&& (one[i].checkZone(one[j]))) {
						one[j].setDamage(20);
					}
				}
			if (one[i].itsType() == 3) //якщо це соловей
				for (int j = 0; j < another.length; j++) {
					if ((one[j].itsType() != 4) && (one[i].checkZone(another[j]))) //якщо ворожий дроїд не є вампіром та знаходиться у силовому полі солов'я
						one[j].setDamage(-5);
				}
			if (one[i].itsType() == 2)
				((Elf) one[i]).renewPotatoAmount();
		}		
	}
	
	static void setLucky(Droid droid[]) {
		int x, y;
		Random rand = new Random();
		for (int i = 0; i < droid.length; i++) {
			x = rand.nextInt(21);
			y = rand.nextInt(21);
			droid[i].setX(x);
			droid[i].setY(y);
		}
	}
	
	static void setCustom(Droid droid[]) {
		Scanner sc = new Scanner(System.in);
		int x, y;
		for (int i = 0; i < droid.length; i++) {
			System.out.print("\n\n\t" + droid[i].getName() + "-> ");
			x = sc.nextInt();
			y = sc.nextInt();
			droid[i].setX(x);
			droid[i].setY(y);
		}
	}
	
	static void setDefaults(Droid [] droid) {
		for (int i = 0; i < droid.length; i++) {
			droid[i].renewHealth();
		}
	}
	
	static void setPositionsMode(Droid droids1[], Droid droids2[]) {
		Scanner sc = new Scanner(System.in);
		String option;
		System.out.print("\n\tВведіть режим вибору розташувань дроїдів [lucky або custom] -> ");
		option = sc.nextLine();
		if (option.equals("custom")) {
			System.out.println("\n\tВведіть координати (x, y) для кожного дроїда (межі - від 0 до 20). \n\n");
			setCustom(droids1); //користувач розставляє дроїдів
			setCustom(droids1);
		}
		else {
			setLucky(droids1); //а як ні, то встановлюється режим "щасливчик"
			setLucky(droids2);
			System.out.println("\n\tВипадкові координати встановлено! :)");
		}
	}
	
	static void printInformation() throws Exception { //читання інформації з довідкового файла
		
		File info = new File ("info.txt");
		BufferedReader br = new BufferedReader(new FileReader(info));
		String buffer;
		
		while ((buffer = br.readLine()) != null)
			System.out.println(buffer);
		br.close();
	}
	

	static int commandChooser() throws Exception {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n\n\tВведіть команду: ");
		String option = sc.nextLine();
		//sc.nextLine();
		//sc.close();
				
		if (option.equals("/create"))
			create();
		if (option.equals("/showlist"))
			showList();
		if (option.equals("/battletwo"))
			battleTwo();
		if (option.equals("/battleteam"))
			battleTeam();
		if (option.equals("/exit"))
			return 0;
		if (option.equals("/d"));
			printInformation();	
		return 1;
	}
	
	public static void main(String[] args) throws Exception {
		//ввести команду

		System.out.println("\n\tСписок команд - /d");
		while (commandChooser() != 0);
		System.out.println("\n\tГру завершено.");
		//create();
	}
}
