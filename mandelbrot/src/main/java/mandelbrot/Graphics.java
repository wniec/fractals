package mandelbrot;

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
    public void mandelbrot(Computing cmp){
        int[] ww;
        Complex z;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                raster.getPixel(i,j,pixels);
                z=new Complex(3*(double)i/(double)width-2.3,2.5*(double)j/(double)height-1.25);
                if(cmp.mandelbrot(z)){
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
    public void julia(Computing cmp){
        int[] ww;
        Complex z;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                raster.getPixel(i,j,pixels);
                z=new Complex(3*(double)i/(double)width-1.5,2.5*(double)j/(double)height-1.25);
                if(cmp.julia(z))
                    ww=new int[]{0,0,0,255};
                else{
                    Complex coord=new Complex(2*(double)i/width-1,2*(double)j/height-1);
                    ww=new int[]{0,0,255-(int)(255*coord.squaredModule())/3,255};
                }
                raster.setPixel(i,j,ww);
            }
        }
    }
    public void bifurcation(Computing cmp){
        int[] ww;
        boolean[] tab;
        double r;
        for (int i=0;i<width;i++){
            r=(float)(4*i)/width;
            tab = cmp.bifurcation(height,r,0.2);
            for (int j=0;j<height;j++){
                if(tab[j])
                    ww=new int[]{255,255,255,255};
                else{
                    ww=new int[]{0,0,0,255};
                }
                raster.setPixel(i,j,ww);
            }
        }
    }
    public void sierpinski(Computing cmp){
        int[] ww;
        boolean[][] matrix = new boolean[height][width];
        for(int i=0;i< height;i++){
            if(i==height/2)
                matrix[0][i]=true;
        }
        for (int i=1;i<width;i++){
            for (int j=1;j<height-1;j++){
                if (matrix[i-1][j-1]!=matrix[i-1][j+1])
                    matrix[i][j]=true;
            }
        }
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if (matrix[i][j])
                    ww=new int[]{0,0,0,255};
                else{
                        Complex coord=new Complex(2*(double)i/width-1,2*(double)j/height-1);
                        ww=new int[]{0,0,255-(int)(255*coord.squaredModule())/3,255};
                    }
                    raster.setPixel(i,j,ww);
                }

            }
        }
    public void save(String type,String fileName)throws IOException{
        im.setData(raster);
        ImageIO.write(im,type,new File(fileName));
    }
}
