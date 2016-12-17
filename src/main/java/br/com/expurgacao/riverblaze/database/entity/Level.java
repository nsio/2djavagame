package br.com.expurgacao.riverblaze.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Nysio on 07/02/2016.
 */
@DatabaseTable(tableName = "level")
public class Level {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private Integer indice;
    @DatabaseField
    private Integer largura;
    @DatabaseField
    private Integer altura;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public Integer getIndice() {
        return indice;
    }
    public void setIndice(Integer indice) {
        this.indice = indice;
    }
    public Integer getLargura() {
        return largura;
    }
    public void setLargura(Integer largura) {
        this.largura = largura;
    }
    public Integer getAltura() {
        return altura;
    }
    public void setAltura(Integer altura) {
        this.altura = altura;
    }
}
