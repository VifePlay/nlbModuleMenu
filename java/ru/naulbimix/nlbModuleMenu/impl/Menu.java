package ru.naulbimix.nlbModuleMenu.impl;

import vifeplay.ModuleAPI.server.ModuleServer;
import vifeplay.GameCore.provider.handlers.impl.HandlerPriority;
import vifeplay.ModuleUser.client.user.inventory.line.OriginalInventoryLine;
import vifeplay.ModuleUser.server.user.inventory.impl.ServerInventoryHolder;
import vifeplay.ModuleUser.server.events.user.inventory.ServerInventoryClickEvent;

public abstract class Menu implements ServerInventoryHolder {

    protected ServerInventory inventory;
    private String title;
    private int size;

    public Menu(String title) {
        this(title, 3);
    }

    public Menu(String title, int rows) {
        this.title = title;
        this.size = OriginalInventoryLine.fill(rows);
        this.inventory = ModuleServer.getInventories().createInventory(this, size, title, false, HandlerPriority.TRACK);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public void open(User user) {
        if (inventory == null) setContents();
        if(user.getExploitDetectors().getExploitSystem().getExploitInventory().isExploitInventory()) return;

        user.getProvider().getHandlers().openInventory(this.inventory, true);
    }

    public abstract void setContents();

    @Override
    public ServerInventory getInventory()
    {
        return inventory;
    }

    public abstract void handleClick(ServerInventoryClickEvent paramClickEvent);

}
