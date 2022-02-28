package Repositories;



import Entities.Account;
import MyConnection.SessionFactorySingleton;
import lombok.var;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;

import java.util.List;

public class AccRepositories implements CRUD <Account,Long> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public Long create(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account.getID();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Account readById(Long id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Account.class, id);
        }
    }

    @Override
    public List<Account> readAll() {
        try{
            List<Account> accounts;
            SharedSessionContract session = null;
            org.hibernate.Transaction tx = session.beginTransaction();
            accounts = session.createSQLQuery("SELECT * FROM accs").list();
            if(accounts.size() > 0)
            {
                return accounts;
            }
            return null;
        }
        catch(Exception e)
        {
            throw e;
        }
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
