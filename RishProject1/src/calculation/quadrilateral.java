package calculation;

public class quadrilateral {
	
	private static int[]  xpoints= {350,350,550,550};
	private static  int[] ypoints= {350,550,550,350};
	private int[] linemidpointsx= {350,450,550,450};
	private int[] linemidpointsy= {450,550,450,350};
	private double area=40000;
	private double[] lengths= {200,200,200,200};
	private double diagonal1=200*(Math.sqrt(2));
	private double diagonal2=200*(Math.sqrt(2));
	private double[] angles= {90.0,90.0,90.0,90.0};
	private boolean[] slopecom= {true,true,true,true};
	
	public quadrilateral() {
		
		calculatearea();
		calculatelengths();
		calculateangles();
	}
	
	
	public void calculatearea() {
		
		area=0;
		for(int i=0;i<4;i++) {
			
			area+=xpoints[i]*ypoints[((i+1)%4)]-xpoints[((i+1)%4)]*ypoints[i];
		}
		
		area=Math.abs(area)/2.0d;
		
		//System.out.println(area);
		
		
	}
	
	public void calculatelengths() {
		
		double temp;
		
		for(int i=0;i<4;i++) {
			
			temp=Math.sqrt((xpoints[((i+1)%4)]-xpoints[i])*(xpoints[((i+1)%4)]-xpoints[i])+(ypoints[((i+1)%4)]-ypoints[i])*(ypoints[((i+1)%4)]-ypoints[i]));
			temp=((double)Math.round(temp*100))/100;
			lengths[i]=temp;
			//System.out.println("length "+i+" is "+lengths[i]);
			linemidpointsx[i]=(xpoints[((i+1)%4)]+xpoints[i])/2;
			linemidpointsy[i]=(ypoints[((i+1)%4)]+ypoints[i])/2;
		}
		
		diagonal1=Math.sqrt((xpoints[1]-xpoints[3])*(xpoints[1]-xpoints[3])+(ypoints[1]-ypoints[3])*(ypoints[1]-ypoints[3]));
		diagonal2=Math.sqrt((xpoints[0]-xpoints[2])*(xpoints[0]-xpoints[2])+(ypoints[0]-ypoints[2])*(ypoints[0]-ypoints[2]));
		
		//System.out.println("diagonal1 is "+diagonal1);
		//System.out.println("diagonal2 is "+diagonal2);
	}
	
	 private void calculateangles(){

	        double a0,a1,a2,a3;
	        //int trueFrequency = 0;
	       // int falseFrequency = 0;

	        a0 = Math.acos(((lengths[0]*lengths[0]) + (lengths[3]*lengths[3]) - (diagonal1*diagonal1))/(2*lengths[0]*lengths[3]));
	        a0 = (a0/Math.PI)*180;
	        a0 = ((double)Math.round(a0*100))/100.0;

	        a1 = Math.acos(((lengths[0]*lengths[0]) + (lengths[1]*lengths[1]) - (diagonal2*diagonal2))/(2*lengths[0]*lengths[1]));
	        a1 = (a1/Math.PI)*180;
	        a1 = ((double)Math.round(a1*100))/100.0;

	        a2 = Math.acos(((lengths[1]*lengths[1]) + (lengths[2]*lengths[2]) - (diagonal1*diagonal1))/(2*lengths[2]*lengths[1]));
	        a2 = (a2/Math.PI)*180;
	        a2 = ((double)Math.round(a2*100))/100.0;

	        a3 = Math.acos(((lengths[3]*lengths[3]) + (lengths[2]*lengths[2]) - (diagonal2*diagonal2))/(2*lengths[3]*lengths[2]));
	        a3 = (a3/Math.PI)*180;
	        a3 = ((double)Math.round(a3*100))/100.0;

	        angles[0] = a0;
	        angles[1] = a1;
	        angles[2] = a2;
	        angles[3] = a3;
	          
	        //the following code will check whether the polygon is concave or not and it will re-adjust the angle values
	        //crossProduct[i] is equal to true if the scalar value of cross products of origin vectors of lines forming angle[i] is positive and false if its negative
	        //for a concave polygon, the crossProducts[] value will be either {true, true, false, true} or {false, false, true, false}
	        //the index of the odd value, in above case, its [3], will be the index of the concave angle
	        //hence we re-adjust the value of that angle: e.g. angle[3] = 360 - angle[3];
	        
	        int trueFrequency = 0;
	        int falseFrequency = 0;
	        
	        for(int i=1; i<3;i++){                  
	        	slopecom[i] = ((xpoints[i+1]-xpoints[i])*(ypoints[i]-ypoints[i-1]))>((xpoints[i]-xpoints[i-1])*(ypoints[i+1]-ypoints[i]));
	        }
	            slopecom[0] = ((xpoints[1]-xpoints[0])*(ypoints[0]-ypoints[3]))>((xpoints[0]-xpoints[3])*(ypoints[1]-ypoints[0]));
	            slopecom[3] = ((xpoints[0]-xpoints[3])*(ypoints[3]-ypoints[2]))>((xpoints[3]-xpoints[2])*(ypoints[0]-ypoints[3]));
	        for(int i=0; i<4;i++)
	        	if(slopecom[i]==true)
	        		trueFrequency++;
	        
	    	else          
	    		falseFrequency++;
	        
	        if(falseFrequency>trueFrequency) {
	        	for(int i=0;i<4;i++)slopecom[i] = !slopecom[i];
	        }
	        for(int i=0;i<4;i++)if(slopecom[i] == false)
	        	angles[i] = 360- angles[i];
	        	
	    }
	
	 public void changePoint(int x, int y, int i) {
	    	xpoints[i] = x;
	        ypoints[i] = y;
	        calculatearea();
	        calculatelengths();
	        calculateangles();
	    }
	    
	    public int getX(int i){
	        return xpoints[i];
	    }

	    public int getY(int i){
	        return ypoints[i];
	    }
	    
	    public int[] getXs(){
	        return xpoints;
	    }
	    
	    public int[] getYs(){
	        return ypoints;
	    }
	    
	    public double getXMid(int i){
	        return linemidpointsx[i];
	    }
	    
	    public double getYMid(int i){
	        return linemidpointsy[i];
	    }
	    
	    public double getArea() {
	        return area;
	    }

	    public double getLength(int i){
	        return lengths[i];
	    }
	    
	    public double getD1(){
	        return diagonal1;
	    }

	    public double getD2(){
	        return diagonal2;
	    }

	    public double getAngle(int i){
	        return angles[i];
	    }
	    public boolean getCrossProduct(int i) {
	    	return slopecom[i];
	    }

	public static void main(String[] args) {
		
		/*quadrilateral q=new quadrilateral();
		xpoints[0]=0;
		xpoints[1]=0;
		xpoints[2]=300;
		xpoints[3]=300;
		
		ypoints[0]=0;
		ypoints[1]=200;
		ypoints[2]=200;
		ypoints[3]=0;
		
		q.calculateangles();*/
		
		
		

	}

}
