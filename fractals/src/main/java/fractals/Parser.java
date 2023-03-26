package fractals;

public class Parser {
    public static void parse(String [] args) throws Exception {
        //first argument: fractal type
        //second argument size of output picture
        //third argument number of iterations
        //next argument is complex number if type is julia
        String fractalType = args[0];
        int n =args.length;
        if (fractalType.equals("julia")&&n <4 || n >6)
            throw new Exception("Incorrect number of arguments");
        int size = Integer.parseInt(args[1]);
        int iterations = Integer.parseInt(args[2]);
        StringBuilder complexArg = new StringBuilder();
        for(int i =3;i<n;i++){
            complexArg.append(args[i]);
        }
        Complex complex = new Complex(0,0);
        if (n > 3){
            complex = toComplex(complexArg.toString());
        }
        Graphics graphics = new Graphics(size);
        PixelCheck check=new PixelCheck(iterations,complex);
        if (fractalType.equals("julia"))
            graphics.julia(check);
        else
            graphics.mandelbrot(check);
        graphics.save("result");
    }
    private static Complex toComplex(String str){
        String[] splitted = str.split("\\+");
        int n = splitted.length;
        double[] numbers = new double[n];
        for(int i = 0; i<n; i++){
            numbers[i] = Double.parseDouble(splitted[i].replaceAll("\\s", "").replaceAll("i", ""));
        }
        if (n == 1)
            return new Complex(numbers[0],0);
        return new Complex(numbers[0],numbers[1]);
    }
}
