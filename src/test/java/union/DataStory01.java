package union;

import gov.nih.nlm.nls.lvg.Util.Str;
import simplenlg.features.*;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
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
        sentence2();
        sentence3();
        sentence4();
        sentence5();
        sentence6();
        sentence7();
        sentence8();
        sentence9();
        sentence10();
        sentence11();
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

        NPPhraseSpec sub1 = nlgFactory.createNounPhrase("this", "graph");
        NPPhraseSpec sub2 = nlgFactory.createNounPhrase("more fuel efficient", "car");
        sub2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        // We are using commas instead of brackets here
        // The APPOSITIVE feature could probably be later altered so as to provide an option to add brackets
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

    /**
     * Ideally, we want cars that have a good fuel economy (i.e. can travel more miles per gallon of gas) and at the same
     * time can accelerate quickly (i.e. need fewer seconds to get from 0 to 60 mph).
     */
    private void sentence3(){
        WordElement sub1 = (WordElement) nlgFactory.createWord("we", LexicalCategory.PRONOUN);
        VPPhraseSpec verb1 = nlgFactory.createVerbPhrase("want");
        NPPhraseSpec obj1 = nlgFactory.createNounPhrase("car");
        obj1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        VPPhraseSpec subClause1 = nlgFactory.createVerbPhrase("have a good fuel economy");
//        subClause1.setFeature(Feature.PERSON, Person.THIRD);
        subClause1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
//        subClause1.setPostModifier("a good fuel economy");

        //(i.e. can travel more miles per gallon of gas)
        SPhraseSpec descp1 = nlgFactory.createClause("i.e.", "can",  "travel more miles per gallon of gas");
        descp1.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        descp1.setFeature(Feature.APPOSITIVE, true);
        subClause1.setPostModifier(descp1);

        PPPhraseSpec subClause2 = nlgFactory.createPrepositionPhrase("can accelerate quickly");
//        subClause2.setFeature(Feature.PERSON, Person.THIRD);
        subClause2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
//        subClause2.addPostModifier("accelerate quickly");

        //(i.e. need fewer seconds to get from 0 to 60 mph)
        SPhraseSpec descp2 = nlgFactory.createClause("i.e.", "need", "fewer seconds to get from 0 to 60 mph");
        descp2.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        descp2.setFeature(Feature.APPOSITIVE, true);
        subClause2.addPostModifier(descp2);


        CoordinatedPhraseElement subClause = nlgFactory.createCoordinatedPhrase(subClause1, subClause2);
        subClause.setConjunction("and at the same time");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setIndirectObject(subClause);
//        s1.setFeature(Feature.COMPLEMENTISER, "that");

        SPhraseSpec sentence = nlgFactory.createClause();
        sentence.setSubject(sub1);
        sentence.setVerb(verb1);
        sentence.setObject(obj1);
        sentence.setFrontModifier("ideally");

        sentence.addComplement(s1);

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(sentence);
        System.out.println(output);

    }

    /**
     * Cars that satisfy both constraints are shown in purple.
     */
    private void sentence4(){
        SPhraseSpec satisfy = nlgFactory.createClause();
        satisfy.setVerb("satisfy");
        satisfy.setObject("both constraints");

        satisfy.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        VPPhraseSpec show = nlgFactory.createVerbPhrase("show");
        PPPhraseSpec inPurple = nlgFactory.createPrepositionPhrase("in", "purple");
        show.addModifier(inPurple);
//        show.setFeature(Feature.PASSIVE, true);
        NPPhraseSpec car = nlgFactory.createNounPhrase("car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        car.addComplement(satisfy);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setVerbPhrase(show);
        s1.setObject(car);
        s1.setFeature(Feature.PASSIVE, true);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * Let's explore what type of engineering decision influences a car's fuel efficiency and acceleration.
     */
    private void sentence5(){
        VPPhraseSpec explore = nlgFactory.createVerbPhrase("explore");
        explore.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        NPPhraseSpec type = nlgFactory.createNounPhrase("what type of engineering decision");
        VPPhraseSpec influence = nlgFactory.createVerbPhrase("influence");
        NPPhraseSpec car = nlgFactory.createNounPhrase("a", "car");
        car.setFeature(Feature.POSSESSIVE, true);
        NPPhraseSpec fuelEff = nlgFactory.createNounPhrase("fuel efficiency");
        fuelEff.setSpecifier(car);
        NPPhraseSpec acc = nlgFactory.createNounPhrase("acceleration");
        CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase(fuelEff, acc);

        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(type);
        s2.setVerbPhrase(influence);
        s2.setObject(obj);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject("Let's");
        s1.setVerbPhrase(explore);
        s1.addPostModifier(s2);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * Fuel efficiency can somewhat be improved by decreasing a car's weight.
     */
    private void sentence6(){
//        VPPhraseSpec decrease = nlgFactory.createVerbPhrase("decrease");
//        decrease.addPostModifier("a car's weight");
//        decrease.setFeature(Feature.FORM, Form.GERUND);
//        NPPhraseSpec decreasing = nlgFactory.createNounPhrase(decrease);
        NPPhraseSpec decreasing = nlgFactory.createNounPhrase("decreasing a car's weight");
        VPPhraseSpec improve = nlgFactory.createVerbPhrase("improve");
        improve.setFeature(Feature.MODAL, "can somewhat");
        NPPhraseSpec fuelEff = nlgFactory.createNounPhrase("fuel efficiency");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(decreasing);
        s1.setVerbPhrase(improve);
        s1.setObject(fuelEff);
        s1.setFeature(Feature.PASSIVE, true);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * This can be achieved by decreasing its engine's displacement, which is a result of decreasing the number of
     * cylinders.
     */
    private void sentence7() {
//        WordElement _this = (WordElement) nlgFactory.createWord("this", LexicalCategory.PRONOUN);
        NPPhraseSpec _this = nlgFactory.createNounPhrase("this");
        NPPhraseSpec decreasing = nlgFactory.createNounPhrase("decreasing its engine's displacement");
        VPPhraseSpec achieve = nlgFactory.createVerbPhrase("achieve");
        achieve.setFeature(Feature.MODAL, "can");

        SPhraseSpec s2 = nlgFactory.createClause("which", "be",
                "a result of decreasing the number of cylinders");
        // I am using a comma as the complementiser in this example for subordinate clause.
        // There must probably be a better way to do it
        s2.setFeature(Feature.COMPLEMENTISER, ",");
        decreasing.addComplement(s2);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(decreasing);
        s1.setVerbPhrase(achieve);
        s1.setObject(_this);

        s1.setFeature(Feature.PASSIVE, true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * For example, the Volkswagen Scirocco has few cylinders, low engine displacement, low weight, and a good fuel
     * efficiency.
     */
    private void sentence8(){
        NPPhraseSpec volkswagen = nlgFactory.createNounPhrase("the", "Volkswagen Scirocco");
        VPPhraseSpec have = nlgFactory.createVerbPhrase("have");
        CoordinatedPhraseElement objects = nlgFactory.createCoordinatedPhrase("few cylinders",
                "low engine displacement");
        objects.addCoordinate("low weight");
        objects.addCoordinate("good fuel efficiency");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(volkswagen);
        s1.setVerbPhrase(have);
        s1.setObject(objects);
        s1.addFrontModifier("for example");

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * [...]
     */
    private void sentence9(){
        DocumentElement sentence = nlgFactory.createSentence("[...]");

        String output = realiser.realiseSentence(sentence);
        System.out.println(output);

    }

    /**
     * To sum up, this analysis suggests that there are two ways to improve acceleration: we can increase a car's power or
     * decrease its weight.
     */
    private void sentence10(){
        SPhraseSpec s1, s2, s3;
        s2 = nlgFactory.createClause("there", "be");
        NPPhraseSpec obj = nlgFactory.createNounPhrase("two ways to improve acceleration");
        obj.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        s3 = nlgFactory.createClause("we", "can", "increase a car's power");
        VPPhraseSpec s4 = nlgFactory.createVerbPhrase("decrease its weight");

        CoordinatedPhraseElement s5 = nlgFactory.createCoordinatedPhrase(s3, s4);
        s5.setConjunction("or");
        s5.setFeature(Feature.COMPLEMENTISER, ":");

        s1 = nlgFactory.createClause("this analysis", "suggest");
        s1.addFrontModifier("to sum up");
        realiser.setCommaSepCuephrase(true);

        s2.addComplement(s5);
        s1.addComplement(s2);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * However, only decreasing its weight, will also improve the car's fuel efficiency.
     */
    private void sentence11(){
        PPPhraseSpec subj = nlgFactory.createPrepositionPhrase("only", "decreasing its weight");
        subj.setFeature(Feature.APPOSITIVE, true);

        VPPhraseSpec obj1 = nlgFactory.createVerbPhrase("improve the car's fuel efficiency");
        obj1.setFeature(Feature.TENSE, Tense.FUTURE);

        // I could not manage to generate the "also" in between "will" and "improve" in the sentence.
        CoordinatedPhraseElement s2 = nlgFactory.createCoordinatedPhrase(subj, obj1);
        s2.setFeature(Feature.CONJUNCTION, "also");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setFrontModifier("however");
        s1.setSubject(s2);

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

        public static void main(String[] args) {
        DataStory01 story = new DataStory01();
        story.realise();
    }
}
