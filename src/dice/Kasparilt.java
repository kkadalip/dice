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
		int dicesOriginal = 5; // nii mitme täringu kaupa viskan (nt 5 korraga)
		int dicesTemp;
		int triesOriginal = 3; // mitu korda võimalus täringuid visata
		int triesTemp;
		int rolls = 100000; // viskan täringuid miljon korda kokku (per 1 täring)
		int series = rolls / (dicesOriginal * triesOriginal); // viskan täringut nii mitu seeriat (nt 5 täringut korraga)
		
		int rollResult = 0; // ühe veeretuse tulemus
		int numbers[] = new int[5]; // massiiv täringute tulemuse jaoks
		int howManyWantedNumbers = 0; // mitu täringut olen õigesti juba ära visanud
		int totalTimesSuccessful = 0; // mitu korda olin edukas oma üldeesmärgi saavutamisel
		
		int howManyOfAll = 1; // mitu õiget peab olema kõigist (kas jahin 5t õiget 5st vmt).
		
		// 5t täringut viskan 200 tuhat korda
		for(int i = 0; i < series; i++){
			// uus visketsükkel ja numbritel reset, kuna uus katse (5 täringut, 3 katset)
			triesTemp = triesOriginal; 
			dicesTemp = dicesOriginal;
			// senikaua viskan kuni visete korrad on otsas või pole ühtegi täringut visata
			while(triesTemp > 0 && howManyWantedNumbers < howManyOfAll){ // NB! &&, muidu timestothrow läheb negatiivseks kuna pole veel saanud vajaminevaid tulemusi
				// viskan nii palju täringuid ükshaaval kui on täringuid ette nähtud (nt 5 asemel 3)
				numbers = new int[dicesTemp];
				for(int j=0;j<dicesTemp;j++){
					rollResult = (int)(Math.random()*sides)+1;
					numbers[j]=rollResult;
				} // for
				for(int wantThisNumber : numbers){
					if(wantThisNumber==4){
						howManyWantedNumbers++;
						dicesTemp--;	
					} // if
				} // for
				triesTemp--;
				//System.out.println("timesToThrow " + tries + " result " + result);
				if(howManyWantedNumbers == howManyOfAll){
					totalTimesSuccessful++;
				} // if
			} // END while

			//System.out.print("mitu korda saan veel visata: " + tries );
			//System.out.println(" tulemused: " + Arrays.toString(numbers) );
			howManyWantedNumbers=0;
		} // for
		
		System.out.println("Kokku täringuid visata: " + rolls + " ja see tähendab, et on seeriaid: " + series);
		System.out.println("Tahan " + dicesOriginal + "-st saada " + triesOriginal + "-e viskega " + howManyOfAll + ", tulemus: " + totalTimesSuccessful);
		System.out.println("Percentage: " +  ((double) totalTimesSuccessful / (double) series * 100) + "%");

	}
}