package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class GiantHammer extends AbstractEasyCard {
    public final static String ID = makeID("GiantHammer");


    public GiantHammer() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 2;
        tagAsCat();


    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(m, new DamageInfo(p, baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        int halfDamage = this.baseDamage / this.magicNumber; // Calculate half damage based on your base damage and magic number
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo != m) { // Check if the monster is alive and not the primary target
                atb(new DamageAction(mo, new DamageInfo(p, halfDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(6);
    }
}

