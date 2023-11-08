package spirographs.spirographs;

import javafx.util.Pair;

public class Calculate {

    private double R = 130;
    private double r = 70;
    private double p = 50;

    private double x;
    private double y;
    private double t = 0;


    public Pair<Double, Double> getNextPos() {
        x = (R-r)* Math.cos(t) + p*Math.cos(((R-r)/r)* t) + 400;
        y = (R-r)*Math.sin(t) - p*Math.sin(((R-r)/r)* t) + 400;
        t += 0.01;
        return new Pair<Double, Double>(x, y);
    }

    public void setR(double R) { this.R = R; }
    public void setr(double r) { this.r = r; }
    public void setp(double p) { this.p = p; }
}

//x = (R-r)*cos(t) + O*cos(((R-r)/r)*t)
//y = (R-r)*sin(t) - O*sin(((R-r)/r)*t)