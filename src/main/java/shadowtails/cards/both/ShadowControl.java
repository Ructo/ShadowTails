package shadowtails.cards.both;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.actions.FlipCardsAction;
import shadowtails.cards.AbstractFlipCard;
import shadowtails.powers.FightAtkPower;

import static shadowtails.CharacterFile.Enums.INDIGO_COLOR;
import static shadowtails.ModFile.makeID;

public class ShadowControl extends AbstractFlipCard {
    public static final String ID = makeID("ShadowControl");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ShadowControl() {
        this(new ShadowControlReturn(null));
    }

    public ShadowControl(AbstractFlipCard linkedCard) {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF, INDIGO_COLOR);
        baseMagicNumber = magicNumber = 1;
        if (linkedCard == null) {
            this.setLinkedCard(new ShadowControlReturn(this));
        } else {
            this.setLinkedCard(linkedCard);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DoubleYourBlockAction((AbstractCreature)p));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.cardsToPreview.upgrade();
        }
    }
    @Override
    public void onRightClick() {
        if (AbstractDungeon.player != null && !AbstractDungeon.isScreenUp) {
            AbstractCard newCard = this.cardsToPreview.makeStatEquivalentCopy();
            AbstractDungeon.actionManager.addToBottom(new FlipCardsAction(this, newCard));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new ShadowControl(null);
    }
}
