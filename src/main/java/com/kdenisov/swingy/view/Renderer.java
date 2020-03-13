package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.HibernateManager;

public interface Renderer {
    void renderMenu(HibernateManager hibernateManager);
    void renderHero();
    void renderVillain();
}
