package spirographs.spirographs;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField outerRadius = new TextField("130");
    @FXML
    private TextField innerRadius = new TextField("70");
    @FXML
    private TextField point = new TextField("50");
    @FXML
    private TextField colour = new TextField("FFFFFF");
    @FXML
    private Canvas canvas = new Canvas();

    private Calculate c = new Calculate();

    private GraphicsContext gc = canvas.getGraphicsContext2D();

    public void draw(ActionEvent e) {
        gc.setFill(Color.web(colour.getText()));

        c.setR(Double.parseDouble(outerRadius.getText()));
        c.setr(Double.parseDouble(innerRadius.getText()));
        c.setp(Double.parseDouble(point.getText()));

        int i = 5000;
        while (i != 0) {
            gc.fillRect(c.getNextPos().getKey(), c.getNextPos().getValue(), 1, 1);

            i--;
        }
    }

    @FXML
    private Circle dot = new Circle();

    public void animate(ActionEvent e) {
        Polyline path = new Polyline();
        Double[] points = new Double[5000];
        PathTransition transition = new PathTransition();

        c.setR(Double.parseDouble(outerRadius.getText()));
        c.setr(Double.parseDouble(innerRadius.getText()));
        c.setp(Double.parseDouble(point.getText()));

        for (int i = 0; i < points.length; i += 2) {
            double x = c.getNextPos().getKey();
            double y = c.getNextPos().getValue();
            points[i] = x;
            points[i+1] = y;
        }

        path.getPoints().addAll(points);

        transition.setNode(dot);
        transition.setDuration(Duration.seconds(5));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.play();

    }

    public void clear(ActionEvent e) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 800);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 800);
    }
}