package org.ivkoandrv.tntmoddetector;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTModDetector implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tntmoddetector");
    public static MinecraftServer SERVER;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(this::onServerStarting);
    }

    private void onServerStarting (MinecraftServer server) {
        SERVER = server;
        LOGGER.info("TNT Detector Mod has been initialized. Greetings from ivkoandrv!");
    }

}
