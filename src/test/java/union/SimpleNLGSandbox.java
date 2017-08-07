package union;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class SimpleNLGSandbox {

    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    private Realiser realiser;

    public SimpleNLGSandbox() {
        lexicon = Lexicon.getDefaultLexicon();
        nlgFactory = new NLGFactory(lexicon);
        realiser = new Realiser(lexicon);
    }

    private void realiseString() {
        NLGElement s1 = nlgFactory.createSentence("my dog is happy");
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
    }

    private void realiseSimpleSentence() {
        SPhraseSpec p = nlgFactory.createClause();
        p.setSubject("Mary");
        p.setVerb("chase");
        p.setObject("the monkey");
        String output2 = realiser.realiseSentence(p); // Realiser created earlier.
        System.out.println(output2);
    }

    private void realiseCoordinatedSentence() {
        SPhraseSpec s1 = nlgFactory.createClause("my cat", "like", "fish");
        SPhraseSpec s2 = nlgFactory.createClause("my dog", "like", "big bones");
        SPhraseSpec s3 = nlgFactory.createClause("my horse", "like", "grass");

        // Look at framework/CoordinatedPhraseElement and at syntax/english/CoordinatedPhraseHelper.
        CoordinatedPhraseElement c = nlgFactory.createCoordinatedPhrase();
        c.addCoordinate(s1);
        c.addCoordinate(s2);
        c.addCoordinate(s3);
        String output = realiser.realiseSentence(c);
        System.out.println(output);
    }

    private void realiseSubordinatedSentenceAsComplement() {
        SPhraseSpec p = nlgFactory.createClause("I", "be", "happy");
        SPhraseSpec q = nlgFactory.createClause("I", "eat", "fish");

        q.setFeature(Feature.COMPLEMENTISER, "because");
        q.setFeature(Feature.TENSE, Tense.PAST);
        p.addComplement(q);

        String output4 = realiser.realiseSentence(p);
        System.out.println(output4);
    }

    private void realiseSubordinatedSentenceAsModifier() {
        AdjPhraseSpec happy = nlgFactory.createAdjectivePhrase("happy");
        happy.addModifier("very");
        SPhraseSpec main = nlgFactory.createClause("I", "be", happy);
        SPhraseSpec sub = nlgFactory.createClause("I", "eat", "fish");

        sub.setFeature(Feature.COMPLEMENTISER, "if");
        sub.setFeature(InternalFeature.CLAUSE_STATUS, ClauseStatus.SUBORDINATE);
        main.addFrontModifier(sub); // --> If I eat fish I am happy.
        //main.addModifier(sub); // I am happy if I eat fish.
        main.addFrontModifier("generally");
        // Todo: order in which front/post modifiers are added determines order in realisation.
        // Todo: With front modifiers, commas are messed up.


        String output4 = realiser.realiseSentence(main);
        System.out.println(output4);
    }

    private void realiseComparativeCorrelative() {
        /* This doesn't work. I was just playing around to see what does work. */
        AdjPhraseSpec happy = nlgFactory.createAdjectivePhrase("happy");
        happy.setFeature(Feature.IS_COMPARATIVE, true);
        SPhraseSpec main = nlgFactory.createClause("I", "be", happy);

        NPPhraseSpec fish = nlgFactory.createNounPhrase("fish");
        //fish.setFeature(Feature.IS_COMPARATIVE, true);
        //fish.setSpecifier("more");
        SPhraseSpec sub = nlgFactory.createClause("I", "eat", fish);
        AdvPhraseSpec often = nlgFactory.createAdverbPhrase("often");
        often.setFeature(Feature.IS_COMPARATIVE, true);
        sub.addModifier(often);


        // Instead of creating a CoordinatedPhraseElement create a CorrelativeComparativePhrase (make a new class for that in framework)
        CoordinatedPhraseElement c = nlgFactory.createCoordinatedPhrase(sub, main);
        c.setFeature(Feature.CONJUNCTION, ",");
        // SyntaxProcessor.realise needs a branch to deal with CorrelativeComparativePhrases; and a
        // CorrelativeComparativeHelper class is needed to re-shuffle the words appropriately.
        // We also need a way of marking in main and sub what the parallel comparative phrases are that need to get
        // moved to the front.
        String output = realiser.realiseSentence(c);
        System.out.println(output);
    }

    public static void main(String[] args) {
        SimpleNLGSandbox sandbox = new SimpleNLGSandbox();
        //sandbox.realiseString();
        //sandbox.realiseSimpleSentence();
        //sandbox.realiseCoordinatedSentence();
        //sandbox.realiseSubordinatedSentenceAsComplement();
        //sandbox.realiseSubordinatedSentenceAsModifier();
        sandbox.realiseComparativeCorrelative();
    }
}
