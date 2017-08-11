package union;

import simplenlg.features.Feature;
import simplenlg.features.NumberAgreement;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.*;

import java.awt.datatransfer.FlavorEvent;

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
        SPhraseSpec sentence = nlgFactory.createClause();

        NPPhraseSpec sub1 = nlgFactory.createNounPhrase("this", "dataset");
        VPPhraseSpec verb1 = nlgFactory.createVerbPhrase("contain");
        NPPhraseSpec obj1 = nlgFactory.createNounPhrase("information");
        PPPhraseSpec prep1 = nlgFactory.createPrepositionPhrase("about", "different types of cars");
        obj1.addComplement(prep1);

        NPPhraseSpec fuelEco = nlgFactory.createNounPhrase("fuel economy");
        NPPhraseSpec car =nlgFactory.createNounPhrase("the", "car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        car.setFeature(Feature.POSSESSIVE, true);
        fuelEco.setSpecifier(car);
        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setFrontModifier("such as");
        s1.setObject(fuelEco);
        s1.setFeature(Feature.APPOSITIVE, true);

        PPPhraseSpec indObj1 = nlgFactory.createPrepositionPhrase("to", "accelerate from 0 to 60 mph");
        VPPhraseSpec verb2 = nlgFactory.createVerbPhrase("take");
        WordElement sub2 = (WordElement) nlgFactory.createWord("it", LexicalCategory.PRONOUN);
        WordElement obj2 = (WordElement) nlgFactory.createWord("them",  LexicalCategory.PRONOUN);
        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setFrontModifier("how long");
        s2.setSubject(sub2);
        s2.setVerb(verb2);
        s2.setObject(obj2);
        s2.setPostModifier(indObj1);
        s2.setFeature(Feature.APPOSITIVE, true);

        NPPhraseSpec s3 = nlgFactory.createNounPhrase("their", "weight");
        s3.setFeature(Feature.APPOSITIVE, true);

        NPPhraseSpec engine = nlgFactory.createNounPhrase("their engine");
        engine.setFeature(Feature.POSSESSIVE, true);
        NPPhraseSpec power = nlgFactory.createNounPhrase("power");
        power.setSpecifier(engine);
        NPPhraseSpec disp = nlgFactory.createNounPhrase("displacement");
        NPPhraseSpec numCyl = nlgFactory.createNounPhrase("number of cylinders");
        CoordinatedPhraseElement obj4 = nlgFactory.createCoordinatedPhrase(power, disp);
        obj4.addCoordinate(numCyl);
        SPhraseSpec s4 = nlgFactory.createClause();
        s4.setSubject(obj4);

        CoordinatedPhraseElement object = nlgFactory.createCoordinatedPhrase(s1, s2);
        object.addCoordinate(s3);
        object.addCoordinate(s4);

        sentence.setSubject(sub1);
        sentence.setVerb(verb1);
        sentence.setObject(obj1);
        sentence.addPostModifier(object);

        String output = realiser.realiseSentence(sentence);
        System.out.println(output);



    }

    public static void main(String[] args) {
        DataStory01 story = new DataStory01();
        story.realise();
    }
}
