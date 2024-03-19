package shadowtails.cards.both;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.actions.FlipCardsAction;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.AbstractFlipCard;
import shadowtails.cards.both.FightOrFlightAtk;
import shadowtails.powers.FightAtkPower;
import shadowtails.powers.FlightDefPower;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import static shadowtails.CharacterFile.Enums.INDIGO_COLOR;
import static shadowtails.ModFile.makeID;
@NoPools
public class FightOrFlightDef extends AbstractFlipCard {
    public static final String ID = makeID("FightOrFlightDef");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FightOrFlightDef() {
        this(new FightOrFlightAtk(null));
    }

    public FightOrFlightDef(AbstractFlipCard linkedCard) {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF, INDIGO_COLOR);
        baseMagicNumber = magicNumber = 1; // Start with +1 increment
        if (linkedCard == null) {
            this.setLinkedCard(new FightOrFlightAtk(this));
        } else {
            this.setLinkedCard(linkedCard);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        // Pass the current magicNumber as the amount to the power
        p.addPower(new FlightDefPower(p, this.magicNumber));
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
    public AbstractCard makeCopy() {return new FightOrFlightDef(null);
    }
}