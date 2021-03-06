/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.runtime.remoting;

import java.net.URL;

import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.model.ComponentName;
import org.nuxeo.runtime.model.RegistrationInfo;
import org.nuxeo.runtime.model.RuntimeContext;
import org.osgi.framework.Bundle;

/**
 * TODO: Work in progress.
 *
 * What works: loading resources
 * What doesn't work:
 * loading remote objects that refrences on other remote classes
 * (try to use ClassByteClassLoader from jboss-remoting)
 *
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 *
 */
public class RemoteContext implements RuntimeContext {

//    private static final Log log  = LogFactory.getLog(RemoteContext.class);

    private final RemoteClassLoader cl;

    public RemoteContext(ServerDescriptor sd, ComponentName component, ClassLoader localClassLoader) {
        cl = new RemoteClassLoader(sd, component, localClassLoader);
    }

    public URL getLocalResource(String name) {
        return getResource(name);
    }

    public URL getResource(String name) {
        return cl.getResource(name);
    }

    public Class<?> loadClass(String className) throws ClassNotFoundException {
        return cl.loadClass(className);
    }

    public RuntimeService getRuntime() {
        return Framework.getRuntime();
    }

    public boolean isDeployed(URL url) {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public boolean isDeployed(String location) {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public void undeploy(URL url) throws Exception {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public void undeploy(String location) throws Exception {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public RegistrationInfo deploy(URL url) throws Exception {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public RegistrationInfo deploy(String location) throws Exception {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public void destroy() {

    }

    public Bundle getBundle() {
        return null;
    }
}
