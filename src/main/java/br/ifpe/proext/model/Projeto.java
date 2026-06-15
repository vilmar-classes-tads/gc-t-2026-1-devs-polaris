package br.ifpe.proext.model;

import br.ifpe.proext.enums.ODS;
import br.ifpe.proext.enums.StatusProjeto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Projeto {
    private String titulo;
    private String resumo;
    private ArrayList<String> palavrasChave = new ArrayList<>();
    private String publicoAlvo;
    private String areaTematica;
    private String campus;
    private StatusProjeto status = StatusProjeto.RASCUNHO;
    private Set<ODS> ods = new LinkedHashSet<>();
    private boolean termoAceito;
    private Servidor coordenadorAceite;

    public Projeto(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public ArrayList<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(ArrayList<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public Set<ODS> getOds() {
        return ods;
    }

    public void setOds(Set<ODS> ods) {
        this.ods = (ods == null) ? new LinkedHashSet<>() : new LinkedHashSet<>(ods);
    }

    public boolean isTermoAceito() {
        return termoAceito;
    }

    public void setTermoAceito(boolean termoAceito) {
        this.termoAceito = termoAceito;
    }

    public Servidor getCoordenadorAceite() {
        return coordenadorAceite;
    }

    public void setCoordenadorAceite(Servidor coordenadorAceite) {
        this.coordenadorAceite = coordenadorAceite;
    }
}
