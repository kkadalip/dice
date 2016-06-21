package dice;

import java.text.DecimalFormat;

public class Improved {

	public static void main(String[] args) {
		// Kaks või kolm kolmest täringust: 74056 --> 7,41%
		// Emb kumb: 55971 --> 5,60%
		
		// Vaja selgitada, kas jätta joker täringut alles või mitte
		
		int rolled = 1000000;

		// KAS KUUS VISET (3+3) JA SAAN KAKS KÄTTE
		// VÕI NELI VISET (2+2) JA SAAN ÜHE KÄTTE

		// 1) Kolmest täringust kaks on samad
		int twoOrThreeCertainNumber = 0;
		// 2) Kahest täringust on kas üks või teine, mida vaja // ÜKS EMBA KUMBA 1 või 6 ... JA ÜKS KINDEL nt 4
		int thisOrThat = 0;

		// Kas jätta üks "poolik" ja veeretada 2 täringut, et saada

		Dice dice1 = new Dice(1);
		Dice dice2 = new Dice(2);
		Dice dice3 = new Dice(3);

		int dice1_res;
		int dice2_res;
		int dice3_res;
		int goalNumber = 3; // mis numbrit tahan visata
		int howManyGoalNumbers;

		int goalThisThat1 = 1;
		int goalThisThat2 = 6;
		int goalThisThat3 = 4;

		for(int i=0; i < rolled; i++){
			dice1_res = throwDice();
			dice2_res = throwDice();
			dice3_res = throwDice();

			// CHECK IF 2 OF SAME START  ---------------------------------------------
			howManyGoalNumbers = 0;
			if(dice1_res == goalNumber){
				howManyGoalNumbers++;
			}
			if(dice2_res == goalNumber){
				howManyGoalNumbers++;
			}
			if(dice3_res == goalNumber){
				howManyGoalNumbers++;
			}
			if(howManyGoalNumbers == 2 || howManyGoalNumbers == 3){
				twoOrThreeCertainNumber++;
			}
			// CHECK IF 2 OF SAME END  ---------------------------------------------

			// Dice1 on kas 1 või 6 JA Dice2 on 4
			// VÕI
			// Dice2 on 4 JA Dice1 on kas 1 või 6
			if((dice1_res == goalThisThat1 || dice1_res == goalThisThat2) && (dice2_res == goalThisThat3) ||
					dice2_res == goalThisThat3 && (dice1_res == goalThisThat1 || dice1_res == goalThisThat2)){
				thisOrThat++;
			}

			incrementDiceResult(dice1, dice1_res);
			incrementDiceResult(dice2, dice2_res);
			incrementDiceResult(dice3, dice3_res);
		}
		dice1.printDice(rolled);
		dice2.printDice(rolled);
		dice3.printDice(rolled);

		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println();
		System.out.println("Kaks või kolm kolmest täringust: " + twoOrThreeCertainNumber + " --> " + df.format((double) twoOrThreeCertainNumber / rolled * 100) + "%");
		System.out.println("Emb kumb: " + thisOrThat + " --> " + df.format((double) thisOrThat / rolled * 100) + "%");
	}

	public static int throwDice(){
		int result = (int) (Math.random() * 6 + 1);
		return result;
	}

	public static double calculatePercentage (int smaller, int bigger){
		return (double) smaller / bigger * 100;
	}

	public static void incrementDiceResult(Dice dice, int result){
		switch (result){
		case 1:
			dice.setSide1_total(dice.getSide1_total() + 1);
			break;
		case 2: 
			dice.setSide2_total(dice.getSide2_total() + 1);
			break;
		case 3: 
			dice.setSide3_total(dice.getSide3_total() + 1);
			break;
		case 4: 
			dice.setSide4_total(dice.getSide4_total() + 1);
			break;
		case 5: 
			dice.setSide5_total(dice.getSide5_total() + 1);
			break;
		case 6:
			dice.setSide6_total(dice.getSide6_total() + 1);
			break;
		}
	}

	public static class Dice{
		int dice_number;

		int side1_total = 0;
		int side2_total = 0;
		int side3_total = 0;
		int side4_total = 0;
		int side5_total = 0;
		int side6_total = 0;

		public Dice(int whichDice) {
			dice_number = whichDice;
		}

