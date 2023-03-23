package mandelbrot;

import static java.lang.Math.pow;

public class Complex {
    private double re,im;
    public Complex(double re, double im){
        this.re=re;
        this.im=im;
    }
    public double getReal(){return this.re;}
    public double getImaginary(){return this.im;}
    public void add(Complex other){
        this.re+= other.getReal();
        this.im+= other.getImaginary();
    }
    public void square(){
        double newReal=pow(this.re,2)-pow(this.im,2);
        im=2*im*re;
        this.re=newReal;
    }
    public double squaredModule(){
        return pow(this.re,2)+pow(this.im,2);
    }
}
