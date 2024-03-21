package shadowtails.cards.both;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowtails.actions.FlipCardsAction;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.AbstractFlipCard;
import shadowtails.powers.FightAtkPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.SELF;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;
import static shadowtails.CharacterFile.Enums.INDIGO_COLOR;
import static shadowtails.ModFile.makeID;

public class GauntletsOfStrength extends AbstractEasyCard {
    public static final String ID = makeID("GauntletsOfStrength");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public GauntletsOfStrength() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new StrengthPower((AbstractCreature) p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}