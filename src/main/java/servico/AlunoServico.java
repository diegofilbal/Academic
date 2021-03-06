package servico;

import dominio.Aluno;
import dominio.Pessoa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoServico {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public Aluno buscaPorMatricula(String matricula){
        List listaAluno;

        try{
            transaction.begin();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM graduacao.aluno ")
                    .append("WHERE matricula = '").append(matricula).append("'");

            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Aluno.class);
            listaAluno = query.getResultList();
            transaction.commit();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }

        if(listaAluno.isEmpty()){
            return null;
        }
        return (Aluno) (listaAluno.get(0));
    }

    public boolean existeAluno(String matricula){
        return buscaPorMatricula(matricula) != null;
    }

    public boolean inserir(Aluno aluno){
        boolean sucesso;
        try{
            transaction.begin();
            entityManager.persist(aluno);
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

    public boolean altera(Aluno aluno){
        boolean sucesso;
        try{
            transaction.begin();
            entityManager.merge(aluno);
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

    public boolean remover(Aluno aluno){
        boolean sucesso;
        try {
            transaction.begin();
            entityManager.remove(aluno);
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

    public ArrayList<Aluno> getAlunos(){
        ArrayList<Aluno> lista;
        try {
            transaction.begin();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM graduacao.aluno ");
            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Aluno.class);
            lista = (ArrayList<Aluno>) query.getResultList();
            transaction.commit();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
        return lista;
    }

    public ArrayList<Aluno> getAlunosPorPessoa(Pessoa pessoa){
        ArrayList<Aluno> lista;
        try {
            transaction.begin();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM graduacao.aluno ")
                    .append("WHERE id_pessoa = '").append(pessoa.getId()).append("'");
            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Aluno.class);
            lista = (ArrayList<Aluno>) query.getResultList();
            transaction.commit();
        }finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
        return lista;
    }

    public void atualizaAlunos(Pessoa pessoa){
        pessoa.setAlunos(getAlunosPorPessoa(pessoa));
    }

    public void fechaEntidades(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
