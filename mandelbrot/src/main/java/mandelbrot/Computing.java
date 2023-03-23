package mandelbrot;

public class Computing {
    private final int iterations;
    private Complex c;
    private double bifurcationX0;

    public Computing(int iterations) {
        this.iterations = iterations;
    }
    public Computing(int iterations,Complex c) {
        this.iterations = iterations;
        this.c=c;
    }
    public boolean[] bifurcation(int size,double r,double x0){
        boolean[] result = new boolean[size];
        double xn=x0;
        double xm;
        for(int i=0;i<size;i++){
            xn=r*xn*(1-xn);
        }
        for(int i=0;i<size;i++){
            xm=xn;
            xn=r*xn*(1-xn);
            result[i]= xn > 0 && xn < 1 && (xn-xm<0.001);
        }
        return result;
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
