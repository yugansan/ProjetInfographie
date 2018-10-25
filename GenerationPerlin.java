import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerationPerlin {

	int [][] tab;
	int perm[]= new int[256];

	public GenerationPerlin(int longueur, int largeur){
		this.tab = new int[longueur][largeur];
	}

	public void generate(){
		shuffle();
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab[i].length; j++){
				tab[i][j]= (int) (Get2DPerlinNoiseValue(i, j, 100)*tab.length*2);
			}
		}
	}

	private void shuffle(){
		List<Integer> solution = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);
		for(int i=0; i< perm.length; i++){
			perm[i]=solution.get(i);
		}
	}

	float Get2DPerlinNoiseValue(float x, float y, float res)
	{
		float tempX,tempY;
		int x0,y0,ii,jj,gi0,gi1,gi2,gi3;
		float unit = (float) (1.0f/Math.sqrt(2));
		float tmp,s,t,u,v,Cx,Cy,Li1,Li2;
		float gradient2[][] = {{unit,unit},{-unit,unit},{unit,-unit},{-unit,-unit},{1,0},{-1,0},{0,1},{0,-1}};


		//Adapter pour la résolution
		x /= res;
		y /= res;

		//On récupère les positions de la grille associée à (x,y)
		x0 = (int)(x);
		y0 = (int)(y);

		//Masquage
		ii = x0 & 255;
		jj = y0 & 255;

		//Pour récupérer les vecteurs
		gi0 = perm[ii + perm[jj]] % 8;
		gi1 = perm[ii + 1 + perm[jj]] % 8;
		gi2 = perm[ii + perm[jj + 1]] % 8;
		gi3 = perm[ii + 1 + perm[jj + 1]] % 8;

		//on récupère les vecteurs et on pondère
		tempX = x-x0;
		tempY = y-y0;
		s = gradient2[gi0][0]*tempX + gradient2[gi0][1]*tempY;

		tempX = x-(x0+1);
		tempY = y-y0;
		t = gradient2[gi1][0]*tempX + gradient2[gi1][1]*tempY;

		tempX = x-x0;
		tempY = y-(y0+1);
		u = gradient2[gi2][0]*tempX + gradient2[gi2][1]*tempY;

		tempX = x-(x0+1);
		tempY = y-(y0+1);
		v = gradient2[gi3][0]*tempX + gradient2[gi3][1]*tempY;


		//Lissage
		tmp = x-x0;
		Cx = 3 * tmp * tmp - 2 * tmp * tmp * tmp;

		Li1 = s + Cx*(t-s);
		Li2 = u + Cx*(v-u);

		tmp = y - y0;
		Cy = 3 * tmp * tmp - 2 * tmp * tmp * tmp;

		return Li1 + Cy*(Li2-Li1);
	}

}
