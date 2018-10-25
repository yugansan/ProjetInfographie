


import java.util.Random;

public class GenCarte {

	private static int INFINI =100000;
	private int [][]tabGenerer;
	private Random r = new Random();
	private static int nbInfini;


	public GenCarte(int taille) {
		this.tabGenerer= new int [taille][taille];
		miseALInfini();
		initialisationValeurAleatoire();
		lanceGeneration();

	}

	public GenCarte(int [][] tab) {
		this.tabGenerer= tab;
		miseALInfini();
		launchGeneration();

	}


	public int [][]getGererateCarte(){
		return tabGenerer;
	}

	private void miseALInfini() {
		for (int i = 0; i < tabGenerer.length; i++) {
			for (int j = 0; j < tabGenerer.length; j++) {
				if(this.tabGenerer[i][j]==0) {
					this.tabGenerer[i][j]=INFINI;
					nbInfini++;
				}
			}
		}	
	}

	private void initialisationValeurAleatoire() {
		int valInfini =nbInfini;
		while(nbInfini > valInfini-(valInfini/3) ) {
			for (int i = 0; i < tabGenerer.length; i++) {
				for (int j = 0; j < tabGenerer.length; j++) {
					int randomNumber = r.nextInt(tabGenerer[i].length);
					if(tabGenerer[i][j]==INFINI && (i==randomNumber || j==randomNumber) ){
						tabGenerer[i][j]=2*randomNumber;
						nbInfini--;
					}
				}
			}
		}
	}

