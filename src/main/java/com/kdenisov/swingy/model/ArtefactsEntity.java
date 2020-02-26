package com.kdenisov.swingy.model;

import javax.persistence.*;

@Entity
@Table(name = "ARTEFACTS", schema = "swingy")
public class ArtefactsEntity {
    private int id;
    private int heroId;
    private Artefact artefact;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "int default 1")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "HERO_ID")
    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    @Basic
    @Column(name = "TYPE")
    public Artefact getArtefact() {
        return artefact;
    }

    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtefactsEntity that = (ArtefactsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
