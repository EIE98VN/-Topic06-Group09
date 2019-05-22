import javafx.scene.paint.Color;

public class MergeSort extends Pane{
	int[] L = new int [100];
    int[] R = new int [100]; 
    int cur_size = 1;
    int left_start = 0;
    int right_end;
    
    @Override
    public void sort() {
		//set default color
		for(int m=0;m<=arr.length-1;m++) {
			rectangles.get(m).setFill(Color.BROWN);
		}
		//SET SIZE AND TARGET PHASE
		if (cur_size > arr.length - 1) {
			timeline.stop();
			//sortAlgo.setDisable(false);
			cur_size = 1;
			left_start = 0;
			right_end = 0;
			return;
		}
		if (left_start >= arr.length - 1) 
		{
			System.out.print("double\n");
			left_start = 0;
			cur_size *= 2;
		}
		
		if(left_start + 2*cur_size - 1 < arr.length - 1)
		{
			right_end = left_start + 2*cur_size - 1;
		}
		else {
			right_end = arr.length - 1;
		}
		
		//MERGE PHASE
		
		int l = left_start;
		int m = left_start + cur_size - 1;
		int r = right_end;
		for(int index=l;index<=r;index++) {
			rectangles.get(index).setFill(Color.YELLOWGREEN);
		}
		
		int i, j, k; 
		k = 0;
	
	    int n1 = m - l + 1; 
	    int n2 =  r - m; 
	    
	    System.out.println("n1 = " + n1);
	    for (i = 0; i < n1; i++) 
	    {
	    	//System.out.print(l + i + " ");
	    	if(l + i >= arr.length)
	    		break;
	        L[i] = arr[l + i]; 
	        
	    }
	    
	    for (j = 0; j < n2; j++) 
	    {
	    	System.out.print(m + 1 + j + " ");
	        R[j] = arr[m + 1 + j]; 
	        
	    }
	    
	    i = 0; 
	    j = 0; 
	    k = l; 
	    while (i < n1 && j < n2) 
	    { 
	        if (L[i] <= R[j]) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	        } 
	        else
	        { 
	            arr[k] = R[j]; 
	            j++; 
	        } 
	        k++; 
	    }
	    
	    while (i < n1) 
	    { 
	    	if (k >= arr.length) {
				break;
			}
	        arr[k] = L[i]; 
	        i++; 
	        k++; 
	    } 
	  
	    while (j < n2) 
	    { 
	    	if (k >= arr.length) {
				break;
			}
	        arr[k] = R[j]; 
	        j++; 
	        k++; 
	    } 
		
		//set value for rectangles and text fields
		for(int index=0;index < 10;index++) {
			tFields.get(index).setText(Integer.toString(arr[index]));
			rectangles.get(index).setHeight(arr[index]*14*53/max(arr));
		}
		System.out.print("\n");
		
		left_start += 2*cur_size;
		if (this.StepByStep == 1) {
			this.timeline.stop();
		}
    }
    public MergeSort() {
    	super();
	}
    @Override
    public void reset() {
    	// TODO Auto-generated method stub
    	super.reset();
    	cur_size = 1;
        left_start = 0;
        right_end = 0;
    }
}
