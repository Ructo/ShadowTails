package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;


import static shadowtails.ModFile.makeID;

public class QuickWit extends AbstractEasyCard {
    public final static String ID = makeID("QuickWit");

    public QuickWit() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 18;
        tags.add(CustomTags.CAT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new NoDrawPower((AbstractCreature) p)));
    }
    public void upp() {
        super.upp();
        upgradeName();
        upgradeBlock(4);
    }
}