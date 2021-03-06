/*
 * (C) Copyright 2006-2008 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     bstefanescu
 *
 * $Id$
 */

package org.nuxeo.runtime.deploy;

// FIXME: interface has changed and this example is no more appropriate.
/**
 * An example of listener implementation:
 * <pre>
 * public class MyListener implements FileChangeListener {
 *   long lastNotif = 0;
 *   public void fileChanged(File file, long since, long now) {
 *     if (now == lastNotifFlush) return;
 *     if (isIntersetedInFile(file)) {
 *       lastNotif = now;
 *       flushCache(); // flush internal cache because file on disk changed
 *     }
 *   }
 * }
 * </pre>
 *
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 */
public interface FileChangeListener {

    /**
     * Notifies that the given file changed.
     *
     * @param entry
     * @param now the time stamp when the change was detected.
     *      This value can be used as a notification ID by listeners to avoid
     *      multiple processing for notification that will send multiple events
     */
    void fileChanged(FileChangeNotifier.FileEntry entry, long now) throws Exception;

}
