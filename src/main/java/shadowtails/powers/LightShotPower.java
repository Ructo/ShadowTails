package shadowtails.powers;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowtails.cards.adventurecat.MultiShot;
import shadowtails.cards.cardvars.CustomTags;
import shadowtails.util.Wiz;

import static shadowtails.ModFile.makeID;

public class LightShotPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("LightShotPower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
public LightShotPower(AbstractCreature owner, int amount) {

    super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    this.amount = amount;
    this.type = PowerType.BUFF;
    this.isTurnBased = true;
}
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.tags.contains(CustomTags.ARROW)) {
            // Special handling for MultiShot
            if (card.cardID.equals(MultiShot.ID)) {
                AbstractMonster targetMonster = (AbstractMonster) action.target;
                if (targetMonster != null && !targetMonster.isDeadOrEscaped()) {
                    int monsterCount = (int) AbstractDungeon.getMonsters().monsters.stream().filter(mo -> !mo.isDeadOrEscaped()).count();
                    // Apply the effect once for each monster, to the single target
                    for (int i = 0; i < monsterCount; i++) {
                        addToBot(new ApplyPowerAction(targetMonster, this.owner, new WeakPower(targetMonster, this.amount, false), this.amount));
                        addToBot(new ApplyPowerAction(targetMonster, this.owner, new FeeblePower(targetMonster, 0), 1));
                    }
                }
            } else {
                // Original switch-case handling for other cards
                switch (card.target) {
                    case ENEMY: // Single target cards
                        AbstractMonster targetMonster = (AbstractMonster) action.target;
                        if (targetMonster != null && !targetMonster.isDeadOrEscaped()) {
                            addToBot(new ApplyPowerAction(targetMonster, this.owner, new WeakPower(targetMonster, this.amount, false), this.amount));
                        }
                        break;
                    case ALL_ENEMY: // Cards targeting all enemies
                        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                            if (!m.isDeadOrEscaped()) {
                                addToBot(new ApplyPowerAction(m, this.owner, new WeakPower(m, this.amount, false), this.amount));
                            }
                        }
                        break;
                    // Add cases for other targets if needed
                }
            }
        }
    }

@Override
public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    static {
        NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    }
}
