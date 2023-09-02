package com.laws.visualoverlay;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Visual Overlay"
)
public class VisualOverlayPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private VisualOverlayConfig config;

    @Override
    protected void startUp() {
        log.info("Visual overlay started!");
    }

    @Override
    protected void shutDown() {
        log.info("Visual overlay stopped!");
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
        }
    }

    @Provides
    VisualOverlayConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(VisualOverlayConfig.class);
    }
}
