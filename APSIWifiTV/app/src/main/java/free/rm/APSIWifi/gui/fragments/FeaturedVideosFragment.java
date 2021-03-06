/*
 * APSI Wifi TV
 * Copyright (C) 2018 APSI Wif
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

package free.rm.APSIWifi.gui.fragments;

import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.businessobjects.VideoCategory;import free.rm.APSIWifi.R;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.businessobjects.VideoCategory;
import free.rm.APSIWifi.businessobjects.VideoCategory;

/**
 * A fragment that holds featured videos.
 */
public class FeaturedVideosFragment extends VideosGridFragment {

	@Override
	protected VideoCategory getVideoCategory() {
		return VideoCategory.FEATURED;
	}


	@Override
	public String getFragmentName() {
		return APSIWifiApp.getStr(R.string.featured);
	}

}
