package shadowtails.cards.adventurecat;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.CustomTags;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import static shadowtails.ModFile.makeID;
import static shadowtails.util.Wiz.atb;

public class MeowshroomBomb extends AbstractEasyCard {
    public final static String ID = makeID("MeowshroomBomb");


    public MeowshroomBomb() {
            super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
            baseDamage = 10;
            baseMagicNumber = magicNumber = 2;
            baseSecondMagic = secondMagic = 3;
        }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply 2 Vulnerable to the targeted enemy.
        atb(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));

        // Using a delayed action to ensure the Vulnerable stack check occurs after Vulnerable has been applied.
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                // Check if the targeted enemy has 3 or more Vulnerable.
                if (m.hasPower(VulnerablePower.POWER_ID) && m.getPower(VulnerablePower.POWER_ID).amount >= 3) {
                    // If so, deal 10 HP loss to all enemies.
                    atb(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(MeowshroomBomb.this.baseDamage, true), DamageInfo.DamageType.HP_LOSS, AttackEffect.POISON));
                }
                this.isDone = true;
            }
        });
    }

        @Override
        public void upgrade() {
            if (!this.upgraded) {
                this.upgradeName();
                this.upgradeMagicNumber(1);
            }
        }
    }

