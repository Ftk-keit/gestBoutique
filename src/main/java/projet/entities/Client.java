package projet.entities;

import lombok.Data;

@Data
public class Client {
    private int id;
    private String telephone;
    private String surnom;
    private String adresse;
    private static int nb = 1;
    private User user;
    public Client() {
        id = nb++;
    }
}
