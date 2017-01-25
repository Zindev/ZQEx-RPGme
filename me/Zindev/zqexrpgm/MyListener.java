package me.Zindev.zqexrpgm;

import me.Zindev.zqexrpgm.objectives.RmGainExpObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;
import net.flamedek.rpgme.events.SkillExpGainEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyListener implements Listener{
	@EventHandler
	public void xpGain(SkillExpGainEvent e){
		Player p = e.getPlayer();
		if(!ZQuestAPI.playerIsMakingQuest(p.getUniqueId()))return;
		RmGainExpObjective ob = ZQuestAPI.playerHasObjective(p.getUniqueId(), RmGainExpObjective.class,
				true);
		if(ob != null)ob.checkIn(e.getSkill().readableName(),Math.round(e.getExp()), p);
	}

}
