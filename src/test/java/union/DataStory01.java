package union;

import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Tense;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
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
        sentence2();
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
//        It would be ideal if we could use a noun phrase for this part,
//        however the FrontModifier "such as" does not get realized.
//        fuelEco.setFrontModifier("such as");
//        fuelEco.setFeature(Feature.APPOSITIVE, true);
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

        CoordinatedPhraseElement object = nlgFactory.createCoordinatedPhrase(fuelEco, s2);
        object.addCoordinate(s3);
        object.addCoordinate(s4);

        sentence.setSubject(sub1);
        sentence.setVerb(verb1);
        sentence.setObject(obj1);
        sentence.addPostModifier(object);

        String output = realiser.realiseSentence(sentence);
        System.out.println(output);

    }

    /**
     * This graph shows that more fuel efficient cars (cars with a higher gas mileage, shown in red) tend
     * to take longer to accelerate from 0 to 60 mph.
     */
    private void sentence2() {
        VPPhraseSpec verb1 = nlgFactory.createVerbPhrase("show");
        VPPhraseSpec verb2 = nlgFactory.createVerbPhrase("tend");
        VPPhraseSpec verb3 = nlgFactory.createVerbPhrase("take");
        verb3.setFeature(Feature.FORM, Form.INFINITIVE);
        VPPhraseSpec verb4 = nlgFactory.createVerbPhrase("accelerate");
        verb4.setFeature(Feature.FORM, Form.INFINITIVE);
        VPPhraseSpec verb5 = nlgFactory.createVerbPhrase("show");

        NPPhraseSpec sub1 = nlgFactory.createNounPhrase("this", "graph");
        NPPhraseSpec sub2 = nlgFactory.createNounPhrase("more fuel efficient", "car");
        sub2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        NPPhraseSpec cars = nlgFactory.createNounPhrase("cars with a higher gas mileage");
        AdvPhraseSpec shown = nlgFactory.createAdverbPhrase("shown in red");
        shown.setFeature(Feature.APPOSITIVE, true);
        cars.setPostModifier(shown);
        cars.setFeature(Feature.APPOSITIVE, true);

        sub2.setPostModifier(cars);

        AdjPhraseSpec adj1 = nlgFactory.createAdjectivePhrase("long");
        adj1.setFeature(Feature.IS_COMPARATIVE, true);
        adj1.setPostModifier(verb4);

        PPPhraseSpec prep1 = nlgFactory.createPrepositionPhrase("from 0 to 60 mph");
        verb4.setPostModifier(prep1);

        verb3.setPostModifier(adj1);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(sub1);
        s1.setVerb(verb1);

        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(sub2);
        s2.setVerb(verb2);
        verb2.setPostModifier(verb3);

        s1.addComplement(s2);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }


        public static void main(String[] args) {
        DataStory01 story = new DataStory01();
        story.realise();
    }
}
