package GUI;

import calculation.quadrilateral;


import javax.swing.*;//for Jframe

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;  
import java.awt.event.*;  //for MouseMotionListener

public class Frame extends JPanel implements MouseMotionListener {
	
	private quadrilateral polygon=new quadrilateral();
	private int verticesize=22;
	private Rectangle[] vertices=new Rectangle[4];
	//A Rectangle specifies an area in a coordinate space that is 
	//enclosed by the Rectangle object's top-left point ( x ,  y ) 
	//in the coordinate space, its width, and its height.
	 private java.awt.Polygon poly = new java.awt.Polygon(polygon.getXs(), polygon.getYs(), 4);
     //(xcoordinates,ycoordinates,no of coordinates)
		private int currentVertexIndex = -1;                        
	   	private Color mainColor = new Color(0, 5, 34);
	   	private Color backgroundColor = new Color(200, 30, 15);
	   	private float polygonWidth = 4;
	   	private int areaLabelLocationXandY = 40;
	   	
	   	public Frame() {
	   		
	   	 setSize(900,1200);// setting size of frame
		 setBackground(backgroundColor);// background colour setting
		 
		 for(int i = 0; i < 4; i++)                               
		  {
			   Rectangle r = new Rectangle();        
			   r.setBounds((int)(polygon.getX(i) ),(int)(polygon.getY(i)), verticesize, verticesize);
			   vertices[i] = r;
		  }
		 
		 addMouseListener(new MouseAdapter() {
			 
		      @Override
		      public void mousePressed(MouseEvent me) {                
		         int x = me.getX();
		         int y = me.getY();
		         currentVertexIndex = getVertexIndex(x, y);
		 
		         if (getVertexIndex(x, y) >= 0) {//after changing it will in our hand
		        	 setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	 }
		         
		         else {//before changing points
		        	 setCursor(Cursor.getDefaultCursor());}
		      }});
			  
		      addMouseMotionListener(this);
		 
		 
	   		
	   		
	   	}
	   	
	    public int getVertexIndex(int x, int y) {                    
	        for (int i = 0; i < 4; i++) {
	           if (vertices[i].contains(x, y)) {
	           return i;}}
	       
	        return -1;
	     }
	    
