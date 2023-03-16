package readability;

public final class Context {
    public int characters;
    public int words;
    public int sentences;
    public int syllables;
    public int polysyllables;

    @Override
    public String toString() {
        return """
                Words: %d
                Sentences: %d
                Characters: %d
                Syllables: %d
                Polysyllables: %d
                """.formatted(words, sentences, characters, syllables, polysyllables);
    }
}
