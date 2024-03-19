package shadowtails.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static shadowtails.ModFile.makeID;

public class FlightDefPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("FlightDefPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int blockIncrement;

    public FlightDefPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        blockIncrement = amount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = "When you play a skill, gain " + blockIncrement + " Block and increase this by +" + amount + " this turn.";
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, blockIncrement));
            blockIncrement += amount; // Increase Block for the next Skill
            updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        blockIncrement = amount; // Reset Block increment at the start of each turn
    }
}
