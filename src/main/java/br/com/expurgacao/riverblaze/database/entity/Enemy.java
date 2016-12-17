package br.com.expurgacao.riverblaze.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Nysio on 07/02/2016.
 */
@DatabaseTable(tableName = "enemy")
public class Enemy {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
