/**
* Name: Nikhil Mahendran
* Email: nmahendran@ucsd.edu
* PID: A17317228
* Sources: None
*
* This file is meant to represent the user in this
reddit style system. 
*/
import java.util.ArrayList;

/**
 * This class creates a User who can interact 
 * with posts and have their unique stats, such as
 * karma, change with each interaction.
 */
public class User {
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;
    /**
     * This method intializes the user.
     * @param username
     * @return User
     */
    public User (String username){
        if(username.isEmpty() == false){
            this.username = username;
            this.posts = new ArrayList<Post>();
            this.upvoted = new ArrayList<Post>();
            this.downvoted = new ArrayList<Post>();
            this.karma = 0;
        }
    }
    /**
     * This method adds a post to the end of the user's posts
     * @param post
     * @return none
     */
    public void addPost(Post post){
        if(post != null && posts != null){
            posts.add(post);
            this.updateKarma();
        }
        else
            return;
    }
    /**
     * This method updates the karma of the user based on their
     * interaction with a post.
     * @param none
     * @return none
     */
    public void updateKarma(){
    Post getPost;
        if(posts == null){
            return;
        }
        karma = 0;
        for(int i = 0; i< posts.size(); i++){
            getPost = posts.get(i);
            if(getPost != null){
                karma += getPost.getUpvoteCount() 
                    - getPost.getDownvoteCount();
            }
        }
    }
    /**
     * This method gets the karma value.
     * @param none
     * @return karma value
     */
    public int getKarma(){
        return karma;
    }
    /**
     * This method allows the user to upvote a post.
     * @param post
     * @return none
     */
    public void upvote(Post post){
        if(post == null){
            return;
        }
        if(upvoted.contains(post) == true || post.getAuthor() == this){
            return;
        }
        if(downvoted.contains(post) == true){
            downvoted.remove(post);
            post.updateDownvoteCount(false); 
        }
        upvoted.add(post);
        post.updateUpvoteCount(true);
        User author = post.getAuthor();
        author.updateKarma();
    }
    /**
     * This method allows the user to downvote a post.
     * @param post
     * @return none
     */
    public void downvote(Post post){
        if(post == null){
            return;
        }
        if(downvoted.contains(post) == true || post.getAuthor() == this){
            return;
        }
        if(upvoted.contains(post) == true){
            upvoted.remove(post);
            post.updateUpvoteCount(false);
            //post removed from upvoted array list and upvote count reduced
        }
        downvoted.add(post);
        post.updateDownvoteCount(true);
        User author = post.getAuthor();
        author.updateKarma();
    }
    /**
     * This method returns the original post with the highest karma.
     * @param none
     * @return top post
     */
    public Post getTopPost(){ 
    Post post; 
    int postValue = 0;
    Post topPost = null;
    int topPostValue = 0;
        if(posts != null){
            for(int i = 0; i < posts.size(); i++){
                post = posts.get(i);
                if(post.getPostType() == 0){
                    if(topPost == null){
                        topPost = post;
                        topPostValue = topPost.getUpvoteCount() 
                            - topPost.getDownvoteCount();
                    }
                    postValue = post.getUpvoteCount() 
                        - post.getDownvoteCount();
                    if(postValue > topPostValue){
                    topPost = post;
                    topPostValue = postValue;
                    }
                }
            }
            return topPost;
        }
        else{
            return null;
        }
    }
    /**
     * This method returns the comment with the highest karma.
     * @param none
     * @return top comment
     */
    public Post getTopComment(){
    Post post; 
    int postValue = 0;
    Post topComment = null;
    int topCommentValue = 0;
        if(posts != null){
            for(int i = 0; i < posts.size(); i++){
                post = posts.get(i);
                if(post.getPostType() == 1){
                    if(topComment == null){
                        topComment = post;
                        //makes first post the top post to start comparisons
                        topCommentValue = topComment.getUpvoteCount()
                            - topComment.getDownvoteCount();
                    }
                    postValue = post.getUpvoteCount() 
                        - post.getDownvoteCount();
                    if(postValue > topCommentValue){
                        topComment = post;
                        topCommentValue = postValue;
                        // top post is replaced by current post
                    }
                }
            }
            return topComment;
        }
        else{
            return null;
        }
    }
    /**
     * This method returns a list of original posts and comments
     * made by the user.
     * @param none
     * @return list of posts
     */
    public ArrayList<Post> getPosts(){ 
        return this.posts;
    }
    /**
     * This method returns the information in string format.
     * @param none
     * @return String format
     */
    public String toString(){
        String STRING_FORMAT_KARMA = "u/%s Karma: %d";
        return String.format(STRING_FORMAT_KARMA, this.username, this.karma);
    }
    /**
     * This method returns the size of the list of posts made
     * by the user
     * @param none
     * @return post count
     */
    public int getPostCount(){
        if(posts != null){
            return posts.size();
        }
        else
            return 0;
    }
}