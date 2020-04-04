package cn.wode490390.nukkit.stripesgen;

public class StripesGeneratorSettings {

    private final int stripe1Id;
    private final int stripe1Meta;
    private final int stripe2Id;
    private final int stripe2Meta;

    public StripesGeneratorSettings(int stripe1Id, int stripe1Meta, int stripe2Id, int stripe2Meta) {
        this.stripe1Id = stripe1Id;
        this.stripe1Meta = stripe1Meta;
        this.stripe2Id = stripe2Id;
        this.stripe2Meta = stripe2Meta;
    }

    public int getStripe1Id() {
        return this.stripe1Id;
    }

    public int getStripe1Meta() {
        return this.stripe1Meta;
    }

    public int getStripe2Id() {
        return this.stripe2Id;
    }

    public int getStripe2Meta() {
        return this.stripe2Meta;
    }
}
