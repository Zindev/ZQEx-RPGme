package me.Zindev.zqexrpgm.objectives;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestObjective;
import me.Zindev.zquest.objects.extension.QuestObjectiveMark;
import net.flamedek.rpgme.skills.SkillType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@QuestObjectiveMark(objectiveID="RPGmeXPGainObjective",hideSuccess= true)
public class RmGainExpObjective extends QuestObjective{
	private static final long serialVersionUID = 1L;
	private Integer amt;
	private String skillType;
	
	
	public RmGainExpObjective(){
		setVariables(new String[2]);
		setVariable("<amount>", "remaining XP", 0);
		setVariable("<name>", "name of the skill", 1);
		setCompleteMessage("&aYou just completed a gain EXP Objective !");
		setDisplayName("&dGain <amount> EXP in <name> skill");
		this.amt = 500;
		skillType = SkillType.STAMINA.readableName();
	}
	@Override
	public void success() {
		check();
		
	}

	@Override
	public boolean check() {
		if(amt > 0){return false;}

		return true;
	}
	public boolean checkIn(String stype,Integer cms,Player p){
		if(!stype.equals(skillType))return false;
		if(!checkConditions(p))return false;
		amt = amt>cms?amt-cms:0;
		success();
		if(amt <= 0)Gerekli.yollaMesaj(p, getCompleteMessage());
		return true;
	}

	@Override
	public String buildString() {
		return "(amount:"+amt+",skillType:"+skillType+")";
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amt).replaceAll("<name>", skillType);
	}
	@Override
	public String getSuccessMessage() {
		if(amt == 0){
			return super.getCompleteMessage();
		}
		return super.getSuccessMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", skillType);
	}
	@Override
	public String getCompleteMessage() {
		return super.getCompleteMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", skillType);
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow much XP player",
										"&dshould gain ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amt", "&dRequired Experience", 0, 9999999),
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
								))
						
					)));
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
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&ePlayer needs to collect",
				"&eamount of choosen skill's exp."
				));

	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

}
