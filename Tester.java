/**
* Name: Nikhil Mahendran
* Email: nmahendran@ucsd.edu
* PID: A17317228
* Sources: None
*
* This file is meant to test the post and user class methods 
*/
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        
        User u1 = new User("CSE12Student");
        Post p1 = new Post("Title u1p1", "Content u1p1", u1);
        Post c1 = new Post("Comment u1p1c1", p1, u1);

        User u2 = new User("CSE11Student");
        Post p2 = new Post("Title u2p2", "Content u2p2", u2);
        Post p21 = new Post("Title u21p21", "Content u21p21", u2);
        Post c21 = new Post("Comment u21p2c21", p2, u2);
        Post c22 = new Post("Comment u22p2c22", p2, u2);

        User u3 = new User("CSE10Student");
        Post p3 = new Post("Title u3p3", "Content u3p3", u3);
        Post c3 = new Post("Comment u3p3c3", p3, u3);
        ArrayList<Post> threads = new ArrayList<Post>();

        //System.out.println(u1);
        //System.out.println(u2);
        //System.out.println(u3);
        
        u1.addPost(p1);
        u1.addPost(c1);
        u2.addPost(p2);
        u2.addPost(p21);
        u2.addPost(c21);
        u2.addPost(c22);
        u3.addPost(p3);
        u3.addPost(c3);
        u1.addPost(null);

        u1.upvote(null);
        u3.upvote(c3); // checks same author as post
        u1.upvote(p2);
        u1.upvote(p2); // checks upvote twice
        u3.upvote(p2);
        u1.upvote(c3);
        u3.downvote(p1);
        u3.upvote(p1); // upvotes a downvoted post
        u2.upvote(c1);
        u1.downvote(null); // checks null downvote
        u2.downvote(c21); 
        u2.downvote(c21); // checls duplicate downvote
        u1.upvote(c3);
        u1.downvote(c3); // downvotes an upvoted post
        u1.upvote(c22);
        u3.upvote(c22);
        u1.getPosts();
        u1.toString();
        threads = p1.getThread();
        User testUser = p21.getAuthor();
        threads.size();
        testUser.toString();
        p1.getTitle();
        p2.toString();
        System.out.println(u2.getTopComment());
        //System.out.println(p1);
        //System.out.println(c1);
        //System.out.println(p2);
        //System.out.println(c21);
        //System.out.println(c22);
        //System.out.println(p3);
        System.out.println("c22 = " + c22);


        System.out.println(u2.getTopPost());
        System.out.println(u1.getKarma());
        System.out.println(u2.getKarma());
        System.out.println(u3.getKarma());
    }
}