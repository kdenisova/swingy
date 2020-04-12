package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.HibernateManager;

import java.io.IOException;

public interface Renderer {
    void renderMenu(HibernateManager hibernateManager) throws IOException;
    void renderHero();
    void renderVillain();
}
