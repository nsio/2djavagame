package br.com.expurgacao.riverblaze.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Nysio on 07/02/2016.
 */
@DatabaseTable(tableName = "checkpoint")
public class CheckPoint {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private Integer posCheckPoint;
    @DatabaseField
    private String nome;
    @DatabaseField(foreign = true)
    private Level level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosCheckPoint() {
        return posCheckPoint;
    }

    public void setPosCheckPoint(Integer posCheckPoint) {
        this.posCheckPoint = posCheckPoint;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
