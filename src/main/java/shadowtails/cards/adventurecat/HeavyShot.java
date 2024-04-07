package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowtails.cards.AbstractEasyCard;

import static shadowtails.ModFile.makeID;

public class HeavyShot extends AbstractEasyCard {
    public final static String ID = makeID("Enflame");

    public HeavyShot() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;
        magicNumber = baseMagicNumber = 2;
        tagAsCat();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    public void upp() {
        super.upp();
        upgradeName();
        upgradeDamage(4);
    }
}