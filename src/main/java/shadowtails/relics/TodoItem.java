package shadowtails.relics;

import shadowtails.CharacterFile;

import static shadowtails.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.INDIGO_COLOR);
    }
}
