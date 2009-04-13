package net.sf.jodconverter.office;

import java.io.File;

import net.sf.jodconverter.process.ProcessManager;
import net.sf.jodconverter.process.PureJavaProcessManager;

class OfficeProcessConfiguration {

    private final OfficeConnectionMode connectionMode;

    private File officeHome = OfficeUtils.getDefaultOfficeHome();
    private File templateProfileDir;
    private ProcessManager processManager = new PureJavaProcessManager();

    public OfficeProcessConfiguration(OfficeConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }

    public OfficeConnectionMode getConnectionMode() {
        return connectionMode;
    }

    public File getOfficeHome() {
        return officeHome;
    }

    public void setOfficeHome(File officeHome) {
        this.officeHome = officeHome;
    }

    public File getTemplateProfileDir() {
        return templateProfileDir;
    }

    public void setTemplateProfileDir(File templateProfileDir) {
        this.templateProfileDir = templateProfileDir;
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    public void setProcessManager(ProcessManager processManager) {
        this.processManager = processManager;
    }

}
