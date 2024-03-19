package shadowtails.cards.ninjapuppy;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class SmokeEscape extends AbstractEasyCard {
    public final static String ID = makeID("SmokeEscape");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SmokeEscape() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 8;
        tags.add(CustomTags.PUPPY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));

        // Check if the last card played was a Skill
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 2)).type == AbstractCard.CardType.SKILL) {
            addToTop((AbstractGameAction)new GainEnergyAction(1));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2); // For example, increases block by 2 upon upgrade
            // Additional upgrade effects can be added here
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1)).type == AbstractCard.CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SmokeEscape();
    }
}