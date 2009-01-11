//
// JODConverter - Java OpenDocument Converter
// Copyright (C) 2004-2009 - Mirko Nasato and Contributors
//
// JODConverter is free software: you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
//
// JODConverter is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General
// Public License along with JODConverter.  If not, see
// <http://www.gnu.org/licenses/>.
//
package net.sf.jodconverter;

import static org.testng.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import net.sf.jodconverter.office.ManagedProcessOfficeManager;
import net.sf.jodconverter.office.OfficeManager;
import net.sf.jodconverter.office.OfficeUtils;
import org.testng.annotations.Test;

@Test(groups="functional")
public class OfficeDocumentConverterFunctionalTest {

    private static final File OFFICE_HOME = OfficeUtils.getDefaultOfficeHome();
    private static final File OFFICE_PROFILE = OfficeUtils.getDefaultProfileDir();
    private static final String CONNECT_STRING = "socket,host=127.0.0.1,port=8100";

    public void runAllPossibleConversions() throws IOException {
        OfficeManager officeManager = new ManagedProcessOfficeManager(OFFICE_HOME, OFFICE_PROFILE, CONNECT_STRING);
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        DocumentFormatRegistry formatRegistry = converter.getFormatRegistry();
        
        officeManager.start();
        try {
            File dir = new File("src/test/resources/documents");
            File[] files = dir.listFiles(new FilenameFilter() {
            	public boolean accept(File dir, String name) {
            		return !name.startsWith(".");
            	}
            });
			for (File inputFile : files) {
                String inputExtension = FilenameUtils.getExtension(inputFile.getName());
                DocumentFormat inputFormat = formatRegistry.getFormatByExtension(inputExtension);
                assertNotNull(inputFormat, "unknown input format: " + inputExtension);
                Set<DocumentFormat> outputFormats = formatRegistry.getOutputFormats(inputFormat.getInputFamily());
                for (DocumentFormat outputFormat : outputFormats) {
                    File outputFile = File.createTempFile("test", "." + outputFormat.getExtension());
                    outputFile.deleteOnExit();
                    System.out.printf("-- converting %s to %s... ", inputFormat.getExtension(), outputFormat.getExtension());
                    converter.convert(inputFile, outputFile, outputFormat);
                    System.out.printf("done.\n");
                    assertTrue(outputFile.isFile() && outputFile.length() > 0);
                    //TODO use file detection to make sure outputFile is in the expected format
                }
            }
        } finally {
            officeManager.stop();
        }
    }

}
