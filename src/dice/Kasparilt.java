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
		int sides = 6; // täringul on 6 tahku
		int dicesOriginal = 5; // mitu täringut (nii mitme täringu kaupa viskan (nt 5 korraga))
		int dicesTemp;
		int triesOriginal = 3; // mitu proovi (e mitu korda võimalus täringuid visata)
		int triesTemp;
		int rolls = 1000000; // viskan täringuid miljon korda kokku (per 1 täring)
		int series = rolls / (dicesOriginal * triesOriginal); // viskan täringut nii mitu seeriat (nt 5 täringut korraga)
		
		int rollResult = 0; // ühe veeretuse tulemus
		int numbers[] = new int[5]; // massiiv täringute tulemuse jaoks
		int alreadyCorrect = 0; // mitu täringut olen õigesti juba ära visanud
		int totalTimesSuccessful = 0; // mitu korda olin edukas oma üldeesmärgi saavutamisel
		
		int goalAmount = 5; // mitu õiget peab olema kõigist (kas jahin 5t õiget 5st vmt).
		
		// 5t täringut viskan 200 tuhat korda
		for(int i = 0; i < series; i++){
			// uus visketsükkel ja numbritel reset, kuna uus katse (5 täringut, 3 katset)
			triesTemp = triesOriginal; 
			dicesTemp = dicesOriginal;
			// senikaua viskan kuni visete korrad on otsas või pole ühtegi täringut visata
			while(triesTemp > 0 && alreadyCorrect < goalAmount){ // NB! &&, muidu timestothrow läheb negatiivseks kuna pole veel saanud vajaminevaid tulemusi
				// viskan nii palju täringuid ükshaaval kui on täringuid ette nähtud (nt 5 asemel 3)
				numbers = new int[dicesTemp];
				for(int j=0;j<dicesTemp;j++){
					rollResult = (int)(Math.random()*sides)+1;
					numbers[j]=rollResult;
				} // for
				for(int wantThisNumber : numbers){
					if(wantThisNumber==4){
						alreadyCorrect++;
						dicesTemp--;	
					} // if
				} // for
				triesTemp--;
				//System.out.println("timesToThrow " + tries + " result " + result);
				if(alreadyCorrect == goalAmount){
					totalTimesSuccessful++;
				} // if
			} // END while

			//System.out.print("mitu korda saan veel visata: " + tries );
			//System.out.println(" tulemused: " + Arrays.toString(numbers) );
			alreadyCorrect=0;
		} // for
		
		System.out.println("Kokku täringuid visata: " + rolls + " ja see tähendab, et on seeriaid: " + series);
		System.out.println(triesOriginal + " viset " + dicesOriginal + " täringuga ja vaja saada "+ goalAmount +" õiget, tulemus: " + totalTimesSuccessful);
		System.out.println("Percentage: " +  ((double) totalTimesSuccessful / (double) series * 100) + "%");

	}
}