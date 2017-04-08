import javax.swing.*;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.drew.imaging.ImageProcessingException;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class CustomPainter extends WaypointPainter {
    public void setWaypoints(List<SwingWaypoint> waypoints) {
        super.setWaypoints(new HashSet<Waypoint>(waypoints));
    }
}

public class WayPoints {
	private MenuBar mb = new MenuBar();
	public  static  List<SwingWaypoint> waypoints = new ArrayList<SwingWaypoint>();
	public static   JXMapKit jxMapKit = new JXMapKit();
	public static   WaypointPainter<SwingWaypoint> painter = new WaypointPainter<SwingWaypoint>();
	
    public static void main(String[] args) throws ImageProcessingException, IOException {
        GeoPosition frankfurt = new GeoPosition(50,  7, 0, 8, 41, 0);
        painter.setRenderer(new MySuperRenderer());
        
        jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jxMapKit.getMainMap().setOverlayPainter(painter);

        final fen1 frame = new fen1();
        frame.add(jxMapKit);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}