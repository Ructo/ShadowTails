package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class DefendAC extends AbstractEasyCard {
    public final static String ID = makeID("DefendAC");
    // intellij stuff skill, self, basic, , ,  5, 3, , 

    public DefendAC() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        setBlock(5, +3);
        tags.add(CardTags.STARTER_DEFEND);
        tagAsCat();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}