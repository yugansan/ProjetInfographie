import java.awt.Color;
import MyMath.Point;

public class Carte extends WorldObject{
	int profondeur= -10;

	public Carte(int[][] tab, int taille, int hauteur){
		int neige= (3*tab.length)/4;
		int montagne= (int) (tab.length/1.5);

		for(int x=0; x<tab.length; x++){
			for(int y=0; y<tab[x].length; y++){
				int hfinal;
				if(tab[x][y]>=neige){
					hfinal= (montagne*(hauteur/3)) 
							+ ((neige-montagne)*hauteur)
							+ ((tab[x][y]-neige) * (hauteur/2));
				}
				else if(tab[x][y]>=montagne){
					hfinal= (montagne*(hauteur/3)) + ((tab[x][y]-montagne)*hauteur);
				}
				else if(tab[x][y]>=0){
					hfinal= tab[x][y]*(hauteur/3);
				}
				else{
					hfinal=0;

				}
				Point d= new Point((x-tab.length/2)*taille, (y-tab.length/2)*taille, hfinal);
				add(d);

			}
		}


		for(int i=0; i<tab.length-1; i++){
			int eps= i*tab.length;
			int eps2= (i+1)*tab.length;
			int unDixieme = tab.length/10;
			for(int j=0; j<tab[i].length-1; j++){

				Color col;
				if(tab[i][j]>=unDixieme*9) {
					col = Color.WHITE;
				}else if(tab[i][j]>=unDixieme*7) {
					//GRIS
					if(tab[i][j]>unDixieme*7+5) {
						col =new Color(102, 100, 100);
					}else {
						col =new Color(160, 160, 160);
					}
				}else if(tab[i][j]>=unDixieme*6) {
					//MARRON
					if(tab[i][j]>unDixieme*6+5) {
						col =new Color(100, 50, 30);
					}else {
						col =new Color(160, 70, 50);
					}
				}else if(tab[i][j]>=unDixieme*4) {
					//GREEN
					if(tab[i][j]>unDixieme*4+5) {
						col =new Color(20, 100, 30);
					}else {
						col =new Color(30, 160, 50);
					}
				}else if(tab[i][j]>=unDixieme*2) {
					//GREEN
					if(tab[i][j]>unDixieme*2+5) {
						col =new Color(80, 180, 50);
					}else {
						col =new Color(100, 230, 60);
					}
				}else if(tab[i][j]>=unDixieme) {
					//BEIGE
					if(tab[i][j]>unDixieme*1+5) {
						col =new Color(215, 180, 60);
					}else {
						col =new Color(240, 200, 130);
					}
				}else if(tab[i][j]>=0) {
					col =new Color(240, 240, 210);
				}else if(tab[i][j]<0  && tab[i][j]>-unDixieme) {
					col =new Color(125, 220, 255).darker();
				}else if(tab[i][j]<=-unDixieme  && tab[i][j]>-unDixieme*2) {
					col =new Color(0, 110, 255).darker();
				}else{
					col =new Color(30, 20, 140).darker();
				}
				add(col, eps2+j, eps+j, eps+j+1, eps2+1+j);

			}
		}


	}
}
