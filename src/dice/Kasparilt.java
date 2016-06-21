package dice;

import java.util.Arrays;

public class Kasparilt {

	public static void main(String[] args) {
		// Tõenäosused:
		// 1: 62.8%
		// 2: 54.4%
		// 3: 30.2%
		// 4: 9.7%
		// 5: 1.3%
		
		// parameetrid:
		int sides = 6; // täringul on 6 tahku
		int dicesOriginal = 5; // 5 // mitu täringut (nii mitme täringu kaupa viskan (nt 5 korraga))
		int triesOriginal = 3; // 3 // mitu proovi (e mitu korda võimalus täringuid visata)
		int goalAmount = 3; // mitu õiget (peab olema kõigist (kas jahin 5t õiget 5st vmt))
		int rolls = 1000000; // viskan täringuid miljon korda kokku (per 1 täring)
		int series = rolls / (dicesOriginal * triesOriginal); // viskan täringut nii mitu seeriat (nt 5 täringut korraga)
		int wantThisNumber = 4;
		
		// ajutised kohahoidjad:
		int dicesTemp;
		int triesTemp;
		int rollResult = 0; // ühe veeretuse tulemus
		int numbers[] = new int[5]; // massiiv täringute tulemuse jaoks
		int alreadyCorrect = 0; // mitu täringut olen õigesti juba ära visanud
		
		// lõpptulemused:
		int totalTimesSuccessful = 0; // mitu korda olin edukas oma üldeesmärgi saavutamisel
		
		// 5te täringut viskan nt 60 tuhat korda (miljon total vmt)
		// üks seeria on näiteks 3 korda 5 täringut visata
		for(int i = 0; i < series; i++){
			// uus visketsükkel ja numbritel reset, kuna uus katse (5 täringut, 3 katset)
			triesTemp = triesOriginal; 
			dicesTemp = dicesOriginal;
			alreadyCorrect = 0;
			// senikaua viskan kuni visete kordi veel on JA veel on vaja saada õigeid täringuid juurde
			while(triesTemp > 0 && alreadyCorrect < goalAmount){ // NB! &&, muidu timestothrow läheb negatiivseks kuna pole veel saanud vajaminevaid tulemusi
				// viskan nii palju täringuid ükshaaval kui on täringuid ette nähtud (nt 5 asemel 3)
				numbers = new int[dicesTemp]; // hoidik tulemuste jaoks, resetin ära uute jaoks, pikkus vastab täringute arvule
				// reaalne täringute viskamine nii palju arv kordi, kui on täringuid visata
				for(int j=0;j<dicesTemp;j++){
					rollResult = (int)(Math.random()*sides)+1;
					numbers[j]=rollResult;
				} // for
				// kontrollin saadud tulemuste massiivi läbi, kui on tahetud number, siis läheb korrektsete sekka
				for(int number : numbers){
					if(number == wantThisNumber){
						alreadyCorrect++; // õigeid tulemusi on nüüd ühe võrra rohkem
						dicesTemp--; // ühe võrra vähem saan visata täringuid
					} // if
				} // for
				triesTemp--;
				// kui on õigeid nii palju kui vaja, siis olen olnud edukas
				if(alreadyCorrect == goalAmount){
					totalTimesSuccessful++;
				} // if
				// TÖÖTAV KOODI KONTROLL:
				//System.out.print("Saan veel visata: " + triesTemp);
				//System.out.println(" tulemused: " + Arrays.toString(numbers));
			} // END while
		} // for
		
		System.out.println("Kokku täringuid visata: " + rolls + " ja see tähendab, et on seeriaid: " + series);
		System.out.println(triesOriginal + " viset " + dicesOriginal + " täringuga ja vaja saada "+ goalAmount +" õiget, tulemus: " + totalTimesSuccessful);
		System.out.println("Percentage: " +  ((double) totalTimesSuccessful / (double) series * 100) + "%");

	}
}