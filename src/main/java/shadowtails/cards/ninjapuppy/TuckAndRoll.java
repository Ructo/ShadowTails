package shadowtails.cards.ninjapuppy;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import shadowtails.ModFile;
import shadowtails.cards.AbstractEasyCard;

import static shadowtails.ModFile.makeID;

public class TuckAndRoll extends AbstractEasyCard {
    public static final String ID = makeID("TuckAndRoll");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public TuckAndRoll() {
        super(ID, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = 4;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.blck();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        this.upgradeBlock(2);
        this.initializeDescription();
    }
}
