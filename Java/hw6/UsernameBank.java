package hw7;

import edu.princeton.cs.introcs.Stopwatch;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


/**
 * Created by Arsen on 10/26/17.
 */
public class UsernameBank {

    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private UserName[] st;  // array of linked-list symbol tables
    private ArrayList<String> badUserNames;
    private ArrayList<String> badEmails;


    public UsernameBank(){
        this(INIT_CAPACITY);
    }

    public UsernameBank(int m ){
        this.m = m;
        st = new UserName[m];
        for (int i =0; i<m; i++){
            st[i] = new UserName();
        }
    }





    public void generateUsername(String name, String email) {
        if (name.length() == 0) throw new NullPointerException("Requested username is null!");
        if (name.length() < 2 || name.length() > 3) {

            throw new IllegalArgumentException("A username is just 2 to 3 alphanumeric characters (0-9, a-z, A-Z).");
        }
        if (!name.matches("[a-zA-Z0-9]*")){
            badUserNames.add(name);
            throw new IllegalArgumentException("Your characters should be in range [a-zA-Z0-9] ");
        }

        if (!isValidEmailAddress(email)){
            badEmails.add(email);
            throw new IllegalArgumentException("The email address is Incorrect");
        }

        int i = hash(name);
        if (st[i].contains(name)) {
            throw new IllegalArgumentException("The name " + name + " already exists in the database" + "\n" + "Try this usernames: " + suggestUsername(name));
        }

        st[i].put(name,email);
    }


    // hash value between 0 and m-1
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /** Returns the emal address of the USER */
    public String getEmail(String username) {
        // YOUR CODE HERE
        if (username == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(username);
        return st[i].get(username);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }



    public void getBadEmails() {
        System.out.println(badEmails);
    }

    public void getBadUsernames() {
        System.out.println(badUserNames);
    }

    public String suggestUsername(String name) {
        // YOUR CODE HERE
        String newval="";
        Random rn = new Random();
        if (name.length() == 3) {
            newval += name.substring(0,1);
            newval += name.substring(1,2);
            newval += rn.nextInt(10);
        }
        else if (name.length()==2) {
            newval += name.substring(0,1);
            newval += name.substring(1,2);
            newval += rn.nextInt(10);
        }

        int k = hash(newval);
        if (st[k].contains(newval)){
                suggestUsername(newval);}

        return newval;
    }


    public static void main(String[] args){
        UsernameBank bank = new UsernameBank(20);
        Stopwatch a = new Stopwatch();
        bank.generateUsername("asx" + "","arsenzairov@mail.usf.edu");
        bank.generateUsername("mau","maidenalmaty@gmail.com");
        bank.generateUsername("atm","atm@gmail.com");
        bank.generateUsername("amc","arsen@mail.ru");
        bank.generateUsername("mal","asd@mail.ru");
        bank.generateUsername("asc" + "","arsenzairov@mail.usf.edu");
        bank.generateUsername("maz","maidenalmaty@gmail.com");
        bank.generateUsername("atb","atm@gmail.com");
        bank.generateUsername("ama","arsen@mail.ru");
        bank.generateUsername("may","asd@mail.ru");



        bank.getEmail("asc");
        System.out.println(a.elapsedTime());
    }



}