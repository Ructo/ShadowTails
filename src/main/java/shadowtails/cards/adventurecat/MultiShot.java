package shadowtails.cards.adventurecat;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import java.util.ArrayList;
import java.util.Iterator;

import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class MultiShot extends AbstractEasyCard {
    public final static String ID = makeID("MultiShot");


    public MultiShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        tags.add(CustomTags.ARROW);
        tagAsCat();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int monsterCount = (int)AbstractDungeon.getMonsters().monsters.stream().filter((monster) -> {
            return !monster.isDeadOrEscaped();
        }).count();
        if (m != null && !m.isDeadOrEscaped()) {
            for(int i = 0; i < monsterCount; ++i) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }
}