	    public void paintComponent(Graphics g) {             
	    	//The Graphics class is the abstract base class for all graphics 
	    	//contexts that allow an application to draw onto components that are 
	    	//realized on various devices, as well as onto off-screen images
	        super.paintComponent(g);
	        g.setColor(backgroundColor);//setting initial background color
	        
	        for (int i = 0; i < 4; i++) {
	     	   ((Graphics2D)g).draw(vertices[i]); 
	        }
	           
	        g.setColor(mainColor);//graphic setcolor
	        ((Graphics2D)g).setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        //To set the stroke attribute, you create a BasicStroke object and pass 
	        //it into the Graphics2D setStroke method.

	       // A BasicStroke object holds information about the line width, join style,
	        //end-cap style, and dash style. This information is used when a Shape is 
	        //rendered with the draw method.
	        
	        ((Graphics2D)g).draw(poly);
	        ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 15));
	        ((Graphics2D)g).drawString(polygon.getAngle(0)+"\u00B0", (int)polygon.getX(0)+10, (int)polygon.getY(0)+30);
	        ((Graphics2D)g).drawString(polygon.getAngle(1)+"\u00B0", (int)polygon.getX(1)+10, (int)polygon.getY(1)-20);
	        ((Graphics2D)g).drawString(polygon.getAngle(2)+"\u00B0", (int)polygon.getX(2)-40, (int)polygon.getY(2)-20);
	        ((Graphics2D)g).drawString(polygon.getAngle(3)+"\u00B0", (int)polygon.getX(3)-40, (int)polygon.getY(3)+30);
	        ((Graphics2D)g).drawString(polygon.getLength(0)+" units", (int)polygon.getXMid(0)-80, (int)polygon.getYMid(0)+5);
	        ((Graphics2D)g).drawString(polygon.getLength(1)+" units", (int)polygon.getXMid(1)-35, (int)polygon.getYMid(1)+20);
	        ((Graphics2D)g).drawString(polygon.getLength(2)+" units", (int)polygon.getXMid(2)+7, (int)polygon.getYMid(2)+5);
	        ((Graphics2D)g).drawString(polygon.getLength(3)+" units", (int)polygon.getXMid(3)-35, (int)polygon.getYMid(3)-8);
	        ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 25));
	        ((Graphics2D)g).drawString("Area  -  "+polygon.getArea()+" unit\u00B2", areaLabelLocationXandY,areaLabelLocationXandY);   
	        //showing all angles and length and polygon in string
	     }
	   	
	public static void main(String[] args) {
		
		 JFrame jFrame = new JFrame();
	        jFrame.setTitle("JAVA PROJECT ON QUADRILATERAL");
	  
	        Frame frame = new Frame(); 
	        jFrame.setSize(frame.getSize());// setting jframe size
	      	jFrame.setResizable(true);    // resize screen
	    
	        Container cPane = jFrame.getContentPane();
	       // The getContentPane() method retrieves the content pane
	        //layer so that you can add an object to it. The content
	        //pane is an object created by the Java run time environment.
	        //You do not have to know the name of the content pane to use it.
	        cPane.add(frame);
	 
	        jFrame.setVisible(true);
	        jFrame.setLocationRelativeTo(null);
	        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseDragged(MouseEvent me) {//before and after dragging mouse area length and angle is displayed
		
		 int x = me.getX();
	       int y = me.getY();
	     
	       if(getBounds().contains(x,y)) {if (currentVertexIndex >= 0) {
	 
	            Graphics g = getGraphics();
	            ((Graphics2D)g).setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	            g.setColor(backgroundColor);
	            ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 15));
	            ((Graphics2D)g).draw(poly);
	            ((Graphics2D)g).drawString(polygon.getAngle(0)+"\u00B0", (int)polygon.getX(0)+10, (int)polygon.getY(0)+30);
	            ((Graphics2D)g).drawString(polygon.getAngle(1)+"\u00B0", (int)polygon.getX(1)+10, (int)polygon.getY(1)-20);
	            ((Graphics2D)g).drawString(polygon.getAngle(2)+"\u00B0", (int)polygon.getX(2)-40, (int)polygon.getY(2)-20);
	            ((Graphics2D)g).drawString(polygon.getAngle(3)+"\u00B0", (int)polygon.getX(3)-40, (int)polygon.getY(3)+30);
	            ((Graphics2D)g).drawString(polygon.getLength(0)+" units", (int)polygon.getXMid(0)-80, (int)polygon.getYMid(0)+5);
	            ((Graphics2D)g).drawString(polygon.getLength(1)+" units", (int)polygon.getXMid(1)-35, (int)polygon.getYMid(1)+20);
	            ((Graphics2D)g).drawString(polygon.getLength(2)+" units", (int)polygon.getXMid(2)+7, (int)polygon.getYMid(2)+5);
	            ((Graphics2D)g).drawString(polygon.getLength(3)+" units", (int)polygon.getXMid(3)-35, (int)polygon.getYMid(3)-8);
	  
	            ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 25));
	            ((Graphics2D)g).drawString("Area  -  "+polygon.getArea()+" unit\u00B2", areaLabelLocationXandY,areaLabelLocationXandY);
	            
	            polygon.changePoint(x,y,currentVertexIndex);
	            
	            
	            vertices[currentVertexIndex].x = (int)(polygon.getX(currentVertexIndex));
	            vertices[currentVertexIndex].y = (int)(polygon.getY(currentVertexIndex));
	            poly =  new java.awt.Polygon(polygon.getXs(), polygon.getYs(), 4);
	            ((Graphics2D)g).draw(vertices[currentVertexIndex]);

	             g.setColor(mainColor);
	             ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 25));
	             ((Graphics2D)g).drawString("Area  -  "+polygon.getArea()+" unit\u00B2", areaLabelLocationXandY,areaLabelLocationXandY); 
	             ((Graphics2D)g).setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	             ((Graphics2D)g).draw(poly);
	             ((Graphics2D)g).setFont(new Font("Century Gothic", Font.BOLD, 15));
	             ((Graphics2D)g).drawString(polygon.getAngle(0)+"\u00B0", (int)polygon.getX(0)+10, (int)polygon.getY(0)+30);
	             ((Graphics2D)g).drawString(polygon.getAngle(1)+"\u00B0", (int)polygon.getX(1)+10, (int)polygon.getY(1)-20);
	             ((Graphics2D)g).drawString(polygon.getAngle(2)+"\u00B0", (int)polygon.getX(2)-40, (int)polygon.getY(2)-20);
	             ((Graphics2D)g).drawString(polygon.getAngle(3)+"\u00B0", (int)polygon.getX(3)-40, (int)polygon.getY(3)+30);
	             ((Graphics2D)g).drawString(polygon.getLength(0)+" units", (int)polygon.getXMid(0)-80, (int)polygon.getYMid(0)+5);
	             ((Graphics2D)g).drawString(polygon.getLength(1)+" units", (int)polygon.getXMid(1)-35, (int)polygon.getYMid(1)+20);
	             ((Graphics2D)g).drawString(polygon.getLength(2)+" units", (int)polygon.getXMid(2)+7, (int)polygon.getYMid(2)+5);
	             ((Graphics2D)g).drawString(polygon.getLength(3)+" units", (int)polygon.getXMid(3)-35, (int)polygon.getYMid(3)-8);
	 
	             g.dispose();
	    }}
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		 int x = me.getX();
	       int y = me.getY();
	 
	       if (getVertexIndex(x, y) >= 0) {
	     	   setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));} 
	       else 
	    	   setCursor(Cursor.getDefaultCursor());
		
	}

}
