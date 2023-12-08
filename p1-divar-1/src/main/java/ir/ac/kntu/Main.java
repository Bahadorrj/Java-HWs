package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String> usernames = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Seller> sellers = new ArrayList<>();
        ArrayList<Buyer> buyers = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();

        admins.add(new Admin("bahador", "Bahador12345"));
        sellers.add(new Seller(new Profile("aryan", "Aryan12345", "aryan@gamil.com", "09121234567"), 0));
        sellers.add(new Seller(new Profile("mershad", "Mershad12345", "mershad@gamil.com", "09121234567"), 0));
        sellers.add(new Seller(new Profile("erfan", "Erfan12345", "erfan@gamil.com", "09121234567"), 0));
        sellers.add(new Seller(new Profile("sardar", "Sardar12345", "sardar@gamil.com", "09121234567"), 0));
        buyers.add(new Buyer(new Profile("sarah", "Sarah12345", "sarah@gamil.com", "09121234567"), 0));
        buyers.add(new Buyer(new Profile("pariya", "Pariya12345", "pariya@gamil.com", "09121234567"), 0));
        buyers.add(new Buyer(new Profile("elin", "Elin12345", "elin@gamil.com", "09121234567"), 0));
        posts.add(new Post("post1", 12.25, sellers.get(0), "phone", 1));
        posts.add(new Post("post2", 100, sellers.get(3), "phone", 1));
        posts.add(new Post("post3", 55, sellers.get(2), "home", 0));
        posts.add(new Post("post4", 123.5, sellers.get(1), "stationary", -1));
        usernames.addAll(Arrays.asList("bahador", "aryan", "mershad", "erfan", "sardar", "sarah", "pariya", "elin"));

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Divar!");
        System.out.println("Please note that you can press x to escape every steps and get back to the previous menu");
        boolean running = true;
        while (running) {
            System.out.println("Please choose the user type you want.");
            System.out.println("1. Admin\n2. Seller\n3. Buyer\n4. Guest");
            char user = sc.next().charAt(0);
            // ADMIN
            if (user == '1') {
                int index = -1;
                System.out.print("username: ");
                String username = sc.next();
                if (username.equals("x")) {
                    continue;
                }
                String password = sc.next();
                if (password.equals("x")) {
                    continue;
                }
                for (Admin a : admins) {
                    if (a.getUsername().equals(username)) {
                        index = admins.indexOf(a);
                        break;
                    }
                }
                boolean inAdmin = false;
                if (index != -1) {
                    Admin a = admins.get(index);
                    System.out.print("password: ");
                    if (a.getPassword().equals(password)) {
                        inAdmin = true;
                        System.out.println("Signed in successfully!");
                    } else {
                        System.out.println("Invalid login or password!");
                    }
                } else {
                    System.out.println("Invalid login or password!");
                }
                while (inAdmin) {
                    System.out.println("Choose action:");
                    System.out.println("1. Requests\n2. All ads\n3. Sellers\n4. Buyers\n5. Sign out");
                    char action = sc.next().charAt(0);
                    //REQUESTS
                    if (action == '1') {
                        for (Post p : posts) {
                            System.out.println(posts.indexOf(p) + ": " + p.toString());
                        }
                        boolean inRequest = true;
                        while (inRequest) {
                            System.out.println("Choose action:");
                            System.out.println("1. Approve\n2. Disapprove\n3. Back");
                            char innerAction = sc.next().charAt(0);
                            if (innerAction == '1') {
                                System.out.println("Index of post you want to approve: ");
                                char postAction = sc.next().charAt(0);
                                if (postAction == 'x') {
                                    continue;
                                }
                                int postIndex = Character.getNumericValue(postAction);
                                System.out.println(postIndex);
                                if (postIndex < posts.size() && postIndex >= 0) {
                                    Post selected = posts.get(postIndex);
                                    if (selected.isApproved() == 2) {
                                        System.out.println("This post had been approved before!");
                                    } else {
                                        selected.setApproved(2);
                                        System.out.println("Approved " + selected.getName());
                                        inRequest = false;
                                    }
                                } else {
                                    System.out.println("Out of range!");
                                }
                            } else if (innerAction == '2') {
                                System.out.println("Index of post you want to disapprove: ");
                                char postAction = sc.next().charAt(0);
                                if (postAction == 'x') {
                                    continue;
                                }
                                int postIndex = Character.getNumericValue(postAction);
                                if (postIndex < posts.size() && postIndex >= 0) {
                                    Post selected = posts.get(postIndex);
                                    if (selected.isApproved() == 0) {
                                        System.out.println("This post is not approved!");
                                    } else {
                                        selected.setApproved(0);
                                        System.out.println("Disapproved " + selected.getName());
                                        inRequest = false;
                                    }
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            } else if (innerAction == '3' || innerAction == 'x') {
                                inRequest = false;
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    //ALL ADS
                    else if (action == '2') {
                        boolean inAllAds = true;
                        while (inAllAds) {
                            for (Post p : posts) {
                                System.out.println(posts.indexOf(p) + "-: " + p.toString());
                            }
                            System.out.println("Choose action:");
                            System.out.println("1. Delete\n2. Back");
                            char innerAction = sc.next().charAt(0);
                            if (innerAction == '1') {
                                System.out.println("Index of the post you want to delete: ");
                                char postAction = sc.next().charAt(0);
                                if (postAction == 'x') {
                                    continue;
                                }
                                int postIndex = Character.getNumericValue(postAction);
                                if (postIndex < posts.size() && postIndex >= 0) {
                                    Post selected = posts.get(postIndex);
                                    Seller postSeller = selected.getSeller();
                                    postSeller.getAvailableAds().remove(selected);
                                    postSeller.getHistory().add(selected);
                                    posts.remove(selected);
                                    for (Buyer b : buyers) {
                                        b.getSavedBox().remove(selected);
                                    }
                                    System.out.println("Removed " + selected.getName());
                                    inAllAds = false;
                                    break;
                                } else {
                                    System.out.println("Index out of range!");
                                }
                            } else if (innerAction == '2' || innerAction == 'x') {
                                inAllAds = false;
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    // SELLER
                    else if (action == '3') {
                        boolean inSeller = true;
                        while (inSeller) {
                            for (Seller s : sellers) {
                                System.out.println(sellers.indexOf(s) + "-:" + s.getProfile().getUsername());
                            }
                            System.out.println("Choose action:");
                            System.out.println("1. Delete\n2. Back");
                            char innerAction = sc.next().charAt(0);
                            //DELETE
                            if (innerAction == '1') {
                                System.out.println("Index of seller you want to delete: ");
                                char sellerAction = sc.next().charAt(0);
                                if (sellerAction == 'x') {
                                    continue;
                                }
                                int sellerIndex = Character.getNumericValue(sellerAction);
                                if (sellerIndex < sellers.size() && sellerIndex >= 0) {
                                    System.out.println("Removed " + sellers.get(sellerIndex).getProfile().getUsername());
                                    sellers.remove(sellerIndex);
                                    inSeller = false;
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            } else if (innerAction == '2' || innerAction == 'x') {
                                inSeller = false;
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    // BUYER
                    else if (action == '4') {
                        boolean inBuyer = true;
                        while (inBuyer) {
                            for (Buyer b : buyers) {
                                System.out.println(buyers.indexOf(b) + "- " + b.getProfile().getUsername());
                            }
                            System.out.println("Choose action:");
                            System.out.println("1. Delete\n2. Back");
                            char innerAction = sc.next().charAt(0);
                            //DELETE
                            if (innerAction == '1') {
                                System.out.println("Index of buyer you want to delete: ");
                                char buyerAction = sc.next().charAt(0);
                                if (buyerAction == 'x') {
                                    continue;
                                }
                                int buyerIndex = Character.getNumericValue(buyerAction);
                                if (buyerIndex < sellers.size() && buyerIndex >= 0) {
                                    System.out.println("Removed " + buyers.get(buyerIndex).getProfile().getUsername());
                                    buyers.remove(buyerIndex);
                                    inBuyer = false;
                                } else {
                                    System.out.println("Out of range!");
                                }
                            } else if (innerAction == '2' || innerAction == 'x') {
                                inBuyer = false;
                            } else {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    // BACK
                    else if (action == '5' || action == 'x') {
                        System.out.println("Signed out!");
                        inAdmin = false;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }

            }
            // SELLER
            else if (user == '2') {
                boolean inSeller = true;
                while (inSeller) {
                    System.out.println("1. Sign in\n2. Sign up\n3. Back");
                    char login = sc.next().charAt(0);
                    boolean loggedIn = false;
                    int sellerIndex = -1;
                    Seller seller = new Seller();
                    if (login == '1') {
                        System.out.print("username: ");
                        String username = sc.next();
                        if (username.equals("x")) {
                            continue;
                        }
                        System.out.print("password: ");
                        String password = sc.next();
                        if (password.equals("x")) {
                            continue;
                        }
                        for (Seller s : sellers) {
                            if (s.getProfile().getUsername().equals(username) &&
                                    s.getProfile().getPassword().equals(password)) {
                                sellerIndex = sellers.indexOf(s);
                                break;
                            }
                        }
                        if (sellerIndex != -1) {
                            System.out.println("Signed in successfully!");
                            seller = sellers.get(sellerIndex);
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid login or password!");
                        }
                    } else if (login == '2') {
                        String username = getUniqueUsername(sc);
                        if (username.equals("x")) {
                            continue;
                        }
                        String password = getValidPassword(sc);
                        if (password.equals("x")) {
                            continue;
                        }
                        System.out.print("phone no.: ");
                        String phoneNumber = getValidPhoneNumber(sc);
                        if (phoneNumber.equals("x")) {
                            continue;
                        }
                        System.out.print("email: ");
                        String email = getValidEmail(sc);
                        if (email.equals("x")) {
                            continue;
                        }
                        seller = new Seller(new Profile(username, password, email, phoneNumber), 0);
                        sellers.add(seller);
                        usernames.add(username);
                        loggedIn = true;
                    } else if (login == '3' || login == 'x') {
                        inSeller = false;
                    } else {
                        System.out.println("Invalid input!");
                    }
                    while (loggedIn) {
                        System.out.println("Choose action:");
                        System.out.println("1. Available ads\n2. History\n3. Add ad\n4. Deposit\n5. Profile\n6. Sign out");
                        char action = sc.next().charAt(0);
                        // AVAILABLE
                        if (action == '1') {
                            if (seller.getAvailableAds().isEmpty()) {
                                System.out.println("No post availbale!");
                            }
                            else {
                                for (Post p : seller.getAvailableAds()) {
                                    System.out.println(p.toString());
                                }
                            }
                        }
                        // HISTORY
                        else if (action == '2') {
                            for (Post p : seller.getHistory()) {
                                System.out.println(p.toString());
                            }
                        }
                        // ADD
                        else if (action == '3') {
                            System.out.print("Choose a name for your post: ");
                            String name = sc.next();
                            if (name.equals("x")) {
                                System.out.println("Post was not added!");
                                continue;
                            }
                            String category = "";
                            System.out.println("Select a catgory:");
                            System.out.println("1. Phone\n2. Home Appliances\n3. Stationery\n4. Wearing\n5. Car\n6. Back");
                            char categoryAction = sc.next().charAt(0);
                            if (categoryAction == '1') {
                                category = "phone";
                            } else if (categoryAction == '2') {
                                category = "home";
                            } else if (categoryAction == '3') {
                                category = "stationary";
                            } else if (categoryAction == '4') {
                                category = "wearing";
                            } else if (categoryAction == '5') {
                                category = "car";
                            } else if (categoryAction == '6' || categoryAction == 'x') {
                                continue;
                            } else {
                                System.out.println("Invalid input!");
                                System.out.println("Post was not added!");
                                continue;
                            }
                            System.out.print("Select a price: ");
                            try {
                                double priceValue = sc.nextDouble();
                                Post p = new Post(name, priceValue, seller, category, 1);
                                posts.add(p);
                                System.out.println("Post added!");
                            } catch (InputMismatchException e) {
                                String s = sc.next();
                                if (sc.equals("x")) {
                                    continue;
                                } else {
                                    System.out.println("Invalid input!");
                                    System.out.println("Post was not added!");
                                }
                            }

                        }
                        // DEPOSIT
                        else if (action == '4') {
                            System.out.println("The amount of money for deposition: ");
                            try {
                                double moneyValue = sc.nextDouble();
                                if (seller.getWallet() > moneyValue) {
                                    seller.setWallet(seller.getWallet() - moneyValue);
                                } else {
                                    System.out.println("The amount of money you requested was bigger than your wallet!");
                                }
                            } catch (InputMismatchException e) {
                                String s = sc.next();
                                if (!s.equals("x")) {
                                    System.out.println("Invalid input!");
                                    System.out.println("Deposit failed!");
                                }
                            }

                        }
                        // PROFILE
                        else if (action == '5') {
                            boolean inEdit = true;
                            while (inEdit) {
                                System.out.println(seller.getProfile().toString());
                                System.out.println("Choose action:");
                                System.out.println("1. Edit\n2. Back");
                                char innerAction = sc.next().charAt(0);
                                if (innerAction == '1') {
                                    Profile p = seller.getProfile();
                                    editProfile(p, sc);
                                } else if (innerAction == '2') {
                                    inEdit = false;
                                } else {
                                    System.out.println("Invalid input");
                                }
                            }
                        }
                        // BACK
                        else if (action == '6' || action == 'x') {
                            loggedIn = false;
                        } else {
                            System.out.println("Invalid input!");
                        }
                    }
                }
            }
            // BUYER
            else if (user == '3') {
                boolean inBuyer = true;
                while (inBuyer) {
                    System.out.println("1. Sign in\n2. Sign up\n3. Back");
                    char login = sc.next().charAt(0);
                    int buyerIndex = -1;
                    boolean loggedIn = false;
                    Buyer buyer = new Buyer();
                    if (login == '1') {
                        System.out.print("username: ");
                        String username = sc.next();
                        if (username.equals("x")) {
                            continue;
                        }
                        System.out.print("password: ");
                        String password = sc.next();
                        if (password.equals("x")) {
                            continue;
                        }
                        for (Buyer b : buyers) {
                            if (b.getProfile().getUsername().equals(username) &&
                                    b.getProfile().getPassword().equals(password)) {
                                buyerIndex = buyers.indexOf(b);
                                break;
                            }
                        }
                        if (buyerIndex != -1) {
                            System.out.println("Signed in successfully!");
                            buyer = buyers.get(buyerIndex);
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid login or password!");
                        }
                    } else if (login == '2') {
                        System.out.print("username: ");
                        String username = getUniqueUsername(sc);
                        if (username.equals("x")) {
                            continue;
                        }
                        System.out.print("password: ");
                        String password = getValidPassword(sc);
                        if (password.equals("x")) {
                            continue;
                        }
                        System.out.print("phone no.: ");
                        String phoneNumber = getValidPhoneNumber(sc);
                        if (phoneNumber.equals("x")) {
                            continue;
                        }
                        System.out.print("email: ");
                        String email = getValidEmail(sc);
                        if (email.equals("x")) {
                            continue;
                        }
                        buyer = new Buyer(new Profile(username, password, email, phoneNumber), 0);
                        buyers.add(buyer);
                        usernames.add(username);
                        loggedIn = true;
                    } else if (login == '3' || login == 'x') {
                        inBuyer = false;
                    } else {
                        System.out.println("Invalid input!");
                    }
                    while (loggedIn) {
                        System.out.println("Choose action:");
                        System.out.println("1. Saved box\n2. History\n3. Sales ads\n4. Add credit\n5. Profile\n6. Back");
                        char action = sc.next().charAt(0);
                        // AVAILABLE
                        if (action == '1') {
                            boolean inSavedBox = false;
                            for (int i = 0; i < buyer.getSavedBox().size(); i++) {
                                System.out.println(i + ": " + buyer.getSavedBox().get(i).toString());
                                inSavedBox =  true;
                            }
                            if (!inSavedBox) {
                                System.out.println("No post in your saved box!");
                            }
                            while (inSavedBox) {
                                System.out.println("Choose action:");
                                System.out.println("1. Buy\n2. Delete\n3. Back");
                                char innerAction = sc.next().charAt(0);
                                if (innerAction == '1') {
                                    boolean inBuy = true;
                                    while (inBuy) {
                                        System.out.print("Choose the index of post you want to buy: ");
                                        char postAction = sc.next().charAt(0);
                                        if (postAction == 'x') {
                                            inBuy = false;
                                        }
                                        int postIndex = Character.getNumericValue(postAction);
                                        if (postIndex < buyer.getSavedBox().size() && postIndex >= 0) {
                                            Post p = posts.get(postIndex);
                                            if (buyer.getWallet() >= p.getPrice()) {
                                                buyer.getSavedBox().remove(p);
                                                buyer.getHistory().add(p);
                                                buyer.setWallet(buyer.getWallet() - p.getPrice());
                                                System.out.println("Congrats on your purchase!");
                                                inBuy = false;
                                            }
                                        } else {
                                            System.out.println("Index out of range!");
                                        }
                                    }
                                } else if (innerAction == '2') {
                                    boolean inDelete = true;
                                    while (inDelete) {
                                        System.out.print("Choose the index of post you want to delete: ");
                                        char postAction = sc.next().charAt(0);
                                        if (postAction == 'x') {
                                            break;
                                        }
                                        int postIndex = Character.getNumericValue(postAction);
                                        if (postIndex < buyer.getSavedBox().size() && postIndex >= 0) {
                                            Post p = posts.get(postIndex);
                                            buyer.getSavedBox().remove(p);
                                            System.out.println("Post removed!");
                                            inDelete = false;
                                        } else {
                                            System.out.println("Index out of range!");
                                        }
                                    }
                                } else if (innerAction == '3' || innerAction == 'x') {
                                    inSavedBox = false;
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            }
                        }
                        // HISTORY
                        else if (action == '2') {
                            for (Post p : buyer.getHistory()) {
                                System.out.println(p.toString());
                            }
                        }
                        // ADD
                        else if (action == '3') {
                            String category = "";
                            System.out.println("Select a catgory:");
                            System.out.println("1. Phone\n2. Home Appliances\n3. Stationery\n4. Wearing\n5. Car\n6. Back");
                            char categoryAction = sc.next().charAt(0);
                            if (categoryAction == '1') {
                                category = "phone";
                            } else if (categoryAction == '2') {
                                category = "home";
                            } else if (categoryAction == '3') {
                                category = "stationary";
                            } else if (categoryAction == '4') {
                                category = "wearing";
                            } else if (categoryAction == '5') {
                                category = "car";
                            } else if (categoryAction == '6' || categoryAction == 'x') {
                                continue;
                            } else {
                                System.out.println("Invalid input!");
                                System.out.println("Post was not added!");
                                continue;
                            }
                            ArrayList<Post> filteredPosts = new ArrayList<>();
                            boolean inPost = false;
                            for (Post p : posts) {
                                if (p.getCategory().equals(category) && p.isApproved() == 1) {
                                    filteredPosts.add(p);
                                    inPost = true;
                                }
                            }
                            if (!inPost) {
                                System.out.println("No post available in this category!");
                            }
                            while (inPost) {
                                int index = 0;
                                for (Post p : filteredPosts) {
                                    System.out.println(index++ + ": " + p);
                                }
                                index = 0;
                                System.out.println("Choose action:");
                                System.out.println("1. Sort\n2. Buy\n3. Add to wishlist\n4. Back");
                                String innerAction = sc.next();
                                if (innerAction.equals("1")) {
                                    filteredPosts.sort(new PostPriceComparator());
                                    System.out.println("1. Ascending\n2. Descending");
                                    String sortOrder = sc.next();
                                    if (sortOrder.equals("1")) {
                                        for (int i = 0; i < filteredPosts.size(); i++) {
                                            System.out.println(index++ + ": " + filteredPosts.get(i));
                                        }
                                    } else if (sortOrder.equals("2")) {
                                        for (int i = filteredPosts.size() - 1; i >= 0; i--) {
                                            System.out.println(index++ + ": " + filteredPosts.get(i));
                                        }
                                    }
                                } else if (innerAction.equals("2")) {
                                    System.out.print("Choose the index of the post you want to add buy: ");
                                    try {
                                        int chosenIndex = sc.nextInt();
                                        if  (chosenIndex < filteredPosts.size() && chosenIndex >= 0) {
                                            Post selected = posts.get(chosenIndex);
                                            if (selected.getPrice() <= buyer.getWallet()) {
                                                posts.remove(selected);
                                                buyer.getHistory().add(selected);
                                                buyer.setWallet(buyer.getWallet() - selected.getPrice());
                                                System.out.println("Congrats on your purchase!");
                                            }
                                            else {
                                                System.out.println("You can't afford to buy this!");
                                            }
                                        } else {
                                            System.out.println("Index out of range!");
                                        }
                                    } catch (InputMismatchException e) {
                                        if (!sc.next().equals("x")) {
                                            System.out.println("Invalid input!");
                                        }
                                    }
                                } else if (innerAction.equals("3")) {
                                    System.out.print("Choose the index of the post you want to add add to your wishlist: ");
                                    try {
                                        int chosenIndex = sc.nextInt();
                                        if  (chosenIndex < filteredPosts.size() && chosenIndex >= 0) {
                                            Post selected = posts.get(chosenIndex);
                                            if (buyer.getSavedBox().contains(selected)) {
                                                System.out.println("This post is already on your list!");
                                            } else {
                                                buyer.getSavedBox().add(selected);
                                                System.out.println("Added to your wishlist!");
                                            }
                                        } else {
                                            System.out.println("Index out of range!");
                                        }
                                    } catch (InputMismatchException e) {
                                        if (!sc.next().equals("x")) {
                                            System.out.println("Invalid input!");
                                        }
                                    }
                                } else if (innerAction.equals("4") || innerAction.equals("x")) {
                                    inPost = false;
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            }

                        }
                        // CREDIT
                        else if (action == '4') {
                            System.out.print("In cart: ");
                            System.out.println(buyer.getWallet());
                            System.out.println("The amount of money you want to add to your wallet: ");
                            String money = sc.next();
                            char moneyAction = money.charAt(0);
                            if (moneyAction == 'x') {
                                continue;
                            }
                            double moneyValue = Float.parseFloat(money);
                            buyer.setWallet(buyer.getWallet() + moneyValue);
                            System.out.println("Added " + moneyValue + " to your wallet!");
                        }
                        // PROFILE
                        else if (action == '5') {
                            boolean inEdit = true;
                            while (inEdit) {
                                System.out.println(buyer);
                                System.out.println("Choose action:");
                                System.out.println("1. Edit\n2. Back");
                                char innerAction = sc.next().charAt(0);
                                if (innerAction == '1') {
                                    Profile p = buyer.getProfile();
                                    editProfile(p, sc);
                                } else if (innerAction == '2') {
                                    inEdit = false;
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            }
                        }
                        // BACK
                        else if (action == '6' || action == 'x') {
                            loggedIn = false;
                        } else {
                            System.out.println("Invalid input!");
                        }
                    }
                }
            }
            // GUEST
            else if (user == '4') {
                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("Choose action:");
                    System.out.println("1. All posts\n2. Back");
                    char action = sc.next().charAt(0);
                    if (action ==  '1') {
                        for (Post p : posts) {
                            System.out.println(p);
                        }
                    } else if (action == '2' || action == 'x') {
                        loggedIn = false;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
            }
        }
        sc.close();
    }

    static int validatePassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(regex);
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
        if (m.matches()) {
            return 1;
        }
        return 0;
    }

    static int validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 11) {
            return 1;
        }
        return 0;
    }

    static int validateEmail(String email) {
        String regex = "^(.+)@(\\S+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return 1;
        }
        return 0;
    }

    static int uniqueUserName(String username) {
        boolean exist = false;
        for (String u : usernames) {
            if (username.equals(u)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            return 1;
        }
        return 0;
    }

    static String getUniqueUsername(Scanner sc) {
        String username = sc.next();
        while (uniqueUserName(username) != 1 && !username.equals("x")) {
            System.out.println("This useername is already taken!");
            System.out.print("username: ");
            username = sc.next();
        }
        return username;
    }

    static String getValidPassword(Scanner sc) {
        String password = sc.next();
        while (validatePassword(password) != 1 && !password.equals("x")) {
            System.out.println("not a good password!");
            System.out.print("password: ");
            password = sc.next();
        }
        return password;
    }

    static String getValidPhoneNumber(Scanner sc) {
        String phoneNumber = sc.next();
        while (validatePhoneNumber(phoneNumber) != 1 && !phoneNumber.equals("0")) {
            System.out.println("Invalid phone number!");
            System.out.println("phone no.: ");
            phoneNumber = sc.next();
        }
        return phoneNumber;
    }

    static String getValidEmail(Scanner sc) {
        String email = sc.next();
        while (validateEmail(email) != 1 && !email.equals("0")) {
            System.out.println("Invalid email!");
            System.out.println("email: ");
            email = sc.next();
        }
        return email;
    }

    static void editProfile(Profile p, Scanner sc) {
        boolean inEditMenu = true;
        while (inEditMenu) {
            System.out.println("1. Edit username\n2. Edit password\n3. Edit phone number\n4. Edit email\n5. Back");
            char editOption = sc.next().charAt(0);
            if (editOption == '1') {
                System.out.print("new username: ");
                String newUsername = getUniqueUsername(sc);
                if (newUsername.equals("x")) {
                    continue;
                }
                p.setUsername(newUsername);
                System.out.println("username changed to " + newUsername);
            } else if (editOption == '2') {
                System.out.print("new password: ");
                String newPassword = getValidPassword(sc);
                if (newPassword.equals("x")) {
                    continue;
                }
                p.setPassword(newPassword);
                System.out.println("password changed to " + newPassword);
            } else if (editOption == '3') {
                System.out.print("new phone no.: ");
                String newPhoneNumber = getValidPhoneNumber(sc);
                if (newPhoneNumber.equals("x")) {
                    continue;
                }
                p.setPhoneNumber(newPhoneNumber);
                System.out.println("phone number changed to " + newPhoneNumber);
            } else if (editOption == '4') {
                System.out.print("new email: ");
                String newEmail = getValidEmail(sc);
                if (newEmail.equals("x")) {
                    continue;
                }
                p.setEmail(newEmail);
                System.out.println("email changed to " + newEmail);
            } else if (editOption == '5' || editOption == 'x') {
                inEditMenu = false;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
