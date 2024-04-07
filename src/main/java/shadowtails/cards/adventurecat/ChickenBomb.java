package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static shadowtails.ModFile.makeID;

public class ChickenBomb extends AbstractEasyCard {
    public final static String ID = makeID("ChickenBomb");
    public ChickenBomb() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
        tagAsCat();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(player, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(player, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }


    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}