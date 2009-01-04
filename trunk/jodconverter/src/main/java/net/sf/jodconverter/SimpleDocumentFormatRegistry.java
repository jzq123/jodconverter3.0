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
package net.sf.jodconverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleDocumentFormatRegistry implements DocumentFormatRegistry {

	private List<DocumentFormat> documentFormats = new ArrayList<DocumentFormat>();

	public void addFormat(DocumentFormat documentFormat) {
		documentFormats.add(documentFormat);
	}

	public DocumentFormat getFormatByExtension(String extension) {
        if (extension == null) {
            return null;
        }
        String lowerExtension = extension.toLowerCase();
        //TODO create a documentByExtension map instead
		for (DocumentFormat format : documentFormats) {
			if (format.getExtension().equals(lowerExtension)) {
				return format;
			}
		}
		return null;
	}

	public Set<DocumentFormat> getOutputFormats(DocumentFamily family) {
	    Set<DocumentFormat> formats = new HashSet<DocumentFormat>();
        for (DocumentFormat format : documentFormats) {
            if (format.getStoreProperties(family) != null) {
                formats.add(format);
            }
        }
	    return formats;
	}

}
