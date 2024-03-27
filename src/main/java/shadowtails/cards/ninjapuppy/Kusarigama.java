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

public class Kusarigama extends AbstractEasyCard {
    public final static String ID = makeID("Kusarigama");

    public Kusarigama() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 2;
        tagAsPuppy();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        // Select a random monster (excluding dead ones)
        AbstractMonster randomTarget = AbstractDungeon.getMonsters().getRandomMonster(true);
        // Apply ConstrictedPower to the randomly selected monster, if any
        if (randomTarget != null) {
            addToBot(new ApplyPowerAction(randomTarget, p, new ConstrictedPower(randomTarget, p, magicNumber), magicNumber));
        }
    }

    public void upp() {
        super.upp();
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}