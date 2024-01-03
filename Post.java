/**
* Name: Nikhil Mahendran
* Email: nmahendran@ucsd.edu
* PID: A17317228
* Sources: None
*
* This file is meant to represent the posts in this
reddit style system.
*/
import java.util.ArrayList;

/**
 * This class creates a Post system with two main types.
 * These can be created and interacted with by Users.
 */

public class Post {
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    /**
     * This method intializes an original post.
     * @param title
     * @param content
     * @param author
     * @return original post
     */
    public Post(String title, String content, User author){
        
        this.title = title;
        this.content = content;
        this.author = author;
        this.replyTo = null;

        this.upvoteCount = 1;
        this.downvoteCount = 0;

    }
    /**
     * This method intializes a comment for an original Post.
     * @param content
     * @param replyTo
     * @param author
     * @return comment
     */
    public Post(String content, Post replyTo, User author){

        this.title = null;
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;

        this.upvoteCount = 1;
        this.downvoteCount = 0;
    }
    /**
     * This method determines whether a post is original or a comment for
     * an orginal post.
     * @param none
     * @return int to determine original post or comment 
     */
    public int getPostType(){
        int postType = 0;
        if(this != null){
            if(this.replyTo == null){
                postType = 0;
            }
            else 
                postType = 1;
        }
        return postType; // returns post as either original or comment
    }
    /**
     * This method returns the title of an orginal post
     * and returns null if the post is a comment
     * @param none
     * @return String
     */
    public String getTitle(){
        if(this.getPostType() == 0){
            return this.title;
        }
        else {
            return null;
        }
    }
    /**
     * This method returns the parent post of the given post if it is a
     * comment. If the post is original then it returns null.
     * @param none
     * @return original post
     */
    public Post getReplyTo(){
        if(this.getPostType() == 1){
            return this.replyTo;
        }
        else
            return null;
    }
    /**
     * This method returns the author of the post
     * @param none
     * @return the author
     */
    public User getAuthor(){
        return this.author;
    }
    /**
     * This method returns the number of up votes for
     * a post.
     * @param none
     * @return number of up votes
     */
    public int getUpvoteCount(){
        return this.upvoteCount;
    }
    /**
     * This method returns the number of down votes for
     * a post.
     * @param none
     * @return number of down votes
     */
    public int getDownvoteCount(){
        return this.downvoteCount;
    }
    /**
     * This method increases the up vote count by 1 if the post is
     * up voted
     * @param isIncrement
     * @return none
     */
    public void updateUpvoteCount(boolean isIncrement){
        if( isIncrement == true)
            upvoteCount += 1;
        else
            upvoteCount -= 1;
    }
    /**
     * This method increases the down vote count by 1 if the post is
     * up voted
     * @param isIncrement
     * @return none
     */
    public void updateDownvoteCount(boolean isIncrement){
        if( isIncrement == true){
            downvoteCount += 1;
        }
        else
            downvoteCount -= 1;
    }
    /**
     * This method returns all posts in a single thread, 
     * stopping at the original post. It does not cover comments
     * that aren't directly in the thread.
     * @param none
     * @return arraylist of posts
     */
    public ArrayList<Post> getThread(){
        ArrayList<Post> thread = new ArrayList<Post>();
        Post currentPost = this; 
            while (currentPost != null){
                thread.add(0, currentPost);
                //adds post to the front of the arraylist
                currentPost = currentPost.getReplyTo();
            }
            return thread;
    }
    /**
     * This method returns the information in a string format.
     * @param none
     * @return String
     */
    public String toString(){
        String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
        String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";
        if(this.getPostType() == 0){
            return String.format(TO_STRING_POST_FORMAT, 
                upvoteCount, downvoteCount, title, content);
        }
        else
            return String.format(TO_STRING_COMMENT_FORMAT, 
                upvoteCount, downvoteCount, content);
    }
}