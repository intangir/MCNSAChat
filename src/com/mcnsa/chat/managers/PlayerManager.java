package com.mcnsa.chat.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.mcnsa.chat.chat.ChatPlayer;
import com.mcnsa.chat.main.MCNSAChat;

public class PlayerManager
{
  public static ArrayList<ChatPlayer> players;

  public static void init()
  {
    players = new ArrayList<ChatPlayer>();
  }

  public static ChatPlayer getPlayer(String name, String server) {
    for (ChatPlayer play : players)
      if ((play.name.equalsIgnoreCase(name)) && (play.server.equalsIgnoreCase(server)))
        return play;
    return null;
  }

  public static ChatPlayer getPlayer(ChatPlayer play) {
    return getPlayer(play.name, play.server);
  }

  public static void removePlayer(String name, String server) {
    for (int i = 0; i < players.size(); i++) {
      ChatPlayer play = (ChatPlayer)players.get(i);
      if ((play.name.equalsIgnoreCase(name)) && (play.server.equalsIgnoreCase(server))) {
        players.remove(play);
        break;
      }
    }
  }

  public static void removePlayer(ChatPlayer play) {
    removePlayer(play.name, play.server);
  }

  public static ArrayList<ChatPlayer> getPlayersByName(String name) {
    ArrayList<ChatPlayer> plays = new ArrayList<ChatPlayer>();
    for (ChatPlayer play : players)
      if (play.name.equalsIgnoreCase(name))
        plays.add(play);
    return plays;
  }

  public static ArrayList<ChatPlayer> getPlayersByFuzzyName(String name) {
    ArrayList<ChatPlayer> plays = new ArrayList<ChatPlayer>();
    for (ChatPlayer play : players) {
      if (play.name.toLowerCase().startsWith(name.toLowerCase()))
        plays.add(play);
    }
    return plays;
  }

  public static ArrayList<ChatPlayer> getPlayersListeningToChannel(String channel) {
    ArrayList<ChatPlayer> plays = new ArrayList<ChatPlayer>();
    for (ChatPlayer play : players) {
      boolean forced = (Bukkit.getPlayer(play.name) != null) && (MCNSAChat.permissions.has(Bukkit.getPlayer(play.name), "mcnsachat.forcelisten." + channel.toLowerCase()));
      if ((play.listening.contains(channel.toLowerCase())) || (play.modes.contains(ChatPlayer.Mode.SEEALL)) || (forced))
        plays.add(play);
    }
    return plays;
  }

  public static ArrayList<ChatPlayer> getPlayersInChannel(String channel) {
    ArrayList<ChatPlayer> plays = new ArrayList<ChatPlayer>();
    for (ChatPlayer play : players)
      if (play.channel.equals(channel.toLowerCase()))
        plays.add(play);
    return plays;
  }

  public static ArrayList<ChatPlayer> getPlayersByServer(String server) {
    ArrayList<ChatPlayer> plays = new ArrayList<ChatPlayer>();
    for (ChatPlayer play : players)
      if (play.server.equalsIgnoreCase(server))
        plays.add(play);
    return plays;
  }
}
