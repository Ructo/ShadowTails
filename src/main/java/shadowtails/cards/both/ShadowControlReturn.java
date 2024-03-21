package shadowtails.cards.both;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
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

public class ShadowControlReturn extends AbstractFlipCard {
    public static final String ID = makeID("ShadowControl");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ShadowControlReturn() {
        this(new ShadowControl(null));
    }

    public ShadowControlReturn(AbstractFlipCard linkedCard) {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF, INDIGO_COLOR);
        baseMagicNumber = magicNumber = 4;
        if (linkedCard == null) {
            this.setLinkedCard(new ShadowControl(this));
        } else {
            this.setLinkedCard(linkedCard);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.discardPile.isEmpty()) {
            addToBot((AbstractGameAction)new EmptyDeckShuffleAction());
            addToBot((AbstractGameAction)new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
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
        return new ShadowControlReturn(null);
    }
}
