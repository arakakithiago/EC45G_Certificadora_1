/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author kaki
 */
public class Tarefa {

    private int id;
    private String nome;
    private Date datahora;
    private boolean concluida;
    private int idcategoria;

    public Tarefa() {
        this.concluida = false; // Valor padrão para novas tarefas
    }

    public Tarefa(int id, String nome, Date datahora, boolean concluida, int idcategoria) {
        this.id = id;
        this.nome = nome;
        this.datahora = datahora;
        this.concluida = concluida;
        this.idcategoria = idcategoria;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
