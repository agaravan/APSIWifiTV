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

package free.rm.APSIWifi.businessobjects.YouTube.POJOs;

import java.util.Random;

import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.gui.fragments.preferences.OthersPreferenceFragment;
import free.rm.APSIWifi.app.APSIWifiApp;import free.rm.APSIWifi.gui.fragments.preferences.OthersPreferenceFragment;import free.rm.APSIWifi.BuildConfig;
import free.rm.APSIWifi.R;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.gui.fragments.preferences.OthersPreferenceFragment;

/**
 * Represents a YouTube API key.
 */
public class YouTubeAPIKey {

	/** User's YouTube API key which is inputted via the
	 * {@link OthersPreferenceFragment}.  Will be null if the user did not
	 * input a key. **/
	private String userAPIKey;
	private Random random = new Random();

	private static YouTubeAPIKey youTubeAPIKey = null;


	/**
	 * Constructor.  Will retrieve user's YouTube API key if set.
	 */
	private YouTubeAPIKey() {
		userAPIKey = getUserApiKey();
	}


	/**
	 * @return An instance of {@link YouTubeAPIKey}.
	 */
	public static YouTubeAPIKey get() {
		if (youTubeAPIKey == null) {
			youTubeAPIKey = new YouTubeAPIKey();
		}

		return youTubeAPIKey;
	}



	/**
	 * @return Return YouTube API key.
	 */
	public String getYouTubeAPIKey() {
		String key;

		if (isUserApiKeySet()) {
			// if the user has not set his own API key, then use the default APSI Wifi key
			key = userAPIKey;
		} else {
			if (BuildConfig.DEBUG) {
				key = BuildConfig.YOUTUBE_API_KEYS_DEBUG;
			} else {
				// else we are going to choose one of the defaults keys at random
				int i = random.nextInt(BuildConfig.YOUTUBE_API_KEYS.length);
				key = BuildConfig.YOUTUBE_API_KEYS[i];
			}
		}

		return key;
	}


	/**
	 * @return True if the user has set his own YouTube API key (via the
	 * {@link OthersPreferenceFragment}); false otherwise.
	 */
	public boolean isUserApiKeySet() {
		return (userAPIKey != null);
	}


	/**
	 * @return User's YouTube API key (if set).  If the user did not set a key, then it will return null.
	 */
	private String getUserApiKey() {
		String userApiKey = APSIWifiApp.getPreferenceManager().getString(APSIWifiApp.getStr(R.string.pref_youtube_api_key), null);

		if (userApiKey != null) {
			userApiKey = userApiKey.trim();

			if (userApiKey.isEmpty())
				userApiKey = null;
		}

		return userApiKey;
	}

}
