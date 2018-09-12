package free.rm.APSIWifi.gui.businessobjects;

import free.rm.APSIWifi.businessobjects.YouTube.POJOs.YouTubePlaylist;

/**
 * Interface for an object that will respond to a Playlist being clicked on
 */
public interface PlaylistClickListener {
	void onClickPlaylist(YouTubePlaylist playlist);
}
