package com.kdenisov.swingy.model;

import javax.persistence.*;

@Entity
@Table(name = "VILLAIN", schema = "swingy", catalog = "")
public class VillainEntity {
    private int id;
    private String name;
    private Integer level;
    private Integer attack;
    private Integer defense;
    private Integer hitPoints;

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
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "Attack")
    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Basic
    @Column(name = "Defense")
    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    @Basic
    @Column(name = "HitPoints")
    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VillainEntity that = (VillainEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (attack != null ? !attack.equals(that.attack) : that.attack != null) return false;
        if (defense != null ? !defense.equals(that.defense) : that.defense != null) return false;
        if (hitPoints != null ? !hitPoints.equals(that.hitPoints) : that.hitPoints != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (attack != null ? attack.hashCode() : 0);
        result = 31 * result + (defense != null ? defense.hashCode() : 0);
        result = 31 * result + (hitPoints != null ? hitPoints.hashCode() : 0);
        return result;
    }
}
