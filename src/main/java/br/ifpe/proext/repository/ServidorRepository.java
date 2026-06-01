package br.ifpe.proext.repository;

import br.ifpe.proext.model.Servidor;

import java.util.ArrayList;
import java.util.List;

public class ServidorRepository {

    private static final List<Servidor> servidores = new ArrayList<>();

    private ServidorRepository(){}

    public static void criarServidor(Servidor servidor){
        servidores.add(servidor);
    }

    public static Servidor buscarPorCpf(String cpf){
        for (Servidor servidor: servidores) {
            if(servidor.getCpf().equals(cpf)){
                return servidor;
            }
        }
        return null;
    }

    public static Servidor buscarPorEmail(String email){
        for (Servidor servidor: servidores) {
            if(servidor.getEmail().equals(email)){
                return servidor;
            }
        }
        return null;
    }

    public static void atualizarServidor(Servidor servidor) {

        for (Servidor servidorAux : servidores) {

            if (servidorAux.getCpf().equals(servidor.getCpf())) {

                servidorAux.setNome(servidor.getNome());
                servidorAux.setNomeSocial(servidor.getNomeSocial());
                servidorAux.setEmail(servidor.getEmail());
                servidorAux.setSenhaHash(servidor.getSenhaHash());
                servidorAux.setTelefone(servidor.getTelefone());
                servidorAux.setCampus(servidor.getCampus());
                servidorAux.setAreaFormacao(servidor.getAreaFormacao());
                servidorAux.setTitulacao(servidor.getTitulacao());
                servidorAux.setSexo(servidor.getSexo());
                servidorAux.setLinkLattes(servidor.getLinkLattes());

                return;
            }
        }
    }

    public static boolean removerServidor(String cpf) {
        return servidores.removeIf(
                servidor -> servidor.getCpf().equals(cpf)
        );
    }


    public static List<Servidor> listarTodos(){
        return new ArrayList<>(servidores); //retornando uma cópia da lista atual de servidores
    }

}
