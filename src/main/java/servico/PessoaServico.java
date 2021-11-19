package servico;

import dominio.Pessoa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaServico {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public Pessoa buscaPorCPF(String cpf){
        List listaPessoa;

        try{
            transaction.begin();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM comum.pessoa ")
                    .append("WHERE cpf = '").append(cpf).append("'");

            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Pessoa.class);
            listaPessoa = query.getResultList();
            transaction.commit();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }

        if(listaPessoa.isEmpty()){
            return null;
        }
        return (Pessoa) (listaPessoa.get(0));
    }

    public boolean existePessoa(String cpf){
        return buscaPorCPF(cpf) != null;
    }

    public boolean inserir(Pessoa pessoa){
        boolean sucesso;
        try{
            transaction.begin();
            entityManager.persist(pessoa);
            transaction.commit();
            sucesso = true;
        }finally{
            if (transaction.isActive()){
                transaction.rollback();
                sucesso = false;
            }
        }
        return sucesso;
    }

    public boolean altera(Pessoa pessoa){
        boolean sucesso;
        try{
            transaction.begin();
            entityManager.merge(pessoa);
            transaction.commit();
            sucesso = true;
        }finally{
            if (transaction.isActive()){
                transaction.rollback();
                sucesso = false;
            }
        }
        return sucesso;
    }

    public boolean remover(Pessoa pessoa){
        boolean sucesso;
        try {
            transaction.begin();
            entityManager.remove(pessoa);
            transaction.commit();
            sucesso = true;
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
                sucesso = false;
            }
        }
        return sucesso;
    }

    public ArrayList<Pessoa> getPessoas(){
        ArrayList<Pessoa> lista;
        try {
            transaction.begin();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM comum.pessoa ");
            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Pessoa.class);
            lista = (ArrayList<Pessoa>) query.getResultList();
            transaction.commit();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
        return lista;
    }

    public void fechaEntidades(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
