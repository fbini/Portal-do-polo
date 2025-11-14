package br.com.polo.cesmar.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do PostgreSQL não encontrado");
            throw new RuntimeException("Falha ao carregar o driver JDBC", e);
        }
    }

    public static Connection getConnetion() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/vagas";
        String user = "postgres";
        String password = "bini";

        Connection conexao = DriverManager.getConnection(url, user, password);
        return conexao;
    }

}

