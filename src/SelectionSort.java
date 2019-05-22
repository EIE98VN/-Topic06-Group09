import javafx.scene.paint.Color;

public class SelectionSort extends Pane{
	int i = 0;
	int last = arr.length - 1;
	int imax = 0;
	int max = -999999;
	int SwapPhase = 0;
	
	@Override
	public void sort() {
		//set default color
		for(int m=0;m<=arr.length-1;m++) {
			rectangles.get(m).setFill(Color.BROWN);
		}
		//Return when done sorting
		if(last < 0) {
			timeline.stop();
			//sortAlgo.setDisable(false);
			i = 0;
			last = arr.length - 1;
			imax = 0;
			max = -999999;
			return;
		}
		
		if (SwapPhase == 1) {
			int temp = arr[i - 1];
			arr[i - 1] = arr[imax];
			arr[imax] = temp;
			rectangles.get(imax).setFill(Color.BLUEVIOLET);
			rectangles.get(i - 1).setFill(Color.BLUEVIOLET);
			max = -999999;
			last -= 1;
			SwapPhase = 0;
			i = -1;
		}
		else {
		
		
			//Compare to set max
			if(arr[i] > max) {
				imax = i;
				max = arr[i];
			}
		
			//Last element, swap
			if(i == last) {
				SwapPhase = 1;
				//i -= 1;
				//imax = last;
				//max = -999999;
				//last -= 1;
			}
			rectangles.get(imax).setFill(Color.BLUEVIOLET);
			if(i != -1) rectangles.get(i).setFill(Color.YELLOWGREEN);
		}
		//set color for chosen elements
		
		//set value for rectangles and text fields
		for(int j=0;j<10;j++) {
			tFields.get(j).setText(Integer.toString(arr[j]));
			rectangles.get(j).setHeight(arr[j]*14*53/max(arr));
		}
		
		i += 1;
		if (this.StepByStep == 1) {
			this.timeline.stop();
		}
	}
	public SelectionSort() {
		super();	
	}
	@Override
	public void reset() {
		super.reset();
		i = 0;
		last = arr.length - 1;
		imax = 0;
		max = -999999;
	}
}
