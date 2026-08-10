package br.ifpe.proext.model;

import br.ifpe.proext.enums.StatusProjeto;

public class FiltroProjeto {
    private Edital edital;
    private String campus;
    private String areaTematica;
    private StatusProjeto status;

    public FiltroProjeto() {}

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }
}
