package org.example.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Versão da classe para serialização
    private String name;
    private String email;
    private String address;
    private Plan subscriptionPlan;

    public User(String name, String email, String address, Plan subscriptionPlan) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.subscriptionPlan = subscriptionPlan;   
    }

    public User(){
        this.name = "";
        this.email = "";
        this.address = "";
        //this.subscriptionPlan = new Plan();
    }

    public User (User user) {
        this.name = user.name;
        this.email = user.email;
        this.address = user.address;
        this.subscriptionPlan = user.subscriptionPlan;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Plan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(Plan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", subscriptionPlan=" + subscriptionPlan.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                email.equals(user.email) &&
                address.equals(user.address) &&
                subscriptionPlan.equals(user.subscriptionPlan);
    }

    @Override
    public User clone() {
        return new User(this.name, this.email, this.address, this.subscriptionPlan);
    }

/*    public boolean canCreatePlaylists() {
        return subscriptionPlan instanceof PremiumBasePlan || subscriptionPlan instanceof PremiumTopPlan;
    }
*/

    public boolean isPremium() {
        return subscriptionPlan instanceof PlanPremiumBase || subscriptionPlan instanceof PlanPremiumTop;
    }

        public static void saveUsers(List<User> users, String filePath) {
            System.out.println("entrei\n");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(users);
                System.out.println("Utilizadores salvos no arquivo: " + filePath);
            } catch (IOException e) {
                System.err.println("Erro ao salvar utilizadores: " + e.getMessage());
            }
        }

    public static List<User> loadUsers(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar utilizadores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
