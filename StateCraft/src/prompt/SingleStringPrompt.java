package prompt;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import main.StateCraft;
import net.md_5.bungee.api.ChatColor;

public class SingleStringPrompt extends StringPrompt {
	
	private String prompt;
	private SingleStringPromptInterface i;
	
	public SingleStringPrompt(String prompt, SingleStringPromptInterface i) {
		super();
		this.prompt = prompt;
		this.i = i;
	}
	
	public static void doConversation(String prompt, Player player, SingleStringPromptInterface i) {
		if(player.isConversing()) return;
		ConversationFactory cf = new ConversationFactory(StateCraft.getInstance());
		Conversation conv = cf.withFirstPrompt(new SingleStringPrompt(prompt, i)).withLocalEcho(true).buildConversation(player);
		conv.begin();
	}

	@Override
	public Prompt acceptInput(ConversationContext con, String answer) {
		i.onAccept(answer);
		return null;
	}

	@Override
	public String getPromptText(ConversationContext con) {
		return ChatColor.YELLOW + "" + ChatColor.ITALIC + prompt;
	}
	
	public interface SingleStringPromptInterface {
		public void onAccept(String answer);
	}
	
}
