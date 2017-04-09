import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.drew.imaging.ImageProcessingException;

class CustomPainter extends WaypointPainter {
	public void setWaypoints(List<SwingWaypoint> waypoints) {
		super.setWaypoints(new HashSet<Waypoint>(waypoints));
	}
}

public class Main {
	public static Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
	public static JXMapKit jxMapKit = new JXMapKit();
	public static WaypointPainter<SwingWaypoint> painter = new WaypointPainter<SwingWaypoint>();

	public static void main(String[] args) throws ImageProcessingException, IOException {

		GeoPosition frankfurt = new GeoPosition(50, 7, 0, 8, 41, 0);
		//painter.setRenderer(new MySuperRenderer());

		jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
		jxMapKit.getMainMap().setOverlayPainter(painter);

		final MainWindow frame = new MainWindow();
		frame.add(jxMapKit);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}
}