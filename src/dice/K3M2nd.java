package dice;

import java.util.Arrays;

public class K3M2nd {

	public static void main(String[] args) {

		// 25%, EI JÄTA MÄNDA ALLES KUI KA TULI 2 MÄNDA PÄRAST ESIMEST VEERETAMIST
		
		// MUL ON OLEMAS 2 ÕIGET, VEERETAN TEISED UUESTI
		// EHK 3 + 3 TÄRINGUT VISATA
		// KUI SAAN ÜHE MÄNNA, SIIS SEDA ALLES EI JÄTA, ÕIGED JÄTAN!
		// VÕIDU TINGIMUSED:
		// 1) SAAN 2 ÕIGET
		// 2) SAAN 3 ÕIGET
		// 3) SAAN 2 MÄNDA ja 1 ÕIGE
		// ehk
		// 1) 1 + 1
		// 2) 1 + 1 + 1
		// 3) 6 + 6 + 1
		
		// MUL ON OLEMAS 2 ÕIGET, MÄND, VEERETAN TEISED UUESTI
		// EHK 2 + 2 TÄRINGUT VISATA
		// KUI SAAN TEISE MÄNNA VÕI ÕIGE, SIIS SELLE JÄTAN KA ALLES JA VEERETAN VIIMAST TÄRINGUT ERALDI
		// VÕIDU TINGIMUSED:
		// 1) MÄND + ÕIGE
		// 2) ÕIGE + ÕIGE (2 ÕIGET)
		// ehk
		// 1) 6 + 1
		// 2) 1 + 1
		
		// mida veel kontrollida, 3tk 4st õiged ja 1 mänd käes, kas on mõtet mända alles jätta
		
		// parameetrid:
		int sides = 6; // täringul on 6 tahku
		int dicesOriginal = 2; // 5 // mitu täringut (nii mitme täringu kaupa viskan (nt 5 korraga))
		int triesOriginal = 2; // 3 // mitu proovi (e mitu korda võimalus täringuid visata)
		//int goalAmount = 2; // mitu õiget (peab olema kõigist (kas jahin 5t õiget 5st vmt))
		int rolls = 1000000; // viskan täringuid miljon korda kokku (per 1 täring)
		int series = rolls / (dicesOriginal * triesOriginal); // viskan täringut nii mitu seeriat (nt 5 täringut korraga)
		int wantNumber = 1;
		int jokerNumber = 6;
		
		// ajutised kohahoidjad:
		int dicesTemp;
		int triesTemp;
		int rollResult = 0; // ühe veeretuse tulemus
		int numbers[] = new int[5]; // massiiv täringute tulemuse jaoks
//		int alreadyCorrect = 0; // mitu täringut olen õigesti juba ära visanud
		
		// lõpptulemused:
		int totalTimesSuccessful = 0; // mitu korda olin edukas oma üldeesmärgi saavutamisel
		
		int jokers;
		int wantNumbers;
		
		// 5te täringut viskan nt 60 tuhat korda (miljon total vmt)
		// üks seeria on näiteks 3 korda 5 täringut visata
		for(int i = 0; i < series; i++){
			System.out.println();
			// uus visketsükkel ja numbritel reset, kuna uus katse (5 täringut, 3 katset)
			triesTemp = triesOriginal; 
			dicesTemp = dicesOriginal;
//			alreadyCorrect = 0;

			wantNumbers = 0;
			jokers = 0;
			// senikaua viskan kuni visete kordi veel on JA veel on vaja saada õigeid täringuid juurde
			//while(triesTemp > 0 && alreadyCorrect < goalAmount){ // NB! &&, muidu timestothrow läheb negatiivseks kuna pole veel saanud vajaminevaid tulemusi
			while(triesTemp > 0){
				// viskan nii palju täringuid ükshaaval kui on täringuid ette nähtud (nt 5 asemel 3)
				numbers = new int[dicesTemp]; // hoidik tulemuste jaoks, resetin ära uute jaoks, pikkus vastab täringute arvule
				// reaalne täringute viskamine nii palju arv kordi, kui on täringuid visata
				for(int j=0;j<dicesTemp;j++){
					rollResult = (int)(Math.random()*sides)+1;
					numbers[j]=rollResult;
				} // for
			
				// kontrollin saadud tulemuste massiivi läbi, kui on tahetud number, siis läheb korrektsete sekka
				for(int number : numbers){
					if(number == wantNumber){
						wantNumbers++;
						dicesTemp--; // ühe võrra vähem saan visata täringuid
					}else if(number == jokerNumber && jokers < 1){
						jokers++;
						dicesTemp--; // ühe võrra vähem saan visata täringuid
					}					
				} // for
				triesTemp--;
				// kui on õigeid nii palju kui vaja, siis olen olnud edukas
				
				// TÖÖTAV KOODI KONTROLL:
				System.out.print("Saan veel visata: " + triesTemp);
				System.out.println(" tulemused: " + Arrays.toString(numbers));
				
				if((jokers == 1 && wantNumbers == 1) || wantNumbers == 2){
					totalTimesSuccessful++;
					System.out.println("SUCCESSFUL! \n");
					break;
				}
			} // END while
		} // for
		
		System.out.println("\nKokku täringuid visata: " + rolls + " ja see tähendab, et on seeriaid: " + series);
		System.out.println(triesOriginal + " viset " + dicesOriginal + " täringuga " + totalTimesSuccessful); // ja vaja saada "+ goalAmount +" õiget, tulemus: 
		System.out.println("Percentage: " +  ((double) totalTimesSuccessful / (double) series * 100) + "%");

	}
}