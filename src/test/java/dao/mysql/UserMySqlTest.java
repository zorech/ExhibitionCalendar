package dao.mysql;

import entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class UserMySqlTest {

    private Connection connection;
    private static final ResourceBundle QUERIES_MY_SQL_TEST = ResourceBundle
            .getBundle("QueriesMySql_Test");
    private static final ResourceBundle DB_TEST = ResourceBundle.getBundle("MySqlDB_TEST");

    @Before
    public void setUp() throws Exception {
        Class.forName(DB_TEST.getString("mysql_test.driver")).newInstance();
        connection = DriverManager.getConnection(prepareURL(), DB_TEST.getString("mysql_test.username"),
                DB_TEST.getString("mysql_test.password"));
        crateTestTables();
    }

    @After
    public void tearDown() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DB_TEST.getString("mysql_test.drop_all_test_tables"));
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }

        closeConnection();
    }

    @Test
    public void getByIdTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        User user = new User().emptyUser();
        user.setId(1);

        try {
            // insert user to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // get user by id
            User expectedUser = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getById(1);

            assertEquals(user, expectedUser);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void getByNameTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        String userName = "name";
        User user = new User().emptyUser();
        user.setName(userName);

        try {
            // insert user to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // get by name
            User expectedUser = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getByName(userName);

            assertEquals(user, expectedUser);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void getByMailTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        String userMail = "mail@mail";
        User user = new User().emptyUser();
        user.setMail(userMail);

        try {
            // insert user to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // get by mail
            User expectedUser = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getByMail(userMail);
            assertEquals(user, expectedUser);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void getAllUsersTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);
            user.setMail("2");
            user.setName("2");
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // get all users
            List<User> expectedUsers = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getAllUsers();
            assertEquals(2, expectedUsers.size());

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void updateUserTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // change user
            user.setName("new name");
            // update user
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .updateUser(user);

            User expectedUser = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getById(1);

            assertEquals(user, expectedUser);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void insertUserTest() {
        FactoryMySql factoryMySql = new FactoryMySql();

        User user = new User().emptyUser();

        try {
            // insert user to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            User expectedUser = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getById(1);
            assertEquals(user, expectedUser);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void deleteUserTest() {
        FactoryMySql factoryMySql = new FactoryMySql();
        String userMailDelete = "delte@mail";

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);
            user.setMail(userMailDelete);
            user.setName("2");
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            // delete user
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .deleteUser(userMailDelete);

            // get all users
            List<User> expectedUsers = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .getAllUsers();
            assertEquals(1, expectedUsers.size());

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void isNameInTableTest() {
        FactoryMySql factoryMySql = new FactoryMySql();
        String userNameSearch = "nameSearch";
        String userNameNotInTable = "bad name";

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);
            user.setMail("2");
            user.setName(userNameSearch);
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            boolean lookingRealName = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isNameInTable(userNameSearch);
            boolean lookingMissedName = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isNameInTable(userNameNotInTable);

            assertTrue(lookingRealName);
            assertFalse(lookingMissedName);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void isMailInTableTest() {
        FactoryMySql factoryMySql = new FactoryMySql();
        String userMailSearch = "mail@Search";
        String userMailNotInTable = "bad@mail";

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);
            user.setMail(userMailSearch);
            user.setName("2");
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            boolean lookingRealMail = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isMailInTable(userMailSearch);
            boolean lookingMissedMail = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isMailInTable(userMailNotInTable);

            assertTrue(lookingRealMail);
            assertFalse(lookingMissedMail);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void isNameOrMailInTableTest() {
        FactoryMySql factoryMySql = new FactoryMySql();
        String userMailSearch = "mail@Search";
        String userNameSearch = "coolName";
        String userMailNotInTable = "bad@mail";

        User user = new User().emptyUser();

        try {
            // insert users to table
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);
            user.setMail(userMailSearch);
            user.setName(userNameSearch);
            factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .insertUser(user);

            boolean lookingRealMail = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isNameOrMailInTable(userMailSearch);
            boolean lookingRealName = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isNameOrMailInTable(userMailSearch);
            boolean lookingMissedMail = factoryMySql.createUser(connection, QUERIES_MY_SQL_TEST)
                    .isNameOrMailInTable(userMailNotInTable);

            assertTrue(lookingRealMail);
            assertTrue(lookingRealName);
            assertFalse(lookingMissedMail);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    private void crateTestTables() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_exhibition_center"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_exhibition_center_phone"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_exhibition"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_description"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_exhibition_contract"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_user"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_user_phone"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_role"));
            statement.executeUpdate(DB_TEST.getString("mysql_test.create_tables_ticket"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String prepareURL() {
        StringBuilder buildUrl = new StringBuilder();
        buildUrl.append(DB_TEST.getString("mysql_test.url")).append("&");
        buildUrl.append(DB_TEST.getString("mysql_test.useJDBCCompliantTimezoneShift")).append("&");
        buildUrl.append(DB_TEST.getString("mysql_test.useLegacyDatetimeCode")).append("&");
        buildUrl.append(DB_TEST.getString("mysql_test.serverTimeZone")).append("&");
        buildUrl.append(DB_TEST.getString("mysql_test.autoReconnect")).append("&");
        buildUrl.append(DB_TEST.getString("mysql_test.autoReconnect")).append("&");

        buildUrl.append(DB_TEST.getString("mysql_test.useSSL"));
        return buildUrl.toString();
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

}