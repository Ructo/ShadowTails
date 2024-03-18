package shadowtails.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowtails.cards.cardvars.CustomTags;
import shadowtails.util.Wiz;

import static shadowtails.ModFile.makeID;

public class PuppyPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("PuppyPower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PuppyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        {
            this.updateDescription();
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