		public int getSide1_total() {
			return side1_total;
		}
		public void setSide1_total(int side1_total) {
			this.side1_total = side1_total;
		}
		public int getSide2_total() {
			return side2_total;
		}
		public void setSide2_total(int side2_total) {
			this.side2_total = side2_total;
		}
		public int getSide3_total() {
			return side3_total;
		}
		public void setSide3_total(int side3_total) {
			this.side3_total = side3_total;
		}
		public int getSide4_total() {
			return side4_total;
		}
		public void setSide4_total(int side4_total) {
			this.side4_total = side4_total;
		}
		public int getSide5_total() {
			return side5_total;
		}
		public void setSide5_total(int side5_total) {
			this.side5_total = side5_total;
		}
		public int getSide6_total() {
			return side6_total;
		}
		public void setSide6_total(int side6_total) {
			this.side6_total = side6_total;
		}

		public void printDice(int totalTimesRolled){
			DecimalFormat df = new DecimalFormat("0.00");      			
			System.out.println("Dice "+ dice_number +":");
			System.out.println("1: " + side1_total + " --> " + df.format(calculatePercentage(side1_total, totalTimesRolled)) + "%");
			System.out.println("2: " + side2_total + " --> " + df.format(calculatePercentage(side2_total, totalTimesRolled)) + "%");
			System.out.println("3: " + side3_total + " --> " + df.format(calculatePercentage(side3_total, totalTimesRolled)) + "%");
			System.out.println("4: " + side4_total + " --> " + df.format(calculatePercentage(side4_total, totalTimesRolled)) + "%");
			System.out.println("5: " + side5_total + " --> " + df.format(calculatePercentage(side5_total, totalTimesRolled)) + "%");
			System.out.println("6: " + side6_total + " --> " + df.format(calculatePercentage(side6_total, totalTimesRolled)) + "%");
		}

	}
}











//// DICE NR1
//int dice1_1 = 0;
//int dice1_2 = 0;
//int dice1_3 = 0;
//int dice1_4 = 0;
//int dice1_5 = 0;
//int dice1_6 = 0;
//// DICE NR2
//int dice2_1 = 0;
//int dice2_2 = 0;
//int dice2_3 = 0;
//int dice2_4 = 0;
//int dice2_5 = 0;
//int dice2_6 = 0;
//// DICE NR3
//int dice3_1 = 0;
//int dice3_2 = 0;
//int dice3_3 = 0;
//int dice3_4 = 0;
//int dice3_5 = 0;
//int dice3_6 = 0;

//int throwDiceOnce = throwDice();
//System.out.println(throwDiceOnce);

//double dice_1_percentage = calculatePercentage(dice_1, howManyTimesToRollDice);
//double dice_2_percentage = calculatePercentage(dice_2, howManyTimesToRollDice);
//double dice_3_percentage = calculatePercentage(dice_3, howManyTimesToRollDice);
//double dice_4_percentage = calculatePercentage(dice_4, howManyTimesToRollDice);
//double dice_5_percentage = calculatePercentage(dice_5, howManyTimesToRollDice);
//double dice_6_percentage = calculatePercentage(dice_6, howManyTimesToRollDice);

//System.out.println("Veeretasin täringut " + howManyTimesToRollDice + " korda");
//System.out.println("1: " + dice_1 + " -> " + dice_1_percentage + "%");
//System.out.println("2: " + dice_2 + " -> " + dice_2_percentage + "%");
//System.out.println("3: " + dice_3 + " -> " + dice_3_percentage + "%");
//System.out.println("4: " + dice_4 + " -> " + dice_4_percentage + "%");
//System.out.println("5: " + dice_5 + " -> " + dice_5_percentage + "%");
//System.out.println("6: " + dice_6 + " -> " + dice_6_percentage + "%");

//System.out.println("Dice 1:");
//System.out.println("1: " + dice1.getSide1_total());
//System.out.println("2: " + dice1.getSide2_total());
//System.out.println("3: " + dice1.getSide3_total());
//System.out.println("4: " + dice1.getSide4_total());
//System.out.println("5: " + dice1.getSide5_total());
//System.out.println("6: " + dice1.getSide6_total());
//
//System.out.println("Dice 2:");
//System.out.println("1: " + dice2.getSide1_total());
//System.out.println("2: " + dice2.getSide2_total());
//System.out.println("3: " + dice2.getSide3_total());
//System.out.println("4: " + dice2.getSide4_total());
//System.out.println("5: " + dice2.getSide5_total());
//System.out.println("6: " + dice2.getSide6_total());
//
//System.out.println("Dice 3:");
//System.out.println("1: " + dice3.getSide1_total());
//System.out.println("2: " + dice3.getSide2_total());
//System.out.println("3: " + dice3.getSide3_total());
//System.out.println("4: " + dice3.getSide4_total());
//System.out.println("5: " + dice3.getSide5_total());
//System.out.println("6: " + dice3.getSide6_total());
