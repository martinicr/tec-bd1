package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;
import tec.bd.gui.MainFrame;

@CommandLine.Command(name = "gui", description = "Open App in GUI mode")
public class GUICommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @Override
    public void run() {
        System.out.println("GUI");

        new MainFrame();
    }
}
