import javafx.scene.paint.Color;

public class BubbleSort extends Pane{
	int k=0;
	int i = arr.length-2;
	@Override
	public void sort() {
		//set default color
		for(int m=0;m<=arr.length-1;m++) {
			rectangles.get(m).setFill(Color.BROWN);
		}
		//condition to exit loop
		if(k==arr.length-2) {
			timeline.stop();
			//sortAlgo.setDisable(false);
			k=0;
			i=arr.length-2;
			return;
		}
		//compare two adjacent array elements
		if(arr[i]>arr[i+1]) {
			int temp = arr[i+1];
			arr[i+1] = arr[i];
			arr[i] = temp;
		}
		//set color for chosen elements
		rectangles.get(i).setFill(Color.YELLOWGREEN);
		rectangles.get(i+1).setFill(Color.YELLOWGREEN);
		//set value for rectangles and text fields
		for(int j=0;j<10;j++) {
			tFields.get(j).setText(Integer.toString(arr[j]));
			rectangles.get(j).setHeight(arr[j]*53*14/max(arr));
		}
		i--;
		if (i<k){
			k++;
			i=arr.length-2;
		}
		if (this.StepByStep == 1) {
			this.timeline.stop();
		}
		
	}
	public BubbleSort() {
		super();
	}
	@Override
	public void reset() {
		super.reset();
		i = arr.length-2;
		k=0;
	}
}
