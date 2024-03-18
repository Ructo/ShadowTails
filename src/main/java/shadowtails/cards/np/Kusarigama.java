package shadowtails.cards.np;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class Kusarigama extends AbstractEasyCard {
    public final static String ID = makeID("Kusarigama");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Kusarigama() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.PUPPY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.NONE);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, magicNumber)));
    }

    public void upp() {
        super.upp();
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}