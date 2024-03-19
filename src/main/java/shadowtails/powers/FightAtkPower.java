package shadowtails.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static shadowtails.ModFile.makeID;

public class FightAtkPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("FightAtkPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int vigorIncrement;

    public FightAtkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        vigorIncrement = amount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = "When you play an attack, gain " + vigorIncrement + " Vigor and increase this by +" + amount + " this turn.";
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(owner, owner, new VigorPower(owner, vigorIncrement), vigorIncrement));
            vigorIncrement += amount; // Increase Block for the next Skill
            updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        vigorIncrement = amount; // Reset Block increment at the start of each turn
    }
}
