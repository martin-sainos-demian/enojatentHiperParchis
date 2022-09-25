
package enojatenthiperparchis.gfx;

import java.awt.image.BufferedImage;

public class Divide {

	private BufferedImage sheet;
	
	public Divide(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
}