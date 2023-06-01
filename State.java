class State {
    private final String s;
    private int position;

    public State(String s) {
        this.s = s;
        this.position = 0;
    }

    public final Character peek() {
        if (position < s.length()) {
            return s.charAt(position);
        }
        return '\0';
    }

    public final void next() {
        position++;
    }
}