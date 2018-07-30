package union;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import gov.nih.nlm.nls.lvg.Util.Str;
import gov.nih.nlm.nls.lvg.Util.Word;
import simplenlg.features.Feature;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.*;
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
        sentence2();
//        sentence3();
//        sentence4();
//        sentence5();
//        sentence6();
//        sentence7();
//        sentence8();
//        sentence9();
//        sentence10();
//        sentence11();
//        sentence12();
//        sentence13();
//        sentence14();
//        sentence15();
//        sentence16();
//        sentence17();
//        sentence18();
    }

    /**
     * This dataset contains information about different types of cars, such as the cars' fuel economy, how long it
     * takes them to accelerate from 0 to 60 mph, their weight, and their engine's power, displacement, and number of
     * cylinders.
     **/
    private void sentence1() {

    }

    /**
     * We analyzed the relationships between these dimensions and will now show three complete cause-effect sequences.
     */
    private void sentence2(){
        WordElement we = (WordElement) nlgFactory.createWord("we", LexicalCategory.PRONOUN);
        VPPhraseSpec analyze = nlgFactory.createVerbPhrase("analyze");
        NPPhraseSpec relationship = nlgFactory.createNounPhrase("the", "relationship");
        relationship.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        PPPhraseSpec between = nlgFactory.createPrepositionPhrase("between", "these dimensions");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(we);
        s1.setVerbPhrase(analyze);
        relationship.addPostModifier(between);
        s1.setObject(relationship);
        s1.setFeature(Feature.TENSE, Tense.PAST);

        // Same problem as before
        // I have difficulties in placing the adverb "now" in between "will" and "show"
        VPPhraseSpec show = nlgFactory.createVerbPhrase("show three complete cause-effect sequences");
        show.setPreModifier("now");
        show.setFeature(Feature.TENSE, Tense.FUTURE);

        CoordinatedPhraseElement s2 = nlgFactory.createCoordinatedPhrase(s1, show);

        String output = realiser.realiseSentence(s2);
        System.out.println(output);

    }

    /**
     * View 1 shows the cars' number of cylinders (x-axis) and their displacement (y-axis).
     */
    private void sentence3(){
        NPPhraseSpec view1 = nlgFactory.createNounPhrase("view 1");
        VPPhraseSpec show = nlgFactory.createVerbPhrase("show");
        NPPhraseSpec car = nlgFactory.createNounPhrase("the", "car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        car.setFeature(Feature.POSSESSIVE, true);

        // I am again using the APPOSITIVE feature to generate "x-axis" in between commas rather than brackets.
        NPPhraseSpec x_axis = nlgFactory.createNounPhrase("x-axis");
        x_axis.setFeature(Feature.APPOSITIVE, true);
        NPPhraseSpec cyl = nlgFactory.createNounPhrase("number of", "cylinder");
        cyl.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        cyl.addPostModifier(x_axis);

        // "y-axis" is generated in the same way as "x-axis"
        NPPhraseSpec y_axis = nlgFactory.createNounPhrase("y-axis");
        y_axis.setFeature(Feature.APPOSITIVE, true);
        NPPhraseSpec disp = nlgFactory.createNounPhrase("their", "displacement");
        disp.addPostModifier(y_axis);

        CoordinatedPhraseElement cylDisp = nlgFactory.createCoordinatedPhrase(cyl, disp);
        car.addPostModifier(cylDisp);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(view1);
        s1.setVerbPhrase(show);
        s1.setObject(car);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * For example, the Chevrolet Chevelle Malibu (red dot) has a high number of cylinders and high displacement.
     */
    private void sentence4(){
        NPPhraseSpec chevrolet = nlgFactory.createNounPhrase("the", "Chevrolet Chevelle Malibu");
        AdjPhraseSpec red = nlgFactory.createAdjectivePhrase("red dot");
        red.setFeature(Feature.APPOSITIVE, true);
        chevrolet.addPostModifier(red);
        VPPhraseSpec have = nlgFactory.createVerbPhrase("have");
        CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase("a high number of cylinders", "high displacement");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(chevrolet);
        s1.setVerbPhrase(have);
        s1.setObject(obj);
        s1.setFrontModifier("for example");

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println( output);
    }

    /**
     * The upward trend of the data points suggests that the number of cylinders of a car and its displacement are related.
     */
    private void sentence5(){
        NPPhraseSpec trend = nlgFactory.createNounPhrase("the", "upward trend of the data points");
        VPPhraseSpec suggest = nlgFactory.createVerbPhrase("suggest");
        SPhraseSpec s1 = nlgFactory.createClause(trend, suggest);

        // I am not sure if "number of" should be part of the specifier in this noun phrase
        // When I added it with cylinder, "number of cylinder," the "s" of cylinder would not get realized.
        NPPhraseSpec cyl = nlgFactory.createNounPhrase("the number of", "cylinder");
        cyl.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        cyl.addPostModifier("of a car");

        NPPhraseSpec disp = nlgFactory.createNounPhrase("its", "displacement");
        CoordinatedPhraseElement subj = nlgFactory.createCoordinatedPhrase(cyl, disp);
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be related");
        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(subj);
        s2.setVerbPhrase(be);

        s1.addComplement(s2);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * In fact, these two dimensions are especially correlated (r=0.93): cars with a higher displacement, such as the
     * Chevrolet Chevelle Malibu, also weigh more.
     */
    private void sentence6(){
        WordElement these = (WordElement) nlgFactory.createWord("these", LexicalCategory.DETERMINER);
        these.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        AdjPhraseSpec two = nlgFactory.createAdjectivePhrase("two dimensions");
        two.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        two.addPreModifier(these);

        VPPhraseSpec be = nlgFactory.createVerbPhrase("be");
        AdjPhraseSpec corr = nlgFactory.createAdjectivePhrase("correlated");
        NPPhraseSpec r = nlgFactory.createNounPhrase("r=0.93");
        r.setFeature(Feature.APPOSITIVE, true);
        corr.setPostModifier(r);
        corr.addPreModifier("especially");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(two);
        s1.setVerbPhrase(be);
        s1.setObject(corr);
        s1.setFrontModifier("in fact");

        NPPhraseSpec car = nlgFactory.createNounPhrase("car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        PPPhraseSpec with = nlgFactory.createPrepositionPhrase("with");
        NPPhraseSpec disp = nlgFactory.createNounPhrase("a", "higher displacement");
        AdjPhraseSpec suchAs = nlgFactory.createAdjectivePhrase("such as the Chevrolet Chevelle Malibu");
        suchAs.setFeature(Feature.APPOSITIVE, true);
        disp.addPostModifier(suchAs);
        with.addComplement(disp);
        car.addPostModifier(with);

        // I am not able to generate "also" before the verb, even if I use PreModifier or the FrontModifier.
        VPPhraseSpec weigh = nlgFactory.createVerbPhrase("weigh more");
        weigh.setPreModifier("also");

        // I am using the COMPLEMENTISER feature to generate the colon, however this adds unnecessary
        // spaces in the sentence generated.
        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(car);
        s2.setVerbPhrase(weigh);
        s2.setFeature(Feature.COMPLEMENTISER, ":");

        s1.addComplement(s2);

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * Further analysis shows that this relationship is strong (r=0.95) and indicates that cars with more cylinders also
     * have a higher displacement.
     */
    private void sentence7(){
        NPPhraseSpec analysis = nlgFactory.createNounPhrase("analysis");
        analysis.addPreModifier("further");
        VPPhraseSpec show = nlgFactory.createVerbPhrase("show");

        SPhraseSpec s1 = nlgFactory.createClause(analysis, show);

        NPPhraseSpec rel = nlgFactory.createNounPhrase("this", "relationship");
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be strong (r=0.95)");
        VPPhraseSpec indicate = nlgFactory.createVerbPhrase("indicate");
        CoordinatedPhraseElement verb = nlgFactory.createCoordinatedPhrase(be, indicate);

        NPPhraseSpec car = nlgFactory.createNounPhrase("car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        PPPhraseSpec with = nlgFactory.createPrepositionPhrase("with", "more cylinders");
        car.addPostModifier(with);
        VPPhraseSpec have = nlgFactory.createVerbPhrase("have a higher displacement");
        // I am having the same problem as before
        // I cannot place "also" before the verb
        AdvPhraseSpec also = nlgFactory.createAdverbPhrase("also");
        have.addPreModifier(also);

        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(car);
        s2.setVerbPhrase(have);

        indicate.addComplement(s2);

        SPhraseSpec s3 = nlgFactory.createClause();
        s3.setSubject(rel);
        s3.setVerbPhrase(verb);

        s1.addComplement(s3);

        String output = realiser.realiseSentence(s1);
        System.out.println( output);
    }

    /**
     * Next, let's look at displacement and weight in view 2.
     */
    private void sentence8(){
        PPPhraseSpec atDisp = nlgFactory.createPrepositionPhrase("at", "displacement");
        NPPhraseSpec weight = nlgFactory.createNounPhrase("weight in view 2");
        CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase(atDisp, weight);
        VPPhraseSpec look = nlgFactory.createVerbPhrase("look");
        look.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        SPhraseSpec s1 = nlgFactory.createClause();
        // There should probably be a bettere way of generating "let's" as the subject here
        s1.setSubject("let's");
        s1.setVerbPhrase(look);
        s1.setObject(obj);
        s1.setFrontModifier("next");

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * We have already seen that the Chevrolet Chevelle Malibu (red dot) has a higher displacement than most cars.
     */
    private void sentence9(){
        WordElement we = (WordElement) nlgFactory.createWord("we", LexicalCategory.PRONOUN);
        VPPhraseSpec see = nlgFactory.createVerbPhrase("see");
        see.setPreModifier("already");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(we);
        s1.setVerbPhrase(see);
        s1.setFeature(Feature.PERFECT, true);

        NPPhraseSpec cheverolet = nlgFactory.createNounPhrase("the", "Cheverolet Chevelle Malibu (red dot)");
        VPPhraseSpec have = nlgFactory.createVerbPhrase("have a higher displacement than most cars");
        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(cheverolet);
        s2.setVerbPhrase(have);

        s1.addComplement(s2);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * This plot shows that it is also heavier than most cars.
     */
    private void sentence10(){
        NPPhraseSpec plot = nlgFactory.createNounPhrase("this", "plot");
        VPPhraseSpec show = nlgFactory.createVerbPhrase("show");
        SPhraseSpec s1 = nlgFactory.createClause(plot, show);

        WordElement it = (WordElement) nlgFactory.createWord("it", LexicalCategory.PRONOUN);
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be");

        AdjPhraseSpec heavy = nlgFactory.createAdjectivePhrase("heavy");
        heavy.setFeature(Feature.IS_COMPARATIVE, true);
        heavy.setPreModifier("also");
        heavy.setPostModifier("than most cars");
        SPhraseSpec s2 = nlgFactory.createClause();
        s2.setSubject(it);
        s2.setVerbPhrase(be);
        s2.setObject(heavy);

        s1.addComplement(s2);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);

    }

    /**
     * The upward trend of the data points again suggests a relationship between a car's displacement and its weight.
     */
    private void sentence11(){
        NPPhraseSpec upwardTrend = nlgFactory.createNounPhrase("the",
                "upward trend of the data points again");
        VPPhraseSpec suggest = nlgFactory.createVerbPhrase("suggest");
        NPPhraseSpec relationship = nlgFactory.createNounPhrase("a", "relationship");
        NPPhraseSpec carDisp = nlgFactory.createNounPhrase("a", "car's displacecment");
        NPPhraseSpec weight = nlgFactory.createNounPhrase("its", "weight");
        CoordinatedPhraseElement indObj = nlgFactory.createCoordinatedPhrase(carDisp, weight);
        PPPhraseSpec between = nlgFactory.createPrepositionPhrase("between", indObj);
        relationship.setPostModifier(between);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(upwardTrend);
        s1.setVerbPhrase(suggest);
        s1.setObject(relationship);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * Thus we have seen that more cylinders lead to a higher displacement, which leads to increased weight, which leads to
     * a lower fuel economy.
     */
    private void sentence12(){
        SPhraseSpec s1, s2, s3, s4;

        s1 = nlgFactory.createClause();
        s1.setSubject("which");
        VPPhraseSpec lead = nlgFactory.createVerbPhrase("lead");
        PPPhraseSpec lowFuel = nlgFactory.createPrepositionPhrase("to", "a lower fuel economy");
        s1.setVerbPhrase(lead);
        s1.setIndirectObject(lowFuel);
        // Need to add a comma before "which"
//        s1.setFeature(Feature.COMPLEMENTISER, ",");

        s2 = nlgFactory.createClause();
        s2.setSubject("which");
        s2.setVerbPhrase(nlgFactory.createVerbPhrase("lead to"));
        AdjPhraseSpec increased = nlgFactory.createAdjectivePhrase("increased");
        NPPhraseSpec weight = nlgFactory.createNounPhrase("weight");
        weight.setPreModifier(increased);
        s2.setIndirectObject(weight);

        weight.setPostModifier(s1);

        s3 = nlgFactory.createClause();
        NPPhraseSpec cyl = nlgFactory.createNounPhrase("cylinder");
        cyl.addPreModifier("more");
        cyl.setFeature(Feature.NUMBER,  NumberAgreement.PLURAL);
        s3.setSubject(cyl);
        s3.setVerbPhrase(nlgFactory.createVerbPhrase("lead to"));
        NPPhraseSpec disp = nlgFactory.createNounPhrase("a", "displacement");
        AdjPhraseSpec high = nlgFactory.createAdjectivePhrase("high");
        high.setFeature(Feature.IS_COMPARATIVE, true);
        disp.addPreModifier(high);
        s3.setObject(disp);

        disp.setPostModifier(s2);

        s4 = nlgFactory.createClause();
        s4.setSubject((WordElement) nlgFactory.createWord("we", LexicalCategory.PRONOUN));
        VPPhraseSpec see = nlgFactory.createVerbPhrase("see");
        s4.setVerbPhrase(see);
        s4.setFeature(Feature.PERFECT, true);
        s4.setFrontModifier("thus");

        s4.addComplement(s3);

        String output = realiser.realiseSentence(s4);
        System.out.println(output);
    }

    /**
     * To summarize, we can find the following cause-effect sequences in this dataset.
     */
    private void sentence13(){
        WordElement we = (WordElement) nlgFactory.createWord("we", LexicalCategory.PRONOUN);
        VPPhraseSpec find = nlgFactory.createVerbPhrase("find");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(we);
        s1.setVerbPhrase(find);
        s1.setObject("the following cause-effect sequences in this dataset");
        s1.setFeature(Feature.MODAL, "can");
        s1.setFrontModifier("to summarize");

        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * More cylinders lead to a higher displacement.
     */
    private void sentence14(){
        NPPhraseSpec cyl = nlgFactory.createNounPhrase("cylinder");
        cyl.addPreModifier("more");
        cyl.setFeature(Feature.NUMBER,  NumberAgreement.PLURAL);

        VPPhraseSpec lead = nlgFactory.createVerbPhrase("lead to");
        NPPhraseSpec disp = nlgFactory.createNounPhrase("a", "displacement");
        AdjPhraseSpec high = nlgFactory.createAdjectivePhrase("high");
        high.setFeature(Feature.IS_COMPARATIVE, true);
        disp.addPreModifier(high);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(cyl);
        s1.setVerbPhrase(lead);
        s1.setObject(disp);

        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * The higher the displacement, the heavier the car.
     */
    private void sentence15(){
        SPhraseSpec s1, s2;

        s1 = nlgFactory.createClause();
        NPPhraseSpec disp = nlgFactory.createNounPhrase("the", "displacement");
        s1.setSubject(disp);
        s1.setVerbPhrase(nlgFactory.createVerbPhrase("be"));
        AdjPhraseSpec high = nlgFactory.createAdjectivePhrase("high");
        high.setFeature(Feature.IS_COMPARATIVE, true);
        s1.setObject(high);

        s2 = nlgFactory.createClause();
        NPPhraseSpec car = nlgFactory.createNounPhrase("the", "car");
        s2.setSubject(car);
        s2.setVerbPhrase(nlgFactory.createVerbPhrase("be"));
        AdjPhraseSpec heavy = nlgFactory.createAdjectivePhrase("heavy");
        heavy.setFeature(Feature.IS_COMPARATIVE, true);
        s2.setObject(heavy);

        // The code should probably read something as below.
//        s1.addComparativeCorrelative(s2);
        s1.addComplement(s2);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * And heavier cars have worse fuel economy, but need less time to accelerate.
     */
    private void sentence16(){
        NPPhraseSpec car = nlgFactory.createNounPhrase("car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        AdjPhraseSpec heavy = nlgFactory.createAdjectivePhrase("heavy");
        heavy.setFeature(Feature.IS_COMPARATIVE, true);
        car.addPreModifier(heavy);
        VPPhraseSpec have = nlgFactory.createVerbPhrase("have");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(car);
        s1.setVerbPhrase(have);
        s1.setObject("worse fuel economy");
        s1.setFrontModifier("and");

        VPPhraseSpec need = nlgFactory.createVerbPhrase("need less time to accelerate");
        need.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        CoordinatedPhraseElement s2 = nlgFactory.createCoordinatedPhrase(s1, need);
        // I am trying to add "but" as a conjunction
        // I also added the comma in the conjunction here but this is probably something that needs to be worked on.
        s2.setConjunction(", but");

        realiser.setCommaSepCuephrase(false);
        String output = realiser.realiseSentence(s2);
        System.out.println(output);
    }

    /**
     * In addition, a higher displacement also leads to greater power.
     */
    private void sentence17(){
        NPPhraseSpec disp = nlgFactory.createNounPhrase("a", "displacement");
        AdjPhraseSpec high = nlgFactory.createAdjectivePhrase("high");
        high.setFeature(Feature.IS_COMPARATIVE, true);
        disp.addPreModifier(high);

        VPPhraseSpec lead = nlgFactory.createVerbPhrase("lead to");
//        lead.setPreModifier("also");
        // This way of adding "also" gave the correct structuring of the sentence,
        // however I think the code commented out should have been the way to do it.
        // This syntax seems weird.
        disp.setPostModifier("also");

        NPPhraseSpec power = nlgFactory.createNounPhrase("power");
        AdjPhraseSpec great = nlgFactory.createAdjectivePhrase("great");
        great.setFeature(Feature.IS_COMPARATIVE, true);
        power.setPreModifier(great);

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(disp);
        s1.setVerbPhrase(lead);
        s1.setObject(power);
        s1.setFrontModifier("in addition");

        realiser.setCommaSepCuephrase(true);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    /**
     * And cars with a greater power also need less time to accelerate.
     */
    private void sentence18(){
        NPPhraseSpec car = nlgFactory.createNounPhrase("car");
        car.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        PPPhraseSpec with = nlgFactory.createPrepositionPhrase("with", "a greater power");
        car.setPostModifier(with);

        VPPhraseSpec need = nlgFactory.createVerbPhrase("need");
        need.addPreModifier("also");

        SPhraseSpec s1 = nlgFactory.createClause();
        s1.setSubject(car);
        s1.setVerbPhrase(need);
        s1.setObject("less time to accelerate");
        s1.setFrontModifier("and");

        realiser.setCommaSepCuephrase(false);
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    public static void main(String[] args) {
        DataStory02 story = new DataStory02();
        story.realise();
    }
}
