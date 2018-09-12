package free.rm.APSIWifi.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.gui.businessobjects.MainActivityListener;
import free.rm.APSIWifi.gui.businessobjects.PlaylistClickListener;
import free.rm.APSIWifi.gui.businessobjects.adapters.PlaylistsGridAdapter;
import free.rm.APSIWifi.businessobjects.VideoCategory;import free.rm.APSIWifi.gui.businessobjects.MainActivityListener;import free.rm.APSIWifi.gui.businessobjects.PlaylistClickListener;import free.rm.APSIWifi.gui.businessobjects.adapters.PlaylistsGridAdapter;import free.rm.APSIWifi.R;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.businessobjects.YouTube.POJOs.YouTubeChannel;
import free.rm.APSIWifi.businessobjects.YouTube.POJOs.YouTubePlaylist;
import free.rm.APSIWifi.gui.businessobjects.MainActivityListener;
import free.rm.APSIWifi.gui.businessobjects.PlaylistClickListener;
import free.rm.APSIWifi.gui.businessobjects.adapters.PlaylistsGridAdapter;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.gui.businessobjects.MainActivityListener;
import free.rm.APSIWifi.gui.businessobjects.PlaylistClickListener;
import free.rm.APSIWifi.gui.businessobjects.adapters.PlaylistsGridAdapter;

/**
 * A fragment that displays the Playlists belonging to a Channel
 */
public class ChannelPlaylistsFragment extends VideosGridFragment implements PlaylistClickListener, SwipeRefreshLayout.OnRefreshListener {
	private PlaylistsGridAdapter playlistsGridAdapter;
	private YouTubeChannel          channel;
	private MainActivityListener mainActivityListener;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		swipeRefreshLayout.setOnRefreshListener(this);

		if (playlistsGridAdapter == null) {
			playlistsGridAdapter = new PlaylistsGridAdapter(getActivity(), this);
		} else {
			playlistsGridAdapter.setContext(getActivity());
		}

		channel = (YouTubeChannel)getArguments().getSerializable(ChannelBrowserFragment.CHANNEL_OBJ);
		playlistsGridAdapter.setYouTubeChannel(channel);

		gridView.setAdapter(playlistsGridAdapter);

		return view;
	}

	@Override
	public String getFragmentName() {
		return APSIWifiApp.getStr(R.string.playlists);
	}

	@Override
	public void onClickPlaylist(YouTubePlaylist playlist) {
		if(mainActivityListener != null)
			mainActivityListener.onPlaylistClick(playlist);
	}

	public void setMainActivityListener(MainActivityListener mainActivityListener) {
		this.mainActivityListener = mainActivityListener;
	}

	@Override
	public void onRefresh() {
		playlistsGridAdapter.refresh(new Runnable() {
			@Override
			public void run() {
				swipeRefreshLayout.setRefreshing(false);
			}
		});
	}


	@Override
	protected VideoCategory getVideoCategory() {
		return null;
	}

}
