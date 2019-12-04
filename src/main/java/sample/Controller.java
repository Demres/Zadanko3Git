package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Controller {

    public Pane pane, rgbPane, cmykPane;
    public Slider redSlider, greenSlider, blueSlider;
    public Slider cyanSlider, magentaSlider, yellowSlider, blackSlider;
    public TextField redTextField, greenTextField, blueTextField;
    public TextField cyanTextField, magentaTextfield, yellowTextField, blackTextField;
    public Rectangle rgbRectangle = new Rectangle(1,39,200,40);
    public Rectangle cmykRectangle = new Rectangle(0,0,200,40);
    public Group cube = new Group();
    private Timeline animation;

    private void setCube(){
        double size = 75;
        //set color for the cube
        Color color = Color.DARKCYAN;
        cube.getChildren().addAll(
                RectangleBuilder.create() // back face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.5 * 1), 1.0))
                        .translateX(-0.5 * size)
                        .translateY(-0.5 * size)
                        .translateZ(0.5 * size)
                        .build(),
                RectangleBuilder.create() // bottom face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.4 * 1), 1.0))
                        .translateX(-0.5 * size)
                        .translateY(0)
                        .rotationAxis(Rotate.X_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // right face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.3 * 1), 1.0))
                        .translateX(-1 * size)
                        .translateY(-0.5 * size)
                        .rotationAxis(Rotate.Y_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // left face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.2 * 1), 1.0))
                        .translateX(0)
                        .translateY(-0.5 * size)
                        .rotationAxis(Rotate.Y_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // top face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.1 * 1), 1.0))
                        .translateX(-0.5 * size)
                        .translateY(-1 * size)
                        .rotationAxis(Rotate.X_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // front face
                        .width(size).height(size)
                        .fill(color)
                        .translateX(-0.5 * size)
                        .translateY(-0.5 * size)
                        .translateZ(-0.5 * size)
                        .build());
        cube.getTransforms().addAll(new Rotate(45, Rotate.X_AXIS), new Rotate(45, Rotate.Y_AXIS));
    }

    @FXML
    public void initialize() {

//        setCube();
//        animation = new Timeline();
//        animation.getKeyFrames().addAll(
//                new KeyFrame(Duration.ZERO,
//                        new KeyValue(cube.rotationAxisProperty(), Rotate.Z_AXIS),
//                        new KeyValue(cube.rotateProperty(), 0d)),
//                new KeyFrame(Duration.seconds(5),
//                        new KeyValue(cube.rotationAxisProperty(), Rotate.Z_AXIS),
//                        new KeyValue(cube.rotateProperty(), 360d)));
//        animation.setCycleCount(Animation.INDEFINITE);
//        animation.play();

//        PhongMaterial phongMaterial = new PhongMaterial();
//        ph
//        Box box = new Box(100, 100, 100);
//        box.set
//
        final int rgbMax = 255, cmykMax=100;

//        double[] cmyk = ColorConversion.rgbToCmyk(0.68, 0.25, 0.7346);
//
//        double[] rgb = ColorConversion.cmykToRgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
//
////        Color rgbColor = Color.rgb(156,234,12);
////
////        Color cmykColor = Color.rgb(rgb[0], rgb[1], rgb[2]);
//
//        Color rgbColor = new Color(0.9, 0.7568, 0.5724,1);
//
//        //Rectangle rgbRectangle = new Rectangle(0, 0,20,20 );
//        rgbRectangle.setFill(rgbColor);
//        //Rectangle cmykRectangle = new Rectangle(100, 0, 100, 100);
//        //cmykRectangle.setFill(cmykColor);

        redTextField.setText(String.valueOf(redSlider.getValue()));
        greenTextField.setText(String.valueOf(greenSlider.getValue()));
        blueTextField.setText(String.valueOf(blueSlider.getValue()));

        cyanTextField.setText(String.valueOf(cyanSlider.getValue()));
        magentaTextfield.setText(String.valueOf(magentaSlider.getValue()));
        yellowTextField.setText(String.valueOf(yellowSlider.getValue()));
        blackTextField.setText(String.valueOf(blackSlider.getValue()));

        rgbRectangle.setFill(new Color(redSlider.getValue()/rgbMax,greenSlider.getValue()/rgbMax,blueSlider.getValue()/rgbMax, 1));
        rgbPane.getChildren().add(rgbRectangle);

        double[] rgb = ColorConversion.cmykToRgb(cyanSlider.getValue()/cmykMax, magentaSlider.getValue()/cmykMax,yellowSlider.getValue()/cmykMax, blackSlider.getValue()/cmykMax);
        cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2],1));
        cmykPane.getChildren().add(cmykRectangle);
        pane.getChildren().add(cube);

        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                redTextField.setText(newValue.toString());
                rgbRectangle.setFill(new Color(newValue.doubleValue()/rgbMax, greenSlider.getValue()/rgbMax, blueSlider.getValue()/rgbMax,1));

                double[] cmyk = ColorConversion.rgbToCmyk(newValue.doubleValue()/rgbMax, greenSlider.getValue()/rgbMax, blueSlider.getValue()/rgbMax);
                double[] rgb = ColorConversion.cmykToRgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
                cyanTextField.setText(String.valueOf(cmyk[0]*cmykMax));
               // cyanSlider.setValue(cmyk[0]*cmykMax);

                magentaTextfield.setText(String.valueOf(cmyk[1]*cmykMax));
               // magentaSlider.setValue(cmyk[1]*cmykMax);

                yellowTextField.setText(String.valueOf(cmyk[2]*cmykMax));
               // yellowSlider.setValue(cmyk[2]*cmykMax);

                blackTextField.setText(String.valueOf(cmyk[3]*cmykMax));
                //blackSlider.setValue(cmyk[3]*cmykMax);
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));
            }
        });

        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                greenTextField.setText(newValue.toString());
                rgbRectangle.setFill(new Color(redSlider.getValue()/rgbMax, newValue.doubleValue()/rgbMax, blueSlider.getValue()/rgbMax,1));

                double[] cmyk = ColorConversion.rgbToCmyk(redSlider.getValue()/rgbMax, newValue.doubleValue()/rgbMax, blueSlider.getValue()/rgbMax);
                double[] rgb = ColorConversion.cmykToRgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
                cyanTextField.setText(String.valueOf(cmyk[0]*cmykMax));
                //cyanSlider.setValue(cmyk[0]*cmykMax);

                magentaTextfield.setText(String.valueOf(cmyk[1]*cmykMax));
               //magentaSlider.setValue(cmyk[1]*cmykMax);

                yellowTextField.setText(String.valueOf(cmyk[2]*cmykMax));
                //yellowSlider.setValue(cmyk[2]*cmykMax);

                blackTextField.setText(String.valueOf(cmyk[3]*cmykMax));
                //blackSlider.setValue(cmyk[3]*cmykMax);
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));
            }
        });

        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                blueTextField.setText(newValue.toString());
                rgbRectangle.setFill(new Color(redSlider.getValue()/rgbMax, greenSlider.getValue()/rgbMax, newValue.doubleValue()/rgbMax,1));

                double[] cmyk = ColorConversion.rgbToCmyk(redSlider.getValue()/rgbMax, greenSlider.getValue()/rgbMax, newValue.doubleValue()/rgbMax);
                double[] rgb = ColorConversion.cmykToRgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);

                cyanTextField.setText(String.valueOf(cmyk[0]*cmykMax));
               // cyanSlider.setValue(cmyk[0]*cmykMax);

                magentaTextfield.setText(String.valueOf(cmyk[1]*cmykMax));
               // magentaSlider.setValue(cmyk[1]*cmykMax);

                yellowTextField.setText(String.valueOf(cmyk[2]*cmykMax));
                //yellowSlider.setValue(cmyk[2]*cmykMax);

                blackTextField.setText(String.valueOf(cmyk[3]*cmykMax));
                //blackSlider.setValue(cmyk[3]*cmykMax);

                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));
            }
        });


        cyanSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                cyanTextField.setText(newValue.toString());
                double[] rgb = ColorConversion.cmykToRgb(newValue.doubleValue()/cmykMax, magentaSlider.getValue()/cmykMax, yellowSlider.getValue()/cmykMax, blackSlider.getValue()/cmykMax );
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));

                redTextField.setText(String.valueOf(rgb[0]*rgbMax));
               // redSlider.setValue(rgb[0]*rgbMax);
                greenTextField.setText(String.valueOf(rgb[1]*rgbMax));
               // greenSlider.setValue(rgb[0]*rgbMax);
                blueTextField.setText(String.valueOf(rgb[2]*rgbMax));
               // blackSlider.setValue(rgb[0]*rgbMax);

                rgbRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2],1));
            }
        });

        magentaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                magentaTextfield.setText(newValue.toString());
                double[] rgb = ColorConversion.cmykToRgb(cyanSlider.getValue()/cmykMax, newValue.doubleValue()/cmykMax,yellowSlider.getValue()/cmykMax, blackSlider.getValue()/cmykMax);
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));

                redTextField.setText(String.valueOf(rgb[0]*rgbMax));
               // redSlider.setValue(rgb[0]*rgbMax);
                greenTextField.setText(String.valueOf(rgb[1]*rgbMax));
               // greenSlider.setValue(rgb[0]*rgbMax);
                blueTextField.setText(String.valueOf(rgb[2]*rgbMax));
              // blackSlider.setValue(rgb[0]*rgbMax);

                rgbRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2],1));
            }
        });

        yellowSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                yellowTextField.setText(newValue.toString());
                double[] rgb = ColorConversion.cmykToRgb( cyanSlider.getValue()/cmykMax, magentaSlider.getValue()/cmykMax, newValue.doubleValue()/cmykMax, blackSlider.getValue()/cmykMax);
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));

                redTextField.setText(String.valueOf(rgb[0]*rgbMax));
               // redSlider.setValue(rgb[0]*rgbMax);
                greenTextField.setText(String.valueOf(rgb[1]*rgbMax));
               // greenSlider.setValue(rgb[0]*rgbMax);
                blueTextField.setText(String.valueOf(rgb[2]*rgbMax));
               // blackSlider.setValue(rgb[0]*rgbMax);

                rgbRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2],1));
            }
        });

        blackSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                blackTextField.setText(newValue.toString());
                double[] rgb = ColorConversion.cmykToRgb( cyanSlider.getValue()/cmykMax, magentaSlider.getValue()/cmykMax, yellowSlider.getValue()/cmykMax, newValue.doubleValue()/cmykMax);
                cmykRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2], 1));

                redTextField.setText(String.valueOf(rgb[0]*rgbMax));
              //  redSlider.setValue(rgb[0]*rgbMax);
                greenTextField.setText(String.valueOf(rgb[1]*rgbMax));
               // greenSlider.setValue(rgb[0]*rgbMax);
                blueTextField.setText(String.valueOf(rgb[2]*rgbMax));
               // blackSlider.setValue(rgb[0]*rgbMax);

                rgbRectangle.setFill(new Color(rgb[0], rgb[1], rgb[2],1));
            }
        });

    }
}
