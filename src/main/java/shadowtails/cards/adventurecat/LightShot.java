package shadowtails.cards.adventurecat;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.adventurecat.tempcards.TippedArrow;
import shadowtails.cards.cardvars.CustomTags;
import shadowtails.powers.LightShotPower;

import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class LightShot extends AbstractEasyCard {
    public final static String ID = makeID("LightShot");


    public LightShot() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        tagAsCat();


    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.addPower(new LightShotPower(p, 1));
        this.addToBot(new MakeTempCardInHandAction(new TippedArrow(), this.magicNumber));
    }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeMagicNumber(1);
    }
}

