package me.vali.Virtual.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class VirtualJavaUtils {

	public static BufferedImage toBufferedImage(Image img) {
	    if (img instanceof BufferedImage)
	        return (BufferedImage) img;
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();
	    return bimage;
	}
	
}
