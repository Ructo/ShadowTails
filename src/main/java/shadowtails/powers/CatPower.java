package shadowtails.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import shadowtails.cards.cardvars.CustomTags;
import shadowtails.util.Wiz;

import static shadowtails.ModFile.makeID;

public class CatPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("CatPower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public CatPower(AbstractCreature owner, int amount) {

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
