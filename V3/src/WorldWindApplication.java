
/*
	import java.awt.BorderLayout; 
	import java.awt.Panel; 
	import java.awt.event.ActionEvent; 
	import java.awt.event.ActionListener; 
	 
	import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindowGLDrawable;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas; 
	import gov.nasa.worldwind.geom.Position; 
	 import gov.nasa.worldwind.awt.WorldWindowGLJPanel;

	
	import javax.swing.*;
	/*
	
	public class WorldWindApplication {
		private BasicModel r;

	/** Created by Antonio Rodriges, rodriges@wikience.org on 1/11/2016. */ 
/*
	    public JFrame frame; 
	    public WorldWindowGLDrawable t; 
	 
	    public static void main(String arguments[]) { 
	        new WorldWindApplication().launch(); 
	    } 
	 
	    public void launch() { 
	        // Create frame. 
	        frame = new JFrame("Sample WorldWind Java Application"); 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        frame.setSize(800, 600); 
	        frame.setLocationRelativeTo(null); 
	        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH); 
	 
	        // Create panel 
	        Panel panel = new Panel(new BorderLayout()); 
	        frame.add(panel); 
	 
	        // Create WorldWind canvas 
	         
	        /*Model model = (Model)WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
	        WorldWindowGLCanvas wwj = new WorldWindowGLCanvas();
	        wwj.setModel(model);*/
	        
	        
	        
	        // Add menu 
		/*
	        createMainMenu(); 
	 
	        // Show frame 
	        frame.setVisible(true); 
	 
	    } 
	 
	    public void createMainMenu() { 
	        JMenuBar menuBar = new JMenuBar(); 
	 
	        // Add the menubar to the frame 
	        frame.setJMenuBar(menuBar); 
	 
	        // Define and add two drop down menu to the menubar 
	        JMenu fileMenu = new JMenu("File"); 
	        JMenu editMenu = new JMenu("Edit"); 
	        menuBar.add(fileMenu); 
	        menuBar.add(editMenu); 
	 
	        // Create and add simple menu item to one of the drop down menu 
	        JMenuItem flyToAction = new JMenuItem("Fly to"); 
	        JMenuItem openAction = new JMenuItem("Open"); 
	 
	        fileMenu.add(flyToAction); 
	 
	        // Add a listener to the New menu item. actionPerformed() method will 
	        // invoked, if user triggred this menu item 
	        flyToAction.addActionListener(new ActionListener() { 
	            public void actionPerformed(ActionEvent arg0) { 
	                // Fly to Moscow 
	                wwd.getView().goTo(Position.fromDegrees(55.75, 37.65), 120000); 
	            } 
	        }); 
	    } 
	} 
*/
