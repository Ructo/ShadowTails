package shadowtails.cards.adventurecat.tempcards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class TippedArrow extends AbstractEasyCard {
    public final static String ID = makeID("TippedArrow");

    public TippedArrow() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 1;
        tags.add(CustomTags.ARROW);
        tagAsCat();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            // Upgrade the card's effects.
        }
    }
}