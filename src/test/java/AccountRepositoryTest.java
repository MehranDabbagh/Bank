import Entities.Account;
import Entities.Status;
import MyConnection.PostgresConnection;
import MyConnection.SessionFactorySingleton;
import Repositories.AccRepositories;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountRepositoryTest {
    private AccRepositories accRepositories= new AccRepositories();;
    private Account account;
    @BeforeAll
    public static void setup() {
        SessionFactory sessionFactory= SessionFactorySingleton.getInstance();

    }
    @BeforeEach
    public void beforeEach()  {

    }
    @AfterEach
    public void cleanUp()  {
        List<Account> accountList=accRepositories.readAll();
        if(accountList!=null) {
            for (Account account1 : accountList
            ) {
                accRepositories.delete(account1);
            }
        }
    }
    @Test
    public void CreateTest() {
        // Arrange -->
        Account account = new Account("asdadw", Status.OPEN,"asd",15,"swea",0,"adwa");

        // Act
        String accId = accRepositories.create(account);

        // Assert
        Account loadedAccount = accRepositories.readById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedAccount),
                () -> assertEquals(accId, loadedAccount.getAccId()),
                () -> assertEquals("asd", loadedAccount.getPassword()),
                () ->  assertEquals(15,loadedAccount.getAmount()),
                () ->  assertEquals("swea",loadedAccount.getBranchName()),
                () ->  assertEquals(0,loadedAccount.getFoul()),
                () ->  assertEquals("adwa",loadedAccount.getUserNationalCode())
        );
    }
    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        accRepositories.create(new Account("asdadw", Status.OPEN,"asd",15,"swea",0,"adwa"));
        accRepositories.create(new Account("asdadwa", Status.OPEN,"asd",15,"swea",0,"adwa"));
        accRepositories.create(new Account("asdadww", Status.OPEN,"asd",15,"swea",0,"adwa"));

        // Act
        List<Account> accounts = accRepositories.readAll();

        // Assert
        assertThat(accounts).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        String accId = accRepositories.create( new Account("asdadw", Status.OPEN,"asd",15,"swea",0,"adwa"));
        Account account= accRepositories.readById(accId);
        // Act
        account.setUserNationalCode("newOne");
        accRepositories.update(account);
        // Assert
        Account loadedAccount = accRepositories.readById(accId);
        assertThat(loadedAccount.getUserNationalCode()).isEqualTo("newOne");
        assertThat(loadedAccount.getAccId()).isEqualTo(accId);
    }
    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Account account = new Account("asdadw", Status.OPEN,"asd",15,"swea",0,"adwa");
        accRepositories.create(account);
        // Act
        accRepositories.delete(account);
        // Assert
        assertThat(accRepositories.readAll()).isEmpty();
    }


}
