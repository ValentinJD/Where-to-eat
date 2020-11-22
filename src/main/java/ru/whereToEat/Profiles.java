package ru.whereToEat;

public class Profiles {
    public static final String
            JDBC = "jdbc",
            JPA = "jpa";

    public static final String REPOSITORY_IMPLEMENTATION = JPA;

    public static final String
            POSTGRES_DB = "postgres2020",
            HSQL_DB = "hsqldb2020";

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }
}
