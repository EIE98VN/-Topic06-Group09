import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		root.setVgap(5);
		// root.setGridLinesVisible(true);
		Button startBtn = new Button(" Play To End ");
		ChoiceBox<String> sortAlgo = new ChoiceBox<String>();
		sortAlgo.getItems().addAll("Bubble Sort", "Selection Sort", "Bucket Sort", "Merge Sort");

		SelectionSort selectionSort = new SelectionSort();
		BucketSort bucketSort = new BucketSort();
		BubbleSort bubbleSort = new BubbleSort();
		MergeSort mergeSort = new MergeSort();

		Button btn1 = new Button();
		Button btn2 = new Button();
		Button step = new Button("Step By Step");

		GridPane.setHalignment(startBtn, HPos.CENTER);
		GridPane.setValignment(startBtn, VPos.BOTTOM);
		root.add(sortAlgo, 1, 0);
		root.add(btn1, 1, 1);
		root.add(startBtn, 1, 2);
		root.add(btn2, 1, 3);
		GridPane.setHalignment(step, HPos.CENTER);
		btn1.setVisible(false);
		btn2.setVisible(false);
		root.add(step, 1, 4);


		root.widthProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				//double index = ((double) newValue - 100) / ((double) oldValue - 100); //100 is the width of left buttons
				//System.out.println((double)newValue);
				//double index = ((double)newValue -100)/11.6;
				double index = (root.getWidth()-100)/11.6;
				for (int i = 0; i < 10; i++) {
					if(mergeSort.pane != null)
					{
						mergeSort.rectangles.get(i).setWidth(index);
					}
					if(bubbleSort.pane != null) {
						bubbleSort.rectangles.get(i).setWidth(index);
					}
					if(selectionSort.pane != null)
					{
						selectionSort.rectangles.get(i).setWidth( index);
					}
					if(bucketSort.pane != null)
					{
						bucketSort.rects.get(i).setWidth(index);
					}
				}
			}
		});
		
		ChangeListener<String> changeAlgo = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				bubbleSort.timeline.stop();
				bucketSort.timeline.stop();
				selectionSort.timeline.stop();
				mergeSort.timeline.stop();
				double index = (root.getWidth()-100)/11.6;
				if (newValue.equals("Bubble Sort")) {
					root.getChildren().remove(bubbleSort.pane);
					root.getChildren().remove(bucketSort.pane);
					root.getChildren().remove(selectionSort.pane);
					root.getChildren().remove(mergeSort.pane);
					bubbleSort.reset();
					bubbleSort.initialize();
					for(int i=0;i<10;i++) bubbleSort.rectangles.get(i).setWidth(index);
					root.add(bubbleSort.pane, 2, 0, 2, 6);
				} else if (newValue.equals("Selection Sort")) {
					root.getChildren().remove(bubbleSort.pane);
					root.getChildren().remove(bucketSort.pane);
					root.getChildren().remove(selectionSort.pane);
					root.getChildren().remove(mergeSort.pane);
					selectionSort.reset();
					selectionSort.initialize();
					for(int i=0;i<10;i++) selectionSort.rectangles.get(i).setWidth(index);
					root.add(selectionSort.pane, 2, 0, 2, 6);
				} else if (newValue.equals("Merge Sort")) {
					root.getChildren().remove(bubbleSort.pane);
					root.getChildren().remove(bucketSort.pane);
					root.getChildren().remove(selectionSort.pane);
					root.getChildren().remove(mergeSort.pane);
					mergeSort.reset();
					mergeSort.initialize();
					for(int i=0;i<10;i++) mergeSort.rectangles.get(i).setWidth(index);
					root.add(mergeSort.pane, 2, 0, 2, 6);
				} else if (newValue.equals("Bucket Sort")) {
					root.getChildren().remove(bubbleSort.pane);
					root.getChildren().remove(bucketSort.pane);
					root.getChildren().remove(selectionSort.pane);
					root.getChildren().remove(mergeSort.pane);
					bucketSort.reset();
					bucketSort.initialize();
					for(int i=0;i<10;i++) bucketSort.rects.get(i).setWidth(index);
					root.add(bucketSort.pane, 2, 0, 2, 6);
				}
			}
		};
			
		sortAlgo.getSelectionModel().selectedItemProperty().addListener(changeAlgo);
		Scene scene = new Scene(root, 1260, 800);
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// sortAlgo.setDisable(true);
				bubbleSort.StepByStep = 0;
				bucketSort.StepByStep = 0;
				mergeSort.StepByStep = 0;
				selectionSort.StepByStep = 0;
				if (sortAlgo.getValue() == "Bubble Sort")
					bubbleSort.timeline.play();
				else if (sortAlgo.getValue() == "Selection Sort") {
					selectionSort.timeline.play();
				} else if (sortAlgo.getValue() == "Merge Sort") {
					mergeSort.timeline.play();
				} else {
					bucketSort.timeline.play();
				}
				
			}
		});
		step.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bubbleSort.StepByStep = 1;
				bucketSort.StepByStep = 1;
				mergeSort.StepByStep = 1;
				selectionSort.StepByStep = 1;
				if (sortAlgo.getValue() == "Bubble Sort")
					bubbleSort.timeline.play();
				else if (sortAlgo.getValue() == "Selection Sort") {
					selectionSort.timeline.play();
				} else if (sortAlgo.getValue() == "Merge Sort") {
					mergeSort.timeline.play();
				} else {
					bucketSort.timeline.play();
				}
			}
		});
		sortAlgo.getSelectionModel().select(0);
		primaryStage.setTitle("Sort Visualization");
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(840);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
