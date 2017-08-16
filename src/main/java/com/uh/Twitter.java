package com.uh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {

    private static int timestamp = 0;

    Map<Integer, User> userMap;

    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }

    public static void main(String[] args) {

        Twitter t = new Twitter();

        t.postTweet(1, 6);
        t.postTweet(1, 7);
        t.postTweet(1, 8);
        t.postTweet(2, 9);
        t.postTweet(3, 10);
        t.postTweet(4, 11);
        t.postTweet(2, 12);
        t.postTweet(2, 13);
        t.postTweet(3, 14);
        t.postTweet(3, 15);
        t.postTweet(3, 16);
        t.postTweet(3, 17);
        

        t.follow(1, 2);
        t.follow(1, 3);
        t.follow(3, 4);

        List<Integer> newsFeed = t.getNewsFeed(1);
        System.out.println(Arrays.deepToString(newsFeed.toArray()));

        t.unfollow(1, 2);
        
        List<Integer> newsFeed2 = t.getNewsFeed(1);
        System.out.println(Arrays.deepToString(newsFeed2.toArray()));
    }

    private List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if (!userMap.containsKey(userId))
            return res;

        Set<Integer> followed = userMap.get(userId).followed;
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(followed.size(), (a, b) -> b.time - a.time);
        for (int user : followed) {
            Tweet t = userMap.get(user).tweet_head;
            if (t != null) {
                maxHeap.add(t);
            }
        }

        int i = 0;
        while (!maxHeap.isEmpty() && i < 10) {
            Tweet poll = maxHeap.poll();
            res.add(poll.tweetId);
            i++;
            if (poll.next != null)
                maxHeap.add(poll.next);
        }

        return res;
    }

    private void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followeeId == followerId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    private void follow(int followerId, int followeeId) {

        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }

        if (!userMap.containsKey(followeeId)) {
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }

        userMap.get(followerId).follow(followeeId);
    }

    private void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }

        userMap.get(userId).post(tweetId);

    }

    private class User {
        int userId;
        Set<Integer> followed;
        Tweet tweet_head;

        public User(int userId) {
            this.userId = userId;
            followed = new HashSet<>();
            tweet_head = null;
            follow(userId);
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }

        public void post(int id) {
            Tweet tweet = new Tweet(id);
            tweet.next = tweet_head;
            tweet_head = tweet;
        }
    }

    private class Tweet {
        int tweetId;
        int time;
        Tweet next;

        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            time = timestamp++;
            next = null;
        }
    }
}
