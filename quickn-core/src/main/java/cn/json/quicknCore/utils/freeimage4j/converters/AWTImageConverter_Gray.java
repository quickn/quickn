/*
 * Created on 22.05.2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cn.json.quicknCore.quickn.utils.freeimage4j.converters;

import cn.json.quicknCore.quickn.utils.freeimage4j.FreeImage;
import cn.json.quicknCore.quickn.utils.freeimage4j.FreeImageRuntimeException;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

/**
 * @author gru
 *
 */
public final class AWTImageConverter_Gray extends AWTImageConverter {

    private final static AWTImageConverter_Gray INSTANCE   = new AWTImageConverter_Gray();

    private AWTImageConverter_Gray(){}

    protected static AWTImageConverter_Gray getInstance() {
        return INSTANCE;
    }

    @Override
    public final FreeImage convert(BufferedImage bufferedImage) {

        DataBuffer db   = bufferedImage.getData().getDataBuffer();
        if ( !(db instanceof DataBufferByte) ) {
            throw new FreeImageRuntimeException("Unsupported DataBuffer class \""+db.getClass()+"\" for gray image type!");
        }

        // allocate image
        int w           = bufferedImage.getWidth();
        int h           = bufferedImage.getHeight();
        FreeImage fi    = new FreeImage(w,h,8, 0x00FF0000,0x0000FF00,0x000000FF);

        // set gray palette
        for ( int i = 0; i < 256; i++ ) {
            fi.setPaletteColor(i, 0xFF000000|i<<16|i<<8|i);
        }

        // copy indices
        ByteBuffer buf  = fi.getPixelBuffer();
        byte[] pixels   = ((DataBufferByte)db).getData();
        for ( int y = (h-1)*w; y >= 0; y-=w ) {
            buf.put(pixels,y,w);
        }

        return fi;
    }

	@Override
	public final BufferedImage convert(FreeImage image) {
		return AWTImageConverter_8Bit.getInstance().convert(image);
	}

}
