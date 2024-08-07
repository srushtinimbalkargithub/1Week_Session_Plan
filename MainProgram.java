package myPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Client class to hold client details
class Client {
    private int id;
    private String name;
    private String email;

    public Client(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Client [ID=" + id + ", Name=" + name + ", Email=" + email + "]";
    }
}

// ClientManager class to manage the list of clients
class ClientManager {
    private List<Client> clients;

    public ClientManager() {
        clients = new ArrayList<>();
    }

    // Add a new client to the list
    public void addClient(int id, String name, String email) {
        clients.add(new Client(id, name, email));
        System.out.println("Client added: " + name);
    }

    // Remove a client from the list using their ID
    public void removeClient(int id) {
        clients.removeIf(client -> client.getId() == id);
        System.out.println("Client removed with ID: " + id);
    }

    // Search for a client by their ID and return their details
    public Client searchClient(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    // Display all clients in the list
    public void listAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    // Sort the clients alphabetically by their name
    public void sortClientsByName() {
        Collections.sort(clients, Comparator.comparing(Client::getName));
        System.out.println("Clients sorted by name.");
    }

    // Sort the clients numerically by their ID
    public void sortClientsById() {
        Collections.sort(clients, Comparator.comparingInt(Client::getId));
        System.out.println("Clients sorted by ID.");
    }
}

// Main class to demonstrate the functionality
public class MainProgram {
    public static void main(String[] args) {
        ClientManager manager = new ClientManager();
        
        // Adding clients
        manager.addClient(101, "Alice", "alice@example.com");
        manager.addClient(102, "Bob", "bob@example.com");
        manager.addClient(103, "Charlie", "charlie@example.com");
        
        // Listing all clients
        System.out.println("\nAll Clients:");
        manager.listAllClients();
        
        // Searching for a client
        System.out.println("\nSearching for client with ID 102:");
        Client client = manager.searchClient(102);
        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("Client not found.");
        }

        // Removing a client
        manager.removeClient(101);
        
        // Listing all clients after removal
        System.out.println("\nAll Clients after removal:");
        manager.listAllClients();
        
        // Sorting clients by name
        manager.sortClientsByName();
        System.out.println("\nAll Clients sorted by name:");
        manager.listAllClients();
        
        // Sorting clients by ID
        manager.sortClientsById();
        System.out.println("\nAll Clients sorted by ID:");
        manager.listAllClients();
    }
}
