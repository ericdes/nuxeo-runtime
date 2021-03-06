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
 * $Id: JOOoConvertPluginImpl.java 18651 2007-05-13 20:28:53Z sfermigier $
 */

package org.nuxeo.runtime;

import junit.framework.TestCase;

public class TestVersion extends TestCase {

    Version version;

    @Override
    public void setUp() {
        version = new Version(1, 2, 3);
    }

    public void testAccessors() {
        assertEquals(1, version.getMajorVersion());
        assertEquals(2, version.getMinorVersion());
        assertEquals(3, version.getUpdateVersion());
        assertEquals("1.2.3", version.toString());
    }

    public void testParseString() {
        version = Version.parseString("0.0.0");
        assertEquals("0.0.0", version.toString());
        version = Version.parseString("2");
        assertEquals("2.0.0", version.toString());
        version = Version.parseString("3.1");
        assertEquals("3.1.0", version.toString());
    }

    @SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsBetweenInconvertibleTypes"})
    public void testEquals() {
        assertTrue(version.equals(new Version(1, 2, 3)));
        assertFalse(version.equals(""));
    }

    public void testIsGreaterThan() {
        assertTrue(version.isGreaterThan(new Version(0, 0, 0)));
        assertTrue(version.isGreaterThan(new Version(0, 0, 1)));
        assertTrue(version.isGreaterThan(new Version(0, 1, 0)));
        assertTrue(version.isGreaterThan(new Version(1, 0, 0)));
        assertTrue(version.isGreaterThan(new Version(1, 2, 0)));
        assertTrue(version.isGreaterThan(new Version(1, 2, 2)));

        assertFalse(version.isGreaterThan(new Version(1, 2, 3)));
        assertFalse(version.isGreaterThan(new Version(1, 2, 4)));
        assertFalse(version.isGreaterThan(new Version(1, 3, 0)));
        assertFalse(version.isGreaterThan(new Version(2, 0, 0)));
        assertFalse(version.isGreaterThan(new Version(2, 0, 1)));
        assertFalse(version.isGreaterThan(new Version(2, 1, 0)));
    }

    public void testIsGreaterOrEqualThan() {
        assertTrue(version.isGreaterOrEqualThan(new Version(0, 0, 0)));
        assertTrue(version.isGreaterOrEqualThan(new Version(0, 0, 1)));
        assertTrue(version.isGreaterOrEqualThan(new Version(0, 1, 0)));
        assertTrue(version.isGreaterOrEqualThan(new Version(1, 0, 0)));
        assertTrue(version.isGreaterOrEqualThan(new Version(1, 2, 0)));
        assertTrue(version.isGreaterOrEqualThan(new Version(1, 2, 2)));
        assertTrue(version.isGreaterOrEqualThan(new Version(1, 2, 3)));

        assertFalse(version.isGreaterOrEqualThan(new Version(1, 2, 4)));
        assertFalse(version.isGreaterOrEqualThan(new Version(1, 3, 0)));
        assertFalse(version.isGreaterOrEqualThan(new Version(2, 0, 0)));
        assertFalse(version.isGreaterOrEqualThan(new Version(2, 0, 1)));
        assertFalse(version.isGreaterOrEqualThan(new Version(2, 1, 0)));
    }

    public void testHashCode() {
        version = new Version(0, 0, 0);
        assertEquals(0, version.hashCode());
    }

}
