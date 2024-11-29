import java.util.Scanner;

public class GestionStock {
    private static Produit[] produits = new Produit[100]; // Tableau de produits
    private static int compteur = 0; // Nombre actuel de produits dans le tableau

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    modifierProduit(scanner);
                    break;
                case 3:
                    supprimerProduit(scanner);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercherProduit(scanner);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);

        scanner.close();
    }

    // Affichage du menu
    public static void printMenu() {
        System.out.println("\n--- Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option: ");
    }

    // Ajouter un produit
    public static void ajouterProduit(Scanner scanner) {
        if (compteur >= produits.length) {
            System.out.println("Erreur : Capacité maximale atteinte !");
            return;
        }

        System.out.print("Entrez le code produit : ");
        int code = scanner.nextInt();

        // Vérification de l'unicité du code
        for (Produit p : produits) {
            if (p != null && p.getCode() == code) {
                System.out.println("Erreur : Un produit avec ce code existe déjà.");
                return;
            }
        }

        scanner.nextLine(); // Consommer la ligne restante
        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez la quantité : ");
        int quantite = scanner.nextInt();

        System.out.print("Entrez le prix : ");
        double prix = scanner.nextDouble();

        if (quantite < 0 || prix < 0) {
            System.out.println("Erreur : La quantité et le prix doivent être positifs.");
            return;
        }

        produits[compteur++] = new Produit(code, nom, quantite, prix);
        System.out.println("Produit ajouté avec succès !");
    }

    // Modifier un produit
    public static void modifierProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à modifier : ");
        int code = scanner.nextInt();

        for (int i = 0; i < compteur; i++) {
            if (produits[i].getCode() == code) {
                scanner.nextLine(); // Consommer la ligne restante
                System.out.print("Entrez le nouveau nom : ");
                String nouveauNom = scanner.nextLine();

                System.out.print("Entrez la nouvelle quantité : ");
                int nouvelleQuantite = scanner.nextInt();

                System.out.print("Entrez le nouveau prix : ");
                double nouveauPrix = scanner.nextDouble();

                produits[i].setNom(nouveauNom);
                produits[i].setQuantite(nouvelleQuantite);
                produits[i].setPrix(nouveauPrix);

                System.out.println("Produit modifié avec succès !");
                return;
            }
        }

        System.out.println("Erreur : Aucun produit trouvé avec ce code.");
    }

    // Supprimer un produit
    public static void supprimerProduit(Scanner scanner) {
        System.out.print("Entrez le code du produit à supprimer : ");
        int code = scanner.nextInt();

        for (int i = 0; i < compteur; i++) {
            if (produits[i].getCode() == code) {
                produits[i] = produits[--compteur];
                produits[compteur] = null;
                System.out.println("Produit supprimé avec succès !");
                return;
            }
        }

        System.out.println("Erreur : Aucun produit trouvé avec ce code.");
    }

    // Afficher tous les produits
    public static void afficherProduits() {
        if (compteur == 0) {
            System.out.println("Aucun produit en stock.");
        } else {
            System.out.println("--- Liste des produits ---");
            for (int i = 0; i < compteur; i++) {
                System.out.println(produits[i]);
            }
        }
    }

    // Rechercher un produit
    public static void rechercherProduit(Scanner scanner) {
        scanner.nextLine(); // Consommer la ligne restante
        System.out.print("Entrez le nom du produit à rechercher : ");
        String nom = scanner.nextLine();

        for (Produit p : produits) {
            if (p != null && p.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Produit trouvé : " + p);
                return;
            }
        }

        System.out.println("Erreur : Aucun produit trouvé avec ce nom.");
    }

    // Calculer la valeur totale du stock
    public static void calculerValeurStock() {
        double valeurTotale = 0;

        for (Produit p : produits) {
            if (p != null) {
                valeurTotale += p.calculerValeur();
            }
        }

        System.out.println("Valeur totale du stock : " + valeurTotale);
    }
}