	private void lanceGeneration() {
		while(nbInfini>0) {
			for (int i = 0; i < tabGenerer.length/2; i++) {
				for (int j = 0; j < tabGenerer.length/2; j++) {
					if(tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}
			for (int i = tabGenerer.length-1; i >0; i--) {
				for (int j = tabGenerer.length-1; j >0; j--) {
					if(tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}
		}
		for (int i = 0; i < tabGenerer.length; i++) {
			for (int j = 0; j < tabGenerer.length; j++) {
				if(tabGenerer[i][j]>0) {
					int randomNumber = r.nextInt(tabGenerer[i][j]);
					if(randomNumber>tabGenerer[i][j]/2) {
						tabGenerer[i][j]+=5;
						getCaseAutourEtInitialiser(i, j);
					}else {
						tabGenerer[i][j]-=5;
						getCaseAutourEtInitialiser(i, j);
					}
				}else {
					if(-5>tabGenerer[i][j]/2) {
						tabGenerer[i][j]+=5;
						getCaseAutourEtInitialiser(i, j);
					}else {
						tabGenerer[i][j]-=5;
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}
		}
	}

	private void launchGeneration() {
		while(nbInfini >0) {
			int quart =(tabGenerer.length-1)/5;
			for (int i = quart; i < 4*quart; i++) {
				for (int j =quart ; j<3*quart; j++) {
					if(this.tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}		
			for (int i = 0; i < tabGenerer.length/2; i++) {
				for (int j = 0; j < tabGenerer[i].length/2; j++) {
					if(this.tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}
			for (int i =tabGenerer.length/2; i<tabGenerer.length; i++) {
				for (int j =tabGenerer[i].length/2 ; j<tabGenerer[i].length; j++) {
					if(this.tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}
			for (int i = 0; i < tabGenerer.length/2; i++) {
				for (int j =tabGenerer[i].length/2 ; j<tabGenerer[i].length; j++) {
					if(this.tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}	
			for (int i =tabGenerer.length/2; i<tabGenerer.length; i++) {
				for (int j = 0; j < tabGenerer[i].length/2; j++) {
					if(this.tabGenerer[i][j]!=INFINI) {
						getCaseAutourEtInitialiser(i, j);
					}
				}
			}	

		}
	}


	public void afficheCarte() {
		for (int i = 0; i < tabGenerer.length; i++) {
			for (int j = 0; j < tabGenerer[i].length; j++) {
				System.out.print(tabGenerer[i][j]+" ");

			}
			System.out.println();
		}
	}


	private void getCaseAutourEtInitialiser(int i,int j) {
		int TAILLEX = tabGenerer.length;
		int TAILLEY = tabGenerer[i].length;

		if (i == 0 && j == 0) {
			// case en dessous
			if (tabGenerer[i + 1][j] == INFINI) {
				tabGenerer[i + 1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i + 1][j]= (tabGenerer[i][j]+tabGenerer[i + 1][j])/2;
			}
			// case a droite
			if (tabGenerer[i][j + 1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case en bas a droite
			if (tabGenerer[i + 1][j + 1] == INFINI) {
				tabGenerer[i + 1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i + 1][j+1]= (tabGenerer[i][j]+tabGenerer[i + 1][j+1])/2;
			}

		}if (i == 0 && j == TAILLEY - 1) {
			//case en desous
			if (tabGenerer[i + 1][j] == INFINI) {
				tabGenerer[i + 1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i+1][j]= (tabGenerer[i][j]+tabGenerer[i+1][j])/2;
			}
			//case  gauche
			if (tabGenerer[i][j - 1]== INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}
			// case bas gauche
			if (tabGenerer[i + 1][j - 1]== INFINI) {
				tabGenerer[i + 1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i+1][j-1]= (tabGenerer[i][j]+tabGenerer[i+1][j-1])/2;
			}

		}if (i == TAILLEX - 1 && j == 0) {
			//case a droite
			if (tabGenerer[i][j+1]== INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}
			//haut droite
			if (tabGenerer[i -1][j + 1] == INFINI) {
				tabGenerer[i - 1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i-1][j+1]= (tabGenerer[i][j]+tabGenerer[i-1][j+1])/2;
			}

		}if (i == TAILLEX - 1 && j == TAILLEY - 1) {
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else{
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}
			//case gauche
			if (tabGenerer[i][j-1] == INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}
			//case gauche haut
			if (tabGenerer[i-1][j-1] == INFINI) {
				tabGenerer[i-1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i-1][j-1]= (tabGenerer[i][j]+tabGenerer[i-1][j-1])/2;
			}

		}if (i == 0 && j > 0 && j < TAILLEY - 1) {
			//case bas
			if (tabGenerer[i + 1][j] == INFINI) {
				tabGenerer[i+1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i+1][j]= (tabGenerer[i][j]+tabGenerer[i+1][j])/2;
			}
			//case bas droite
			if (tabGenerer[i + 1][j+1] == INFINI) {
				tabGenerer[i+1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else{
				tabGenerer[i+1][j+1]= (tabGenerer[i][j]+tabGenerer[i+1][j+1])/2;
			}
			//case bas gauche
			if (tabGenerer[i+1][j-1] == INFINI) {
				tabGenerer[i+1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i+1][j-1]= (tabGenerer[i][j]+tabGenerer[i+1][j-1])/2;
			}
			//case droite
			if (tabGenerer[i][j+1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case gauche
			if (tabGenerer[i][j-1] == INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}

		}if ( i > 0 && i < TAILLEX - 1 && j == 0) {
			//case droite
			if (tabGenerer[i][j+1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else{
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case bas droite
			if (tabGenerer[i+1][j+1] == INFINI) {
				tabGenerer[i+1][j+1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else {
				tabGenerer[i+1][j+1]= (tabGenerer[i][j]+tabGenerer[i+1][j+1])/2;
			}
			//case  haut droite
			if (tabGenerer[i-1][j+1] == INFINI) {
				tabGenerer[i-1][j+1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else{
				tabGenerer[i-1][j+1]= (tabGenerer[i][j]+tabGenerer[i-1][j+1])/2;
			}
			//case bas
			if (tabGenerer[i+1][j] == INFINI) {
				tabGenerer[i+1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i+1][j]= (tabGenerer[i][j]+tabGenerer[i+1][j])/2;
			}
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else {
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}

		}if (i > 0 && i < TAILLEX - 1 && j == TAILLEY - 1) {
			//case gauche
			if (tabGenerer[i][j-1] == INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}
			//case haut gauche
			if (tabGenerer[i-1][j-1] == INFINI) {
				tabGenerer[i-1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i-1][j-1]= (tabGenerer[i][j]+tabGenerer[i-1][j-1])/2;
			}
			//case bas gauche
			if (tabGenerer[i+1][j-1] == INFINI) {
				tabGenerer[i+1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i+1][j-1]= (tabGenerer[i][j]+tabGenerer[i+1][j-1])/2;
			}
			//case bas
			if (tabGenerer[i+1][j] == INFINI) {
				tabGenerer[i+1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else{
				tabGenerer[i+1][j]= (tabGenerer[i][j]+tabGenerer[i+1][j])/2;
			}
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}

		}if (i == TAILLEX -1 && j > 0 && j < TAILLEY -1) {
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			}else {
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}
			//case haut gauche
			if (tabGenerer[i-1][j-1] == INFINI) {
				tabGenerer[i-1][j-1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			} else {
				tabGenerer[i-1][j-1]= (tabGenerer[i][j]+tabGenerer[i-1][j-1])/2;
			}
			//case haut droite
			if (tabGenerer[i-1][j+1] == INFINI) {
				tabGenerer[i-1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i-1][j+1]= (tabGenerer[i][j]+tabGenerer[i-1][j+1])/2;
			}
			//case droite
			if (tabGenerer[i][j+1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]); 
				nbInfini--;
			} else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case gauche
			if (tabGenerer[i][j-1] == INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}

		}if(i>0 && i<TAILLEX-1 && j>0 && j<TAILLEY-1){
			//case haut
			if (tabGenerer[i-1][j] == INFINI) {
				tabGenerer[i-1][j]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i-1][j]= (tabGenerer[i][j]+tabGenerer[i-1][j])/2;
			}
			//case haut gauche
			if (tabGenerer[i-1][j-1] == INFINI) {
				tabGenerer[i-1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i-1][j-1]= (tabGenerer[i][j]+tabGenerer[i-1][j-1])/2;
			}
			//case haut droite
			if (tabGenerer[i-1][j+1] == INFINI) {
				tabGenerer[i-1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i-1][j+1]= (tabGenerer[i][j]+tabGenerer[i-1][j+1])/2;
			}
			//case bas droite
			if (tabGenerer[i+1][j+1] == INFINI) {
				tabGenerer[i+1][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i+1][j+1]= (tabGenerer[i][j]+tabGenerer[i+1][j+1])/2;
			}
			//case bas gauche
			if (tabGenerer[i+1][j-1] == INFINI) {
				tabGenerer[i+1][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i+1][j-1]= (tabGenerer[i][j]+tabGenerer[i+1][j-1])/2;
			}
			//case bas
			if (tabGenerer[i][j+1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case droite
			if (tabGenerer[i][j+1] == INFINI) {
				tabGenerer[i][j+1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			} else {
				tabGenerer[i][j+1]= (tabGenerer[i][j]+tabGenerer[i][j+1])/2;
			}
			//case gauche
			if (tabGenerer[i][j-1] == INFINI) {
				tabGenerer[i][j-1]=getNbAleatoire(tabGenerer[i][j]);
				nbInfini--;
			}else {
				tabGenerer[i][j-1]= (tabGenerer[i][j]+tabGenerer[i][j-1])/2;
			}

		}
	}

	private int getNbAleatoire(int nombre) {
		if(nombre==0) {
			nombre=1;
		}else if(nombre<0) {
			nombre*=-1;
		}
		int n=r.nextInt(nombre)+nombre;
		int m=r.nextInt(nombre)-2*nombre;
		return n+m;
	}
}
