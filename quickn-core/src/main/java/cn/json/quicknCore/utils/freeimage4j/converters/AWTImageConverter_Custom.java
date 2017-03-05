package cn.json.quicknCore.quickn.utils.freeimage4j.converters;


import cn.json.quicknCore.quickn.utils.freeimage4j.FreeImage;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

/**
 * @author gru
 *
 */
public final class AWTImageConverter_Custom extends AWTImageConverter {

	private final static AWTImageConverter_Custom INSTANCE	= new AWTImageConverter_Custom();

	private AWTImageConverter_Custom(){}

	protected static AWTImageConverter_Custom getInstance() {
		return INSTANCE;
	}

	@Override
	public final FreeImage convert(BufferedImage bufferedImage) {

		// allocate image
		int w			= bufferedImage.getWidth();
		int h			= bufferedImage.getHeight();
		FreeImage fi	= new FreeImage(w,h,32, 0x00FF0000,0x0000FF00,0x000000FF);

		// copy pixels
		ByteBuffer buf	= fi.getPixelBuffer();
		int[] rgbs		= new int[w*h];
		bufferedImage.getRGB(0,0, w,h, rgbs, 0,w);
		for ( int y = (h-1)*w; y >= 0; y-=w ) {
			int xEnd= y+w;
			for ( int x = y; x < xEnd; x++ ) {
				int rgb	= rgbs[x];
				buf.put((byte)rgb);
				rgb	>>=8;
				buf.put((byte)rgb);
				rgb	>>=8;
				buf.put((byte)rgb);
				rgb	>>=8;
				buf.put((byte)rgb);
			}
		}

		return fi;
	}

	@Override
	public final BufferedImage convert(FreeImage image) {
		return null;
	}
}
