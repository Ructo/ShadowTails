//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package shadowtails.powers;

import shadowtails.ModFile;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import static shadowtails.ModFile.makeID;

public class FeeblePower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("FeeblePower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public FeeblePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    public void atStartOfTurn() {
        this.amount = 0;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.owner.name, "y") + DESCRIPTIONS[1];
        if (this.amount != 0) {
            this.description = this.description + DESCRIPTIONS[2] + this.amount * 5 + DESCRIPTIONS[3];
        }

    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new FeeblePower(this.owner, 1), 1));
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageType.NORMAL ? damage * (1.0F - (float)this.amount * 0.05F) : damage;
    }

    static {
        NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    }
}
