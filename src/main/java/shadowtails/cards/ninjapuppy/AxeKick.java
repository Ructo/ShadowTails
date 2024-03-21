package shadowtails.cards.ninjapuppy;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class AxeKick extends AbstractEasyCard {
    public final static String ID = makeID("AxeKick");

    public AxeKick() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        tags.add(CustomTags.PUPPY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(6);
    }
}