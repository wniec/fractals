package fractals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Graphics {
    private final BufferedImage im;
    private final WritableRaster raster;
    private final int width;
    private final int height;
    private final int[] pixels = new int[4];

    public Graphics(int size){
        width= size;
        height=size;
        this.im = new BufferedImage(size,size,5);
        this.raster=im.getRaster();
    }
    public void mandelbrot(PixelCheck check){
        int[] ww;
        Complex z;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                raster.getPixel(i,j,pixels);
                z=new Complex(3*(double)i/(double)width-2.3,2.5*(double)j/(double)height-1.25);
                if(check.mandelbrot(z)){
                    ww=new int[]{0,0,0,255};
                }
                else{
                    Complex coord=new Complex(2*(double)i/width-1,2*(double)j/height-1);
                    ww=new int[]{0,0,255-(int)(255*coord.squaredModule())/3,255};
                }
                raster.setPixel(i,j,ww);
            }
        }
    }
    public void julia(PixelCheck check){
        int[] ww;
        Complex z;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                raster.getPixel(i,j,pixels);
                z=new Complex(3*(double)i/(double)width-1.5,2.5*(double)j/(double)height-1.25);
                if(check.julia(z))
                    ww=new int[]{0,0,0,255};
                else{
                    Complex coord=new Complex(2*(double)i/width-1,2*(double)j/height-1);
                    ww=new int[]{0,0,255-(int)(255*coord.squaredModule())/3,255};
                }
                raster.setPixel(i,j,ww);
            }
        }
    }

    public void save(String fileName)throws IOException{
        im.setData(raster);
        File file = new File(fileName+".png");
        ImageIO.write(im,"PNG",file);
    }
}
