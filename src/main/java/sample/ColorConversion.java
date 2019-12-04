package sample;

public class ColorConversion {

    public static double[] rgbToCmyk(double red, double green, double blue){
        double cyan, magenta, yellow, black;

        black = min(new double[]{red, green, blue});
        cyan = (1 - red - black)/(1 - black);
        magenta = (1 - green - black)/(1 - black);
        yellow = (1 - blue - black)/(1 - black);

        return new double[]{cyan, magenta, yellow, black};
    }

    public static double[] cmykToRgb(double cyan, double magenta, double yellow, double black){
        double red, green, blue;

        red = 1 - min(new double[]{1, cyan * (1 - black) + black});
        green = 1 - min(new double[]{1, magenta * (1 - black) + black});
        blue = 1 - min(new double[]{1, yellow * (1 - black) + black});

        return new double[]{red, green, blue};

    }

    private static double min(double[] colors){

        double min = colors[0];

        for(int i = 1; i < colors.length; i++){
            if(min > colors[i]){
                min = colors[i];
            }
        }

        return min;
    }
}
