package shadowtails.cards.ninjapuppy;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class FireMagic extends AbstractEasyCard {
    public final static String ID = makeID("FireMagic");


    public FireMagic() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 3;
        tagAsPuppy();


    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeMagicNumber(1);
    }
}

