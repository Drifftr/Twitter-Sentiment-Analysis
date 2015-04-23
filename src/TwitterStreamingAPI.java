import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamingAPI {
	public static void main(String[] args){
	       
        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText()+ "  Tweeted AT: " + status.getCreatedAt());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub
               
            }
            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
               
            }           
        };
       
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("IYD545kX3ni6wubh4PSzz3Hzu")
          .setOAuthConsumerSecret("6DR29ngB9KykHNvGv8szF1UuMZArhkX0jF6bfKxZBNTvnJWAdp")
          .setOAuthAccessToken("767602178-Bv289YXBc0zAO77OwCqzJCj0QFSXtEY3j9yITi4A")
          .setOAuthAccessTokenSecret("EvtWdwQnhNbmwThIzsdoqoQS8xohmIR8tRS01nbDtcXrW");
       
        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        TwitterStream twitterStream = tf.getInstance();
        twitterStream.addListener(listener);
       
        FilterQuery filtre = new FilterQuery();
        String[] keywordsArray = {"lanka"}; //filter based on your choice of keywords
        filtre.track(keywordsArray);
        twitterStream.filter(filtre);       
    }       
}
