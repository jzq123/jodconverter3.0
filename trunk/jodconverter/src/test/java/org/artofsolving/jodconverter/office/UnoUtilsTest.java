//
// JODConverter - Java OpenDocument Converter
// Copyright (C) 2004-2008 - Mirko Nasato <mirko@artofsolving.com>
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, you can find it online
// at http://www.gnu.org/licenses/lgpl-2.1.html.
//
package org.artofsolving.jodconverter.office;

import static org.artofsolving.jodconverter.office.OfficeUtils.*;
import static org.testng.Assert.*;

import java.io.File;

public class UnoUtilsTest {

    public void testToUrl() {
        //TODO create separate tests for Windows
        assertEquals(toUrl(new File("/tmp/document.odt")), "file:///tmp/document.odt");
        assertEquals(toUrl(new File("/tmp/document with spaces.odt")), "file:///tmp/document%20with%20spaces.odt");
    }

}
