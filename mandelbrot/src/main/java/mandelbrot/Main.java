package mandelbrot;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Graphics graphics=new Graphics(1024);
        Computing cmp=new Computing(64,new Complex(-0.73,0.19));
        graphics.julia(cmp);
        graphics.save("png","result");
    }
}
