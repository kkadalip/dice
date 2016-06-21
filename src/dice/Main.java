package dice;

//import java.lang.Math;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Result: " + throwDice());

		int howManyTimesToRollDice = 100000;
		
		int dice_1 = 0;
		int dice_2 = 0;
		int dice_3 = 0;
		int dice_4 = 0;
		int dice_5 = 0;
		int dice_6 = 0;

		int throwDiceOnce;
		for(int i=0; i < howManyTimesToRollDice; i++){
			throwDiceOnce = rollDice();
			//System.out.println(throwDiceOnce);
			switch (throwDiceOnce){
			case 1: 
				dice_1++;
				break;
			case 2: 
				dice_2++;
				break;
			case 3: 
				dice_3++;
				break;
			case 4: 
				dice_4++;
				break;
			case 5: 
				dice_5++;
				break;
			case 6:
				dice_6++;
				break;
			}
		}
		
		double dice_1_percentage = calculatePercentage(dice_1, howManyTimesToRollDice);
		double dice_2_percentage = calculatePercentage(dice_2, howManyTimesToRollDice);
		double dice_3_percentage = calculatePercentage(dice_3, howManyTimesToRollDice);
		double dice_4_percentage = calculatePercentage(dice_4, howManyTimesToRollDice);
		double dice_5_percentage = calculatePercentage(dice_5, howManyTimesToRollDice);
		double dice_6_percentage = calculatePercentage(dice_6, howManyTimesToRollDice);
		
		System.out.println("Veeretasin täringut " + howManyTimesToRollDice + " korda");
		System.out.println("1: " + dice_1 + " -> " + dice_1_percentage + "%");
		System.out.println("2: " + dice_2 + " -> " + dice_2_percentage + "%");
		System.out.println("3: " + dice_3 + " -> " + dice_3_percentage + "%");
		System.out.println("4: " + dice_4 + " -> " + dice_4_percentage + "%");
		System.out.println("5: " + dice_5 + " -> " + dice_5_percentage + "%");
		System.out.println("6: " + dice_6 + " -> " + dice_6_percentage + "%");
	}

	public static int rollDice(){
		int result = (int) (Math.random() * 6 + 1);
		//System.out.println(result);
		return result;
	}
	
	public static double calculatePercentage (int smaller, int bigger){
		return (double) smaller / bigger * 100;
	}


}
