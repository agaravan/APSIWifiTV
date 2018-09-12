/*
 * APSI Wifi TV
 * Copyright (C) 2018  Ramon Mifsud
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation (version 3 of the License).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package free.rm.APSIWifi.businessobjects.YouTube.Tasks;

import java.io.IOException;
import java.util.List;

import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.Logger;
import free.rm.APSIWifi.app.APSIWifiApp;import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;import free.rm.APSIWifi.businessobjects.Logger;import free.rm.APSIWifi.R;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.YouTube.GetVideoDescription;
import free.rm.APSIWifi.businessobjects.YouTube.POJOs.YouTubeVideo;
import free.rm.APSIWifi.businessobjects.Logger;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.AsyncTaskParallel;
import free.rm.APSIWifi.businessobjects.Logger;

/**
 * Get the video's description.
 */
public class GetVideoDescriptionTask extends AsyncTaskParallel<Void, Void, String> {
	private YouTubeVideo youTubeVideo;
	private GetVideoDescriptionTaskListener listener;

	public interface GetVideoDescriptionTaskListener {
		void onFinished(String description);
	}

	public GetVideoDescriptionTask(YouTubeVideo youTubeVideo, GetVideoDescriptionTaskListener listener) {
		this.youTubeVideo = youTubeVideo;
		this.listener = listener;
	}

	@Override
	protected String doInBackground(Void... params) {
		GetVideoDescription getVideoDescription = new GetVideoDescription();
		String description = APSIWifiApp.getStr(R.string.error_get_video_desc);

		try {
			getVideoDescription.init(youTubeVideo.getId());
			List<YouTubeVideo> list = getVideoDescription.getNextVideos();

			if (list.size() > 0) {
				description = list.get(0).getDescription();
			}
		} catch (IOException e) {
			Logger.e(this, description + " - id=" + youTubeVideo.getId(), e);
		}

		return description;
	}

	@Override
	protected void onPostExecute(String description) {
		if(listener instanceof GetVideoDescriptionTaskListener)
			listener.onFinished(description);
	}

}