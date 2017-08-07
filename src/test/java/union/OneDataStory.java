package union;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

/**
 * The story:
 *
 * This dataset contains information about different types of cars, such as the cars' fuel economy, how long it takes
 * them to accelerate from 0 to 60 mph, their weight, and their engine's power, displacement, and number of cylinders.
 *
 *
 **/

public class OneDataStory {

    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    private Realiser realiser;

    private OneDataStory() {
        lexicon = Lexicon.getDefaultLexicon();
        nlgFactory = new NLGFactory(lexicon);
        realiser = new Realiser(lexicon);
    }

    private void realise() {

    }

    public static void main(String[] args) {
        OneDataStory story = new OneDataStory();
        story.realise();
    }
}
