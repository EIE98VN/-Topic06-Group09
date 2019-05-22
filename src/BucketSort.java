import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BucketSort{
	int StepByStep = 0;
	Timeline timeline = new Timeline();
	ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
	GridPane pane = new GridPane();
	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	Button[][] swaps = new Button[10][10];
	int[] arr = new int[10];

	int i=0; //index of array to push to bucket
	int j=0; //index of bucket
	int k=0; //index of bucket to push back to array
	int m=0; //index of elements in bucket
	int n=0; //index of array pushed back from buckets
	
	public int max(int[] arr) {
		int max=-1;
		for(int i=0;i<arr.length;i++)
			if(max<arr[i]) max=arr[i];
		return max;
	}
	
	public void sort() {
		if(i<arr.length) {
			String value = buttons.get(i).getText();
			buttons.get(i).setVisible(false);
			int index = arr[i]*10/(max(arr)+1);
			Button btn = swaps[9-buckets.get(index).size()][index];
			rects.get(index).setFill(Color.YELLOW);
			btn.setText(value);
			btn.setVisible(true);
			buckets.get(index).add(Integer.parseInt(value));
		}else {
			for(int x=0;x<arr.length;x++) rects.get(x).setFill(Color.BROWN);
			if(j<arr.length) {
				Collections.sort(buckets.get(j));
				rects.get(j).setFill(Color.YELLOW);
				for(int l=0;l<buckets.get(j).size();l++) {
					swaps[9-l][j].setText(Integer.toString(buckets.get(j).get(l)));
				}
			}else {
				if(k<arr.length) {
					for(int x=0;x<arr.length;x++) rects.get(x).setFill(Color.BROWN);
					rects.get(k).setFill(Color.YELLOW);
					if(m<buckets.get(k).size()) {
						String value = swaps[9-m][k].getText();
						swaps[9-m][k].setVisible(false);
						buttons.get(n).setText(value);
						buttons.get(n).setVisible(true);
						n++;
						m++;
					}else {
						m=0;
						k++;
					}
				}else {
					System.out.println("stop");
					return;
				}
			}
			j++;
		}
		i++;
		if (this.StepByStep == 1) {
			this.timeline.stop();
		}
	}
	public void reset() {
		i=j=k=m=n=0;
		rects.clear();
		buttons.clear();
		this.pane.getChildren().clear();
		buckets.clear();
	}
	
	public void initialize() {
		for(int i=0;i<10;i++) {
			ArrayList<Integer> bucket = new ArrayList<Integer>();
			buckets.add(bucket);
		}
		pane.setPadding(new Insets(20.0));
		pane.setVgap(40);
		pane.setHgap(5);
		for(int i=0;i<10;i++) {
			Button button = new Button(Integer.toString(arr[i]));
			GridPane.setHalignment(button, HPos.CENTER);
			buttons.add(button);
			pane.add(button,i,1);
			Rectangle rectangle = new Rectangle(100, 20);
			rectangle.setFill(Color.BROWN);
			rects.add(rectangle);
			GridPane.setValignment(rectangle, VPos.BOTTOM);
			GridPane.setHalignment(rectangle, HPos.CENTER);
			pane.add(rectangle, i, 10);	
		}
		//add button quanh map
		for(int i=2;i<10;i++)
			for(int j=0;j<10;j++) {
				Button button = new Button();
				GridPane.setHalignment(button, HPos.CENTER);
				button.setVisible(false);
				GridPane.setHalignment(button, HPos.CENTER);
				swaps[i][j]=button;
				pane.add(button, j, i);
			}
	}
	public BucketSort() {
		initialize();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				sort();
			}
		}));
		timeline.setAutoReverse(false);
		timeline.setCycleCount(Animation.INDEFINITE);
	}
}
