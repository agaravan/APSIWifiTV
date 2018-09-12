package free.rm.APSIWifi.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.BindView;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.gui.businessobjects.adapters.OrderableVideoGridAdapter;
import free.rm.APSIWifi.gui.businessobjects.fragments.OrderableVideosGridFragment;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;import free.rm.APSIWifi.businessobjects.VideoCategory;import free.rm.APSIWifi.gui.businessobjects.adapters.OrderableVideoGridAdapter;import free.rm.APSIWifi.gui.businessobjects.fragments.OrderableVideosGridFragment;import free.rm.APSIWifi.R;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.businessobjects.db.DownloadedVideosDb;
import free.rm.APSIWifi.gui.businessobjects.adapters.OrderableVideoGridAdapter;
import free.rm.APSIWifi.gui.businessobjects.fragments.OrderableVideosGridFragment;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.gui.businessobjects.adapters.OrderableVideoGridAdapter;
import free.rm.APSIWifi.gui.businessobjects.fragments.OrderableVideosGridFragment;

/**
 * A fragment that holds videos downloaded by the user.
 */
public class DownloadedVideosFragment extends OrderableVideosGridFragment implements DownloadedVideosDb.DownloadedVideosListener {
	@BindView(R.id.noDownloadedVideosText)
	View noDownloadedVideosText;
	@BindView(R.id.downloadsDisabledWarning)
	View downloadsDisabledWarning;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		videoGridAdapter = new OrderableVideoGridAdapter(getActivity(), DownloadedVideosDb.getVideoDownloadsDb());
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		swipeRefreshLayout.setEnabled(false);
		populateList();
		displayDownloadsDisabledWarning();
	}


	@Override
	public void onFragmentSelected() {
		super.onFragmentSelected();

		populateList();
//		if (DownloadedVideosDb.getVideoDownloadsDb().isHasUpdated()) {
//			DownloadedVideosDb.getVideoDownloadsDb().setHasUpdated(false);
//		}

		displayDownloadsDisabledWarning();
	}


	/**
	 * If the user is using mobile network (e.g. 4G) and the preferences setting was not ticked to
	 * allow downloading functionality over the mobile data, then inform the user by displaying the
	 * warning;  else hide such warning.
	 */
	private void displayDownloadsDisabledWarning() {
		if (downloadsDisabledWarning != null) {
			boolean allowDownloadsOnMobile = APSIWifiApp.getPreferenceManager().getBoolean(APSIWifiApp.getStr(R.string.pref_key_allow_mobile_downloads), false);
			downloadsDisabledWarning.setVisibility(APSIWifiApp.isConnectedToMobile() && !allowDownloadsOnMobile ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_downloads;
	}


	@Override
	protected VideoCategory getVideoCategory() {
		return VideoCategory.DOWNLOADED_VIDEOS;
	}


	@Override
	public String getFragmentName() {
		return APSIWifiApp.getStr(R.string.downloads);
	}


	@Override
	public void onDownloadedVideosUpdated() {
		populateList();
		videoGridAdapter.refresh(true);
	}


	private void populateList() {
		new PopulateDownloadsTask().executeInParallel();
	}


	/**
	 * A task that:
	 *   1. gets the current total number of downloads
	 *   2. updated the UI accordingly (wrt step 1)
	 *   3. get the downloaded videos asynchronously.
	 */
	private class PopulateDownloadsTask extends AsyncTaskParallel<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			return DownloadedVideosDb.getVideoDownloadsDb().getNumDownloads();
		}


		@Override
		protected void onPostExecute(Integer numVideosDownloaded) {
			// If no videos have been bookmarked, show the text notifying the user, otherwise
			// show the swipe refresh layout that contains the actual video grid.
			if (numVideosDownloaded <= 0) {
				swipeRefreshLayout.setVisibility(View.GONE);
				noDownloadedVideosText.setVisibility(View.VISIBLE);
			} else {
				swipeRefreshLayout.setVisibility(View.VISIBLE);
				noDownloadedVideosText.setVisibility(View.GONE);

				// set video category and get the bookmarked videos asynchronously
				videoGridAdapter.setVideoCategory(VideoCategory.DOWNLOADED_VIDEOS);
			}
		}

	}
}
