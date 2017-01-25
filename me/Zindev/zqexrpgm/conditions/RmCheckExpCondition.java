package me.Zindev.zqexrpgm.conditions;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestCondition;
import me.Zindev.zquest.objects.extension.QuestConditionMark;
import net.flamedek.rpgme.RPGme;
import net.flamedek.rpgme.RPGmeAPI;
import net.flamedek.rpgme.skills.SkillType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@QuestConditionMark(conditionID = "checkRPGmeExp")
public class RmCheckExpCondition extends QuestCondition{
	private static final long serialVersionUID = 1L;
	private Integer amount;
	private String skillType;
	private String operation;
	public RmCheckExpCondition() {
		setVariables(new String[3]);
		setVariable("<name>", "name of the skill", 0);
		setVariable("<amount>", "condition amount", 1);
		setVariable("<operation>", "operation's name", 2);
		operation = "EQUAL";
		skillType = SkillType.STAMINA.readableName();
		amount = 5;
		setDisplayName("&eYour <name>'s EXP must <operation> to <amount>");
		setErrMessage("&eYour <name>'s EXP is not <operation> to <amount>");
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amount).replaceAll("<name>", skillType)
				.replaceAll("<operation>", operation);
	}
	@Override
	public String getErrMessage() {
		return super.getErrMessage().replaceAll("<amount>", ""+amount).replaceAll("<name>", skillType)
				.replaceAll("<operation>", operation);
	}

	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&eCheck EXP of a skill",
				"&ewith given operations."
				));
	}
	@Override
	public boolean check(Player p) {
		RPGmeAPI api = RPGme.getAPI();
		int current = Math.round(api.getExp(p, SkillType.getByAlias(skillType)));
		switch (operation) {
		case "EQUAL":
			return amount == current;
		case "LARGER":
			return current > amount;
		case "SMALLER":
			return current < amount;
		case "LARGER_EQUAL":
			return current >= amount;
		case "SMALLER_EQUAL":
			return current <= amount;
		default:
			break;
		}
		
		return false;
	}
	@Override
	public String getWikiName() {
		return "&6"+getID();
	}
	@Override
	public Material getWikiMaterial() {
		return Material.IRON_SWORD;
	}

	@Override
	public String buildString() {
		return "(amount:"+amount+
				",skillType:"+skillType+
				",operation:"+operation+
				")";
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dOperation will use",
										"&dthis value on EXP",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dExperience", 0, 9999999),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&4&lSkill Type", 
								new ArrayList<String>(Arrays.asList(
										"&cWhich skill do you",
										"&cwant to check ?",
										"&4&lCurrently:&c<value>"
										))
								, (short)0)
						
						, null, null, "skillType", "&cSkill Type", 0, 0,new ArrayList<String>(
								SkillType.getEnabledNames()
								)),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.IRON_INGOT), "&3&lOperation", 
								new ArrayList<String>(Arrays.asList(
										"&bWhat do you want to check ?",
										"&3&lCurrently:&b<value>"
										))
								, (short)0)
						
						, null, null, "operation", "&dOperation", 0, 0,new ArrayList<String>(Arrays.asList(
								"EQUAL","LARGER","SMALLER","LARGER_EQUAL","SMALLER_EQUAL"
								)))
						
					)));
	}


	public Integer getAmount() {
		return amount;
	}


	public String getSkillType() {
		return skillType;
	}


	public String getOperation() {
		return operation;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}


}
