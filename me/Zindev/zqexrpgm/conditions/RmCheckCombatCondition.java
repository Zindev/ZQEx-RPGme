package me.Zindev.zqexrpgm.conditions;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestCondition;
import me.Zindev.zquest.objects.extension.QuestConditionMark;
import net.flamedek.rpgme.RPGme;
import net.flamedek.rpgme.RPGmeAPI;
import net.flamedek.rpgme.player.RPGPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@QuestConditionMark(conditionID = "checkRPGmeCombatLevel")
public class RmCheckCombatCondition extends QuestCondition{
	private static final long serialVersionUID = 1L;
	private Integer amount;
	private String operation;
	public RmCheckCombatCondition() {
		setVariables(new String[2]);
		setVariable("<amount>", "condition amount", 0);
		setVariable("<operation>", "operation's name", 1);
		operation = "EQUAL";
		amount = 5;
		setDisplayName("&eYour Combat Level must <operation> to <amount>");
		setErrMessage("&eYour Combat Level is not <operation> to <amount>");
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amount)
				.replaceAll("<operation>", operation);
	}
	@Override
	public String getErrMessage() {
		return super.getErrMessage().replaceAll("<amount>", ""+amount)
				.replaceAll("<operation>", operation);
	}

	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&eCheck Combat Level of the ",
				"&eplayer with given operations."
				));
	}
	@Override
	public boolean check(Player p) {
		RPGmeAPI api = RPGme.getAPI();
		RPGPlayer rp = api.get(p);
		if(rp != null){
			int current = rp.getSkillSet().getCombatLevel();
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
										"&dthis value on Combat Level",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dCombat Level", 0, 9999999),
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




	public String getOperation() {
		return operation;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}



	public void setOperation(String operation) {
		this.operation = operation;
	}


}
