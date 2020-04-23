package com.kdenisov.swingy.controller;

import com.kdenisov.swingy.view.GUIRenderer;
import com.kdenisov.swingy.view.Renderer;
import com.kdenisov.swingy.view.CLIRenderer;

public class Swingy {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("usage: java -jar swingy.jar [console/gui]");
            System.exit(1);
        }

        HibernateManager hibernateManager = HibernateManager.getHibernateManager();

        Renderer renderer = null;
        if (args[0].equals("gui")) {
            renderer = new GUIRenderer(hibernateManager);
        } else if (args[0].equals("console")) {
            renderer = new CLIRenderer(hibernateManager);
        }
        else {
            System.out.println("usage: java -jar swingy.jar [console/gui]");
            System.exit(1);
        }

        renderer.renderMenu();
    }
}
