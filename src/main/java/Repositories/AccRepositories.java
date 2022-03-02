package Repositories;



import Entities.Account;
import MyConnection.SessionFactorySingleton;
import lombok.var;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.query.Query;
import org.hibernate.search.sandbox.standalone.FullTextQuery;


import java.util.List;

public class AccRepositories implements CRUD <Account,String> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public String create(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                System.out.println(account);
                return account.getAccId();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Account readById(String accId) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Account.class, accId);
        }
    }

    @Override
    public List<Account> readAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Account ");
        List<Account> accounts = (List<Account>) q.getResultList();

return accounts;
    }

    @Override
    public void update(Account account) {

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public void delete(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
