package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.HibernateManager;

import java.io.IOException;

public interface Renderer {
    void renderMenu(HibernateManager hibernateManager) throws IOException;
}
