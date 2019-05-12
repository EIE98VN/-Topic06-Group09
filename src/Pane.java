import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Pane{
	int StepByStep = 0;
	ArrayList<TextField> tFields = new ArrayList<TextField>();
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	Timeline timeline = new Timeline();
	GridPane pane  = new GridPane();
	int[] arr = {10,47,6,21,53,9,3,32,50,15};
	int[] arr1 = {10,47,6,21,53,9,3,32,50,15};
	public void initialize() {
		pane.setPadding(new Insets(20));
		pane.setHgap(5);
		pane.setVgap(5);
		for(int i=0;i<10;i++) {
			TextField tf = new TextField();
			tf.setPrefWidth(100);
			tf.setText(Integer.toString(arr[i]));
			tf.setEditable(false);
			tf.setAlignment(Pos.CENTER);
			tFields.add(tf);
			Rectangle rect = new Rectangle(100,arr[i]*14);
			rect.setFill(Color.BROWN);
			rectangles.add(rect);
			pane.add(rect, i+1, 0);
			GridPane.setValignment(rect, VPos.BOTTOM);
			GridPane.setHalignment(rect, HPos.CENTER);
			GridPane.setHgrow(rect,Priority.ALWAYS);
			pane.add(tf,i+1,1);
		}
	}
	public void reset() {
		for(int i=0;i<10;i++) {
			arr[i]=arr1[i];
		}
		this.pane.getChildren().clear();
		tFields.clear();
		rectangles.clear();
	}
	public void sort() {
		
	}
	public Pane(){
		initialize();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				sort();
			}
		}));
		timeline.setAutoReverse(false);
		timeline.setCycleCount(Animation.INDEFINITE);
	}
}
