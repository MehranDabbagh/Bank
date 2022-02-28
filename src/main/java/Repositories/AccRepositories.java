package Repositories;



import Entities.Account;
import MyConnection.SessionFactorySingleton;
import lombok.var;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;

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
                return account.getAccId();
            } catch (Exception e) {
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
