module dal {
    exports MySQL;
    exports Sqlite;

    requires logic;
    requires java.sql;
    requires mysql.connector.java;
    requires sqlite.jdbc;
}