package fractals;

public class PixelCheck {
    private final int iterations;
    private final Complex c;

    public PixelCheck(int iterations, Complex c) {
        this.iterations = iterations;
        this.c=c;
    }
    public boolean mandelbrot(Complex z){
        Complex point = new Complex(0.0,0.0);
        for(int i=0;i<iterations;i++){
            point.square();
            point.add(z);
            if(point.squaredModule()>=4)
                return false;
        }
        return true;
    }
    public boolean julia(Complex z){
        for(int i=0;i<iterations;i++){
            z.square();
            z.add(c);
            if(z.squaredModule()>=4)
                return false;
        }
        return true;
    }
}
