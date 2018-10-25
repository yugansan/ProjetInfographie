
import java.util.Random;

public class GenerationDiamantCarre {

	int [][] tab;

	public GenerationDiamantCarre(int longeur, int largeur) {
		this.tab = new int[longeur][largeur];
	}

	public int getLongueur() {
		return tab.length;
	}

	public int getLargeur() {
		return tab[0].length;
	}


	public void generate() {
		int h=tab.length;
		Random ran = new Random();
		tab[0][0]=-h+ran.nextInt(2*h+1);
		tab[0][h-1]=-h+ran.nextInt(2*h+1);
		tab[h-1][tab[0].length-1]=-h+ran.nextInt(2*h+1);
		tab[h-1][0]= -h+ran.nextInt(2*h+1);
		int i=h-1;
		int id,decalage,moyenne,somme,n;
		while(i>1) {

			id = i/2;
			for (int x = id; x < h; x+=i) {
				for (int y = id; y < h; y+=i) {
					moyenne = (tab[x-id][y-id]+tab[x-id][y+id]
							+tab[x+id][y+id]+tab[x+id][y-id])/4;
					tab[x][y]=moyenne+(-id+ran.nextInt(2*id+1));
				}
			}
			for (int x = 0; x < h; x+=id) {
				if(x%i==0) {
					decalage = id;
				}else {
					decalage=0;
				}
				for (int y = decalage; y < h; y+=i) {
					somme=0;
					n=0;
					if(x>=id) {
						somme += tab[x-id][y];
						n++;
					}
					if(x+id <h) {
						somme += tab[x+id][y];
						n++;
					}
					if(y >= id) {
						somme += tab[x][y-id];
						n++;
					}
					if(y+id <h) {
						somme += tab[x][y+id];
						n++;
					}
					tab[x][y]= somme/n+(-id+ran.nextInt(2*id+1));
				}
			}
			i=id;
		}
	}
}
