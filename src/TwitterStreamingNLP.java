import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.featuregen.FeatureGeneratorUtil;

public class TwitterStreamingNLP {
	static DocumentCategorizerME classificationME;
	static NameFinderME nameFinder;

	public static void main(String[] args) {
		File test = new File("/home/hetti/Desktop/Devops/NLP/en-doccat.bin");
		String classificationModelFilePath = test.getAbsolutePath();

		try {
			classificationME = new DocumentCategorizerME(new DoccatModel(
					new FileInputStream(classificationModelFilePath)));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				// System.out.println(status.getUser().getName() + " : " +
				// status.getText()+ "  Tweeted AT: " + status.getCreatedAt());
				try {
					new TwitterStreamingNLP().DocumentCategorizer(
							status.getText() + "", classificationME);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
			}
		};

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("IYD545kX3ni6wubh4PSzz3Hzu")
				.setOAuthConsumerSecret(
						"6DR29ngB9KykHNvGv8szF1UuMZArhkX0jF6bfKxZBNTvnJWAdp")
				.setOAuthAccessToken(
						"767602178-Bv289YXBc0zAO77OwCqzJCj0QFSXtEY3j9yITi4A")
				.setOAuthAccessTokenSecret(
						"EvtWdwQnhNbmwThIzsdoqoQS8xohmIR8tRS01nbDtcXrW");

		TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
		TwitterStream twitterStream = tf.getInstance();
		twitterStream.addListener(listener);

		FilterQuery filtre = new FilterQuery();
		String[] keywordsArray = { "iphone", "galaxy" }; // Keyword tika daapiya
		filtre.track(keywordsArray);
		twitterStream.filter(filtre);
	}

	public void DocumentCategorizer(String text,
			DocumentCategorizerME classificationME) throws IOException {

		String documentContent = text;
		double[] classDistribution = classificationME
				.categorize(documentContent);

		String predictedCategory = classificationME
				.getBestCategory(classDistribution);
		System.out.println("Tweet" + text + " , Model prediction : "
				+ predictedCategory);

	}
	
	public void NERCategorizer(String text,
			DocumentCategorizerME classificationME) throws IOException {

		String documentContent = text;
		double[] classDistribution = classificationME
				.categorize(documentContent);

		String predictedCategory = classificationME
				.getBestCategory(classDistribution);
		System.out.println("Tweet" + text + " , Model prediction : "
				+ predictedCategory);

	}
}
