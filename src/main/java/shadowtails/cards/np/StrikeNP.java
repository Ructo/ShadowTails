package shadowtails.cards.np;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;

import static shadowtails.ModFile.makeID;

public class StrikeNP extends AbstractEasyCard {
    public final static String ID = makeID("StrikeNP");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public StrikeNP() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        setDamage(6, +3);
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CustomTags.PUPPY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }
}