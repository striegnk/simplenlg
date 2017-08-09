package union;

import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

/**
 * The story:
 * <p>
 * This dataset contains information about different types of cars, such as the cars' fuel economy, how long it takes
 * them to accelerate from 0 to 60 mph, their weight, and their engine's power, displacement, and number of cylinders.
 * <p>
 * We analyzed the relationships between these dimensions and will now show three complete cause-effect sequences.
 * <p>
 * View 1 shows the cars' number of cylinders (x-axis) and their displacement (y-axis).
 * <p>
 * For example, the Chevrolet Chevelle Malibu (red dot) has a high number of cylinders and high displacement.
 * <p>
 * The upward trend of the data points suggests that the number of cylinders of a car and its displacement are related.
 * <p>
 * Further analysis shows that this relationship is strong (r=0.95) and indicates that cars with more cylinders also
 * have a higher displacement.
 * <p>
 * Next, let's look at displacement and weight in view 2.
 * <p>
 * We have already seen that the Chevrolet Chevelle Malibu (red dot) has a higher displacement than most cars.
 * <p>
 * This plot shows that it is also heavier than most cars.
 * <p>
 * The upward trend of the data points again suggests a relationship between a car's displacement and its weight.
 * <p>
 * In fact, these two dimensions are especially correlated (r=0.93): cars with a higher displacement, such as the
 * Chevrolet Chevelle Malibu, also weigh more.
 * <p>
 * Thus we have seen that more cylinders lead to a higher displacement, which leads to increased weight, which leads to
 * a lower fuel economy.
 * <p>
 * To summarize, we can find the following cause-effect sequences in this dataset.
 * <p>
 * More cylinders lead to a higher displacement.
 * <p>
 * The higher the displacement, the heavier the car.
 * <p>
 * And heavier cars have worse fuel economy, but need less time to accelerate.
 * <p>
 * In addition, a higher displacement also leads to greater power.
 * <p>
 * And cars with a greater power also need less time to accelerate.
 **/

public class DataStory02 {

    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    private Realiser realiser;

    private DataStory02() {
        lexicon = Lexicon.getDefaultLexicon();
        nlgFactory = new NLGFactory(lexicon);
        realiser = new Realiser(lexicon);
    }

    private void realise() {
        sentence1();
    }

    /**
     * This dataset contains information about different types of cars, such as the cars' fuel economy, how long it
     * takes them to accelerate from 0 to 60 mph, their weight, and their engine's power, displacement, and number of
     * cylinders.
     **/
    private void sentence1() {

    }

    public static void main(String[] args) {
        DataStory02 story = new DataStory02();
        story.realise();
    }
}
