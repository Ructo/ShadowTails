package shadowtails.cards.ninjapuppy;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class ChainWrap extends AbstractEasyCard {
    public final static String ID = makeID("ChainWrap");

    public ChainWrap() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 4;
        tagAsPuppy();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, magicNumber), magicNumber));
    }
    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}