package servico;

import dominio.Aluno;

import javax.persistence.*;
import java.util.ArrayList;

public class AlunoServico {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

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

    public void fechaEntidades(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
