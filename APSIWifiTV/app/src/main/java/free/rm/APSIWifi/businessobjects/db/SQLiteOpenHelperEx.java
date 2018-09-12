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

package free.rm.APSIWifi.businessobjects.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.app.APSIWifiApp;import free.rm.APSIWifi.app.APSIWifiApp;
import free.rm.APSIWifi.app.APSIWifiApp;

/**
 * An extended {@link SQLiteOpenHelper} with extra goodies.
 *
 * <p>Class assumes that sub-classes shall make use of the singleton design pattern.</p>
 */
public abstract class SQLiteOpenHelperEx extends SQLiteOpenHelper {

	public SQLiteOpenHelperEx(Context context, String name, android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}


//	/**
//	 * Closes the database and clears any singleton instances.
//	 */
//	@Override
//	public synchronized void close() {
//		super.close();
//		clearDatabaseInstance();
//	}


	/**
	 * Clears the (singleton) database instance by setting it to null.
	 */
	protected abstract void clearDatabaseInstance();


	/**
	 * @return The database (full) path.
	 */
	public String getDatabasePath() {
		return APSIWifiApp.getContext().getDatabasePath(getDatabaseName()).getPath();
	}


	/**
	 * @return The database directory (as a {@link File}).
	 */
	public File getDatabaseDirectory() {
		return APSIWifiApp.getContext().getDatabasePath(getDatabaseName()).getParentFile();
	}

}
