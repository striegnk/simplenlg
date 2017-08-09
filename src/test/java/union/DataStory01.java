package union;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;

/**
 * The story:
 * <p>
 * This dataset contains information about different types of cars, such as the cars' fuel economy, how long it takes
 * them to accelerate from 0 to 60 mph, their weight, and their engine's power, displacement, and number of cylinders.
 * <p>
 * This graph shows that more fuel efficient cars (cars with a higher gas mileage, shown in red) tend to take longer to
 * accelerate from 0 to 60 mph.
 * <p>
 * Ideally, we want cars that have a good fuel economy (i.e. can travel more miles per gallon of gas) and at the same
 * time can accelerate quickly (i.e. need fewer seconds to get from 0 to 60 mph).
 * <p>
 * Cars that satisfy both constraints are shown in purple.
 * <p>
 * Let's explore what type of engineering decision influence a car's fuel efficiency and acceleration.
 * <p>
 * Fuel efficiency can be somewhat improved by decreasing a car's weight.
 * <p>
 * This can be achieved by decreasing its engine's displacement, which is a result of decreasing the number of
 * cylinders.
 * <p>
 * For example, the Volkswagen Scirocco has few cylinders, low engine displacement, low weight, and a good fuel
 * efficiency.
 * <p>
 * [...]
 * <p>
 * To sum up, this analysis suggests that there are two ways to improve acceleration: we can increase a car's power or
 * decrease its weight.
 * <p>
 * However, only decreasing its weight, will also improve the car's fuel efficiency.
 **/

public class DataStory01 {

    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    private Realiser realiser;

    private DataStory01() {
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
        DataStory01 story = new DataStory01();
        story.realise();
    }
}
