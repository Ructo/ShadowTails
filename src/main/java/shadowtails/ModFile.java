package shadowtails;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowtails.powers.CatPower;
import shadowtails.powers.PuppyPower;
import shadowtails.cards.AbstractEasyCard;
import shadowtails.cards.cardvars.AbstractEasyDynamicVariable;
import shadowtails.cards.cardvars.CustomTags;
import shadowtails.potions.AbstractEasyPotion;
import shadowtails.relics.AbstractEasyRelic;
import shadowtails.util.ProAudio;
import shadowtails.util.Wiz;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ModFile implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnCardUseSubscriber,
        AddAudioSubscriber {

    public static final String modID = "shadowtails";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = makeCharacterPath("mainChar/shoulder.png");
    public static final String SHOULDER2 = makeCharacterPath("mainChar/shoulder2.png");
    public static final String CORPSE = makeCharacterPath("mainChar/corpse.png");
    private static String ATTACK_S_ART;
    private static String SKILL_S_ART;
    private static String POWER_S_ART;
    private static String ATTACK_L_ART;
    private static String SKILL_L_ART;
    private static String POWER_L_ART;
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }
    public static void setCardArtPaths(String cardPath) {
        // Check the card path and set the art paths accordingly
        if (cardPath.contains("adventurecat")) {
            ATTACK_S_ART = makeImagePath("512/attack_cat.png");
            SKILL_S_ART = makeImagePath("512/skill_cat.png");
            POWER_S_ART = makeImagePath("512/power_cat.png");
            ATTACK_L_ART = makeImagePath("1024/attack_cat2.png");
            SKILL_L_ART = makeImagePath("1024/skill_cat2.png");
            POWER_L_ART = makeImagePath("1024/power_cat2.png");
        } else if (cardPath.contains("ninjapuppy")) {
            ATTACK_S_ART = makeImagePath("512/attack_puppy.png");
            SKILL_S_ART = makeImagePath("512/skill_puppy.png");
            POWER_S_ART = makeImagePath("512/power_puppy.png");
            ATTACK_L_ART = makeImagePath("1024/attack_puppy2.png");
            SKILL_L_ART = makeImagePath("1024/skill_puppy2.png");
            POWER_L_ART = makeImagePath("1024/power_puppy2.png");
        } else {
            ATTACK_S_ART = makeImagePath("512/attack.png");
            SKILL_S_ART = makeImagePath("512/skill.png");
            POWER_S_ART = makeImagePath("512/power.png");
            ATTACK_L_ART = makeImagePath("1024/attack.png");
            SKILL_L_ART = makeImagePath("1024/skill.png");
            POWER_L_ART = makeImagePath("1024/power.png");
        }
    }


    public ModFile() {
        BaseMod.subscribe(this);

        BaseMod.addColor(CharacterFile.Enums.INDIGO_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath) {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        ModFile thismod = new ModFile();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new CharacterFile(CharacterFile.characterStrings.NAMES[1], CharacterFile.Enums.ACNP),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, CharacterFile.Enums.ACNP);

        new AutoAdd(modID)
                .packageFilter(AbstractEasyPotion.class)
                .any(AbstractEasyPotion.class, (info, potion) -> {
                    if (potion.pool == null)
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
                    else
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
                });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyDynamicVariable.class)
                .any(DynamicVariable.class, (info, var) ->
                        BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if (abstractCard.hasTag(CustomTags.CAT)) {
            if (currentPower.equals(CatPower.POWER_ID)) {
                if (abstractCard.type == AbstractCard.CardType.SKILL) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, 1));
                }
            } else {
                // If Cat is not the current power, draw 1 card and switch to Cat power
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, 1));
                setCurrentPower(CatPower.POWER_ID);
            }
        } else if (abstractCard.hasTag(CustomTags.PUPPY)) {
            if (currentPower.equals(PuppyPower.POWER_ID)) {
                if (abstractCard.type == AbstractCard.CardType.ATTACK) {
                    // If Puppy is active and the card is an Attack, deal 1 additional damage to a random enemy
                    AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(player, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
            } else {
                // If Puppy is not the current power, deal 2 damage to a random enemy and switch to Puppy power
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(player, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                setCurrentPower(PuppyPower.POWER_ID);
            }
        }
        // Cards not tagged with CAT or PUPPY do not change or trigger the currentPower.
    }

    String currentPower = "";

    private void setCurrentPower(String power) {
        // Only attempt to remove a power if the currentPower is set to a recognized power ID
        if (!this.currentPower.isEmpty()) {
            if (this.currentPower.equals(CatPower.POWER_ID)) {
                Wiz.atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, CatPower.POWER_ID));
            } else if (this.currentPower.equals(PuppyPower.POWER_ID)) {
                Wiz.atb(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, PuppyPower.POWER_ID));
            }
        }

        // Apply the new power if it's different from the current power or if no power is currently active
        if (!this.currentPower.equals(power)) {
            if (power.equals(CatPower.POWER_ID)) {
                AbstractPower newPower = new CatPower(AbstractDungeon.player, 1); // Assuming parameters as necessary
                Wiz.atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, newPower));
            } else if (power.equals(PuppyPower.POWER_ID)) {
                AbstractPower newPower = new PuppyPower(AbstractDungeon.player, 1); // Adjust as necessary
                Wiz.atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, newPower));
            }

            // Update the currentPower to the new power
            this.currentPower = power;
        }
    }
}