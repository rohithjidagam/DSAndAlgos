package com.salesforce;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Friendship {

    static Map<String, Set<String>> friendsMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = null;
        try {
            br = constructFriendsMap(br);
            printMap();

            makeFirends("Cindy", "George");
            printMap();

            removeFriends("Cindy", "George");
            printMap();

            Set<String> dFriends = getDirectFriends("Bella");
            System.out.println(dFriends);

            Set<String> IdFriends = new HashSet<>();
            getIndirectDirectFriends("Bella", IdFriends);
            System.out.println(IdFriends);

        } catch (Exception e) {
            throw new FriendsException("Error in Reading file.", e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private static void getIndirectDirectFriends(String person, Set<String> idFriends) {

        if (checkNullOrEmpty(person)) {
            return;
        }

        if (!friendsMap.containsKey(person)) {
            return;
        }

        Set<String> friends = friendsMap.get(person);
        for (String friend : friends) {
            idFriends.add(friend);
            getIndirectDirectFriends(friend, idFriends);
        }

    }

    private static Set<String> getDirectFriends(String person) {

        if (checkNullOrEmpty(person))
            return null;

        if (!friendsMap.containsKey(person)) {
            return null;
        }

        return friendsMap.get(person);

    }

    private static void removeFriends(String person, String friend) {

        if (checkNullOrEmpty(person) || checkNullOrEmpty(friend)) {
            return;
        }

        if (!friendsMap.containsKey(person)) {
            return;
        }

        if (!friendsMap.get(person).contains(friend)) {
            return;
        }

        friendsMap.get(person).remove(friend);

    }

    private static void printMap() {
        System.out.println(friendsMap);
    }

    private static void makeFirends(String person, String friend) {

        if (checkNullOrEmpty(person) || checkNullOrEmpty(friend)) {
            return;
        }

        Set<String> friends = friendsMap.get(person);
        if (friends == null) {
            friends = new HashSet<>();
        }

        friends.add(friend);
        friendsMap.put(person, friends);
    }

    private static boolean checkNullOrEmpty(String s) {
        if (s == null || s.isEmpty())
            return true;
        return false;
    }

    private static BufferedReader constructFriendsMap(BufferedReader br)
            throws FileNotFoundException, IOException {
        FileReader f = new FileReader("./src/main/java/com/salesforce/friends.txt");
        br = new BufferedReader(f);
        String line = null;
        Set<String> friends = null;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(";");
            if (split.length == 2) {
                String person = split[0];
                String friend = split[1];
                if (friendsMap.get(person) == null) {
                    friends = new HashSet<>();
                } else {
                    friends = friendsMap.get(person);
                }

                friends.add(friend);
                friendsMap.put(person, friends);
            }
        }
        return br;
    }
}
