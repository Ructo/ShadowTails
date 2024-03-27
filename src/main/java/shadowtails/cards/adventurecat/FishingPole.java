package shadowtails.cards.adventurecat;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class FishingPole extends AbstractEasyCard {
    public final static String ID = makeID("FishingPole");


    public FishingPole() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 1;
        tagAsCat();


    }
    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}

