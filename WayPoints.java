

import javax.swing.*;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.drew.imaging.ImageProcessingException;

import java.awt.MenuBar;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class CustomPainter extends WaypointPainter {
    public void setWaypoints(List<? extends Waypoint> waypoints) {
        super.setWaypoints(new HashSet<Waypoint>(waypoints));
    }
}

public class WayPoints {
	private MenuBar mb = new MenuBar();
	public  static  List<DefaultWaypoint> waypoints = new ArrayList<DefaultWaypoint>();
	public static   JXMapKit jxMapKit = new JXMapKit();
	public static   CustomPainter painter = new CustomPainter();

    public static void main(String[] args) throws ImageProcessingException, IOException {
        waypoints.add(new DefaultWaypoint(51.5, 0));

        jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        painter.setWaypoints(waypoints);
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