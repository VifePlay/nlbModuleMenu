package ru.naulbimix.nlbModuleMenu.listeners;

import ru.naulbimix.nlbModuleMenu.impl.*;
import vifeplay.ModuleUser.client.user.info.ClientType;
import vifeplay.ModuleAPI.server.events.ServerListener;
import vifeplay.GameCore.provider.handlers.impl.ServerEventHandler;
import vifeplay.ModuleUser.client.user.inventory.line.OriginalInventoryLine;
import vifeplay.ModuleUser.server.user.inventory.impl.ServerInventoryHolder;
import vifeplay.ModuleUser.server.events.user.inventory.ServerInventoryClickEvent;

public class InventoryListener implements ServerListener {

    @ServerEventHandler
    public void onClick(ServerInventoryClickEvent event) {
        ServerInventory inventory = event.getInventory().getClickedInventory();

        if (inventory == null) return;
        if (inventory.getGhostInventory() == null) return;
        if (inventory.getUser().getClient().getClientType().equals(ClientType.ADMIN) || (inventory.getUser().getCustomGroups().isCustomizable() && inventory.getUser().getSession().isVerified())) return;
        if (inventory.getUser().getClient().getClientType().equals(ClientType.TOURNAMENT)) return;

        ServerInventoryHolder invHolder = inventory.getHolder();

        if (!(invHolder instanceof Menu))return;

        Menu menu = (Menu) invHolder;
        event.setCustomClickable(false);
        event.setCheckExploits(false);
        menu.handleClickAction(event);
    }

}
