package br.ifpe.proext.model;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.Sexo;
import br.ifpe.proext.enums.Titulacao;

import java.util.HashSet;
import java.util.Set;

public class Servidor {

    private String nome;
    private String nomeSocial;
    private String cpf;
    private String email;
    private String senhaHash;
    private String telefone;
    private String linkLattes;
    private String campus;
    private String areaFormacao;
    private Titulacao titulacao;
    private Sexo sexo;
    private Set<Perfil> perfis = new HashSet<>();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLinkLattes() {
        return linkLattes;
    }

    public void setLinkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getAreaFormacao() {
        return areaFormacao;
    }

    public void setAreaFormacao(String areaFormacao) {
        this.areaFormacao = areaFormacao;
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    // Construtor padrão utilizado na criação do objeto Servidor

    public Servidor (){}

}
