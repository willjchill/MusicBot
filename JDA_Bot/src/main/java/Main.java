import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String [] args) throws LoginException {  // ignores Login error
        JDABuilder jukebox = new JDABuilder(AccountType.BOT);   // creates the BOT
        jukebox.setToken("NDY0MTk1ODM5Mzk3NDYyMDM3.D0ns4g.6n4RBUljY4LWADxBKDXAAMhfjq0"); // sets BOT token
        jukebox.addEventListener(new Main());   // creates onMessageReceived method?
        jukebox.buildAsync();   // obtains Web Socket
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {  // performs action when a message is sent
        String message = "You're God damn right, " + event.getAuthor().getName();
        String savedMessage;
        if(jukebox(event)) {
            savedMessage = event.getMessage().getContentRaw();  // saves command if message in jukebox
            if (savedMessage.equals("--This bot sucks"))    // test command
                sendMessage(event, message);
            else if (savedMessage.equals("--game"))   // starts tic-tac-toe
                tictactoe(event);
        }
    }

    private void tictactoe(MessageReceivedEvent event) {
        String player1 = event.getAuthor().getName();
        String response = player1 + " will be Player 1!";
        sendMessage(event, response);

        /*char [] board = new char[9];    // creates empty tic-tac-toe board
        for(char i : board)
            board[i] = '-';*/
    }

    private void sendMessage(GenericMessageEvent event, String message) {  // send message method
        event.getChannel().sendMessage(message).queue();
    }

    private boolean jukebox(GenericMessageEvent event) {    // checks for correct channel (jukebox-facility)
        return event.getChannel().getName().equals("jukebox-facility");
    }

}
