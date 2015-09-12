package ro.polak.webserver.controller;

import ro.polak.utilities.Utilities;
import ro.polak.webserver.WebServer;
import ro.polak.webserver.gui.*;

/**
 * The main controller of the server
 *
 * @author Piotr Polak piotr [at] polak [dot] ro
 * @version 201509
 * @since 2001012
 */
public class MainController implements IController {

    private WebServer httpServer;
    private IServerUI gui;
    private Object context;
    private static MainController instance;

    /**
     * Controller made invisible
     */
    private MainController() {

    }

    /**
     * Singletone method
     *
     * @return
     */
    public static MainController getInstance() {
        if (MainController.instance == null) {
            MainController.instance = new MainController();
        }

        return MainController.instance;
    }

    /**
     * Sets server GUI
     *
     * @param gui
     */
    public void setGui(IServerUI gui) {
        this.gui = gui;
    }

    /**
     * Prints the log line into the specific output
     *
     * @param text
     */
    public void println(String text) {
        this.gui.println(WebServer.sdf.format(new java.util.Date()) + "  -  " + text);
    }

    /**
     * Starts the server logic
     */
    public void start() {
        this.gui.initialize(this);
        this.httpServer = new WebServer(this);

        if (this.httpServer.startServer()) {
            this.gui.start();
        } else {
            this.gui.stop();
        }
        Utilities.clearTemp();
    }

    /**
     * Stops the server logic
     */
    public void stop() {
        if (this.httpServer != null) {
            this.httpServer.stopServer();
            this.httpServer = null;
        }
        this.gui.stop();
    }

    /**
     * Returns the server thread
     *
     * @return
     */
    public WebServer getServer() {
        return this.httpServer;
    }

    /**
     * Returns application context, this is mostly used for android applications
     *
     * @return
     */
    public Object getContext() {
        return context;
    }

    /**
     * Sets application context, this is mostly used for android applications
     *
     * @param context
     */
    public void setContext(Object context) {
        this.context = context;
    }
}