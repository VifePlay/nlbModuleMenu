package ru.naulbimix.nlbModuleMenu;

import vifeplay.ModuleAPI.server.ModuleServer;
import vifeplay.ModuleAPI.server.modules.Module;
import ru.naulbimix.nlbModuleMenu.listeners.InventoryListener;
import vifeplay.ModuleBukkit.server.functions.FunctionImitator;
import vifeplay.GameCore.provider.handlers.impl.HandlerPriority;
import vifeplay.GameCore.core.info.modificators.supports.ModificatorSupport;

public class Main extends Module {

    @Override
    public void onLoad(Module module) {
        if(ModuleServer.getCore().getInformation().getModificators().getSupports().contains(ModificatorSupport.BUKKIT)) {
            ModuleServer.getCore().getBukkitImitator().getApplication().getFunctions().stream().forEach(function -> function.getImitators().closeImitator(FunctionImitator.MENU)); // TODO: ModuleBukkit (1.2) branch alpha support this
        }
        ModuleServer.getCore().registerEvents(new InventoryListener(), module, false, null);
    }

    @Override
    public void onUnload(Module module) {
        ModuleServer.getCore().getHandler().stream().forEach(handler -> handler.closeEvents(module, false, 25, System.currentTimeMillis(), HandlerPriority.TRACK)); // TODO: ModuleServer (1.1) tree beta support this or global update 24-03-2024 fully supported automatization module loaders and custom exploit system
    }



}
