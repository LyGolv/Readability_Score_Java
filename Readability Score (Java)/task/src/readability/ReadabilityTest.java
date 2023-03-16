package readability;

public enum ReadabilityTest {

    ARI {
        @Override
        public double calc(Context c) {
            return Math.round(
                    (4.71 * (c.characters/(float)c.words) + 0.5 * (c.words/(float)c.sentences) - 21.43) * 100.0
            ) / 100.0;
        }
    }, FK {
        @Override
        public double calc(Context c) {
            return Math.round(
                    (0.39 * (c.words/(float)c.sentences) + 11.8 * (c.syllables/(float)c.words) - 15.59) * 100.0
            ) / 100.0;
        }
    }, SMOG {
        @Override
        public double calc(Context c) {
            return Math.round(
                    ((1.043 * Math.sqrt(c.polysyllables * (30/(float)c.sentences)) + 3.1291) * 100.0)
            ) / 100.0;
        }
    }, CL {
        @Override
        public double calc(Context c) {
            return Math.round(
                    (0.0588 * (c.characters/(float)c.words*100)
                            - 0.296 * (c.sentences/(float)c.words*100) - 15.8) * 100.0
            ) / 100.0;
        }
    };

    public abstract double calc(Context c);
}
