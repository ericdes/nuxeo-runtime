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
package org.nuxeo.runtime.util;

import java.io.File;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;

/**
 *
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 */
public abstract class NXRuntimeApplication {

    protected static RuntimeService runtime;

    private static final Log log = LogFactory.getLog(NXRuntimeApplication.class);

    protected final File home;


    protected NXRuntimeApplication(File home) {
        this.home = home;
    }

    protected NXRuntimeApplication() {
        this(null);
    }


    public void start() {
        start(new String[0]);
    }

    public void start(String[] args) {
        try {
            initialize(args);
            run();
            shutdown();
        } catch (Throwable t) {
            log.error(t);
            System.exit(1);
        }
    }

    public void initialize(String[] args) throws Exception {
        runtime = new SimpleRuntime(home);
        Framework.initialize(runtime);
        deployAll();
    }

    public  void shutdown() throws Exception {
        Framework.shutdown();
    }

    public void deploy(String bundle) {
        URL url = getResource(bundle);
        // could be more than core design flaw: assert url != null;
        if (url == null) {
            log.error("Cannot locate resource for deploying bundle " + bundle);
            return;
        }
        try {
            Framework.getRuntime().getContext().deploy(url);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void undeploy(String bundle) {
        URL url = getResource(bundle);
        assert url != null;
        try {
            Framework.getRuntime().getContext().undeploy(url);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public URL getResource(String resource) {
        return runtime.getContext().getResource(resource);
    }

    protected void deployAll() {
        //deploy("RemotingService.xml");
        deploy("EventService.xml");
    }

    protected abstract void run() throws Exception;

}
