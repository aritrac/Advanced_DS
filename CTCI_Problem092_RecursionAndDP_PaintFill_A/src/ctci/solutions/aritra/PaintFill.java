package ctci.solutions.aritra;

/*
 * Question: Implement the paint fill function that one might see on many image editing programs. That is, given a screen (represented by a two dimensional array of colors),
 * a point and a new color, fill in the surrounding area until the color changes from the original color
 * 
 * Solution: First, let's visualize how this method works. When we call paintFill (i.e. click paint fill in the image editing application) on, say, a green pixel,
 * we want to bleed outwards. Pixel by pixel, we expand outwards by calling paintFill on the surrounding pixel. When we hit a pixel that is not green, we stop.
 */

enum Color {Black, White, Red, Yellow, Green}

public class PaintFill {
	boolean paintFill(Color[][] screen, int r, int c, Color nColor){
		if(screen[r][c] == nColor)
			return false;
		return paintFill(screen,r,c,screen[r][c], nColor);
	}
	
	boolean paintFill(Color[][] screen, int r, int c, Color ocolor, Color ncolor){
		if(r < 0 || r >= screen.length || c < 0 || c >= screen[0].length){
			return false;
		}
		
		if(screen[r][c] == ocolor){
			screen[r][c] = ncolor;
			paintFill(screen, r - 1, c , ocolor, ncolor); //up
			paintFill(screen, r + 1, c , ocolor, ncolor); //down
			paintFill(screen, r , c - 1 , ocolor, ncolor);//left
			paintFill(screen, r , c + 1 , ocolor, ncolor);//right
		}
		return true;
	}
}
